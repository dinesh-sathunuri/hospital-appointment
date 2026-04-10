package com.Dinesh.Hospital.service;

import com.Dinesh.Hospital.enums.AppointmentStatus;
import com.Dinesh.Hospital.exceptions.ResourceNotFoundException;
import com.Dinesh.Hospital.kafka.AppointmentProducer;
import com.Dinesh.Hospital.models.Appointment;
import com.Dinesh.Hospital.repositories.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private AppointmentProducer appointmentProducer;

    public List<Appointment> getAllAppoinments(){
        return appointmentRepo.findAll();
    }

    public Appointment getAppointmentById(String id) {
        return appointmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
    }

    public List<Appointment> getAppointmentsByPatient(String patientId) {
        return appointmentRepo.findByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsByDoctor(String doctorId) {
        return appointmentRepo.findByDoctorId(doctorId);
    }

    public Appointment bookAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.PENDING);
        Appointment saved = appointmentRepo.save(appointment);

        try {
            appointmentProducer.sendAppointmentEvent(saved);
        } catch (Exception e) {
            System.out.println("Kafka event failed: " + e.getMessage());
        }

        return saved;
    }

    public Appointment updateStatus(String id, AppointmentStatus status) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
        appointment.setStatus(status);
        return appointmentRepo.save(appointment);
    }

    public void cancelAppointment(String id) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepo.save(appointment);
    }





}
