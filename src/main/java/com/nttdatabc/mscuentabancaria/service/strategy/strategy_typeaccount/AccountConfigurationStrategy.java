package com.nttdatabc.mscuentabancaria.service.strategy.strategy_typeaccount;

import com.nttdatabc.mscuentabancaria.model.Account;
import com.nttdatabc.mscuentabancaria.model.response.CustomerExt;
import com.nttdatabc.mscuentabancaria.model.enums.TypeAccountBank;
import com.nttdatabc.mscuentabancaria.service.api.CreditApiExtImpl;
import com.nttdatabc.mscuentabancaria.utils.exceptions.errors.ErrorResponseException;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.nttdatabc.mscuentabancaria.utils.Constantes.REQUIRED_CUENTA_CORRIENTE;

/**
 * Configura la cuenta según la estrategia específica implementada.
 */
public interface AccountConfigurationStrategy {
  Mono<Void> configureAccount(Account account, CustomerExt customerExt) ;

  /**
   * Validate has credit.
   *
   * @param creditApiExt service.
   * @param accountId id acccount.
   * @throws ErrorResponseException error.
   */
  default Mono<Void> validateHasCredit(CreditApiExtImpl creditApiExt, String accountId)  {
    return creditApiExt.hasCreditCustomer(accountId);
  }

  /**
   * Validate required account corriente.
   *
   * @param accountList list.
   * @throws ErrorResponseException error.
   */
  default Mono<Void> validateHasCorriente(Flux<Account> accountList) {
    return accountList
        .any(account -> account.getTypeAccount().equalsIgnoreCase(TypeAccountBank.CORRIENTE.toString()))
        .flatMap(hasCorriente -> {
          if (!hasCorriente) {
            return Mono.error(new ErrorResponseException(REQUIRED_CUENTA_CORRIENTE, HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT));
          }
          return Mono.empty();
        })
        .switchIfEmpty(Mono.error(new ErrorResponseException(REQUIRED_CUENTA_CORRIENTE, HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT)))
        .then();
  }
}

