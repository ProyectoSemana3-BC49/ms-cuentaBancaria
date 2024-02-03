package com.nttdatabc.mscuentabancaria.utils;

import java.util.UUID;

/**
 * Clase utilitarios.
 */
public class Utilitarios {
  public static String generateUuid() {
    return UUID.randomUUID().toString().replace("-", "");
  }

}
