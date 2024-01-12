package com.springsecurity10;

import com.springsecurity10.model.*;
import com.springsecurity10.repository.*;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct
    public void init1() {
        initService.initData();
    }

    @Component @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final AccountsRepository accountsRepository;
        private final AccountTransactionsRepository accountTransactionsRepository;
        private final CardsRepository cardsRepository;
        private final ContactRepository contactRepository;
        private final CustomerRepository customerRepository;
        private final LoanRepository loanRepository;
        private final NoticeRepository noticeRepository;
        private final EntityManager em;

        public void initData() {
            // 현재 날짜 가져오기
            LocalDate currentDate = LocalDate.now();
            // 날짜 형식 지정 (옵션)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // n일 전 날짜 계산
            LocalDate sevenDaysAgo = currentDate.minusDays(7);
            LocalDate sixDaysAgo = currentDate.minusDays(6);
            LocalDate fiveDaysAgo = currentDate.minusDays(5);
            LocalDate fourDaysAgo = currentDate.minusDays(4);
            LocalDate twoDaysAgo = currentDate.minusDays(2);
            LocalDate oneDaysAgo = currentDate.minusDays(1);

            LocalDate oneMonthAgo = currentDate.minusMonths(1);
            LocalDate oneMonthLater = currentDate.plusMonths(1);



            Customer customer = new Customer("tmdgh717@naver.com","$2y$12$oRRbkNfwuR8ug4MlzH5FOeui.//1mkd.RsOAJMbykTSupVy.x/vb2","admin","010-6653-6258","승호");
            customer.setCreateDt(currentDate.format(formatter));

            Authority authority = new Authority();
            authority.setName("ROLE_ADMIN");

            Authority authority2 = new Authority();
            authority2.setName("ROLE_USER");

            em.persist(authority);
            em.persist(authority2);

            customer.setAuthorities(authority);
            customer.setAuthorities(authority2);

            customerRepository.save(customer);

            Accounts accounts = new Accounts(1865764534,1,"Savings","대구 북구 구암서로 41",String.valueOf(new Date(System.currentTimeMillis())));
            accountsRepository.save(accounts);

            AccountTransactions transactions7 = new AccountTransactions();
            transactions7.setTransactionId(java.util.UUID.randomUUID().toString());
            transactions7.setAccountNumber(1865764534);
            transactions7.setCustomerId(1);
            transactions7.setTransactionDt(Date.valueOf(sevenDaysAgo));
            transactions7.setTransactionsSummary("Coffee Shop");
            transactions7.setTransactionType("Withdrawal");
            transactions7.setTransactionAmt(30);
            transactions7.setClosingBalance(34500);
            transactions7.setCreateDt(String.valueOf(sevenDaysAgo));
            accountTransactionsRepository.save(transactions7);

            AccountTransactions transactions6 = new AccountTransactions();
            transactions6.setTransactionId(java.util.UUID.randomUUID().toString());
            transactions6.setAccountNumber(1865764534);
            transactions6.setCustomerId(1);
            transactions6.setTransactionDt(Date.valueOf(sixDaysAgo));
            transactions6.setTransactionsSummary("Uber");
            transactions6.setTransactionType("Withdrawal");
            transactions6.setTransactionAmt(100);
            transactions6.setClosingBalance(34400);
            transactions6.setCreateDt(String.valueOf(sixDaysAgo));
            accountTransactionsRepository.save(transactions6);

            AccountTransactions transactions5 = new AccountTransactions();
            transactions5.setTransactionId(java.util.UUID.randomUUID().toString());
            transactions5.setAccountNumber(1865764534);
            transactions5.setCustomerId(1);
            transactions5.setTransactionDt(Date.valueOf(fiveDaysAgo));
            transactions5.setTransactionsSummary("Self Deposit");
            transactions5.setTransactionType("Deposit");
            transactions5.setTransactionAmt(500);
            transactions5.setClosingBalance(34900);
            transactions5.setCreateDt(String.valueOf(fiveDaysAgo));
            accountTransactionsRepository.save(transactions5);

            AccountTransactions transactions4 = new AccountTransactions();
            transactions4.setTransactionId(java.util.UUID.randomUUID().toString());
            transactions4.setAccountNumber(1865764534);
            transactions4.setCustomerId(1);
            transactions4.setTransactionDt(Date.valueOf(fourDaysAgo));
            transactions4.setTransactionsSummary("Ebay");
            transactions4.setTransactionType("Withdrawal");
            transactions4.setTransactionAmt(600);
            transactions4.setClosingBalance(34300);
            transactions4.setCreateDt(String.valueOf(fourDaysAgo));
            accountTransactionsRepository.save(transactions4);

            AccountTransactions transactions2 = new AccountTransactions();
            transactions2.setTransactionId(java.util.UUID.randomUUID().toString());
            transactions2.setAccountNumber(1865764534);
            transactions2.setCustomerId(1);
            transactions2.setTransactionDt(Date.valueOf(twoDaysAgo));
            transactions2.setTransactionsSummary("OnlineTransfer");
            transactions2.setTransactionType("Deposit");
            transactions2.setTransactionAmt(700);
            transactions2.setClosingBalance(35000);
            transactions2.setCreateDt(String.valueOf(twoDaysAgo));
            accountTransactionsRepository.save(transactions2);

            AccountTransactions transactions1 = new AccountTransactions();
            transactions1.setTransactionId(java.util.UUID.randomUUID().toString());
            transactions1.setAccountNumber(1865764534);
            transactions1.setCustomerId(1);
            transactions1.setTransactionDt(Date.valueOf(oneDaysAgo));
            transactions1.setTransactionsSummary("Amazon.com");
            transactions1.setTransactionType("Withdrawal");
            transactions1.setTransactionAmt(100);
            transactions1.setClosingBalance(34900);
            transactions1.setCreateDt(String.valueOf(oneDaysAgo));
            accountTransactionsRepository.save(transactions1);

            Loans loans1 = new Loans();
            loans1.setCustomerId(1);
            loans1.setStartDt(Date.valueOf("2020-10-13"));
            loans1.setLoanType("Home");
            loans1.setTotalLoan(200000);
            loans1.setAmountPaid(50000);
            loans1.setOutstandingAmount(150000);
            loans1.setCreateDt("2020-10-13");
            loanRepository.save(loans1);

            Loans loans2 = new Loans();
            loans2.setCustomerId(1);
            loans2.setStartDt(Date.valueOf("2020-06-06"));
            loans2.setLoanType("Vehicle");
            loans2.setTotalLoan(40000);
            loans2.setAmountPaid(10000);
            loans2.setOutstandingAmount(30000);
            loans2.setCreateDt("2020-06-06");
            loanRepository.save(loans2);

            Loans loans3 = new Loans();
            loans3.setCustomerId(1);
            loans3.setStartDt(Date.valueOf("2018-02-14"));
            loans3.setLoanType("Home");
            loans3.setTotalLoan(50000);
            loans3.setAmountPaid(10000);
            loans3.setOutstandingAmount(40000);
            loans3.setCreateDt("2018-02-14");
            loanRepository.save(loans3);

            Loans loans4 = new Loans();
            loans4.setCustomerId(1);
            loans4.setStartDt(Date.valueOf("2018-02-14"));
            loans4.setLoanType("Personal");
            loans4.setTotalLoan(10000);
            loans4.setAmountPaid(3500);
            loans4.setOutstandingAmount(6500);
            loans4.setCreateDt("2018-02-14");
            loanRepository.save(loans4);

            Cards card1 = new Cards();
            card1.setCardNumber("4565XXXX4656");
            card1.setCustomerId(1);
            card1.setCardType("Credit");
            card1.setTotalLimit(10000);
            card1.setAmountUsed(500);
            card1.setAvailableAmount(9500);
            card1.setCreateDt(Date.valueOf(currentDate));
            cardsRepository.save(card1);

            Cards card2 = new Cards();
            card2.setCardNumber("3455XXXX8673");
            card2.setCustomerId(1);
            card2.setCardType("Credit");
            card2.setTotalLimit(7500);
            card2.setAmountUsed(600);
            card2.setAvailableAmount(6900);
            card2.setCreateDt(Date.valueOf(currentDate));
            cardsRepository.save(card2);

            Cards card3 = new Cards();
            card3.setCardNumber("2359XXXX9346");
            card3.setCustomerId(1);
            card3.setCardType("Credit");
            card3.setTotalLimit(20000);
            card3.setAmountUsed(4000);
            card3.setAvailableAmount(16000);
            card3.setCreateDt(Date.valueOf(currentDate));
            cardsRepository.save(card3);

            Notice notice1 = new Notice();
            notice1.setNoticeSummary("Home Loan Interest rates reduced");
            notice1.setNoticeDetails("Home loan interest rates are reduced as per the goverment guidelines. The updated rates will be effective immediately");
            notice1.setNoticeBegDt(Date.valueOf(oneMonthAgo));
            notice1.setNoticeEndDt(Date.valueOf(oneMonthLater));
            notice1.setCreateDt(Date.valueOf(currentDate));
            notice1.setUpdateDt(null);
            noticeRepository.save(notice1);

            Notice notice2 = new Notice();
            notice2.setNoticeSummary("Net Banking Offers");
            notice2.setNoticeDetails("Customers who will opt for Internet banking while opening a saving account will get a $50 amazon voucher");
            notice2.setNoticeBegDt(Date.valueOf(oneMonthAgo));
            notice2.setNoticeEndDt(Date.valueOf(oneMonthLater));
            notice2.setCreateDt(Date.valueOf(currentDate));
            notice2.setUpdateDt(null);
            noticeRepository.save(notice2);

            Notice notice3 = new Notice();
            notice3.setNoticeSummary("Mobile App Downtime");
            notice3.setNoticeDetails("The mobile application of the EazyBank will be down from 2AM-5AM on 12/05/2020 due to maintenance activities");
            notice3.setNoticeBegDt(Date.valueOf(oneMonthAgo));
            notice3.setNoticeEndDt(Date.valueOf(oneMonthLater));
            notice3.setCreateDt(Date.valueOf(currentDate));
            notice3.setUpdateDt(null);
            noticeRepository.save(notice3);

            Notice notice4 = new Notice();
            notice4.setNoticeSummary("E Auction notice");
            notice4.setNoticeDetails("There will be a e-auction on 12/08/2020 on the Bank website for all the stubborn arrears.Interested parties can participate in the e-auction");
            notice4.setNoticeBegDt(Date.valueOf(oneMonthAgo));
            notice4.setNoticeEndDt(Date.valueOf(oneMonthLater));
            notice4.setCreateDt(Date.valueOf(currentDate));
            notice4.setUpdateDt(null);
            noticeRepository.save(notice4);

            Notice notice5 = new Notice();
            notice5.setNoticeSummary("Launch of Millennia Cards");
            notice5.setNoticeDetails("Millennia Credit Cards are launched for the premium customers of EazyBank. With these cards, you will get 5% cashback for each purchase");
            notice5.setNoticeBegDt(Date.valueOf(oneMonthAgo));
            notice5.setNoticeEndDt(Date.valueOf(oneMonthLater));
            notice5.setCreateDt(Date.valueOf(currentDate));
            notice5.setUpdateDt(null);
            noticeRepository.save(notice5);

            Notice notice6 = new Notice();
            notice6.setNoticeSummary("COVID-19 Insurance");
            notice6.setNoticeDetails("EazyBank launched an insurance policy which will cover COVID-19 expenses. Please reach out to the branch for more details");
            notice6.setNoticeBegDt(Date.valueOf(oneMonthAgo));
            notice6.setNoticeEndDt(Date.valueOf(oneMonthLater));
            notice6.setCreateDt(Date.valueOf(currentDate));
            notice6.setUpdateDt(null);
            noticeRepository.save(notice6);
        }
    }
}
