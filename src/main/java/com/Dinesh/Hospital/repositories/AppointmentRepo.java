package com.Dinesh.Hospital.repositories;

import com.Dinesh.Hospital.enums.AppointmentStatus;
import com.Dinesh.Hospital.models.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AppointmentRepo extends MongoRepository<Appointment,String> {
    List<Appointment> findByPatientId(String patientId);
    List<Appointment> findByDoctorId(String doctorId);
    List<Appointment> findByStatus(AppointmentStatus status);
}
