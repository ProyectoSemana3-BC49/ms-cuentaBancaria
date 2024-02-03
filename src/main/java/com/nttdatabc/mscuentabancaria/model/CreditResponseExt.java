package com.nttdatabc.mscuentabancaria.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Clase dto consulta.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditResponseExt {
  private List<CreditExt> body;
  private String statusCode;
  private int statusCodeValue;
}

