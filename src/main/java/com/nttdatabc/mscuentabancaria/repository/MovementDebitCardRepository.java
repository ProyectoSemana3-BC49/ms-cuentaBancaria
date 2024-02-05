package com.nttdatabc.mscuentabancaria.repository;

import com.nttdatabc.mscuentabancaria.model.MovementDebitCard;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MovementDebitCardRepository extends ReactiveMongoRepository<MovementDebitCard, String> {
  Flux<MovementDebitCard> findTop10ByOrderByDateDesc(String debitCardId);
}
