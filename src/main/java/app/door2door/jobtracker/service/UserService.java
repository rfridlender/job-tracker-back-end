package app.door2door.jobtracker.service;

import app.door2door.jobtracker.dto.UserDto;
import app.door2door.jobtracker.entity.User;
import app.door2door.jobtracker.exceptions.EmailNotFoundException;
import app.door2door.jobtracker.mapper.UserDtoMapper;
import app.door2door.jobtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserDtoMapper userDtoMapper;

    private UserDto getUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDtoMapper.apply(principal);
    }

    public UserDto show(String email) {
        if (!email.equals(getUser().email())) throw new AccessDeniedException("Access denied");
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("Email not found"));
        return userDtoMapper.apply(user);
    }

}