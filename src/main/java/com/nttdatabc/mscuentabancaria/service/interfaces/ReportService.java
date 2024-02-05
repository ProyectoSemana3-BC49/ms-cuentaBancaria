package com.nttdatabc.mscuentabancaria.service.interfaces;

import com.nttdatabc.mscuentabancaria.model.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Report service interface.
 */
public interface ReportService {
  Mono<BalanceAccounts> getBalanceAverageService(String customerId);
  Flux<Movement> getMovementsWithFee(String accountId);
  Flux<MovementDebitCard>getMovementsDebitCardLastTen(String debitCardId);
  Mono<Account> getAccountMainDebitCardService(String debitCardId);
  Mono<SummaryProductsBank>getSummaryProductsBankService(String customerId);
}
