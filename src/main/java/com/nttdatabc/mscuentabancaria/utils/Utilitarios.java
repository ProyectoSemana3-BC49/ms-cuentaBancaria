package com.nttdatabc.mscuentabancaria.utils;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.nttdatabc.mscuentabancaria.utils.Constantes.DURATION_EXPIRED_DEBIT_CARD;
import static com.nttdatabc.mscuentabancaria.utils.Constantes.PREFIX_NUMBER_CARD;

/**
 * Clase utilitarios.
 */
public class Utilitarios {
  public static String generateUuid() {
    return UUID.randomUUID().toString().replace("-", "");
  }
  public static String generateNumberCard(){
    Random random = new Random();

    String cardNumber = PREFIX_NUMBER_CARD + IntStream.range(0, 12)
        .mapToObj(i -> String.valueOf(random.nextInt(10)))
        .collect(Collectors.joining());

    return IntStream.range(0, cardNumber.length())
        .mapToObj(i -> cardNumber.charAt(i) + (i % 4 == 3 && i != cardNumber.length() - 1 ? "-" : ""))
        .collect(Collectors.joining());
  }
  public static String generateRandomCVV2() {
    Random random = new Random();
    int cvv2 = random.nextInt(1000);
    return String.format("%03d", cvv2);
  }
  public static String calculateExpirationDate() {
    LocalDate currentDate = LocalDate.now();
    LocalDate expirationDate = currentDate.plusYears(DURATION_EXPIRED_DEBIT_CARD);
    int year = expirationDate.getYear();
    int month = expirationDate.getMonthValue();
    return String.format("%02d/%s", month, String.valueOf(year).substring(2));
  }

}
