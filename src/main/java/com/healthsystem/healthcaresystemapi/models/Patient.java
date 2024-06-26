package com.healthsystem.healthcaresystemapi.models;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends Person{
    private long id;
    private long personId;
    private String medicalHistory;
    private String currentHealthStatus;
}
