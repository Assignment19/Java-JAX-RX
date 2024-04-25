package com.healthsystem.healthcaresystemapi.models;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BillingWithPerson {
    private long id;
    private String description;
    private float price;
    private float balance;
    private Person person;
}
