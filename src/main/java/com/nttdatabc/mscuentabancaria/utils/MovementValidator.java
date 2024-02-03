package com.nttdatabc.mscuentabancaria.utils;

import static com.nttdatabc.mscuentabancaria.utils.Constantes.DAY_MOVEMENT_SELECTED;
import static com.nttdatabc.mscuentabancaria.utils.Constantes.EX_ERROR_HAS_MOVEMENT_DAY;
import static com.nttdatabc.mscuentabancaria.utils.Constantes.EX_ERROR_MOVEMENT_BALANCE_INSUFFICIENT;
import static com.nttdatabc.mscuentabancaria.utils.Constantes.EX_ERROR_NOT_DAY_MOVEMENT;
import static com.nttdatabc.mscuentabancaria.utils.Constantes.EX_ERROR_REQUEST;
import static com.nttdatabc.mscuentabancaria.utils.Constantes.EX_ERROR_TYPE_MOVEMENT;
import static com.nttdatabc.mscuentabancaria.utils.Constantes.EX_ERROR_VALUE_MIN_MOVEMENT;
import static com.nttdatabc.mscuentabancaria.utils.Constantes.EX_VALUE_EMPTY;
import static com.nttdatabc.mscuentabancaria.utils.Constantes.FEE_LIMIT_TRANSACTION;
import static com.nttdatabc.mscuentabancaria.utils.Constantes.LIMIT_MAX_MOVEMENTS;
import static com.nttdatabc.mscuentabancaria.utils.Constantes.VALUE_MIN_ACCOUNT_BANK;

import com.nttdatabc.mscuentabancaria.model.Account;
import com.nttdatabc.mscuentabancaria.model.Movement;
import com.nttdatabc.mscuentabancaria.model.enums.TypeAccountBank;
import com.nttdatabc.mscuentabancaria.model.enums.TypeMovement;
import com.nttdatabc.mscuentabancaria.service.AccountServiceImpl;
import com.nttdatabc.mscuentabancaria.utils.exceptions.errors.ErrorResponseException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Clase MovementValidaror.
 */
public class MovementValidator {
  /**
   * Valida que los campos esenciales del movimiento no sean nulos.
   *
   * @param movement El movimiento que se va a validar.
   * @throws ErrorResponseException Si algún campo esencial es nulo.
   */
  public static Mono<Void> validateMovementNoNulls(Movement movement) {
    return Mono.just(movement)
        .filter(c -> c.getAccountId() != null && c.getMount() != null)
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_ERROR_REQUEST,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }

  /**
   * Valida que los campos del movimiento no estén vacíos.
   *
   * @param movement El movimiento que se va a validar.
   * @throws ErrorResponseException Si algún campo esencial está vacío.
   */
  public static Mono<Void> validateMovementEmpty(Movement movement) {
    return Mono.just(movement)
        .filter(c -> !c.getAccountId().isBlank() && !c.getMount().toString().isBlank())
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_VALUE_EMPTY,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }

  /**
   * Verifica que el tipo de movimiento sea válido.
   *
   * @param movement El movimiento que se va a verificar.
   * @throws ErrorResponseException Si el tipo de movimiento no es válido.
   */
  public static void verifyTypeMovement(Movement movement) throws ErrorResponseException {
    Predicate<Movement> existTypeMovement = movementValidate -> movementValidate
        .getTypeMovement()
        .equalsIgnoreCase(TypeMovement.DEPOSITO.toString())
        || movementValidate.getTypeMovement().equalsIgnoreCase(TypeMovement.RETIRO.toString())
        || movementValidate.getTypeMovement().equalsIgnoreCase(TypeMovement.TRANSFER.toString());
    if (existTypeMovement.negate().test(movement)) {
      throw new ErrorResponseException(EX_ERROR_TYPE_MOVEMENT,
          HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * Valida que una cuenta esté registrada.
   *
   * @param accountId          El ID de la cuenta.
   * @param accountServiceImpl Implementación del servicio de cuentas.
   * @throws ErrorResponseException Si la cuenta no está registrada.
   */
  public static Mono<Void> validateAccountRegister(String accountId, AccountServiceImpl accountServiceImpl) {
    return accountServiceImpl.getAccountByIdService(accountId).then();
  }

  /**
   * Verifica que los valores del movimiento sean válidos.
   *
   * @param movement El movimiento que se va a verificar.
   * @throws ErrorResponseException Si los valores no son válidos.
   */
  public static Mono<Void> verifyValues(Movement movement) {
    return Mono.defer(() -> {
      if (movement.getMount().doubleValue() <= VALUE_MIN_ACCOUNT_BANK) {
        return Mono.error(new ErrorResponseException(EX_ERROR_VALUE_MIN_MOVEMENT,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST));
      } else {
        return Mono.empty();
      }
    });
  }

  /**
   * Filtra la lista de movimientos por el mes actual.
   *
   * @param listaFound La lista de movimientos a filtrar.
   * @return Lista filtrada de movimientos del mes actual.
   */
  public static Flux<Movement> listMovementByMonth(List<Movement> listaFound) {
    int monthNow = LocalDate.now().getMonthValue();
    return Flux.fromIterable(listaFound)
        .filter(movement -> {
          LocalDateTime dateMovement = LocalDateTime.parse(movement.getFecha());
          return dateMovement.getMonthValue() == monthNow;
        });
  }

  /**
   * Verifica si hay algún movimiento registrado en el día actual.
   *
   * @param listaFound La lista de movimientos a verificar.
   * @return Verdadero si hay movimientos registrados hoy, falso de lo contrario.
   */
  public static Mono<Boolean> hasMovementInDay(List<Movement> listaFound) {
    LocalDateTime dateTimeNow = LocalDateTime.now();
    int monthNow = dateTimeNow.getMonthValue();
    int dayNow = dateTimeNow.getDayOfMonth();
    return Flux.fromIterable(listaFound)
        .any(movement -> {
          LocalDateTime dateTimeMovement = LocalDateTime.parse(movement.getFecha());
          int monthMovement = dateTimeMovement.getMonthValue();
          int dayMovement = dateTimeMovement.getDayOfMonth();
          return monthNow == monthMovement && dayNow == dayMovement;
        });
  }

  /**
   * Verifica si el día actual es el día programado para movimientos.
   *
   * @return Verdadero si es el día programado, falso de lo contrario.
   */
  public static Mono<Boolean> isDayMovement() {
    LocalDateTime dateTimeNow = LocalDateTime.now();
    int dayNow = dateTimeNow.getDayOfMonth();
    return Mono.just(dayNow == Integer.parseInt(DAY_MOVEMENT_SELECTED));
  }

  /**
   * Valida los movimientos según las reglas de la cuenta.
   *
   * @param accountFound          La cuenta asociada a los movimientos.
   * @param listMovementByAccount La lista de movimientos asociados a la cuenta.
   * @throws ErrorResponseException Si hay violaciones en las reglas de movimientos de la cuenta.
   */
  public static Mono<Void> validateMovements(Account accountFound, List<Movement> listMovementByAccount, Movement movement) {
    if (accountFound.getTypeAccount().equalsIgnoreCase(TypeAccountBank.AHORRO.toString())
        || accountFound.getTypeAccount().equalsIgnoreCase(TypeAccountBank.PLAZO_FIJO.toString())
        || accountFound.getTypeAccount().equalsIgnoreCase(TypeAccountBank.CORRIENTE.toString())) {
      List<Movement> listMovementByAccountByMonth = listMovementByMonth(listMovementByAccount).collectList().block();

      if (listMovementByAccountByMonth.size() >= LIMIT_MAX_MOVEMENTS) {
        // apply fee when limit max movements
        movement.setFee(BigDecimal.valueOf(FEE_LIMIT_TRANSACTION));
      }
    }

    if (accountFound.getTypeAccount().equalsIgnoreCase(TypeAccountBank.PLAZO_FIJO.toString())) {
      return hasMovementInDay(listMovementByAccount)
          .flatMap(hasMovement -> {
            if (hasMovement) {
              return Mono.error(new ErrorResponseException(EX_ERROR_HAS_MOVEMENT_DAY,
                  HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT));
            } else {
              return isDayMovement()
                  .flatMap(isDayMovement -> {
                    if (!isDayMovement) {
                      return Mono.error(new ErrorResponseException(EX_ERROR_NOT_DAY_MOVEMENT,
                          HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT));
                    } else {
                      return Mono.empty();
                    }
                  });
            }
          })
          .then();
    }

    return Mono.empty();
  }

  /**
   * Validate params not null.
   *
   * @param movement object movement.
   * @throws ErrorResponseException error.
   */
  public static Mono<Void> validateMovementTransferNoNulls(Movement movement) {
    return Mono.just(movement)
        .filter(c -> c.getAccountId() != null && c.getMount() != null && c.getDestination() != null)
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_ERROR_REQUEST,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }

  /**
   * not empty.
   *
   * @param movement object.
   * @throws ErrorResponseException error.
   */
  public static Mono<Void> validateMovementTransferEmpty(Movement movement) {
    return Mono.just(movement)
        .filter(c -> !c.getAccountId().isBlank() && !c.getMount().toString().isBlank() && !c.getDestination().isBlank())
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_VALUE_EMPTY,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }

  /**
   * Validate destination exist.
   *
   * @param accountId id.
   * @param accountServiceImpl service.
   * @throws ErrorResponseException error.
   */
  public static Mono<Void> validateAccountDestination(String accountId, AccountServiceImpl accountServiceImpl) {
    return accountServiceImpl.getAccountByIdService(accountId).then();
  }

  /**
   * Balance insufficient.
   *
   * @param accountServiceImpl service.
   * @param movement object.
   * @throws ErrorResponseException err.
   */
  public static Mono<Void> validateCurrentBalance(AccountServiceImpl accountServiceImpl, Movement movement) {
    return accountServiceImpl.getAccountByIdService(movement.getAccountId())
        .flatMap(accountFound -> {
          if (accountFound.getCurrentBalance().doubleValue() < movement.getMount().doubleValue()) {
            return Mono.error(new ErrorResponseException(EX_ERROR_MOVEMENT_BALANCE_INSUFFICIENT,
                HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT));
          } else {
            return Mono.empty();
          }
        });
  }
}


