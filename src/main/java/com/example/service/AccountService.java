package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    
    AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    /*
     * Register a new account
     * @param Account acc
     * check if password is > 4 chars long
     * check if username is not null
     * check  if username is not already registered
     * return account with id
     */
    
     public Account register(Account acc){
        if(acc.getPassword().length() >4 && 
        acc.getUsername().length()> 0 && 
        accountRepository.findByUsername(acc.getUsername()) == null) 
            return accountRepository.save(acc);
        else return null;
     }


     public Account login(Account acc){
        Account b = accountRepository.findByUsername(acc.getUsername());
        if(b != null && acc.equals(b)) return b;
        else return null;
     }

}
