package com.nttdatabc.mscuentabancaria.service;

import com.nttdatabc.mscuentabancaria.model.Account;
import com.nttdatabc.mscuentabancaria.model.enums.TypeAccountBank;
import com.nttdatabc.mscuentabancaria.model.enums.TypeCustomer;
import com.nttdatabc.mscuentabancaria.repository.AccountRepository;
import com.nttdatabc.mscuentabancaria.service.interfaces.AccountService;
import com.nttdatabc.mscuentabancaria.service.strategy.strategy_account.AccountValidationStrategy;
import com.nttdatabc.mscuentabancaria.service.strategy.strategy_account.EmpresaAccountValidationStrategy;
import com.nttdatabc.mscuentabancaria.service.strategy.strategy_account.PersonaAccountValidationStrategy;
import com.nttdatabc.mscuentabancaria.service.strategy.strategy_typeaccount.*;
import com.nttdatabc.mscuentabancaria.utils.Utilitarios;
import com.nttdatabc.mscuentabancaria.utils.exceptions.errors.ErrorResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.nttdatabc.mscuentabancaria.utils.AccountValidator.*;
import static com.nttdatabc.mscuentabancaria.utils.Constantes.EX_ERROR_REQUEST;
import static com.nttdatabc.mscuentabancaria.utils.Constantes.EX_NOT_FOUND_RECURSO;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private CustomerApiExtImpl customerApiExtImpl;

  @Autowired
  private CreditApiExtImpl creditApiExt;

  @Override
  public Flux<Account> getAllAccountsService() {
    return accountRepository.findAll().switchIfEmpty(Flux.empty());
  }

  @Override
  public Mono<Void> createAccountService(Account account)  {
    return validateAccountsNoNulls(account)
        .then(validateAccountEmpty(account))
        .then(verifyTypeAccount(account))
        .then(verifyValues(account))
        .then(Mono.just(account))
        .flatMap(accountMono -> verifyCustomerExists(accountMono.getCustomerId(),customerApiExtImpl)
            .flatMap(customerFound -> getAccountsByCustomerIdService(customerFound.get_id()).collectList()
                .flatMap(listAccountByCustomer  -> {
                  AccountValidationStrategy accountValidationStrategy = null;
                  if (customerFound.getType().equalsIgnoreCase(TypeCustomer.PERSONA.toString())) {
                    accountValidationStrategy = new PersonaAccountValidationStrategy();
                    return accountValidationStrategy.validateAccount(account, listAccountByCustomer)
                        .thenReturn(customerFound);
                  }else{
                    accountValidationStrategy = new EmpresaAccountValidationStrategy();
                    return accountValidationStrategy.validateAccount(account, listAccountByCustomer)
                        .thenReturn(customerFound);
                  }
                })
            )).flatMap(customerFound -> {
          AccountConfigurationStrategy configationStrategy = null;
          if (account.getTypeAccount().equalsIgnoreCase(TypeAccountBank.AHORRO.toString())) {
            configationStrategy = new AhorroAccountConfigurationStrategy();
            return configationStrategy.configureAccount(account, customerFound);
          } else if (account.getTypeAccount().equalsIgnoreCase(TypeAccountBank.CORRIENTE.toString())) {
            configationStrategy = new CorrienteAccountConfigurationStrategy();
            return configationStrategy.configureAccount(account, customerFound);
          } else if (account.getTypeAccount().equalsIgnoreCase(TypeAccountBank.PLAZO_FIJO.toString())) {
            configationStrategy = new PlazoFijoAccountConfigurationStrategy();
            return configationStrategy.configureAccount(account, customerFound);
          } else if (account.getTypeAccount().equalsIgnoreCase(TypeAccountBank.VIP.toString())) {
            configationStrategy = new VipAccountConfigurationStrategy();
            return configationStrategy.validateHasCredit(creditApiExt, customerFound.get_id())
                .then(configationStrategy.configureAccount(account, customerFound));
          } else if (account.getTypeAccount().equalsIgnoreCase(TypeAccountBank.PYME.toString())) {
            configationStrategy = new PymeAccountConfigurationStrategy();
            return configationStrategy.validateHasCredit(creditApiExt, customerFound.get_id())
                .then(configationStrategy.configureAccount(account, customerFound))
                .then(configationStrategy.validateHasCorriente(accountRepository.findByCustomerId(customerFound.get_id())));
          } else {
            return Mono.error(() -> new ErrorResponseException(EX_ERROR_REQUEST,HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST ));
          }
        })
        .then(Mono.just(account))
        .doOnNext(accountFlujo -> accountFlujo.setId(Utilitarios.generateUuid()))
        .flatMap(accountRepository::save)
        .then();

  }

  @Override
  public Mono<Void> updateAccountServide(Account account) {
    return validateAccountsNoNulls(account)
        .then(validateAccountEmpty(account))
        .then(verifyTypeAccount(account))
        .then(Mono.just(account))
        .flatMap(accountRequest -> getAccountByIdService(accountRequest.getId()))
        .map(accountFound -> {
          accountFound.setTypeAccount(account.getTypeAccount());
          accountFound.setCurrentBalance(account.getCurrentBalance());
          accountFound.setCustomerId(account.getCustomerId());
          accountFound.setHolders(account.getHolders());
          accountFound.setDateMovement(account.getDateMovement());
          accountFound.setLimitMaxMovements(account.getLimitMaxMovements());
          accountFound.setMaintenanceFee(account.getMaintenanceFee());
          return accountFound;
        })
        .flatMap(accountRepository::save)
        .then();
  }

  @Override
  public Mono<Void> deleteAccountByIdService(String accountId) {
    return getAccountByIdService(accountId)
        .flatMap(account -> accountRepository.delete(account))
        .then();
  }

  @Override
  public Mono<Account> getAccountByIdService(String accountId) {
    return accountRepository.findById(accountId)
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_NOT_FOUND_RECURSO,
            HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND)));
  }

  @Override
  public Flux<Account> getAccountsByCustomerIdService(String customerId) {
    return verifyCustomerExists(customerId, customerApiExtImpl)
        .thenMany(accountRepository.findByCustomerId(customerId));
  }
}
