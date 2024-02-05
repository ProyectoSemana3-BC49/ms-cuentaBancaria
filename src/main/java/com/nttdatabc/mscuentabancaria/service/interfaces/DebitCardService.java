package com.nttdatabc.mscuentabancaria.service.interfaces;

import com.nttdatabc.mscuentabancaria.model.DebitCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitCardService {
  Flux<DebitCard>getAllDebitCardService();
  Mono<Void> createDebitCardService(DebitCard debitCard);
  Mono<Void> deleteDebitCardService(String debitCardId);
  Mono<DebitCard> getDebitCardByIdService(String debitCardId);
  Mono<Void>updateDebitCardService(DebitCard debitCard);
}
