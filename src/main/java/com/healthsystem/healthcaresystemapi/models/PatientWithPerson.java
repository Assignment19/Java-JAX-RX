package com.healthsystem.healthcaresystemapi.models;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientWithPerson {
    private Person person;
    private long id;
    private String medicalHistory;
    private String currentHealthStatus;
}
