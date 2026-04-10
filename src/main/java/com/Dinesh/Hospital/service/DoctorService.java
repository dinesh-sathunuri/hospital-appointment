package com.Dinesh.Hospital.service;

import com.Dinesh.Hospital.enums.DoctorStatus;
import com.Dinesh.Hospital.exceptions.ResourceNotFoundException;
import com.Dinesh.Hospital.models.Doctor;
import com.Dinesh.Hospital.repositories.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    DoctorRepo doctorRepo;

    @Cacheable(value="doctors")
    public List<Doctor> getAllDoctors()
    {
        return doctorRepo.findAll();
    }

    @Cacheable(value="doctors",key="#specialization")
    public List<Doctor> getDoctorsBySpecialization(String specialization)
    {
        return doctorRepo.findBySpecialization(specialization);
    }

    public List<Doctor> getAvailableDoctors()
    {
        return doctorRepo.findByStatus(DoctorStatus.AVAILABLE);
    }

    public Doctor getDoctorById(String id)
    {
        return doctorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found by Id"+id));
    }

    @CacheEvict(value="Doctors",allEntries=true)
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepo.save(doctor);
    }
    @CacheEvict(value = "doctors", allEntries = true)
    public Doctor updateDoctor(String id, Doctor doctor) {
        if (!doctorRepo.existsById(id))
            throw new ResourceNotFoundException("Doctor not found with id: " + id);
        doctor.setId(id);
        return doctorRepo.save(doctor);
    }

    @CacheEvict(value = "doctors", allEntries = true)
    public void deleteDoctor(String id) {
        if (!doctorRepo.existsById(id))
            throw new ResourceNotFoundException("Doctor not found with id: " + id);
        doctorRepo.deleteById(id);
    }

}
