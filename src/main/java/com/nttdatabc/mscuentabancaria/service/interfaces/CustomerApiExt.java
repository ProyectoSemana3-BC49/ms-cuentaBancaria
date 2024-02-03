package com.nttdatabc.mscuentabancaria.service.interfaces;

import com.nttdatabc.mscuentabancaria.model.CustomerExt;
import reactor.core.publisher.Mono;

/**
 * Interface Customer.
 */
public interface CustomerApiExt {
  Mono<CustomerExt> getCustomerById(String id) ;
}
