package com.Dinesh.Hospital.repositories;

import com.Dinesh.Hospital.models.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PatientRepo extends MongoRepository<Patient,String> {
    Optional<Patient> findByEmail(String email);
}
