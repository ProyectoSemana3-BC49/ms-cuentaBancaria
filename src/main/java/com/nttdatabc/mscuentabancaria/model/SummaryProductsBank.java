package com.nttdatabc.mscuentabancaria.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model SummaryProducts.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SummaryProductsBank {
  private String custormerId;
  private List<Account> accountsBanks;
  private List<CreditExt>creditsBanks;
}
