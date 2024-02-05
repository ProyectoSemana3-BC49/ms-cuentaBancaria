package com.nttdatabc.mscuentabancaria.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import javax.annotation.Generated;


/**
 * AccountsSecundary.
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-04T12:54:31.461025300-05:00[America/Lima]")
public class AccountsSecundary {

  private Integer priority;
  private String accountId;

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public AccountsSecundary accountId(String accountId) {
    this.accountId = accountId;
    return this;
  }

  /**
   * Get accountId
   *
   * @return accountId
   */

  @Schema(name = "accountId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("accountId")
  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountsSecundary accountsSecundary = (AccountsSecundary) o;
    return Objects.equals(this.accountId, accountsSecundary.accountId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountsSecundary {\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
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


