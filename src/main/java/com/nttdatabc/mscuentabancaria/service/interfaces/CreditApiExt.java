package com.nttdatabc.mscuentabancaria.service.interfaces;

import com.nttdatabc.mscuentabancaria.model.CreditExt;
import com.nttdatabc.mscuentabancaria.model.HasDebtResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * interface Credit api.
 */
public interface CreditApiExt {
  Mono<Void> hasCreditCustomer(String customerId);
  Mono<HasDebtResponse>hasDebtCustomer(String customerId);

  Flux<CreditExt>getCreditsByCustomerId(String customerId);
}

