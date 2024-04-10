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
public class Person {
    private long id;
    private String name;
    private String contactInfo;
    private String address;


//    public Person() {
//    }
//
//
//    public Person(long id, String name, String contactInfo, String address) {
//        this.id = id;
//        this.name = name;
//        this.contactInfo = contactInfo;
//        this.address = address;
//    }
//
//
//    public long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getContactInfo() {
//        return contactInfo;
//    }
//
//    public void setContactInfo(String contactInfo) {
//        this.contactInfo = contactInfo;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    // Override toString method for a meaningful representation
//    @Override
//    public String toString() {
//        return "Person{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", contactInfo='" + contactInfo + '\'' +
//                ", address='" + address + '\'' +
//                '}';
//    }
}

