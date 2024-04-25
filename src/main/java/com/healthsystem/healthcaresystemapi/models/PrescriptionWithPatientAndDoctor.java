package com.healthsystem.healthcaresystemapi.models;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionWithPatientAndDoctor {
    private long id;
    private Date date;
    private String medication;
    private String diagnoses;
    private PatientWithPerson patient;
    private DoctorWithPerson doctor;
}
