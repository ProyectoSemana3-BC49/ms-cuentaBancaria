package com.nttdatabc.mscuentabancaria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase AuthorizedSignerExt.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizedSignerExt {
  private String dni;

  private String fullname;

  private String cargo;
}
