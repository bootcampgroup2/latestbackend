
package com.kafka;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.RegisterEvent;
import com.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserSignUpProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserSignUpProducer.class);
    private final KafkaTemplate<String, RegisterEvent> kafkaTemplate;
    private static final String TOPIC_NAME = "user_topics";

    public UserSignUpProducer(KafkaTemplate<String, RegisterEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendSignInMessage(User user) {

        RegisterEvent event = new RegisterEvent();
        event.setMessage("User with ID " + user.getEmail() + " has signed up.");
        event.setEmail(user.getEmail());
//        // Serialize the message object to JSON
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonMessage;
//        try {
//            jsonMessage = objectMapper.writeValueAsString(event);
//        } catch (JsonProcessingException e) {
//            // Handle JSON serialization error
//            LOGGER.error("Error serializing message to JSON: " + e.getMessage());
//            return; // Exit the method if there's an error
//        }
//        LOGGER.info("UserSignin => %models", jsonMessage);
////        String message = "User with ID " + user.getEmail() + " has signed in.";
//        kafkaTemplate.send(TOPIC_NAME, jsonMessage);
////        System.out.println("Produced message for email: " + message);
        Message<RegisterEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, TOPIC_NAME)
                .build();
        System.out.println(message);
        kafkaTemplate.send(message);
    }
}