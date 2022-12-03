package com.bankonline.Final_Project.models.accounts;

import com.bankonline.Final_Project.embedables.Money;
import com.bankonline.Final_Project.models.users.AccountHolder;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
public class CheckingAccount extends Account{
    @Embedded
    private Money minimumBalance = new Money(BigDecimal.valueOf(250L));
    private BigDecimal monthlyMaintenanceFee = new BigDecimal(12);
    private LocalDate lastInterestDay;

    public CheckingAccount() {
    }

    public CheckingAccount(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, LocalDate creationDate, Integer secretKey, Money minimumBalance, BigDecimal monthlyMaintenanceFee, LocalDate lastInterestDay) {
        super(balance, primaryOwner, secondaryOwner, creationDate, secretKey);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.lastInterestDay = lastInterestDay;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public LocalDate getLastInterestDay() {
        return lastInterestDay;
    }

    public void setLastInterestDay(LocalDate lastInterestDay) {
        this.lastInterestDay = lastInterestDay;
    }

    public Money checkMonthlyMaintenanceFee(){
        while (Period.between(getLastInterestDay().plusMonths(1), LocalDate.now()).getMonths() >= 0){
            setBalance((getBalance().decreaseAmount(monthlyMaintenanceFee)));
            setLastInterestDay(lastInterestDay.plusMonths(1));
        }
        setLastInterestDay(LocalDate.now());
        return getBalance();
    }

}
