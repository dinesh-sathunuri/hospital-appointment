package com.Dinesh.Hospital.models;

import com.Dinesh.Hospital.enums.DoctorStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "doctors")
public class Doctor {
    @Id
    private String id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Specialization is required")
    private String specialization;
    private int experience;
    private String phone;
    private DoctorStatus status=DoctorStatus.AVAILABLE;

}
