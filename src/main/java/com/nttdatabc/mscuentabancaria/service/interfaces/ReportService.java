package com.nttdatabc.mscuentabancaria.service.interfaces;

import com.nttdatabc.mscuentabancaria.model.BalanceAccounts;
import com.nttdatabc.mscuentabancaria.model.Movement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Report service interface.
 */
public interface ReportService {
  Mono<BalanceAccounts> getBalanceAverageService(String customerId);

  Flux<Movement> getMovementsWithFee(String accountId);
}
