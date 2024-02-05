package com.nttdatabc.mscuentabancaria.controller.interfaces;

import com.nttdatabc.mscuentabancaria.model.BalanceAccounts;
import com.nttdatabc.mscuentabancaria.model.Movement;
import com.nttdatabc.mscuentabancaria.model.MovementDebitCard;
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
@Tag(name = "Reportes", description = "the Reportes API")
public interface ReportControllerApi {

  /**
   * GET /report/balance_account/{customer_id} : Obtener resumen de saldos promedios del mes en curso de los productos bancarios.
   *
   * @param customerId ID del cliente (required)
   * @return Reporte de saldos promedios. (status code 200)
   *         or Error en request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "getBalanceAccount",
      summary = "Obtener resumen de saldos promedios del mes en curso de los productos bancarios.",
      tags = { "Reportes" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Reporte de saldos promedios.", content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = BalanceAccounts.class))
          }),
          @ApiResponse(responseCode = "400", description = "Error en request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/report/balance_account/{customer_id}",
      produces = { "application/json" }
  )
  default ResponseEntity<Mono<BalanceAccounts>> getBalanceAccount(
      @Parameter(name = "customer_id", description = "ID del cliente", required = true, in = ParameterIn.PATH) @PathVariable("customer_id") String customerId,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
      if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
        String exampleString = "{ \"customerId\" : \"customerId\", \"summary_accounts\" : [ { \"account_id\" : \"account_id\", \"balanceAvg\" : 0.8008281904610115 }, { \"account_id\" : \"account_id\", \"balanceAvg\" : 0.8008281904610115 } ] }";
        result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
        break;
      }
    }
    return ResponseEntity.ok().build();

  }


  /**
   * GET /report/fee/{account_id} : Obtener las comisiones cobradas por cuenta bancaria.
   *
   * @param accountId ID de la cuenta (required)
   * @return Reporte de saldos promedios. (status code 200)
   *         or Error en request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "getFeeByAccount",
      summary = "Obtener las comisiones cobradas por cuenta bancaria.",
      tags = { "Reportes" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Reporte de saldos promedios.", content = {
              @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Movement.class)))
          }),
          @ApiResponse(responseCode = "400", description = "Error en request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/report/fee/{account_id}",
      produces = { "application/json" }
  )
  default ResponseEntity<Flux<Movement>> getFeeByAccount(
      @Parameter(name = "account_id", description = "ID de la cuenta", required = true, in = ParameterIn.PATH) @PathVariable("account_id") String accountId,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
      if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
        String exampleString = "[ { \"type_movement\" : \"type_movement\", \"fecha\" : \"fecha\", \"account_id\" : \"account_id\", \"fee\" : 6.027456183070403, \"destination\" : \"destination\", \"_id\" : \"_id\", \"mount\" : 0.8008281904610115 }, { \"type_movement\" : \"type_movement\", \"fecha\" : \"fecha\", \"account_id\" : \"account_id\", \"fee\" : 6.027456183070403, \"destination\" : \"destination\", \"_id\" : \"_id\", \"mount\" : 0.8008281904610115 } ]";
        result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
        break;
      }
    }
    return ResponseEntity.ok().build();

  }
  /**
   * GET /report/last_movements_debit_card/{debit_card_id}
   *
   * @param debitCardId ID de tarjeta débito (required)
   * @return Lista de últimos 10 movimientos de tarjeta de débito obtenida exitosamente (status code 200)
   *         or Error en request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "getLastMovementDebitCard",
      tags = { "Reportes" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Lista de últimos 10 movimientos de tarjeta de débito obtenida exitosamente", content = {
              @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MovementDebitCard.class)))
          }),
          @ApiResponse(responseCode = "400", description = "Error en request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/report/last_movements_debit_card/{debit_card_id}",
      produces = { "application/json" }
  )
  default ResponseEntity<Flux<MovementDebitCard>> getLastMovementDebitCard(
      @Parameter(name = "debit_card_id", description = "ID de tarjeta débito", required = true, in = ParameterIn.PATH) @PathVariable("debit_card_id") String debitCardId,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
      if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
        String exampleString = "[ { \"date\" : \"date\", \"debitCardId\" : \"debitCardId\", \"accountDebited\" : \"accountDebited\", \"typeMovement\" : \"typeMovement\", \"mount\" : 0.8008281904610115 }, { \"date\" : \"date\", \"debitCardId\" : \"debitCardId\", \"accountDebited\" : \"accountDebited\", \"typeMovement\" : \"typeMovement\", \"mount\" : 0.8008281904610115 } ]";
        result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
        break;
      }
    }
    return ResponseEntity.ok().build();

  }

}
