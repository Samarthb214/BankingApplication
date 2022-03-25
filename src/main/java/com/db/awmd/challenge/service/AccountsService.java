package com.db.awmd.challenge.service;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.domain.TransferDomain;
import com.db.awmd.challenge.repository.AccountsRepository;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountsService {

  @Getter
  private final AccountsRepository accountsRepository;

  @Autowired
  public AccountsService(AccountsRepository accountsRepository) {
    this.accountsRepository = accountsRepository;
  }

  public void createAccount(Account account) {
    this.accountsRepository.createAccount(account);
  }

  public Account getAccount(String accountId) {
    return this.accountsRepository.getAccount(accountId);
  }
  
  
  @Transactional  
	public void transferBalances(TransferDomain transfer) throws Exception {
		
	  
	  Account accountFrom = getAccount(transfer.getAccountFromId().toString());
		
		Account accountTo = getAccount(transfer.getAccountToId().toString());
				
		if(accountFrom.getBalance().compareTo(transfer.getAmount()) < 0) {
			log.info("Source Account with id:" + accountFrom.getAccountId() + " does not have enough balance to transfer.");
		}
		else {
		accountFrom.setBalance(accountFrom.getBalance().subtract(transfer.getAmount()));
		accountTo.setBalance(accountTo.getBalance().add(transfer.getAmount()));
	}
  }
}