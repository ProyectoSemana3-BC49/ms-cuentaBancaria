package com.nttdatabc.mscuentabancaria.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Holders
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-02T16:30:53.069843400-05:00[America/Lima]")
public class Holders {

  private String dni;

  private String fullname;

  public Holders dni(String dni) {
    this.dni = dni;
    return this;
  }

  /**
   * Get dni
   * @return dni
   */

  @Schema(name = "dni", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dni")
  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public Holders fullname(String fullname) {
    this.fullname = fullname;
    return this;
  }

  /**
   * Get fullname
   * @return fullname
   */

  @Schema(name = "fullname", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fullname")
  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Holders holders = (Holders) o;
    return Objects.equals(this.dni, holders.dni) &&
        Objects.equals(this.fullname, holders.fullname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dni, fullname);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Holders {\n");
    sb.append("    dni: ").append(toIndentedString(dni)).append("\n");
    sb.append("    fullname: ").append(toIndentedString(fullname)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

