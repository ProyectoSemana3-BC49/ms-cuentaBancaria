package com.nttdatabc.mscuentabancaria.service.api;

import static com.nttdatabc.mscuentabancaria.utils.Constantes.*;

import com.nttdatabc.mscuentabancaria.model.response.CustomerExt;
import com.nttdatabc.mscuentabancaria.service.interfaces.CustomerApiExt;
import com.nttdatabc.mscuentabancaria.utils.exceptions.errors.ErrorResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;



@Service
public class CustomerApiExtImpl implements CustomerApiExt {
  @Autowired
  private WebClient webClient;

  @Override
  public Mono<CustomerExt> getCustomerById(String id) {
    String apiUrl = URL_CUSTOMER_ID.concat(id);
    return webClient.get()
        .uri(apiUrl)
        .retrieve()
        .onStatus(HttpStatus::isError, response -> Mono.error(new ErrorResponseException(EX_NOT_FOUND_RECURSO, response.statusCode().value(), response.statusCode())))
        .bodyToMono(CustomerExt.class);
  }
}
