package app.door2door.jobtracker.mapper;

import app.door2door.jobtracker.dto.UserDto;
import app.door2door.jobtracker.entity.User;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class UserDtoMapper implements Function<User, UserDto> {

    @Override
    public UserDto apply(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

}
