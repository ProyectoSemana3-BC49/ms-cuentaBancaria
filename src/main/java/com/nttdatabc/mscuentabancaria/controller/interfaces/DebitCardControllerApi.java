package com.nttdatabc.mscuentabancaria.controller.interfaces;

import com.nttdatabc.mscuentabancaria.model.DebitCard;
import com.nttdatabc.mscuentabancaria.utils.ApiUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-04T12:54:31.461025300-05:00[America/Lima]")
@Validated
@Tag(name = "Tarjeta de débito", description = "the Tarjeta de débito API")
public interface DebitCardControllerApi {

  /**
   * POST /debit_card : Agregar tarjeta de credito
   *
   * @param debitCard  (optional)
   * @return Tarjeta de débito creada exitosamente (status code 201)
   *         or Error en Request (status code 400)
   */
  @Operation(
      operationId = "createDebitCard",
      summary = "Agregar tarjeta de credito",
      tags = { "Tarjeta de débito" },
      responses = {
          @ApiResponse(responseCode = "201", description = "Tarjeta de débito creada exitosamente"),
          @ApiResponse(responseCode = "400", description = "Error en Request")
      }
  )
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/debit_card",
      consumes = { "application/json" }
  )
  default ResponseEntity<Mono<Void>> createDebitCard(
      @Parameter(name = "DebitCard", description = "") @Valid @RequestBody(required = false) DebitCard debitCard,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }


  /**
   * DELETE /debit_card/{debit_card_id} : Eliminar Tarjeta de débito
   *
   * @param debitCardId ID de la tarjeta de débito (required)
   * @return Tarjeta de débito eliminada exitosamente (status code 200)
   *         or Error en request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "deleteDebitCard",
      summary = "Eliminar Tarjeta de débito",
      tags = { "Tarjeta de débito" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Tarjeta de débito eliminada exitosamente"),
          @ApiResponse(responseCode = "400", description = "Error en request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.DELETE,
      value = "/debit_card/{debit_card_id}"
  )
  default ResponseEntity<Mono<Void>> deleteDebitCard(
      @Parameter(name = "debit_card_id", description = "ID de la tarjeta de débito", required = true, in = ParameterIn.PATH) @PathVariable("debit_card_id") String debitCardId,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }


  /**
   * GET /debit_card : Obtener todas las tarjetas de débito
   *
   * @return Lista de tarjetas de debito, obtenidas exitosamente (status code 200)
   */
  @Operation(
      operationId = "getAllDebitCard",
      summary = "Obtener todas las tarjetas de débito",
      tags = { "Tarjeta de débito" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Lista de tarjetas de debito, obtenidas exitosamente", content = {
              @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DebitCard.class)))
          })
      }
  )
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/debit_card",
      produces = { "application/json" }
  )
  default ResponseEntity<Flux<DebitCard>> getAllDebitCard(
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
      if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
        String exampleString = "[ { \"cvv2\" : \"cvv2\", \"password\" : \"password\", \"accountIdPrincipal\" : \"accountIdPrincipal\", \"createdCardDebit\" : \"createdCardDebit\", \"customerId\" : \"customerId\", \"expiration\" : \"expiration\", \"accountsSecundary\" : [ { \"accountId\" : \"accountId\" }, { \"accountId\" : \"accountId\" } ], \"numberCard\" : \"numberCard\" }, { \"cvv2\" : \"cvv2\", \"password\" : \"password\", \"accountIdPrincipal\" : \"accountIdPrincipal\", \"createdCardDebit\" : \"createdCardDebit\", \"customerId\" : \"customerId\", \"expiration\" : \"expiration\", \"accountsSecundary\" : [ { \"accountId\" : \"accountId\" }, { \"accountId\" : \"accountId\" } ], \"numberCard\" : \"numberCard\" } ]";
        result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
        break;
      }
    }
    return ResponseEntity.ok().build();

  }


  /**
   * GET /debit_card/{debit_card_id} : Obtener detalle de una tarjeta de débito
   *
   * @param debitCardId ID de la tarjeta de débito (required)
   * @return Detalle de tarjeta de débito obtenido exitosamente (status code 200)
   *         or Error en request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "getDebitCardById",
      summary = "Obtener detalle de una tarjeta de débito",
      tags = { "Tarjeta de débito" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Detalle de tarjeta de débito obtenido exitosamente", content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = DebitCard.class))
          }),
          @ApiResponse(responseCode = "400", description = "Error en request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/debit_card/{debit_card_id}",
      produces = { "application/json" }
  )
  default ResponseEntity<Mono<DebitCard>> getDebitCardById(
      @Parameter(name = "debit_card_id", description = "ID de la tarjeta de débito", required = true, in = ParameterIn.PATH) @PathVariable("debit_card_id") String debitCardId,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
      if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
        String exampleString = "{ \"cvv2\" : \"cvv2\", \"password\" : \"password\", \"accountIdPrincipal\" : \"accountIdPrincipal\", \"createdCardDebit\" : \"createdCardDebit\", \"customerId\" : \"customerId\", \"expiration\" : \"expiration\", \"accountsSecundary\" : [ { \"accountId\" : \"accountId\" }, { \"accountId\" : \"accountId\" } ], \"numberCard\" : \"numberCard\" }";
        result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
        break;
      }
    }
    return ResponseEntity.ok().build();

  }


  /**
   * PUT /debit_card : Actualizar Tarjeta débito
   *
   * @param debitCard  (optional)
   * @return Tarjeta de débito actualizada exitosamente (status code 200)
   *         or Error en Request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "updateDebitCard",
      summary = "Actualizar Tarjeta débito",
      tags = { "Tarjeta de débito" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Tarjeta de débito actualizada exitosamente"),
          @ApiResponse(responseCode = "400", description = "Error en Request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.PUT,
      value = "/debit_card",
      consumes = { "application/json" }
  )
  default ResponseEntity<Mono<Void>> updateDebitCard(
      @Parameter(name = "DebitCard", description = "") @Valid @RequestBody(required = false) DebitCard debitCard,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }

}

