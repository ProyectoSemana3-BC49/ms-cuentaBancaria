package com.nttdatabc.mscuentabancaria.controller.interfaces;

import com.nttdatabc.mscuentabancaria.model.Movement;
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
@Tag(name = "Movimientos Cuenta Bancaria", description = "the Movimientos Cuenta Bancaria API")
public interface MovementControllerApi {

  /**
   * POST /movement/deposit : Crear depósito
   *
   * @param movement  (optional)
   * @return Depósito creado exitosamente (status code 201)
   *         or Error en Request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "createMovementDeposit",
      summary = "Crear depósito",
      tags = { "Movimientos Cuenta Bancaria" },
      responses = {
          @ApiResponse(responseCode = "201", description = "Depósito creado exitosamente"),
          @ApiResponse(responseCode = "400", description = "Error en Request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/movement/deposit",
      consumes = { "application/json" }
  )
  default ResponseEntity<Mono<Void>> createMovementDeposit(
      @Parameter(name = "Movement", description = "") @Valid @RequestBody(required = false) Movement movement,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }


  /**
   * POST /movement/transfer : Crear transferencia
   *
   * @param movement  (optional)
   * @return Transferencia creada exitosamente (status code 201)
   *         or Error en Request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "createTransfer",
      summary = "Crear transferencia",
      tags = { "Movimientos Cuenta Bancaria" },
      responses = {
          @ApiResponse(responseCode = "201", description = "Transferencia creada exitosamente"),
          @ApiResponse(responseCode = "400", description = "Error en Request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/movement/transfer",
      consumes = { "application/json" }
  )
  default ResponseEntity<Mono<Void>> createTransfer(
      @Parameter(name = "Movement", description = "") @Valid @RequestBody(required = false) Movement movement,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }


  /**
   * POST /movement/withdraw : Crear retiro
   *
   * @param movement  (optional)
   * @return Retiro creado exitosamente (status code 201)
   *         or Error en Request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "createWithDraw",
      summary = "Crear retiro",
      tags = { "Movimientos Cuenta Bancaria" },
      responses = {
          @ApiResponse(responseCode = "201", description = "Retiro creado exitosamente"),
          @ApiResponse(responseCode = "400", description = "Error en Request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/movement/withdraw",
      consumes = { "application/json" }
  )
  default ResponseEntity<Mono<Void>> createWithDraw(
      @Parameter(name = "Movement", description = "") @Valid @RequestBody(required = false) Movement movement,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }


  /**
   * GET /movement/{account_id} : Obtener movimientos de cuenta bancaria
   *
   * @param accountId ID de la cuenta bancaria (required)
   * @return Lista de movimientos de cuenta bancaria obtenida exitosamente (status code 200)
   *         or Error en request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "getMovementsByAccountId",
      summary = "Obtener movimientos de cuenta bancaria",
      tags = { "Movimientos Cuenta Bancaria" },
      responses = {
          @ApiResponse(responseCode = "200", description = "Lista de movimientos de cuenta bancaria obtenida exitosamente", content = {
              @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Movement.class)))
          }),
          @ApiResponse(responseCode = "400", description = "Error en request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/movement/{account_id}",
      produces = { "application/json" }
  )
  default ResponseEntity<Flux<Movement>> getMovementsByAccountId(
      @Parameter(name = "account_id", description = "ID de la cuenta bancaria", required = true, in = ParameterIn.PATH) @PathVariable("account_id") String accountId,
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

}
