package com.healthsystem.healthcaresystemapi.models;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    private long id;
    private String title;
    private long personId;
}
