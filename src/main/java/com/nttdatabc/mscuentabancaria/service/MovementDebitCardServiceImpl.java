package com.nttdatabc.mscuentabancaria.service;

import com.nttdatabc.mscuentabancaria.model.Account;
import com.nttdatabc.mscuentabancaria.model.AccountsSecundary;
import com.nttdatabc.mscuentabancaria.model.DebitCard;
import com.nttdatabc.mscuentabancaria.model.MovementDebitCard;
import com.nttdatabc.mscuentabancaria.model.enums.TypeMovementDebitCard;
import com.nttdatabc.mscuentabancaria.repository.MovementDebitCardRepository;
import com.nttdatabc.mscuentabancaria.service.interfaces.MovementDebitCardService;
import com.nttdatabc.mscuentabancaria.service.interfaces.MovementService;
import com.nttdatabc.mscuentabancaria.utils.Utilitarios;
import com.nttdatabc.mscuentabancaria.utils.exceptions.errors.ErrorResponseException;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Comparator;

import static com.nttdatabc.mscuentabancaria.utils.Constantes.EX_ERROR_NOT_MONEY_ACCOUNTS_SECUNDARYS;
import static com.nttdatabc.mscuentabancaria.utils.Constantes.EX_ERROR_NOT_MONEY_AND_ACCOUTS_SECUNDARY;
import static com.nttdatabc.mscuentabancaria.utils.MovementDebitCardValidator.validateMovementDebitCardNoEmpty;
import static com.nttdatabc.mscuentabancaria.utils.MovementDebitCardValidator.validateMovementDebitCardNoNulls;
import static com.nttdatabc.mscuentabancaria.utils.Utilitarios.generateUuid;

@Service
@Slf4j
public class MovementDebitCardServiceImpl implements MovementDebitCardService {

  @Autowired
  private MovementDebitCardRepository movementDebitCardRepository;
  @Autowired
  private DebitCardServiceImpl debitCardService;
  @Autowired
  private AccountServiceImpl accountService;

  @Override
  public Mono<Void> createMovementPaymentService(MovementDebitCard movementDebitCard) {
    return validateMovementDebitCardNoNulls(movementDebitCard)
        .then(validateMovementDebitCardNoEmpty(movementDebitCard))
        .then(Mono.just(movementDebitCard))
        .then(debitCardService.getDebitCardByIdService(movementDebitCard.getDebitCardId()))
        .flatMap(debitCard -> accountService.getAccountByIdService(debitCard.getAccountIdPrincipal())
            .flatMap(account -> {
              if(movementDebitCard.getMount().doubleValue() <=  account.getCurrentBalance().doubleValue()){
                LocalDateTime lcMovementDebitCard = LocalDateTime.now();
                movementDebitCard.setDate(lcMovementDebitCard.toString());
                movementDebitCard.set_id(generateUuid());
                movementDebitCard.setTypeMovement(TypeMovementDebitCard.PAYMENT.toString());
                movementDebitCard.setAccountDebited(debitCard.getAccountIdPrincipal());
                return movementDebitCardRepository.save(movementDebitCard)
                    .flatMap(movementDebitCardInsert -> accountService.getAccountByIdService(movementDebitCardInsert.getAccountDebited())
                        .flatMap(accountFoundUpdate -> {
                          accountFoundUpdate.setCurrentBalance(accountFoundUpdate.getCurrentBalance().subtract(movementDebitCard.getMount()));
                          return accountService.updateAccountServide(accountFoundUpdate);
                        }));
              }
              if(debitCard.getAccountsSecundary() == null || debitCard.getAccountsSecundary().isEmpty()){
                return Mono.error(new ErrorResponseException(EX_ERROR_NOT_MONEY_AND_ACCOUTS_SECUNDARY, HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT));
              }

              Mono<AccountsSecundary> firstMatchingAccount = Flux.fromIterable(debitCard.getAccountsSecundary())
                  .filterWhen(accountsSecundaryFound ->
                      accountService.getAccountByIdService(accountsSecundaryFound.getAccountId())
                          .map(accountMono -> movementDebitCard.getMount().doubleValue() <= accountMono.getCurrentBalance().doubleValue())
                          .defaultIfEmpty(false)
                  )
                  .sort(Comparator.comparingInt(AccountsSecundary::getPriority))
                  .next();

              return firstMatchingAccount
                  .switchIfEmpty(Mono.error(new ErrorResponseException(EX_ERROR_NOT_MONEY_ACCOUNTS_SECUNDARYS,
                      HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT)))
                  .flatMap(accountsSecundaryFilterFound -> {
                    LocalDateTime lcMovementDebitCard = LocalDateTime.now();
                    movementDebitCard.setDate(lcMovementDebitCard.toString());
                    movementDebitCard.set_id(generateUuid());
                    movementDebitCard.setTypeMovement(TypeMovementDebitCard.PAYMENT.toString());
                    movementDebitCard.setAccountDebited(accountsSecundaryFilterFound.getAccountId());
                    return movementDebitCardRepository.save(movementDebitCard)
                        .flatMap(movementDebitCardInsertAccountSecundary -> accountService.getAccountByIdService(movementDebitCardInsertAccountSecundary.getAccountDebited())
                            .flatMap(accountFoundUpdate -> {
                              accountFoundUpdate.setCurrentBalance(accountFoundUpdate.getCurrentBalance().subtract(movementDebitCard.getMount()));
                              return accountService.updateAccountServide(accountFoundUpdate);
                            }));
                  })
              ;
            }).then());

  }


  @Override
  public Mono<Void> createMovementWithDrawSerice(MovementDebitCard movementDebitCard) {
    return validateMovementDebitCardNoNulls(movementDebitCard)
        .then(validateMovementDebitCardNoEmpty(movementDebitCard))
        .then(Mono.just(movementDebitCard))
        .then(debitCardService.getDebitCardByIdService(movementDebitCard.getDebitCardId()))
        .flatMap(debitCard -> accountService.getAccountByIdService(debitCard.getAccountIdPrincipal())
            .flatMap(account -> {
              if(movementDebitCard.getMount().doubleValue() <=  account.getCurrentBalance().doubleValue()){
                LocalDateTime lcMovementDebitCard = LocalDateTime.now();
                movementDebitCard.setDate(lcMovementDebitCard.toString());
                movementDebitCard.set_id(generateUuid());
                movementDebitCard.setTypeMovement(TypeMovementDebitCard.WITHDRAW.toString());
                movementDebitCard.setAccountDebited(debitCard.getAccountIdPrincipal());
                return movementDebitCardRepository.save(movementDebitCard)
                    .flatMap(movementDebitCardInsert -> accountService.getAccountByIdService(movementDebitCardInsert.getAccountDebited())
                        .flatMap(accountFoundUpdate -> {
                          accountFoundUpdate.setCurrentBalance(accountFoundUpdate.getCurrentBalance().subtract(movementDebitCard.getMount()));
                          return accountService.updateAccountServide(accountFoundUpdate);
                        }));
              }
              if(debitCard.getAccountsSecundary() == null || debitCard.getAccountsSecundary().isEmpty()){
                return Mono.error(new ErrorResponseException(EX_ERROR_NOT_MONEY_AND_ACCOUTS_SECUNDARY, HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT));
              }

              Mono<AccountsSecundary> firstMatchingAccount = Flux.fromIterable(debitCard.getAccountsSecundary())
                  .filterWhen(accountsSecundaryFound ->
                      accountService.getAccountByIdService(accountsSecundaryFound.getAccountId())
                          .map(accountMono -> movementDebitCard.getMount().doubleValue() <= accountMono.getCurrentBalance().doubleValue())
                          .defaultIfEmpty(false)
                  )
                  .sort(Comparator.comparingInt(AccountsSecundary::getPriority))
                  .next();

              return firstMatchingAccount
                  .switchIfEmpty(Mono.error(new ErrorResponseException(EX_ERROR_NOT_MONEY_ACCOUNTS_SECUNDARYS,
                      HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT)))
                  .flatMap(accountsSecundaryFilterFound -> {
                    LocalDateTime lcMovementDebitCard = LocalDateTime.now();
                    movementDebitCard.setDate(lcMovementDebitCard.toString());
                    movementDebitCard.set_id(generateUuid());
                    movementDebitCard.setTypeMovement(TypeMovementDebitCard.WITHDRAW.toString());
                    movementDebitCard.setAccountDebited(accountsSecundaryFilterFound.getAccountId());
                    return movementDebitCardRepository.save(movementDebitCard)
                        .flatMap(movementDebitCardInsertAccountSecundary -> accountService.getAccountByIdService(movementDebitCardInsertAccountSecundary.getAccountDebited())
                            .flatMap(accountFoundUpdate -> {
                              accountFoundUpdate.setCurrentBalance(accountFoundUpdate.getCurrentBalance().subtract(movementDebitCard.getMount()));
                              return accountService.updateAccountServide(accountFoundUpdate);
                            }));
                  })
                  ;
            }).then());
  }
}
