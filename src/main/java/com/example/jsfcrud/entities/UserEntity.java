package com.example.jsfcrud.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    private Integer id;

    @Basic
    @NotNull(message = "O nome não pode estar vazio")
    @NotEmpty(message = "O nome não pode estar vazio")
    @Column(length = 50)
    private String nome;

    @Basic
    @Column(unique = true,length = 50)
    @NotNull(message = "O nome de usuário não pode estar vazio")
    @NotEmpty(message = "O nome de usuário não pode estar vazio")
    private String username;

    @Basic
    @NotNull(message = "A senha não pode estar vazia")
    @NotEmpty(message = "A senha não pode estar vazia")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$", message = "A senha deve ter pelo menos 8 caracteres, incluindo um número e uma letra maiúscula")
    @Column(nullable = false)
    private String senha;

    @Basic
    @NotNull(message = "O CEP não pode estar vazio")
    @NotEmpty(message = "O CEP não pode estar vazio")
    @Column(length = 8)
    private String cep;

    @Basic
    @NotNull(message = "O email não pode estar vazio")
    @NotEmpty(message = "O email não pode estar vazio")
    @Email(message = "Email inválido")
    @Column(unique = true, length = 100)
    private String email;

    @Basic
    @Temporal(TemporalType.DATE)
    @NotNull(message = "A data de nascimento não pode estar vazia")
    @Column(nullable = false)
    private Date dataNascimento;

    @Basic
    @NotNull(message = "O CPF/CNPJ não pode estar vazio")
    @NotEmpty(message = "O CPF/CNPJ não pode estar vazio")
    @Column(length = 14)
    private String cpfCnpj;

    @Basic
    @NotNull(message = "O sexo não pode estar vazio")
    @NotEmpty(message = "O sexo não pode estar vazio")
    @Column(nullable = false)
    private String sexo;

    @Basic
    @NotNull(message = "O tipo de usuário não pode estar vazio")
    @NotEmpty(message = "O tipo de usuário não pode estar vazio")
    @Column(nullable = false)
    public String tipoUsuario;
}