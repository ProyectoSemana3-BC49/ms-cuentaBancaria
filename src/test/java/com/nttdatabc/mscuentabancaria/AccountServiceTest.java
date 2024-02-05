package com.nttdatabc.mscuentabancaria;

import com.nttdatabc.mscuentabancaria.model.Account;
import com.nttdatabc.mscuentabancaria.model.enums.TypeAccountBank;
import com.nttdatabc.mscuentabancaria.model.enums.TypeCustomer;
import com.nttdatabc.mscuentabancaria.model.response.CustomerExt;
import com.nttdatabc.mscuentabancaria.repository.AccountRepository;
import com.nttdatabc.mscuentabancaria.service.AccountServiceImpl;
import com.nttdatabc.mscuentabancaria.service.api.CustomerApiExtImpl;
import com.nttdatabc.mscuentabancaria.service.interfaces.CreditApiExt;
import com.nttdatabc.mscuentabancaria.utils.AccountValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.nttdatabc.mscuentabancaria.utils.AccountValidator.verifyCustomerExists;
import static com.nttdatabc.mscuentabancaria.utils.Utilitarios.generateUuid;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountServiceTest {
  @Mock
  private AccountRepository accountRepository;
  @Mock
  private CustomerApiExtImpl customerApiExtImpl;
  @Mock
  private CreditApiExt creditApiExt;
  @Mock
  private AccountValidator accountValidator;

  @InjectMocks
  private AccountServiceImpl accountService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void obtenerTodasLasCuentasServicio_success() {
    // Arrange
    List<Account> cuentas = Arrays.asList(new Account(), new Account());
    when(accountRepository.findAll()).thenReturn(Flux.fromIterable(cuentas));

    // Act
    Flux<Account> flujoCuentas = accountService.getAllAccountsService();

    // Assert
    StepVerifier.create(flujoCuentas)
        .expectNextCount(cuentas.size())
        .verifyComplete();
  }



}
