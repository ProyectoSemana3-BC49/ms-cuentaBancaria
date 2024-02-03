package com.nttdatabc.mscuentabancaria.controller.interfaces;

import com.nttdatabc.mscuentabancaria.model.Account;
import com.nttdatabc.mscuentabancaria.utils.ApiUtil;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.codec.multipart.Part;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-02T16:30:53.069843400-05:00[America/Lima]")
@Validated
@Tag(name = "Cuentas Bancarias", description = "the Cuentas Bancarias API")
public interface AccountControllerApi {

  /**
   * POST /accounts : Agregar cuenta bancaria
   *
   * @param account  (optional)
   * @return Cuenta bancaria creada exitosamente (status code 201)
   *         or Error en Request (status code 400)
   */
  @Operation(
      operationId = "createAccount",
      summary = "Agregar cuenta bancaria",
      tags = { "Cuentas Bancarias" },
      responses = {
          @ApiResponse(responseCode = "201", description = "Cuenta bancaria creada exitosamente"),
          @ApiResponse(responseCode = "400", description = "Error en Request")
      }
  )
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/accounts",
      consumes = { "application/json" }
  )
  default ResponseEntity<Mono<Void>> createAccount(
      @Parameter(name = "Account", description = "") @Valid @RequestBody(required = false) Account account,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }


  /**
   * DELETE /accounts/{account_id} : Eliminar cuenta bancaria
   *
   * @param accountId ID de la cuenta bancaria (required)
   * @return Cuenta bancaria eliminada exitosamente (status code 200)
   *         or Error en request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "deleteAccountById",
      summary = "Eliminar cuenta bancaria",
      tags = { "Cuentas Bancarias" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Cuenta bancaria eliminada exitosamente"),
          @ApiResponse(responseCode = "400", description = "Error en request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.DELETE,
      value = "/accounts/{account_id}"
  )
  default ResponseEntity<Mono<Void>> deleteAccountById(
      @Parameter(name = "account_id", description = "ID de la cuenta bancaria", required = true, in = ParameterIn.PATH) @PathVariable("account_id") String accountId,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }


  /**
   * GET /accounts/{account_id} : Obtener detalle de cuenta bancaria
   *
   * @param accountId ID de la cuenta bancaria (required)
   * @return Detalle de cuenta bancaria obtenido exitosamente (status code 200)
   *         or Error en request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "getAccountById",
      summary = "Obtener detalle de cuenta bancaria",
      tags = { "Cuentas Bancarias" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Detalle de cuenta bancaria obtenido exitosamente", content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))
          }),
          @ApiResponse(responseCode = "400", description = "Error en request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/accounts/{account_id}",
      produces = { "application/json" }
  )
  default ResponseEntity<Mono<Account>> getAccountById(
      @Parameter(name = "account_id", description = "ID de la cuenta bancaria", required = true, in = ParameterIn.PATH) @PathVariable("account_id") String accountId,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
      if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
        String exampleString = "{ \"date_movement\" : \"date_movement\", \"limit_max_movements\" : 6, \"type_account\" : \"type_account\", \"holders\" : [ { \"fullname\" : \"fullname\", \"dni\" : \"dni\" }, { \"fullname\" : \"fullname\", \"dni\" : \"dni\" } ], \"current_balance\" : 0.8008281904610115, \"_id\" : \"_id\", \"customer_id\" : \"customer_id\", \"maintenance_fee\" : 1.4658129805029452 }";
        result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
        break;
      }
    }
    return ResponseEntity.ok().build();

  }


  /**
   * GET /accounts/customer/{customer_id} : Obtener cuentas bancarias por cliente
   *
   * @param customerId ID del cliente (required)
   * @return Detalle de cuenta bancaria obtenido exitosamente (status code 200)
   *         or Error en request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "getAccountsByCustomerId",
      summary = "Obtener cuentas bancarias por cliente",
      tags = { "Cuentas Bancarias" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Detalle de cuenta bancaria obtenido exitosamente", content = {
              @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Account.class)))
          }),
          @ApiResponse(responseCode = "400", description = "Error en request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/accounts/customer/{customer_id}",
      produces = { "application/json" }
  )
  default ResponseEntity<Flux<Account>> getAccountsByCustomerId(
      @Parameter(name = "customer_id", description = "ID del cliente", required = true, in = ParameterIn.PATH) @PathVariable("customer_id") String customerId,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
      if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
        String exampleString = "[ { \"date_movement\" : \"date_movement\", \"limit_max_movements\" : 6, \"type_account\" : \"type_account\", \"holders\" : [ { \"fullname\" : \"fullname\", \"dni\" : \"dni\" }, { \"fullname\" : \"fullname\", \"dni\" : \"dni\" } ], \"current_balance\" : 0.8008281904610115, \"_id\" : \"_id\", \"customer_id\" : \"customer_id\", \"maintenance_fee\" : 1.4658129805029452 }, { \"date_movement\" : \"date_movement\", \"limit_max_movements\" : 6, \"type_account\" : \"type_account\", \"holders\" : [ { \"fullname\" : \"fullname\", \"dni\" : \"dni\" }, { \"fullname\" : \"fullname\", \"dni\" : \"dni\" } ], \"current_balance\" : 0.8008281904610115, \"_id\" : \"_id\", \"customer_id\" : \"customer_id\", \"maintenance_fee\" : 1.4658129805029452 } ]";
        result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
        break;
      }
    }
    return ResponseEntity.ok().build();

  }


  /**
   * GET /accounts : Obtener cuentas bancarias
   *
   * @return Lista de cuentas bancarias obtenida exitosamente (status code 200)
   */
  @Operation(
      operationId = "getAllAccounts",
      summary = "Obtener cuentas bancarias",
      tags = { "Cuentas Bancarias" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Lista de cuentas bancarias obtenida exitosamente", content = {
              @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Account.class)))
          })
      }
  )
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/accounts",
      produces = { "application/json" }
  )
  default ResponseEntity<Flux<Account>> getAllAccounts(
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
      if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
        String exampleString = "[ { \"date_movement\" : \"date_movement\", \"limit_max_movements\" : 6, \"type_account\" : \"type_account\", \"holders\" : [ { \"fullname\" : \"fullname\", \"dni\" : \"dni\" }, { \"fullname\" : \"fullname\", \"dni\" : \"dni\" } ], \"current_balance\" : 0.8008281904610115, \"_id\" : \"_id\", \"customer_id\" : \"customer_id\", \"maintenance_fee\" : 1.4658129805029452 }, { \"date_movement\" : \"date_movement\", \"limit_max_movements\" : 6, \"type_account\" : \"type_account\", \"holders\" : [ { \"fullname\" : \"fullname\", \"dni\" : \"dni\" }, { \"fullname\" : \"fullname\", \"dni\" : \"dni\" } ], \"current_balance\" : 0.8008281904610115, \"_id\" : \"_id\", \"customer_id\" : \"customer_id\", \"maintenance_fee\" : 1.4658129805029452 } ]";
        result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
        break;
      }
    }
    return ResponseEntity.ok().build();
  }


  /**
   * PUT /accounts : Actualizar cuenta bancaria
   *
   * @param account  (optional)
   * @return Cuenta bancaria actualizada exitosamente (status code 200)
   *         or Error en Request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "updateAccount",
      summary = "Actualizar cuenta bancaria",
      tags = { "Cuentas Bancarias" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Cuenta bancaria actualizada exitosamente"),
          @ApiResponse(responseCode = "400", description = "Error en Request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.PUT,
      value = "/accounts",
      consumes = { "application/json" }
  )
  default ResponseEntity<Mono<Void>> updateAccount(
      @Parameter(name = "Account", description = "") @Valid @RequestBody(required = false) Account account,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();
  }

}