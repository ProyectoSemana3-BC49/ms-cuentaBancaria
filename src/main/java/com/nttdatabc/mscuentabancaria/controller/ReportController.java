package com.nttdatabc.mscuentabancaria.controller;

import static com.nttdatabc.mscuentabancaria.utils.Constantes.PREFIX_PATH;

import com.nttdatabc.mscuentabancaria.controller.interfaces.ReportControllerApi;
import com.nttdatabc.mscuentabancaria.model.*;
import com.nttdatabc.mscuentabancaria.service.ReportServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



/**
 * Controller of Report.
 */
@RestController
@RequestMapping(PREFIX_PATH)
@Slf4j
public class ReportController implements ReportControllerApi {

  @Autowired
  private ReportServiceImpl reportService;

  @Override
  public ResponseEntity<Mono<BalanceAccounts>> getBalanceAccount(String customerId, ServerWebExchange exchange) {
    return new ResponseEntity<>(reportService.getBalanceAverageService(customerId)
        .doOnSubscribe(unused -> log.info("getBalanceAccount:: iniciando"))
        .doOnError(throwable -> log.error("getBalanceAccount:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("getBalanceAccount:: finalizado con exito"))
        , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Flux<Movement>> getFeeByAccount(String accountId, ServerWebExchange exchange) {
    return new ResponseEntity<>(reportService.getMovementsWithFee(accountId)
        .doOnSubscribe(unused -> log.info("getFeeByAccount:: iniciando"))
        .doOnError(throwable -> log.error("getFeeByAccount:: error " + throwable.getMessage()))
        .doOnComplete(() -> log.info("getFeeByAccount:: finalizado con exito"))
        , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Flux<MovementDebitCard>> getLastMovementDebitCard(String debitCardId, ServerWebExchange exchange) {
    return new ResponseEntity<>(reportService.getMovementsDebitCardLastTen(debitCardId)
        .doOnSubscribe(unused -> log.info("getLastMovementDebitCard:: iniciando"))
        .doOnError(throwable -> log.error("getLastMovementDebitCard:: error " + throwable.getMessage()))
        .doOnComplete(() -> log.info("getLastMovementDebitCard:: finalizado con exito"))
        , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Mono<Account>> getAccountMainDebitCard(String debitCardId, ServerWebExchange exchange) {
    return new ResponseEntity<>(reportService.getAccountMainDebitCardService(debitCardId)
        .doOnSubscribe(unused -> log.info("getAccountMainDebitCard:: iniciando"))
        .doOnError(throwable -> log.error("getAccountMainDebitCard:: error " + throwable.getMessage()))
        .doOnSuccess((e) -> log.info("getAccountMainDebitCard:: finalizado con exito"))
        , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Mono<SummaryProductsBank>> getSummaryProductsBank(String customerId, ServerWebExchange exchange) {
    return new ResponseEntity<>(reportService.getSummaryProductsBankService(customerId)
        .doOnSubscribe(unused -> log.info("getSummaryProductsBank:: iniciando"))
        .doOnError(throwable -> log.error("getSummaryProductsBank:: error " + throwable.getMessage()))
        .doOnSuccess((e) -> log.info("getSummaryProductsBank:: finalizado con exito"))
        , HttpStatus.OK);
  }
}
