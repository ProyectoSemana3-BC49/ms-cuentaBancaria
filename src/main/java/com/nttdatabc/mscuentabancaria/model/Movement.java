package com.nttdatabc.mscuentabancaria.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.*;
import javax.annotation.Generated;

/**
 * Movement
 */
@Document(value = "movement")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-02T16:30:53.069843400-05:00[America/Lima]")
public class Movement {

  private String id;

  private String accountId;

  private String typeMovement;

  private BigDecimal mount;

  private String fecha;

  private BigDecimal fee;

  private String destination;

  public Movement id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */

  @Schema(name = "_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("_id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Movement accountId(String accountId) {
    this.accountId = accountId;
    return this;
  }

  /**
   * Get accountId
   * @return accountId
   */

  @Schema(name = "account_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("account_id")
  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public Movement typeMovement(String typeMovement) {
    this.typeMovement = typeMovement;
    return this;
  }

  /**
   * Get typeMovement
   * @return typeMovement
   */

  @Schema(name = "type_movement", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type_movement")
  public String getTypeMovement() {
    return typeMovement;
  }

  public void setTypeMovement(String typeMovement) {
    this.typeMovement = typeMovement;
  }

  public Movement mount(BigDecimal mount) {
    this.mount = mount;
    return this;
  }

  /**
   * Get mount
   * @return mount
   */
  @Valid
  @Schema(name = "mount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mount")
  public BigDecimal getMount() {
    return mount;
  }

  public void setMount(BigDecimal mount) {
    this.mount = mount;
  }

  public Movement fecha(String fecha) {
    this.fecha = fecha;
    return this;
  }

  /**
   * Get fecha
   * @return fecha
   */

  @Schema(name = "fecha", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fecha")
  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public Movement fee(BigDecimal fee) {
    this.fee = fee;
    return this;
  }

  /**
   * Get fee
   * @return fee
   */
  @Valid
  @Schema(name = "fee", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fee")
  public BigDecimal getFee() {
    return fee;
  }

  public void setFee(BigDecimal fee) {
    this.fee = fee;
  }

  public Movement destination(String destination) {
    this.destination = destination;
    return this;
  }

  /**
   * Get destination
   * @return destination
   */

  @Schema(name = "destination", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("destination")
  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Movement movement = (Movement) o;
    return Objects.equals(this.id, movement.id) &&
        Objects.equals(this.accountId, movement.accountId) &&
        Objects.equals(this.typeMovement, movement.typeMovement) &&
        Objects.equals(this.mount, movement.mount) &&
        Objects.equals(this.fecha, movement.fecha) &&
        Objects.equals(this.fee, movement.fee) &&
        Objects.equals(this.destination, movement.destination);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, accountId, typeMovement, mount, fecha, fee, destination);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Movement {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
    sb.append("    typeMovement: ").append(toIndentedString(typeMovement)).append("\n");
    sb.append("    mount: ").append(toIndentedString(mount)).append("\n");
    sb.append("    fecha: ").append(toIndentedString(fecha)).append("\n");
    sb.append("    fee: ").append(toIndentedString(fee)).append("\n");
    sb.append("    destination: ").append(toIndentedString(destination)).append("\n");
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
