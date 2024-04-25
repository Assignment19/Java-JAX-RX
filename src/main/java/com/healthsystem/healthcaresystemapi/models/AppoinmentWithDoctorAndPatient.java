package com.healthsystem.healthcaresystemapi.models;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AppoinmentWithDoctorAndPatient {
    private Date appoinmentDate;
    private String description;
    private PatientWithPerson patient;
    private DoctorWithPerson doctor;
}
