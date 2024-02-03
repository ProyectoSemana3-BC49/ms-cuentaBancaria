package com.nttdatabc.mscuentabancaria.service;

import com.nttdatabc.mscuentabancaria.model.CreditResponseExt;
import com.nttdatabc.mscuentabancaria.model.enums.TypeCredit;
import com.nttdatabc.mscuentabancaria.service.interfaces.CreditApiExt;
import com.nttdatabc.mscuentabancaria.utils.exceptions.errors.ErrorResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static com.nttdatabc.mscuentabancaria.utils.Constantes.*;

@Service
@Slf4j
public class CreditApiExtImpl implements CreditApiExt {

  @Autowired
  private WebClient webClient;
  @Override
  public Mono<Void> hasCreditCustomer(String customerId) {
    String apiUrl = URL_CREDIT_CUSTOMER + customerId;
    return webClient.get()
        .uri(apiUrl)
        .retrieve()
        .bodyToMono(CreditResponseExt[].class)
        .flatMapMany(Flux::fromArray)
        .map(CreditResponseExt::getBody)
        .filter(body -> !body.isEmpty())
        .doOnNext(creditExts -> log.info("apiii1::: " + creditExts))
        .flatMapIterable(Function.identity())
        .any(creditExt -> creditExt.getType_credit().equalsIgnoreCase(TypeCredit.TARJETA.toString()))
        .doOnNext(aBoolean -> log.info("apiii2::: " + aBoolean))
        .flatMap(hasCreditCard -> {
          if (hasCreditCard) {
            return Mono.empty();
          } else {
            return Mono.error(new ErrorResponseException(REQUIRED_CREDIT_VIP, HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT));
          }
        })
        .then();

  }
}
