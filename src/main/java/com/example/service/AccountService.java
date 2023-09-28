package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AccountService {
    
    AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository a){
        this.accountRepository = a;
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
         !accountRepository.existsByUsername(acc.getUsername())
        ) {
            return accountRepository.save(acc);
        }
        return null;
     }


     public Account login(Account acc){
        if( accountRepository.existsByUsername(acc.getUsername())){
            Account db = accountRepository.findByUsername(acc.getUsername());
            if(db.getUsername().equals(acc.getUsername()) &&
                db.getPassword().equals(acc.getPassword()))
                    return db;
        }
        return null;
        
     }

}
