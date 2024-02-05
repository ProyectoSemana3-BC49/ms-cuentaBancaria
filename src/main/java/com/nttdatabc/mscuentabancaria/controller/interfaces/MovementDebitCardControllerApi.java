package com.nttdatabc.mscuentabancaria.controller.interfaces;

import com.nttdatabc.mscuentabancaria.model.MovementDebitCard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.annotation.Generated;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-04T13:08:48.141296800-05:00[America/Lima]")
@Validated
@Tag(name = "Movimiento Tarjeta débito", description = "the Movimiento Tarjeta débito API")
public interface MovementDebitCardControllerApi {

  /**
   * POST /movement_debit_card/movement/payment : Agregar Movimiento Pago
   *
   * @param movementDebitCard  (optional)
   * @return Movimiento Tarjeta débito creada exitosamente (status code 201)
   *         or Error en Request (status code 400)
   */
  @Operation(
      operationId = "createMovementPayment",
      summary = "Agregar Movimiento Pago",
      tags = { "Movimiento Tarjeta débito" },
      responses = {
          @ApiResponse(responseCode = "201", description = "Movimiento Tarjeta débito creada exitosamente"),
          @ApiResponse(responseCode = "400", description = "Error en Request")
      }
  )
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/movement_debit_card/payment",
      consumes = { "application/json" }
  )
  default ResponseEntity<Mono<Void>> createMovementPayment(
      @Parameter(name = "MovementDebitCard", description = "") @Valid @RequestBody(required = false) MovementDebitCard movementDebitCard,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }


  /**
   * POST /movement_debit_card/with_draw : Agregar Movimiento retiro
   *
   * @param movementDebitCard  (optional)
   * @return Movimiento Tarjeta débito creada exitosamente (status code 201)
   *         or Error en Request (status code 400)
   */
  @Operation(
      operationId = "createMovementWithDraw",
      summary = "Agregar Movimiento retiro",
      tags = { "Movimiento Tarjeta débito" },
      responses = {
          @ApiResponse(responseCode = "201", description = "Movimiento Tarjeta débito creada exitosamente"),
          @ApiResponse(responseCode = "400", description = "Error en Request")
      }
  )
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/movement_debit_card/with_draw",
      consumes = { "application/json" }
  )
  default ResponseEntity<Mono<Void>> createMovementWithDraw(
      @Parameter(name = "MovementDebitCard", description = "") @Valid @RequestBody(required = false) MovementDebitCard movementDebitCard,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }

}
