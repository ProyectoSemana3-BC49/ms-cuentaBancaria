package com.nttdatabc.mscuentabancaria.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Account.
 */

@Document(value = "account")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-02T16:30:53.069843400-05:00[America/Lima]")
public class Account {

  private String id;

  private String customerId;

  @Valid
  private List<@Valid Holders> holders;

  private String typeAccount;

  private BigDecimal currentBalance;

  private Integer limitMaxMovements;

  private BigDecimal maintenanceFee;

  private String dateMovement;

  public Account id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id.
   *
   * @return id.
   */

  @Schema(name = "_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("_id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Account customerId(String customerId) {
    this.customerId = customerId;
    return this;
  }

  /**
   * Get customerId
   *
   * @return customerId
   */

  @Schema(name = "customer_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("customer_id")
  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public Account holders(List<@Valid Holders> holders) {
    this.holders = holders;
    return this;
  }

  public Account addHoldersItem(Holders holdersItem) {
    if (this.holders == null) {
      this.holders = new ArrayList<>();
    }
    this.holders.add(holdersItem);
    return this;
  }

  /**
   * Get holders
   *
   * @return holders
   */
  @Valid
  @Schema(name = "holders", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("holders")
  public List<@Valid Holders> getHolders() {
    return holders;
  }

  public void setHolders(List<@Valid Holders> holders) {
    this.holders = holders;
  }

  public Account typeAccount(String typeAccount) {
    this.typeAccount = typeAccount;
    return this;
  }

  /**
   * Get typeAccount
   *
   * @return typeAccount
   */

  @Schema(name = "type_account", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type_account")
  public String getTypeAccount() {
    return typeAccount;
  }

  public void setTypeAccount(String typeAccount) {
    this.typeAccount = typeAccount;
  }

  public Account currentBalance(BigDecimal currentBalance) {
    this.currentBalance = currentBalance;
    return this;
  }

  /**
   * Get currentBalance
   *
   * @return currentBalance
   */
  @Valid
  @Schema(name = "current_balance", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("current_balance")
  public BigDecimal getCurrentBalance() {
    return currentBalance;
  }

  public void setCurrentBalance(BigDecimal currentBalance) {
    this.currentBalance = currentBalance;
  }

  public Account limitMaxMovements(Integer limitMaxMovements) {
    this.limitMaxMovements = limitMaxMovements;
    return this;
  }

  /**
   * Get limitMaxMovements
   *
   * @return limitMaxMovements
   */

  @Schema(name = "limit_max_movements", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("limit_max_movements")
  public Integer getLimitMaxMovements() {
    return limitMaxMovements;
  }

  public void setLimitMaxMovements(Integer limitMaxMovements) {
    this.limitMaxMovements = limitMaxMovements;
  }

  public Account maintenanceFee(BigDecimal maintenanceFee) {
    this.maintenanceFee = maintenanceFee;
    return this;
  }

  /**
   * Get maintenanceFee
   *
   * @return maintenanceFee
   */
  @Valid
  @Schema(name = "maintenance_fee", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("maintenance_fee")
  public BigDecimal getMaintenanceFee() {
    return maintenanceFee;
  }

  public void setMaintenanceFee(BigDecimal maintenanceFee) {
    this.maintenanceFee = maintenanceFee;
  }

  public Account dateMovement(String dateMovement) {
    this.dateMovement = dateMovement;
    return this;
  }

  /**
   * Get dateMovement
   *
   * @return dateMovement
   */

  @Schema(name = "date_movement", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("date_movement")
  public String getDateMovement() {
    return dateMovement;
  }

  public void setDateMovement(String dateMovement) {
    this.dateMovement = dateMovement;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(this.id, account.id) &&
        Objects.equals(this.customerId, account.customerId) &&
        Objects.equals(this.holders, account.holders) &&
        Objects.equals(this.typeAccount, account.typeAccount) &&
        Objects.equals(this.currentBalance, account.currentBalance) &&
        Objects.equals(this.limitMaxMovements, account.limitMaxMovements) &&
        Objects.equals(this.maintenanceFee, account.maintenanceFee) &&
        Objects.equals(this.dateMovement, account.dateMovement);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, customerId, holders, typeAccount, currentBalance, limitMaxMovements, maintenanceFee, dateMovement);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    holders: ").append(toIndentedString(holders)).append("\n");
    sb.append("    typeAccount: ").append(toIndentedString(typeAccount)).append("\n");
    sb.append("    currentBalance: ").append(toIndentedString(currentBalance)).append("\n");
    sb.append("    limitMaxMovements: ").append(toIndentedString(limitMaxMovements)).append("\n");
    sb.append("    maintenanceFee: ").append(toIndentedString(maintenanceFee)).append("\n");
    sb.append("    dateMovement: ").append(toIndentedString(dateMovement)).append("\n");
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

