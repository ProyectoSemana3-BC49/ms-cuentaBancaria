package com.nttdatabc.mscuentabancaria.service.strategy.strategy_account;

import com.nttdatabc.mscuentabancaria.model.Account;
import com.nttdatabc.mscuentabancaria.utils.exceptions.errors.ErrorResponseException;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Interface.
 */
public interface AccountValidationStrategy {
  Mono<Void> validateAccount(Account account, List<Account> accountList) ;
}

