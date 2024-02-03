package com.nttdatabc.mscuentabancaria.controller;


import com.nttdatabc.mscuentabancaria.controller.interfaces.AccountControllerApi;
import com.nttdatabc.mscuentabancaria.model.Account;
import com.nttdatabc.mscuentabancaria.service.AccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.nttdatabc.mscuentabancaria.utils.Constantes.PREFIX_PATH;

/**
 * Controlador de Account.
 */
@RestController
@RequestMapping(PREFIX_PATH)
@Slf4j
public class AccountController implements AccountControllerApi {

  @Autowired
  private AccountServiceImpl accountService;
  @Override
  public ResponseEntity<Mono<Void>> createAccount(Account account, ServerWebExchange exchange) {
    return new ResponseEntity<>(accountService.createAccountService(account)
        .doOnSubscribe(unused -> log.info("createAccount:: iniciando"))
        .doOnError(throwable -> log.error("createAccount:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("createAccount:: finalizado con exito"))
        , HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Mono<Void>> deleteAccountById(String accountId, ServerWebExchange exchange) {
    return new ResponseEntity<>(accountService.deleteAccountByIdService(accountId)
        .doOnSubscribe(unused -> log.info("deleteAccountById:: iniciando"))
        .doOnError(throwable -> log.error("deleteAccountById:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("deleteAccountById:: finalizado con exito"))
        , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Mono<Account>> getAccountById(String accountId, ServerWebExchange exchange) {
    return new ResponseEntity<>(accountService.getAccountByIdService(accountId)
        .doOnSubscribe(unused -> log.info("getAccountById:: iniciando"))
        .doOnError(throwable -> log.error("getAccountById:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("getAccountById:: finalizado con exito"))
        , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Flux<Account>> getAccountsByCustomerId(String customerId, ServerWebExchange exchange) {
    return new ResponseEntity<>(accountService.getAccountsByCustomerIdService(customerId)
        .doOnSubscribe(unused -> log.info("getAccountsByCustomerId:: iniciando"))
        .doOnError(throwable -> log.error("getAccountsByCustomerId:: error " + throwable.getMessage()))
        .doOnComplete(() -> log.info("getAccountsByCustomerId:: finalizado con exito")),
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Flux<Account>> getAllAccounts(ServerWebExchange exchange) {
    return new ResponseEntity<>(accountService.getAllAccountsService()
        .doOnSubscribe(unused -> log.info("getAllAccounts:: iniciando"))
        .doOnError(throwable -> log.error("getAllAccounts:: error " + throwable.getMessage()))
        .doOnComplete(() -> log.info("getAllAccounts:: completadoo"))
        , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Mono<Void>> updateAccount(Account account, ServerWebExchange exchange) {
    return new ResponseEntity<>(accountService.updateAccountServide(account)
        .doOnSubscribe(unused -> log.info("updateAccount:: iniciando"))
        .doOnError(throwable -> log.error("updateAccount:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("updateAccount:: finalizado con exito"))
        ,HttpStatus.OK);
  }
}
