package com.healthsystem.healthcaresystemapi.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    private long id;
    private Date appoinmentDate;
    private String description;
    private long patientId;
    private long doctorID;
}
