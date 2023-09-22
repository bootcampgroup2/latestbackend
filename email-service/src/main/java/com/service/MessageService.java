package com.service;

import com.model.Ordermail;
import com.repository.OrdermailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Service
public class MessageService {
    @Autowired
    private OrdermailRepository orderMailRepository;

    public List<Ordermail> getUnreadMessagesByEmail(String email) {
        return orderMailRepository.findByEmail(email);
    }
}
