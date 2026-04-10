package com.Dinesh.Hospital.repositories;

import com.Dinesh.Hospital.enums.DoctorStatus;
import com.Dinesh.Hospital.models.Doctor;
import org.springframework.data.domain.Limit;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DoctorRepo extends MongoRepository<Doctor,String> {
    List<Doctor> findBySpecialization(String specialization);
    List<Doctor> findByStatus(DoctorStatus status);
}
