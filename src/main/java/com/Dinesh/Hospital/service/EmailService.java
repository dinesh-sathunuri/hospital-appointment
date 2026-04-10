package com.Dinesh.Hospital.service;


import com.Dinesh.Hospital.models.Appointment;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendAppointmentConfirmation(String toEmail, Appointment appointment) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("Appointment Confirmation - Hospital System");
            helper.setText(buildEmailBody(appointment), true); // true = HTML

            mailSender.send(message);
            System.out.println("Confirmation email sent to: " + toEmail);

        } catch (MessagingException e) {
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }

    public void sendAppointmentCancellation(String toEmail, String appointmentId) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("Appointment Cancelled - Hospital System");
            helper.setText("<h3>Your appointment has been cancelled.</h3>"
                    + "<p>Appointment ID: <b>" + appointmentId + "</b></p>"
                    + "<p>Please book a new appointment if needed.</p>", true);

            mailSender.send(message);

        } catch (MessagingException e) {
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }

    private String buildEmailBody(Appointment appointment) {
        return "<h2>Appointment Confirmed!</h2>"
                + "<p>Dear Patient,</p>"
                + "<p>Your appointment has been successfully booked.</p>"
                + "<table>"
                + "<tr><td><b>Appointment ID:</b></td><td>" + appointment.getId() + "</td></tr>"
                + "<tr><td><b>Doctor ID:</b></td><td>" + appointment.getDoctorId() + "</td></tr>"
                + "<tr><td><b>Date:</b></td><td>" + appointment.getDate() + "</td></tr>"
                + "<tr><td><b>Time Slot:</b></td><td>" + appointment.getTimeSlot() + "</td></tr>"
                + "<tr><td><b>Status:</b></td><td>" + appointment.getStatus() + "</td></tr>"
                + "</table>"
                + "<br><p>Thank you for choosing our hospital.</p>";
    }
}