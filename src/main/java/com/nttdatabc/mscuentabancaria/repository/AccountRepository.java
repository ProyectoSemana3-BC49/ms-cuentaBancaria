package com.nttdatabc.mscuentabancaria.repository;

import com.nttdatabc.mscuentabancaria.model.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {
  Flux<Account> findByCustomerId(String customerId);
}
