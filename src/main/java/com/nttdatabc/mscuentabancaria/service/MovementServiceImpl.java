package com.nttdatabc.mscuentabancaria.service;

import static com.nttdatabc.mscuentabancaria.utils.Constantes.FEE_LIMIT_TRANSACTION;
import static com.nttdatabc.mscuentabancaria.utils.Constantes.TRANSACTION_FEE_FREE;
import static com.nttdatabc.mscuentabancaria.utils.MovementValidator.*;

import com.nttdatabc.mscuentabancaria.model.Account;
import com.nttdatabc.mscuentabancaria.model.Movement;
import com.nttdatabc.mscuentabancaria.model.enums.TypeMovement;
import com.nttdatabc.mscuentabancaria.repository.MovementRepository;
import com.nttdatabc.mscuentabancaria.service.interfaces.MovementService;
import com.nttdatabc.mscuentabancaria.utils.Utilitarios;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;




@Service
public class MovementServiceImpl implements MovementService {
  @Autowired
  private MovementRepository movementRepository;
  @Autowired
  private AccountServiceImpl accountServiceImpl;

  @Override
  public Mono<Void> createMovementDepositService(Movement movement) {
    return validateMovementNoNulls(movement)
        .then(validateMovementEmpty(movement))
        .then(verifyValues(movement))
        .then(validateAccountRegister(movement.getAccountId(), accountServiceImpl))
        .then(Mono.just(movement))
        .flatMap(validated -> getMovementsByAccountIdService(validated.getAccountId())
            .collectList()
            .zipWith(accountServiceImpl.getAccountByIdService(validated.getAccountId()))
            .flatMap(tuple -> {
              List<Movement> listMovementByAccount = tuple.getT1();
              Account accountFound = tuple.getT2();

              validated.setId(Utilitarios.generateUuid());
              validated.setFecha(LocalDateTime.now().toString());
              validated.setTypeMovement(TypeMovement.RETIRO.toString());
              validated.setFee(BigDecimal.valueOf(TRANSACTION_FEE_FREE));
              validateMovements(accountFound, listMovementByAccount, validated);

              validateCurrentBalance(accountServiceImpl, validated);

              return Mono.just(validated);
            }).flatMap(movement1 -> movementRepository.save(movement1))
        .flatMap(movementMono -> accountServiceImpl.getAccountByIdService(movementMono.getAccountId()))
                .flatMap(accountUpdate -> {
                  //update mount in account bank
                  double mountUpdate = movement.getMount().doubleValue();
                  if (movement.getFee().doubleValue() == FEE_LIMIT_TRANSACTION) {
                    double feeTransaction = movement.getMount().doubleValue() * FEE_LIMIT_TRANSACTION;
                    mountUpdate = mountUpdate - feeTransaction;
                  }
                  accountUpdate.setCurrentBalance(accountUpdate.getCurrentBalance().add(BigDecimal.valueOf(mountUpdate)));
                  return Mono.just(accountUpdate);
                }).flatMap(account -> accountServiceImpl.updateAccountServide(account))
            .then());
  }

  @Override
  public Mono<Void> createWithDrawService(Movement movement) {
    return validateMovementNoNulls(movement)
        .then(validateMovementEmpty(movement))
        .then(verifyValues(movement))
        .then(validateAccountRegister(movement.getAccountId(), accountServiceImpl))
        .then(Mono.just(movement))
        .flatMap(validated -> getMovementsByAccountIdService(validated.getAccountId())
            .collectList()
            .zipWith(accountServiceImpl.getAccountByIdService(validated.getAccountId()))
            .flatMap(tuple -> {
              List<Movement> listMovementByAccount = tuple.getT1();
              Account accountFound = tuple.getT2();

              validated.setId(Utilitarios.generateUuid());
              validated.setFecha(LocalDateTime.now().toString());
              validated.setTypeMovement(TypeMovement.RETIRO.toString());
              validated.setFee(BigDecimal.valueOf(TRANSACTION_FEE_FREE));
              validateMovements(accountFound, listMovementByAccount, validated);

              validateCurrentBalance(accountServiceImpl, validated);

              return Mono.just(validated);
            }).flatMap(movement1 -> movementRepository.save(movement1))
            .flatMap(movementMono -> accountServiceImpl.getAccountByIdService(movementMono.getAccountId()))
            .flatMap(accountUpdate -> {
              //update mount in account bank
              double mountUpdate = movement.getMount().doubleValue();
              if (movement.getFee().doubleValue() == FEE_LIMIT_TRANSACTION) {
                double feeTransaction = movement.getMount().doubleValue() * FEE_LIMIT_TRANSACTION;
                mountUpdate = mountUpdate + feeTransaction;
              }
              accountUpdate.setCurrentBalance(accountUpdate.getCurrentBalance().subtract(BigDecimal.valueOf(mountUpdate)));
              return Mono.just(accountUpdate);
            }).flatMap(account -> accountServiceImpl.updateAccountServide(account))
            .then());
  }

  @Override
  public Flux<Movement> getMovementsByAccountIdService(String accountId) {
    return validateAccountRegister(accountId, accountServiceImpl)
        .thenMany(movementRepository.findByAccountId(accountId));
  }

  @Override
  public Mono<Void> createTransferService(Movement movement) {
    return validateMovementTransferNoNulls(movement)
        .then(validateMovementEmpty(movement))
        .then(validateMovementTransferEmpty(movement))
        .then(verifyValues(movement))
        .then(validateAccountRegister(movement.getAccountId(), accountServiceImpl))
        .then(validateAccountDestination(movement.getDestination(), accountServiceImpl))
        .then(validateCurrentBalance(accountServiceImpl, movement))
        .then(Mono.just(movement))
        .map(movementMono -> {
          movementMono.setId(Utilitarios.generateUuid());
          movementMono.setFecha(LocalDateTime.now().toString());
          movementMono.setTypeMovement(TypeMovement.TRANSFER.toString());
          movementMono.setFee(BigDecimal.valueOf(TRANSACTION_FEE_FREE));
          return movementMono;
        }).flatMap(movementRepository::save)
        .then(Mono.just(movement))
        .zipWith(accountServiceImpl.getAccountByIdService(movement.getAccountId()))
        .flatMap(objectsZip -> {
          Account accountOrigin = objectsZip.getT2();
          Movement movementZip = objectsZip.getT1();
          accountOrigin.setCurrentBalance(accountOrigin.getCurrentBalance().subtract((movementZip.getMount())));
          return accountServiceImpl.updateAccountServide(accountOrigin);
        }).then(Mono.just(movement))
        .zipWith(accountServiceImpl.getAccountByIdService(movement.getDestination()))
        .flatMap(objectsZip -> {
          Account accountDestination = objectsZip.getT2();
          Movement movementZip = objectsZip.getT1();
          accountDestination.setCurrentBalance(accountDestination.getCurrentBalance().add((movementZip.getMount())));
          return accountServiceImpl.updateAccountServide(accountDestination);
        }).then();
  }
}
