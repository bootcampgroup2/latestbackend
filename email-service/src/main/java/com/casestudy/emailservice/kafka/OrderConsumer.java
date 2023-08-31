package com.casestudy.emailservice.kafka;


import com.casestudy.basedomain.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Service
public class OrderConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration config;


    @KafkaListener(topics = "${spring.kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
    public  void  consume(OrderEvent event){
        if(event.getIsInstantEmail()){
            LOGGER.info(String.format("Order event recieved in email service => %s",event.toString()));
            simpleEmail(event.getEmail(),event,"Order");
            System.out.println("Called simpleemail!!!");
        }else{
            System.out.println("Send email to user subscribed to an event");
        }
//        LOGGER.info(String.format("Order event recieved in email service => %s",event.toString()));
//        try {
//            // Sleep for 1 minute (60,000 milliseconds)
//            Thread.sleep(60000);
//        } catch (InterruptedException e) {
//            // Handle the InterruptedException
//            // For example, you can log the interruption and decide how to proceed
//            Thread.currentThread().interrupt();  // Reset the interrupted status
//        }
//        simpleEmail("shivanii2607@gmail.com",event,"Order");
//        System.out.println("Called simpleemail!!!");

    }

    public void simpleEmail(String toEmail,OrderEvent body,String Subject){



       /* Template t = config.getTemplate("email-template.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        // add attachment
        helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
*/
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ammu16042001@gmail.com");
        message.setTo(toEmail);
        message.setText(body.toString());
        message.setSubject(message.getSubject());

        mailSender.send(message);
        System.out.println("Mail Send....");



    }
}
