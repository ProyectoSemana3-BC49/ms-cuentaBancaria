package com.nttdatabc.mscuentabancaria.service.interfaces;

import com.nttdatabc.mscuentabancaria.model.MovementDebitCard;
import reactor.core.publisher.Mono;

public interface MovementDebitCardService {
  Mono<Void> createMovementPaymentService(MovementDebitCard movementDebitCard);

  Mono<Void> createMovementWithDrawSerice(MovementDebitCard movementDebitCard);
}
