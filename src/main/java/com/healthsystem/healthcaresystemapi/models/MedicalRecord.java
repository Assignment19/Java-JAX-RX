package com.healthsystem.healthcaresystemapi.models;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecord {
    private long id;
    private String diseases;
    private String treatments;
    private long patientId;
}
