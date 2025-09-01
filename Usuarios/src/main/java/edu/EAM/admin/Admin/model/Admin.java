package edu.EAM.admin.Admin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Admin {
    private String id;
    private String name;
    private String gender;
    private String Email;
    private String PhoneNumber;
    private Address address; // Agrega esta línea para que coincida con el servicio

}
