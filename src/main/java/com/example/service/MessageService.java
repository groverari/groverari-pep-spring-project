package com.example.service;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageService {
    
    MessageRepository messageRepository;
    AccountRepository accountRepository;
    
    @Autowired
    public MessageService(MessageRepository m, AccountRepository a){
        this.accountRepository = a;
        this.messageRepository = m;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message addNewMessage(Message m){
        if(m.getMessage_text().length() > 0 && 
        m.getMessage_text().length() < 255 &&
        accountRepository.existsById(m.getPosted_by()) ){
            return messageRepository.save(m);
        }
        else return null;
    }

    public Message getMessageById(int id){
        Optional<Message> opt =  messageRepository.findById(id);
        return opt.isPresent()? opt.get() : null;
    }

    public int deleteMessageById(int id){
        if(!messageRepository.existsById(id)) return 0;
        int before = (int)messageRepository.count();
        messageRepository.deleteById(id);
        int after = (int)messageRepository.count();
        return before-after;
    }

    public int updateMessage(int id, String message_text){
        if(message_text.length() ==0 || message_text.length() >  255) return 0;

        Optional<Message> mess = messageRepository.findById(id);
        if(mess.isPresent()){
            Message m = mess.get();
            m.setMessage_text(message_text);
            messageRepository.save(m);
            return 1;
        }
        return 0;
    }

    public List<Message> getAllMessagesById(int id){
        return messageRepository.messagesPostedBy(id);
    }




}
