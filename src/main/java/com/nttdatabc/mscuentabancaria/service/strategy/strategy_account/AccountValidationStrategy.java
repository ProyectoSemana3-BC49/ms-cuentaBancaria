package com.nttdatabc.mscuentabancaria.service.strategy.strategy_account;

import com.nttdatabc.mscuentabancaria.model.Account;
import java.util.List;
import reactor.core.publisher.Mono;

/**
 * Interface.
 */
public interface AccountValidationStrategy {
  Mono<Void> validateAccount(Account account, List<Account> accountList) ;
}

