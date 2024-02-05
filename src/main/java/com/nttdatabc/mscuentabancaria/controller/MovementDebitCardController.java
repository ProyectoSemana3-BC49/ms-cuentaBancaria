package com.nttdatabc.mscuentabancaria.controller;

import com.nttdatabc.mscuentabancaria.controller.interfaces.MovementDebitCardControllerApi;
import com.nttdatabc.mscuentabancaria.model.MovementDebitCard;
import com.nttdatabc.mscuentabancaria.service.MovementDebitCardServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.nttdatabc.mscuentabancaria.utils.Constantes.PREFIX_PATH;

@RestController
@RequestMapping(PREFIX_PATH)
@Slf4j
public class MovementDebitCardController implements MovementDebitCardControllerApi {
  @Autowired
  private MovementDebitCardServiceImpl movementDebitCardService;
  @Override
  public ResponseEntity<Mono<Void>> createMovementPayment(MovementDebitCard movementDebitCard, ServerWebExchange exchange) {
    return new ResponseEntity<>(movementDebitCardService.createMovementPaymentService(movementDebitCard)
        .doOnSubscribe(unused -> log.info("createMovementPayment:: iniciando"))
        .doOnError(throwable -> log.error("createMovementPayment:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("createMovementPayment:: finalizado con exito"))
        , HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Mono<Void>> createMovementWithDraw(MovementDebitCard movementDebitCard, ServerWebExchange exchange) {
    return new ResponseEntity<>(movementDebitCardService.createMovementWithDrawSerice(movementDebitCard)
        .doOnSubscribe(unused -> log.info("createMovementWithDraw:: iniciando"))
        .doOnError(throwable -> log.error("createMovementWithDraw:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("createMovementWithDraw:: finalizado con exito"))
        , HttpStatus.CREATED);
  }
}
