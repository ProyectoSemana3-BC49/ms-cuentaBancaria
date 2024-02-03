package com.nttdatabc.mscuentabancaria.controller;

import com.nttdatabc.mscuentabancaria.controller.interfaces.MovementControllerApi;
import com.nttdatabc.mscuentabancaria.model.Movement;
import com.nttdatabc.mscuentabancaria.service.MovementServiceImpl;
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
 * Clase contoller Movement.
 */
@RestController
@RequestMapping(PREFIX_PATH)
@Slf4j
public class MovementController implements MovementControllerApi {

  @Autowired
  private MovementServiceImpl movementService;
  @Override
  public ResponseEntity<Mono<Void>> createMovementDeposit(Movement movement, ServerWebExchange exchange) {
    return new ResponseEntity<>(movementService.createMovementDepositService(movement)
        .doOnSubscribe(unused -> log.info("createMovementDeposit:: iniciando"))
        .doOnError(throwable -> log.error("createMovementDeposit:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("createMovementDeposit:: finalizado con exito"))
        , HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Mono<Void>> createTransfer(Movement movement, ServerWebExchange exchange) {
    return new ResponseEntity<>(movementService.createTransferService(movement)
        .doOnSubscribe(unused -> log.info("createTransfer:: iniciando"))
        .doOnError(throwable -> log.error("createTransfer:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("createTransfer:: finalizado con exito"))
        , HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Mono<Void>> createWithDraw(Movement movement, ServerWebExchange exchange) {
    return new ResponseEntity<>(movementService.createWithDrawService(movement)
        .doOnSubscribe(unused -> log.info("createWithDraw:: iniciando"))
        .doOnError(throwable -> log.error("createWithDraw:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("createWithDraw:: finalizado con exito"))
        , HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Flux<Movement>> getMovementsByAccountId(String accountId, ServerWebExchange exchange) {
    return new ResponseEntity<>(movementService.getMovementsByAccountIdService(accountId)
        .doOnSubscribe(unused -> log.info("getMovementsByAccountId:: iniciando"))
        .doOnError(throwable -> log.error("getMovementsByAccountId:: error " + throwable.getMessage()))
        .doOnComplete(() -> log.info("getMovementsByAccountId:: finalizado"))
        , HttpStatus.OK);
  }
}
