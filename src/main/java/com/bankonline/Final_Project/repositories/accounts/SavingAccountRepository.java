package com.bankonline.Final_Project.repositories.accounts;

import com.bankonline.Final_Project.models.accounts.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingAccountRepository extends JpaRepository<SavingsAccount, Long> {
}
