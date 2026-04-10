package com.Dinesh.Hospital.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "patients")
public class Patient {
    @Id
    private String id;

    @NotBlank
    private String Name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone;

    private String bloodGroup;
    private int age;
}
