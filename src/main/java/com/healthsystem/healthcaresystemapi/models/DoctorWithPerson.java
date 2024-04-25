package com.healthsystem.healthcaresystemapi.models;

import lombok.*;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class DoctorWithPerson {
        private long id;
        private String title;
        private Person person;
}
