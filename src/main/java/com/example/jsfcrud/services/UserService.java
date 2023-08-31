package com.example.jsfcrud.services;

import com.example.jsfcrud.entities.UserEntity;
import com.example.jsfcrud.exception.InvalidAgeException;
import com.example.jsfcrud.repository.UserRepository;
import com.example.jsfcrud.utils.UserValidationUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import lombok.Setter;
import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ManagedBean
@ViewScoped
@RequiredArgsConstructor
@Getter
@Setter
public class UserService {

    private String mask;

    private String pesquisaNome;

    private final ViaCepService viaCepService;

    private final UserRepository userRepository;
    @Getter
    private List<UserEntity> users;
    @Getter
    private UserEntity user = new UserEntity();

    public void addUser() {
        try {
            if (UserValidationUtils.isUserAbove18(user)) {
                userRepository.save(user);
                users.add(user);
                user = new UserEntity();
            } else {
                throw new InvalidAgeException("Idade inválida");
            }
        } catch (InvalidAgeException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário tem que ter idade maior que 18 anos", "Idade inválida"));
            PrimeFaces.current().executeScript("PF('errorDialog').show();");
        }
    }



    public void updateUser(UserEntity userEntity) {
        if (userEntity.getId() == 0) return;
        userRepository.save(userEntity);
        int j = IntStream.range(0, users.size())
                .filter(i -> userEntity.getId() == users.get(i).getId())
                .findFirst().getAsInt();
        users.set(j, userEntity);
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
        users = users.stream().filter(userEntity -> userEntity.getId() != userId).collect(Collectors.toList());
    }

    @PostConstruct
    public void getAllUser() {
        users = userRepository.findAll();
    }

    public void searchUsers() {
        users =  userRepository.searchUsersByName(pesquisaNome);
    }

    // Método para atualizar a máscara com base no tipo de pessoa selecionado
    public void updateMask() {
        if ("fisica".equals(user.getTipoUsuario())) {
            mask = "999.999.999-99";
        } else if ("juridica".equals(user.getTipoUsuario())) {
            mask = "99.999.999/9999-99";
        }
    }




}
