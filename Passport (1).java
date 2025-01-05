package com.examly.springapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Passport {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String serialNumber;
    private int issueYear;
    private String country;

    @OneToOne(mappedBy = "passport", cascade = CascadeType.ALL)
    @JsonBackReference
    private Person person;

}
