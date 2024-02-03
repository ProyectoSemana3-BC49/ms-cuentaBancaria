package com.nttdatabc.mscuentabancaria.service;

import com.nttdatabc.mscuentabancaria.model.BalanceAccounts;
import com.nttdatabc.mscuentabancaria.model.Movement;
import com.nttdatabc.mscuentabancaria.model.SummaryAccountBalance;
import com.nttdatabc.mscuentabancaria.repository.AccountRepository;
import com.nttdatabc.mscuentabancaria.repository.MovementRepository;
import com.nttdatabc.mscuentabancaria.service.interfaces.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.nttdatabc.mscuentabancaria.utils.AccountValidator.verifyCustomerExists;
import static com.nttdatabc.mscuentabancaria.utils.MovementValidator.validateAccountRegister;

@Service
public class ReportServiceImpl implements ReportService {
  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private MovementRepository movementRepository;
  @Autowired
  private CustomerApiExtImpl customerApiExtImpl;
  @Autowired
  private AccountServiceImpl accountServiceImpl;
  @Override
  public Mono<BalanceAccounts> getBalanceAverageService(String customerId) {
    return verifyCustomerExists(customerId, customerApiExtImpl)
        .flatMap(customerExt -> {
          BalanceAccounts balanceAccounts = new BalanceAccounts();
          balanceAccounts.setCustomerId(customerId);
          LocalDate currentDate = LocalDate.now();
          int daysInMonth = currentDate.lengthOfMonth();
          int year = LocalDate.now().getYear();
          int month = LocalDate.now().getMonthValue();
          String dateFilter = String.format("%d-%s", year, String.valueOf(month).length() == 1 ? "0" + month : month);
          return accountRepository.findByCustomerId(customerId)
              .flatMap(account -> movementRepository.findByAccountId(account.getId())
                  .filter(movement -> movement.getFecha().contains(dateFilter))
                  .collectList()
                  .map(movementsInCurrentMonth -> {
                    double totalBalance = movementsInCurrentMonth.stream()
                        .mapToDouble(movement -> movement.getMount().doubleValue())
                        .sum();

                    BigDecimal averageDailyBalance = BigDecimal.valueOf(totalBalance / daysInMonth);

                    SummaryAccountBalance summaryAccountBalance = new SummaryAccountBalance();
                    summaryAccountBalance.setAccountId(account.getId());
                    summaryAccountBalance.setBalanceAvg(averageDailyBalance);

                    return summaryAccountBalance;
                  })).collectList()
              .doOnSuccess(summaryAccountBalances -> balanceAccounts.setSummaryAccounts(summaryAccountBalances))
              .thenReturn(balanceAccounts);
        });

}

  @Override
  public Flux<Movement> getMovementsWithFee(String accountId) {
    int year = LocalDate.now().getYear();
    int mounth = LocalDate.now().getMonthValue();
    String dateFilter = String.format("%d-%s", year, String.valueOf(mounth).length() == 1 ? "0" + mounth : mounth);

    return validateAccountRegister(accountId, accountServiceImpl)
        .thenMany(movementRepository.findByAccountId(accountId))
        .filter(movement -> movement.getFee().doubleValue() > 0)
        .filter(movement -> movement.getFecha().contains(dateFilter));


  }
}
