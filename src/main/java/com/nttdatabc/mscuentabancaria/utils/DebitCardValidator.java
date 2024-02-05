package com.nttdatabc.mscuentabancaria.utils;

import static com.nttdatabc.mscuentabancaria.utils.Constantes.*;

import com.nttdatabc.mscuentabancaria.model.DebitCard;
import com.nttdatabc.mscuentabancaria.model.HasDebtResponse;
import com.nttdatabc.mscuentabancaria.model.response.CustomerExt;
import com.nttdatabc.mscuentabancaria.repository.DebitCardRepository;
import com.nttdatabc.mscuentabancaria.service.api.CreditApiExtImpl;
import com.nttdatabc.mscuentabancaria.service.api.CustomerApiExtImpl;
import com.nttdatabc.mscuentabancaria.utils.exceptions.errors.ErrorResponseException;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

/**
 * Clase debitCardValidator.
 */
public class DebitCardValidator {
  public static Mono<Void> validateDebitCardNoNulls(DebitCard debitCard) {
    return Mono.just(debitCard)
        .filter(c -> c.getCustomerId() != null)
        .filter(c -> c.getAccountIdPrincipal() != null)
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_ERROR_REQUEST,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }

  public static Mono<Void> validateDebitCardUpdateNoNulls(DebitCard debitCard) {
    return Mono.just(debitCard)
        .filter(c -> c.get_id() != null)
        .filter(c -> c.getAccountsSecundary() != null)
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_ERROR_REQUEST,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }

  public static Mono<Void> validateDebitCardEmpty(DebitCard debitCard) {
    return Mono.just(debitCard)
        .filter(c -> !c.getCustomerId().isEmpty())
        .filter(c -> !c.getAccountIdPrincipal().isBlank())
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_VALUE_EMPTY,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }

  public static Mono<Void> validateDebitCardUpdateEmpty(DebitCard debitCard) {
    return Mono.just(debitCard)
        .filter(c -> !c.get_id().isEmpty())
        .filter(c -> !c.getAccountsSecundary().isEmpty())
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_VALUE_EMPTY,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }

  public static Mono<CustomerExt> verifyCustomerExists(String customerId, CustomerApiExtImpl customerApiExtImpl) {
    return Mono.defer(() -> {
      try {
        return customerApiExtImpl.getCustomerById(customerId);
      } catch (Exception e) {
        return Mono.error(new ErrorResponseException(EX_NOT_FOUND_RECURSO,
            HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND));
      }
    });
  }

  public static Mono<Void> verifyCustomerDebCredit(String customerId, CreditApiExtImpl creditApiExt) {
    return Mono.defer(() -> {
      Mono<HasDebtResponse> hasDebtResponseMono = creditApiExt.hasDebtCustomer(customerId);
      return hasDebtResponseMono.flatMap(hasDebtResponse -> {
        if (hasDebtResponse.getHasExistsDebt()) {
          return Mono.error(new ErrorResponseException(EX_ERROR_CUSTOMER_HAS_DEBT, HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT));
        } else {
          return Mono.empty();
        }
      });
    }).then();
  }

  public static Mono<Void> verifyDuplicateNumberDebitCard(String debitCardId, DebitCardRepository debitCardRepository) {
    return Mono.defer(() -> {
      Mono<DebitCard> findDebitCard = debitCardRepository.findByNumberCard(debitCardId);
      return findDebitCard.hasElement()
          .flatMap(aBoolean -> {
            if (aBoolean) {
              return Mono.error(new ErrorResponseException(EX_ERROR_NUMBER_CARD_EXISTS, HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT));
            } else {
              return Mono.empty();
            }
          });
    }).then();
  }
}
