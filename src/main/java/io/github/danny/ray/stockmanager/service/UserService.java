package io.github.danny.ray.stockmanager.service;

import java.util.Optional;

import io.github.danny.ray.stockmanager.dto.user.RegisterDto;
import io.github.danny.ray.stockmanager.dto.user.UserInfoDto;
import io.github.danny.ray.stockmanager.exception.NotFoundException;
import io.github.danny.ray.stockmanager.exception.RegisterException;
import io.github.danny.ray.stockmanager.model.Users;
import io.github.danny.ray.stockmanager.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Users fetchUser(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found, Id: " + id));
    }

    public UserInfoDto register(RegisterDto dto) {
        Optional<Users> userOptional = repository.findByUsername(dto.getUsername());
        if (userOptional.isPresent()) {
            throw new RegisterException("User already exists");
        }
        Users newUsers = modelMapper.map(dto, Users.class);
        newUsers.setPassword(passwordEncoder.encode(dto.getPassword()));

        newUsers = repository.save(newUsers);
        return new UserInfoDto(newUsers.getUsername(), newUsers.getEmail());
    }
}
