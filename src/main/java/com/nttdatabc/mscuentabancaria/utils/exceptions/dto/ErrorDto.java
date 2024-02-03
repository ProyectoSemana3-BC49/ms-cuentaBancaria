package com.nttdatabc.mscuentabancaria.utils.exceptions.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * Clase DTO del Error.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
  private HttpStatus httpStatus;
  private String message;
  private int code;
}
