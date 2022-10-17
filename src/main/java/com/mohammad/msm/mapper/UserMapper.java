package com.mohammad.msm.mapper;

import com.mohammad.msm.dto.UserDto;
import com.mohammad.msm.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring", uses = UserDto.class )
public interface UserMapper {

    //mapping UserDto properties to User
    @Mapping(target = "fullNameDto",source = "fullName")
    @Mapping(target = "UsernameDto",source = "Username")
    @Mapping(target = "signUpDateDto",source = "signUpDate")
    UserDto toUserDto(User user);

    List<UserDto> toUserDtoList(List<User> userList);


    @Mapping(target = "fullName" ,source = "fullNameDto" )
    @Mapping(target = "Username",source = "UsernameDto" )
    @Mapping(target = "signUpDate" ,source = "signUpDateDto")
    User toUser(UserDto userDto);

    List<User> toUserList(List<UserDto> userDtoList);

}
