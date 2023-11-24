package com.ideamos.test.services;

import com.ideamos.test.models.ProductModel;
import com.ideamos.test.models.UserModel;
import com.ideamos.test.repositories.IProductRepository;
import com.ideamos.test.repositories.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ArrayList<UserModel> getUsers(){
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel saveUser(UserModel user){
        return userRepository.save(user);
    }

    public Optional<UserModel> getById(Long id){
        return userRepository.findById(id);
    }

    public UserModel updateById(UserModel request, Long id){
        Optional<UserModel> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            UserModel user = userOptional.get();

            if (request.getFirst_name() != null) {
                user.setFirst_name(request.getFirst_name());
            }
            if (request.getLast_name() != null) {
                user.setLast_name(request.getLast_name());
            }
            if (request.getEmail() != null) {
                user.setEmail(request.getEmail());
            }
            if (request.getAddress() != null) {
                user.setAddress(request.getAddress());
            }
            if (request.getBirthday() != null) {
                user.setBirthday(request.getBirthday());
            }
            if (request.getPassword() != null) {
                user.setPassword(request.getPassword());
            }

            return userRepository.save(user);
        } else {
            throw new EntityNotFoundException("No product found with id: " + id);
        }
    }

    public Boolean deleteUser(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
