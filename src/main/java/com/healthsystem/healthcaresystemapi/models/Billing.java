package com.healthsystem.healthcaresystemapi.models;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Billing {
    private long id;
    private String description;
    private float price;
    private float balance;
    private long personId;
}
