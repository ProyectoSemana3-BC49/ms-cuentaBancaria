package com.nttdatabc.mscuentabancaria.service.strategy.strategy_typeaccount;


import com.nttdatabc.mscuentabancaria.model.Account;
import com.nttdatabc.mscuentabancaria.model.response.CustomerExt;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static com.nttdatabc.mscuentabancaria.utils.Constantes.LIMIT_MAX_MOVEMENTS;
import static com.nttdatabc.mscuentabancaria.utils.Constantes.MAINTENANCE_FEE;

/**
 * Clase cuenta corriente configuracion.
 */
public class CorrienteAccountConfigurationStrategy implements AccountConfigurationStrategy {
    @Override
  public Mono<Void> configureAccount(Account account, CustomerExt customerExt)  {
    return Mono.fromRunnable(() -> {
      account.setLimitMaxMovements(LIMIT_MAX_MOVEMENTS);
      account.setMaintenanceFee(BigDecimal.valueOf(MAINTENANCE_FEE));
    });
  }
}
