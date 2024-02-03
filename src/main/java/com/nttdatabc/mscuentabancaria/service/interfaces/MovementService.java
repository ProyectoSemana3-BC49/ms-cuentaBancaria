package com.nttdatabc.mscuentabancaria.service.interfaces;

import com.nttdatabc.mscuentabancaria.model.Movement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interface movementService.
 */
public interface MovementService {
  Mono<Void> createMovementDepositService(Movement movement) ;

  Mono<Void> createWithDrawService(Movement movement) ;

  Flux<Movement> getMovementsByAccountIdService(String accountId);

  Mono<Void>  createTransferService(Movement movement);
}
