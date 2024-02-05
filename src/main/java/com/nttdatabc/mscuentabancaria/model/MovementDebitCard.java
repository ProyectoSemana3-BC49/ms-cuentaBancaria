package com.nttdatabc.mscuentabancaria.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MovementDebitCard
 */

@Document(value = "movementDebitCard")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-04T12:54:31.461025300-05:00[America/Lima]")
public class MovementDebitCard {
  private String _id;
  private String debitCardId;

  private String accountDebited;

  private String typeMovement;

  private BigDecimal mount;

  private String date;

  public MovementDebitCard debitCardId(String debitCardId) {
    this.debitCardId = debitCardId;
    return this;
  }

  /**
   * Get debitCardId
   *
   * @return debitCardId
   */

  @Schema(name = "debitCardId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("debitCardId")
  public String getDebitCardId() {
    return debitCardId;
  }

  public void setDebitCardId(String debitCardId) {
    this.debitCardId = debitCardId;
  }

  public MovementDebitCard accountDebited(String accountDebited) {
    this.accountDebited = accountDebited;
    return this;
  }

  /**
   * Get accountDebited
   *
   * @return accountDebited
   */

  @Schema(name = "accountDebited", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("accountDebited")
  public String getAccountDebited() {
    return accountDebited;
  }

  public void setAccountDebited(String accountDebited) {
    this.accountDebited = accountDebited;
  }

  public MovementDebitCard typeMovement(String typeMovement) {
    this.typeMovement = typeMovement;
    return this;
  }

  /**
   * Get typeMovement
   *
   * @return typeMovement
   */

  @Schema(name = "typeMovement", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("typeMovement")
  public String getTypeMovement() {
    return typeMovement;
  }

  public void setTypeMovement(String typeMovement) {
    this.typeMovement = typeMovement;
  }

  public MovementDebitCard mount(BigDecimal mount) {
    this.mount = mount;
    return this;
  }

  /**
   * Get mount
   *
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

  public MovementDebitCard date(String date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   *
   * @return date
   */

  @Schema(name = "date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("date")
  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MovementDebitCard movementDebitCard = (MovementDebitCard) o;
    return Objects.equals(this.debitCardId, movementDebitCard.debitCardId) &&
        Objects.equals(this.accountDebited, movementDebitCard.accountDebited) &&
        Objects.equals(this.typeMovement, movementDebitCard.typeMovement) &&
        Objects.equals(this.mount, movementDebitCard.mount) &&
        Objects.equals(this.date, movementDebitCard.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(debitCardId, accountDebited, typeMovement, mount, date);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MovementDebitCard {\n");
    sb.append("    debitCardId: ").append(toIndentedString(debitCardId)).append("\n");
    sb.append("    accountDebited: ").append(toIndentedString(accountDebited)).append("\n");
    sb.append("    typeMovement: ").append(toIndentedString(typeMovement)).append("\n");
    sb.append("    mount: ").append(toIndentedString(mount)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
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