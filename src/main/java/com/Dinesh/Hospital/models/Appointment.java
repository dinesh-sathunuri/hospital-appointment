package com.Dinesh.Hospital.models;

import com.Dinesh.Hospital.enums.AppointmentStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "appointment")
public class Appointment {

    @Id
    private String id;

    @NotBlank(message = "Patient ID is required")
    private String patientId;

    @NotBlank(message = "Doctor Id is required")
    private String doctorId;

    @NotBlank(message = "Date is required")
    private String Date;

    private String timeSlot;
    private AppointmentStatus status=AppointmentStatus.PENDING;
    private String notes;
}
