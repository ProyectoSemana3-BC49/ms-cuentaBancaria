package com.nttdatabc.mscuentabancaria.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * DebitCard.
 */

@Document(value = "debitCard")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-04T12:54:31.461025300-05:00[America/Lima]")
public class DebitCard {

  private String _id;
  private String customerId;

  private String createdCardDebit;

  private String numberCard;

  private String expiration;

  private String cvv2;

  private String password;

  private String accountIdPrincipal;

  @Valid
  private List<@Valid AccountsSecundary> accountsSecundary;

  public DebitCard customerId(String customerId) {
    this.customerId = customerId;
    return this;
  }

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  /**
   * Get customerId
   *
   * @return customerId
   */

  @Schema(name = "customerId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("customerId")
  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public DebitCard createdCardDebit(String createdCardDebit) {
    this.createdCardDebit = createdCardDebit;
    return this;
  }

  /**
   * Get createdCardDebit
   *
   * @return createdCardDebit
   */

  @Schema(name = "createdCardDebit", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("createdCardDebit")
  public String getCreatedCardDebit() {
    return createdCardDebit;
  }

  public void setCreatedCardDebit(String createdCardDebit) {
    this.createdCardDebit = createdCardDebit;
  }

  public DebitCard numberCard(String numberCard) {
    this.numberCard = numberCard;
    return this;
  }

  /**
   * Get numberCard
   *
   * @return numberCard
   */

  @Schema(name = "numberCard", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("numberCard")
  public String getNumberCard() {
    return numberCard;
  }

  public void setNumberCard(String numberCard) {
    this.numberCard = numberCard;
  }

  public DebitCard expiration(String expiration) {
    this.expiration = expiration;
    return this;
  }

  /**
   * Get expiration
   *
   * @return expiration
   */

  @Schema(name = "expiration", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("expiration")
  public String getExpiration() {
    return expiration;
  }

  public void setExpiration(String expiration) {
    this.expiration = expiration;
  }

  public DebitCard cvv2(String cvv2) {
    this.cvv2 = cvv2;
    return this;
  }

  /**
   * Get cvv2
   *
   * @return cvv2
   */

  @Schema(name = "cvv2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cvv2")
  public String getCvv2() {
    return cvv2;
  }

  public void setCvv2(String cvv2) {
    this.cvv2 = cvv2;
  }

  public DebitCard password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   *
   * @return password
   */

  @Schema(name = "password", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public DebitCard accountIdPrincipal(String accountIdPrincipal) {
    this.accountIdPrincipal = accountIdPrincipal;
    return this;
  }

  /**
   * Get accountIdPrincipal
   *
   * @return accountIdPrincipal
   */

  @Schema(name = "accountIdPrincipal", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("accountIdPrincipal")
  public String getAccountIdPrincipal() {
    return accountIdPrincipal;
  }

  public void setAccountIdPrincipal(String accountIdPrincipal) {
    this.accountIdPrincipal = accountIdPrincipal;
  }

  public DebitCard accountsSecundary(List<@Valid AccountsSecundary> accountsSecundary) {
    this.accountsSecundary = accountsSecundary;
    return this;
  }

  public DebitCard addAccountsSecundaryItem(AccountsSecundary accountsSecundaryItem) {
    if (this.accountsSecundary == null) {
      this.accountsSecundary = new ArrayList<>();
    }
    this.accountsSecundary.add(accountsSecundaryItem);
    return this;
  }

  /**
   * Get accountsSecundary
   *
   * @return accountsSecundary
   */
  @Valid
  @Schema(name = "accountsSecundary", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("accountsSecundary")
  public List<@Valid AccountsSecundary> getAccountsSecundary() {
    return accountsSecundary;
  }

  public void setAccountsSecundary(List<@Valid AccountsSecundary> accountsSecundary) {
    this.accountsSecundary = accountsSecundary;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DebitCard debitCard = (DebitCard) o;
    return Objects.equals(this.customerId, debitCard.customerId) &&
        Objects.equals(this.createdCardDebit, debitCard.createdCardDebit) &&
        Objects.equals(this.numberCard, debitCard.numberCard) &&
        Objects.equals(this.expiration, debitCard.expiration) &&
        Objects.equals(this.cvv2, debitCard.cvv2) &&
        Objects.equals(this.password, debitCard.password) &&
        Objects.equals(this.accountIdPrincipal, debitCard.accountIdPrincipal) &&
        Objects.equals(this.accountsSecundary, debitCard.accountsSecundary);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerId, createdCardDebit, numberCard, expiration, cvv2, password, accountIdPrincipal, accountsSecundary);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DebitCard {\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    createdCardDebit: ").append(toIndentedString(createdCardDebit)).append("\n");
    sb.append("    numberCard: ").append(toIndentedString(numberCard)).append("\n");
    sb.append("    expiration: ").append(toIndentedString(expiration)).append("\n");
    sb.append("    cvv2: ").append(toIndentedString(cvv2)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    accountIdPrincipal: ").append(toIndentedString(accountIdPrincipal)).append("\n");
    sb.append("    accountsSecundary: ").append(toIndentedString(accountsSecundary)).append("\n");
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