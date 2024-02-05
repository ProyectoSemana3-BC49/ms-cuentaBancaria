package com.nttdatabc.mscuentabancaria.model.response;

import java.util.List;

import com.nttdatabc.mscuentabancaria.model.CreditExt;
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

