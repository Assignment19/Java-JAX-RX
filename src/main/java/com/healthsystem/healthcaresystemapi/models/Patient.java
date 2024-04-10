package com.healthsystem.healthcaresystemapi.models;
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
public class Patient extends Person{
    
    private Person person;
    private String medicalHistory;
    private String currentHealthStatus;
    
}
