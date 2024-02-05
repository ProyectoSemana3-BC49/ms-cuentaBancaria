package com.nttdatabc.mscuentabancaria.service.strategy.strategy_typeaccount;

import static com.nttdatabc.mscuentabancaria.utils.Constantes.LIMIT_MAX_MOVEMENTS;
import static com.nttdatabc.mscuentabancaria.utils.Constantes.MAINTENANCE_FEE_FREE;

import com.nttdatabc.mscuentabancaria.model.Account;
import com.nttdatabc.mscuentabancaria.model.response.CustomerExt;
import java.math.BigDecimal;
import reactor.core.publisher.Mono;


/**
 * Clase Strategy.
 */
public class AhorroAccountConfigurationStrategy implements AccountConfigurationStrategy {
  @Override
  public Mono<Void> configureAccount(Account account, CustomerExt customerExt) {
    return Mono.fromRunnable(() -> {
      account.setMaintenanceFee(BigDecimal.valueOf(MAINTENANCE_FEE_FREE));
      account.setLimitMaxMovements(LIMIT_MAX_MOVEMENTS);
    });
  }


}
