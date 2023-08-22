package com.example.jsfcrud.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "users")

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column
    @NotNull(message = "can't be empty")
    @NotEmpty(message = "can't be empty")
    @Email(message = "Invalid Email")
    private String email;
    @Basic
    @Column
    @NotNull(message = "can't be empty")
    @NotEmpty(message = "can't be empty")
    private String firstname;
    @Basic
    @Column
    @NotNull(message = "can't be empty")
    @NotEmpty(message = "can't be empty")
    private String lastname;
    @Basic
    @Column
    @NotNull(message = "can't be empty")
    @NotEmpty(message = "can't be empty")
    private String phoneNumber;
    @Basic
    @Column
    @Temporal(TemporalType.DATE)
    @NotNull(message = "can't be empty")
    private Date dateOfBirth;
}
