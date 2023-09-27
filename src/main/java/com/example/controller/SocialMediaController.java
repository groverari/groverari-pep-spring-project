package com.example.controller;
import com.example.entity.Message;
import com.example.entity.Account;
import com.example.service.MessageService;
import com.example.service.AccountService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
@RequestMapping("/")
public class SocialMediaController {
    @Autowired
    MessageService messageService; 
    @Autowired
    AccountService accountService;

    @PostMapping("register")
    public ResponseEntity<Account> register(@RequestBody Account a){
        Account output = accountService.register(a);
        if(output != null){
            return ResponseEntity.status(200).body(output);
        }
        else return ResponseEntity.status(400).body(null);
        
    }

    @PostMapping("login")
    public ResponseEntity<Account>  login(@RequestBody Account a){
        Account output  = accountService.login(a);
        if(output != null){
            return ResponseEntity.status(200).body(output);
        }
        else return ResponseEntity.status(400).body(null);
    }

    @GetMapping("messages")
    public ResponseEntity<List<Message>> allMessages(){
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }

    @PostMapping("messages")
    public ResponseEntity<Message> newMessage(@RequestBody Message m){
        Message output = messageService.addNewMessage(m);
        if(output != null){
            return ResponseEntity.status(200).body(output);
        }
        else return ResponseEntity.status(400).body(null);
    }
    

}
