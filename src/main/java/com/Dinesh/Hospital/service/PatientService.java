package com.Dinesh.Hospital.service;

import com.Dinesh.Hospital.exceptions.ResourceNotFoundException;
import com.Dinesh.Hospital.models.Patient;
import com.Dinesh.Hospital.repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;

    public List<Patient> getAllPatients(){
        return patientRepo.findAll();
    }

    public Patient getPatientById(String id)
    {
        return patientRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient Not Found"+id));
    }

    public Patient createPatient(Patient patient)
    {
        return patientRepo.save(patient);
    }

    public Patient updatePatient(String id,Patient patient)
    {
        if(!patientRepo.existsById(id))
        throw new ResourceNotFoundException("Patient not found with id:"+id);
        patient.setId(id);
        return patientRepo.save(patient);
    }

    public void deletePatient(String id)
    {
        if(!patientRepo.existsById(id))
            throw new ResourceNotFoundException("Patient not found with id:"+id);
        patientRepo.deleteById(id);
    }



}
