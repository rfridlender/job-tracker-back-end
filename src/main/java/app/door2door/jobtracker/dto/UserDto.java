package app.door2door.jobtracker.dto;

import app.door2door.jobtracker.entity.Role;

public record UserDto (

        Integer id,
        String name,
        String email,
        Role role

){

}