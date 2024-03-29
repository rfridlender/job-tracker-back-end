package app.door2door.jobtracker.service;

import app.door2door.jobtracker.dto.UserDto;
import app.door2door.jobtracker.dto.UserCreateRequest;
import app.door2door.jobtracker.dto.UserUpdateRequest;
import app.door2door.jobtracker.entity.User;
import app.door2door.jobtracker.exceptions.EntityNotFoundException;
import app.door2door.jobtracker.mapper.UserDtoMapper;
import app.door2door.jobtracker.repository.UserRepository;
import app.door2door.jobtracker.exceptions.EmailTakenException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDtoMapper userDtoMapper;
    @Value("${default.password}")
    private String DEFAULT_PASSWORD;

    public List<UserDto> index() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userDtoMapper).toList();
    }

    public UserDto create(UserCreateRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailTakenException("Email already taken");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(DEFAULT_PASSWORD))
                .role(request.getRole())
                .build();
        userRepository.save(user);
        return userDtoMapper.apply(user);
    }

    public UserDto update(Integer userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setRole(request.getRole());
        userRepository.save(user);
        return userDtoMapper.apply(user);
    }

    public UserDto delete(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.delete(user);
        return userDtoMapper.apply(user);
    }

}