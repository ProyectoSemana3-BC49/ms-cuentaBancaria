package com.nttdatabc.mscuentabancaria.utils;

import static com.nttdatabc.mscuentabancaria.utils.Constantes.*;

import com.nttdatabc.mscuentabancaria.model.MovementDebitCard;
import com.nttdatabc.mscuentabancaria.utils.exceptions.errors.ErrorResponseException;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

/**
 * Movement DebitCard Validator.
 */
public class MovementDebitCardValidator {
  public static Mono<Void> validateMovementDebitCardNoNulls(MovementDebitCard movementDebitCard) {
    return Mono.just(movementDebitCard)
        .filter(c -> c.getDebitCardId() != null)
        .filter(c -> c.getMount() != null)
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_ERROR_REQUEST,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }
  public static Mono<Void> validateMovementDebitCardNoEmpty(MovementDebitCard movementDebitCard) {
    return Mono.just(movementDebitCard)
        .filter(c -> !c.getDebitCardId().isBlank())
        .filter(c -> !c.getMount().toString().isBlank())
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_VALUE_EMPTY,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }


}
