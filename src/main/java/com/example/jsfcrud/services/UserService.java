package com.example.jsfcrud.services;

import com.example.jsfcrud.entities.UserEntity;
import com.example.jsfcrud.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@ViewScoped
@Service
public class UserService {

    private final UserRepository userRepository;
    @Getter
    private List<UserEntity> users;
    @Getter
    private UserEntity user = new UserEntity();

    public boolean addUser(){
        userRepository.save(user);
        users.add(user);
        user = new UserEntity();
        return user.getId() > 0;
    }
    public void updateUser(UserEntity userEntity){
        if(userEntity.getId() == 0) return;
        userRepository.save(userEntity);
        int j = IntStream.range(0, users.size())
                .filter(i -> userEntity.getId()== users.get(i).getId())
                .findFirst().getAsInt();
        users.set(j, userEntity);
    }
    public void deleteUser(int userId){
        userRepository.deleteById(userId);
        users = users.stream().filter(userEntity -> userEntity.getId() != userId).collect(Collectors.toList());
    }

    @PostConstruct
    public void getAllUser(){
        users = userRepository.findAll();
    }


}
