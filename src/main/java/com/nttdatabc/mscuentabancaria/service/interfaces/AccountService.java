package com.nttdatabc.mscuentabancaria.service.interfaces;

import com.nttdatabc.mscuentabancaria.model.Account;
import com.nttdatabc.mscuentabancaria.utils.exceptions.errors.ErrorResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Interface de accountService.
 */
public interface AccountService {
  Flux<Account> getAllAccountsService();

  Mono<Void> createAccountService(Account account) throws ErrorResponseException;

  Mono<Void> updateAccountServide(Account account);

  Mono<Void> deleteAccountByIdService(String accountId);

  Mono<Account> getAccountByIdService(String accountId);

  Flux<Account> getAccountsByCustomerIdService(String customerId);
}

