package com.Dinesh.Hospital.kafka;

import com.Dinesh.Hospital.models.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AppointmentProducer {

    private static final String TOPIC = "appointment-booked";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendAppointmentEvent(Appointment appointment) {
        try {
            String message = "New appointment booked - ID: " + appointment.getId()
                    + " | Patient: " + appointment.getPatientId()
                    + " | Doctor: " + appointment.getDoctorId()
                    + " | Date: " + appointment.getDate()
                    + " | Status: " + appointment.getStatus();

            kafkaTemplate.send(TOPIC, message);
            System.out.println("Kafka event sent: " + message);
        } catch (Exception e) {
            System.out.println("Kafka send failed: " + e.getMessage());
        }
    }
}