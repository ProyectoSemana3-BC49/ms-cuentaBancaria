package com.nttdatabc.mscuentabancaria.service.interfaces;

import reactor.core.publisher.Mono;

/**
 * interface Credit api.
 */
public interface CreditApiExt {
  Mono<Void> hasCreditCustomer(String customerId);
}

