package com.nttdatabc.mscuentabancaria.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto credit.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditExt {
  private String _id;
  private String customer_id;
  private String type_credit;
  private double mount_limit;
  private LocalDateTime date_open;
  private double interest_rate;
}

