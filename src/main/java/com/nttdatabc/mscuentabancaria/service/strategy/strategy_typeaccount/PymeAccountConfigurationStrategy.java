package com.nttdatabc.mscuentabancaria.service.strategy.strategy_typeaccount;

import com.nttdatabc.mscuentabancaria.model.Account;
import com.nttdatabc.mscuentabancaria.model.CustomerExt;
import com.nttdatabc.mscuentabancaria.model.enums.TypeCustomer;
import com.nttdatabc.mscuentabancaria.service.CreditApiExtImpl;
import com.nttdatabc.mscuentabancaria.utils.exceptions.errors.ErrorResponseException;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.nttdatabc.mscuentabancaria.utils.Constantes.PERSONA_NOT_PERMITTED_VIP;

/**
 * Class configuration Pyme.
 */
public class PymeAccountConfigurationStrategy implements AccountConfigurationStrategy {
  @Override
  public Mono<Void> configureAccount(Account account, CustomerExt customerExt)  {
    if (customerExt.getType().equalsIgnoreCase(TypeCustomer.PERSONA.toString())) {
      return Mono.error(new ErrorResponseException(PERSONA_NOT_PERMITTED_VIP,
          HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT));
    }
    return Mono.empty();

  }

  @Override
  public Mono<Void> validateHasCredit(CreditApiExtImpl creditApiExt, String accountId)  {
    return AccountConfigurationStrategy.super.validateHasCredit(creditApiExt, accountId);
  }

  @Override
  public Mono<Void> validateHasCorriente(Flux<Account> accountList)  {
    return AccountConfigurationStrategy.super.validateHasCorriente(accountList);
  }
}

