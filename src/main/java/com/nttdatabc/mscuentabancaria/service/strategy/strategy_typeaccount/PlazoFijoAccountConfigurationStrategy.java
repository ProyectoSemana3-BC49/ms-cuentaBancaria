package com.nttdatabc.mscuentabancaria.service.strategy.strategy_typeaccount;

import com.nttdatabc.mscuentabancaria.model.Account;
import com.nttdatabc.mscuentabancaria.model.response.CustomerExt;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static com.nttdatabc.mscuentabancaria.utils.Constantes.*;

/**
 * Clase configuration.
 */
public class PlazoFijoAccountConfigurationStrategy implements AccountConfigurationStrategy {

  @Override
  public Mono<Void> configureAccount(Account account, CustomerExt customerExt) {
    return Mono.fromRunnable(() -> {
      account.setMaintenanceFee(BigDecimal.valueOf(MAINTENANCE_FEE_FREE));
      account.setDateMovement(DAY_MOVEMENT_SELECTED);
      account.setLimitMaxMovements(LIMIT_MAX_MOVEMENTS);
    });
  }
}
