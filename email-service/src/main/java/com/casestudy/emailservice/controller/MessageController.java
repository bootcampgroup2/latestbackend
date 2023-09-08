package com.casestudy.emailservice.controller;

import com.casestudy.emailservice.kafka.MessageService;
import com.casestudy.emailservice.models.Ordermail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/{email}")
    public List<Ordermail> getUnreadMessages(@PathVariable String email) {
        return messageService.getUnreadMessagesByEmail(email);
    }
}
