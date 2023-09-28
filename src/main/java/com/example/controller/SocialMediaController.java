package com.example.controller;
import com.example.entity.Message;
import com.example.entity.Account;
import com.example.service.MessageService;
import com.example.service.AccountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    MessageService messageService; 
    AccountService accountService;

    @Autowired
    public SocialMediaController(MessageService m, AccountService a){
        this.messageService = m;
        this.accountService = a ;
    }
    @PostMapping("register")
    public ResponseEntity<Account> register(@RequestBody Account a){
        Account output = accountService.register(a);
        if(output != null){
            return ResponseEntity.status(200).body(output);
        }
        else return ResponseEntity.status(409).body(null);
        
    }

    @PostMapping("login")
    public ResponseEntity<Account>  login(@RequestBody Account a){
        Account output  = accountService.login(a);
        if(output != null){
            return ResponseEntity.status(200).body(output);
        }
        else return ResponseEntity.status(401).body(null);
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

    @GetMapping("messages/{message_id}")
    public ResponseEntity<Message> getMessage(@PathVariable int message_id){
        return ResponseEntity.status(200).body(messageService.getMessageById(message_id));
    }

    @DeleteMapping("messages/{message_id}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable int message_id){
        int output = messageService.deleteMessageById(message_id);
        return ResponseEntity.status(200).body(output);
    }

    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessage(@PathVariable int message_id, @RequestBody Message m){
        int output = messageService.updateMessage(message_id, m.getMessage_text());
        if(output >  0) return ResponseEntity.status(200).body(output);
        return ResponseEntity.status(400).body(null);

    }

    @GetMapping("accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByPosted(@PathVariable int account_id){
        return ResponseEntity.status(200).body(messageService.getAllMessagesById(account_id));
    }
}
