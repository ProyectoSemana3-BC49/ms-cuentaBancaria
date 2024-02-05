package com.nttdatabc.mscuentabancaria.controller;

import com.nttdatabc.mscuentabancaria.controller.interfaces.DebitCardControllerApi;
import com.nttdatabc.mscuentabancaria.model.DebitCard;
import com.nttdatabc.mscuentabancaria.service.DebitCardServiceImpl;
import com.nttdatabc.mscuentabancaria.utils.Constantes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(Constantes.PREFIX_PATH)
@Slf4j
public class DebitCardController implements DebitCardControllerApi {

  @Autowired
  private DebitCardServiceImpl debitCardService;
  @Override
  public ResponseEntity<Mono<Void>> createDebitCard(DebitCard debitCard, ServerWebExchange exchange) {
    return new ResponseEntity<>(debitCardService.createDebitCardService(debitCard)
        .doOnSubscribe(unused -> log.info("createDebitCard:: iniciando"))
        .doOnError(throwable -> log.error("createDebitCard:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("createDebitCard:: finalizado con exito"))
        , HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Mono<Void>> deleteDebitCard(String debitCardId, ServerWebExchange exchange) {
    return new ResponseEntity<>(debitCardService.deleteDebitCardService(debitCardId)
        .doOnSubscribe(unused -> log.info("deleteDebitCard:: iniciando"))
        .doOnError(throwable -> log.error("deleteDebitCard:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("deleteDebitCard:: finalizado con exito"))
        , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Flux<DebitCard>> getAllDebitCard(ServerWebExchange exchange) {
    return new ResponseEntity<>(debitCardService.getAllDebitCardService()
        .doOnSubscribe(unused -> log.info("getAllDebitCard:: iniciando"))
        .doOnError(throwable -> log.error("getAllDebitCard:: error " + throwable.getMessage()))
        .doOnComplete(() -> log.info("getAllDebitCard:: finalizado con exito"))
        , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Mono<DebitCard>> getDebitCardById(String debitCardId, ServerWebExchange exchange) {
    return new ResponseEntity<>(debitCardService.getDebitCardByIdService(debitCardId)
        .doOnSubscribe(unused -> log.info("getDebitCardById:: iniciando"))
        .doOnError(throwable -> log.error("getDebitCardById:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("getDebitCardById:: finalizado con exito"))
        , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Mono<Void>> updateDebitCard(DebitCard debitCard, ServerWebExchange exchange) {
    return new ResponseEntity<>(debitCardService.updateDebitCardService(debitCard)
        .doOnSubscribe(unused -> log.info("updateDebitCard:: iniciando"))
        .doOnError(throwable -> log.error("updateDebitCard:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("updateDebitCard:: finalizado con exito"))
        , HttpStatus.OK);
  }
}
