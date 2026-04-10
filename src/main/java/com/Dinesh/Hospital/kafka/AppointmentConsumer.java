package com.Dinesh.Hospital.kafka;

import com.Dinesh.Hospital.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AppointmentConsumer {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "appointment-booked",groupId = "hospital-group")
    public void consumeAppoinmentEvent(String message){
        System.out.println("Kafka event received: " + message);
    }
}
