package com.nttdatabc.mscuentabancaria.service;

import static com.nttdatabc.mscuentabancaria.utils.Constantes.EX_NOT_FOUND_RECURSO;
import static com.nttdatabc.mscuentabancaria.utils.DebitCardValidator.*;
import static com.nttdatabc.mscuentabancaria.utils.Utilitarios.*;

import com.nttdatabc.mscuentabancaria.model.AccountsSecundary;
import com.nttdatabc.mscuentabancaria.model.DebitCard;
import com.nttdatabc.mscuentabancaria.repository.DebitCardRepository;
import com.nttdatabc.mscuentabancaria.service.api.CreditApiExtImpl;
import com.nttdatabc.mscuentabancaria.service.api.CustomerApiExtImpl;
import com.nttdatabc.mscuentabancaria.service.interfaces.DebitCardService;
import com.nttdatabc.mscuentabancaria.utils.exceptions.errors.ErrorResponseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Debit card service.
 */
@Service
@Slf4j
public class DebitCardServiceImpl implements DebitCardService {
  @Autowired
  private DebitCardRepository debitCardRepository;
  @Autowired
  private AccountServiceImpl accountService;
  @Autowired
  private CustomerApiExtImpl customerApiExt;
  @Autowired
  private CreditApiExtImpl creditApiExt;

  @Override
  public Flux<DebitCard> getAllDebitCardService() {
    return debitCardRepository.findAll().switchIfEmpty(Flux.empty());
  }

  @Override
  public Mono<Void> createDebitCardService(DebitCard debitCard) {
    return validateDebitCardNoNulls(debitCard)
        .then(validateDebitCardEmpty(debitCard))
        .then(verifyDuplicateNumberDebitCard(debitCard.getNumberCard(), debitCardRepository))
        .then(verifyCustomerExists(debitCard.getCustomerId(), customerApiExt))
        .then(verifyCustomerDebCredit(debitCard.getCustomerId(), creditApiExt))
        .then(accountService.getAccountByIdService(debitCard.getAccountIdPrincipal()))
        .then(Mono.just(debitCard))
        .flatMap(debitCardFlujo -> {
          if (debitCardFlujo.getAccountsSecundary() != null) {
            return Flux.fromIterable(debitCardFlujo.getAccountsSecundary())
                .flatMap(accountsSecundary -> accountService.getAccountByIdService(accountsSecundary.getAccountId())
                    .hasElement()
                    .defaultIfEmpty(false))
                .any(Boolean::booleanValue)
                .defaultIfEmpty(false);
          } else {
            return Mono.empty();
          }

        }).then(Mono.just(debitCard))
        .map(debitCardTransform -> {
          LocalDateTime dateTimeNow = LocalDateTime.now();
          debitCardTransform.setNumberCard(generateNumberCard());
          debitCardTransform.setCvv2(generateRandomCVV2());
          debitCardTransform.setCreatedCardDebit(dateTimeNow.toString());
          debitCardTransform.setExpiration(calculateExpirationDate());
          debitCardTransform.set_id(generateUuid());
          if (debitCardTransform.getAccountsSecundary() == null) {
            debitCardTransform.setAccountsSecundary(new ArrayList<>());
          }
          return debitCardTransform;
        }).flatMap(debitCardSave -> debitCardRepository.save(debitCardSave))
        .then();

  }

  @Override
  public Mono<Void> deleteDebitCardService(String debitCardId) {
    return getDebitCardByIdService(debitCardId)
        .flatMap(debitCard -> debitCardRepository.delete(debitCard));
  }

  @Override
  public Mono<DebitCard> getDebitCardByIdService(String debitCardId) {
    return debitCardRepository.findById(debitCardId)
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_NOT_FOUND_RECURSO, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND)));
  }

  @Override
  public Mono<Void> updateDebitCardService(DebitCard debitCard) {
    return validateDebitCardUpdateNoNulls(debitCard)
        .then(validateDebitCardUpdateEmpty(debitCard))
        .then(getDebitCardByIdService(debitCard.get_id())
            .flatMap(existingDebitCard -> {
              Flux<AccountsSecundary> accountsFlux = Flux.fromIterable(debitCard.getAccountsSecundary());
              Mono<DebitCard> accountFlux = accountsFlux.flatMap(accountsSecundary -> accountService.getAccountByIdService(accountsSecundary.getAccountId()))
                  .collectList()
                  .flatMap(accounts -> {
                    boolean allCustomersExist = accounts.stream()
                        .allMatch(Objects::nonNull);
                    if (!allCustomersExist) {
                      return Mono.error(new Exception("Algunos customerId no existen"));
                    }

                    List<AccountsSecundary> combinedList = new ArrayList<>(existingDebitCard.getAccountsSecundary());
                    combinedList.addAll(debitCard.getAccountsSecundary());
                    existingDebitCard.setAccountsSecundary(combinedList);
                    return debitCardRepository.save(existingDebitCard);
                  });

              return accountFlux;
            })
        )
        .then();
  }

}
