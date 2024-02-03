package com.nttdatabc.mscuentabancaria.service.strategy.strategy_account;

import com.nttdatabc.mscuentabancaria.model.Account;
import com.nttdatabc.mscuentabancaria.model.enums.TypeAccountBank;
import com.nttdatabc.mscuentabancaria.utils.exceptions.errors.ErrorResponseException;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.nttdatabc.mscuentabancaria.utils.Constantes.EX_ERROR_CONFLICTO_CUSTOMER_EMPRESA_NEED_HOLDERS;
import static com.nttdatabc.mscuentabancaria.utils.Constantes.EX_ERROR_CONFLICTO_CUSTOMER_EMPRESA_NOT_TYPE_AUTHORIZED;

/**
 * Empresa validation.
 */
public class EmpresaAccountValidationStrategy implements AccountValidationStrategy {
  public Mono<Void> validateAccount(Account account, List<Account> accountList) {
    if (account.getTypeAccount().equalsIgnoreCase(TypeAccountBank.AHORRO.toString())
        || account.getTypeAccount().equalsIgnoreCase(TypeAccountBank.PLAZO_FIJO.toString())) {
      return Mono.error(new ErrorResponseException(EX_ERROR_CONFLICTO_CUSTOMER_EMPRESA_NOT_TYPE_AUTHORIZED,
          HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT));
    }
    if (account.getHolders() == null) {
      return Mono.error(new ErrorResponseException(EX_ERROR_CONFLICTO_CUSTOMER_EMPRESA_NEED_HOLDERS,
          HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT));
    }
    return Mono.empty();
  }
}

