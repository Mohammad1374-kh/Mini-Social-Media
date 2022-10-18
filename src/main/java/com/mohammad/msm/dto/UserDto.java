package com.mohammad.msm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @JsonProperty("fullName")
    @NotBlank
    @Size(min = 3, max = 32, message = "full name must be between 3 to 32 characters.")
    private String fullNameDto;

    @JsonProperty("signUpDate")
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private Date signUpDateDto;

    //TODO: Should I put unique validation? test it
    @JsonProperty("username")
    @NotBlank
    @Size(min = 3, max = 32, message = "username must be between 3 to 32 characters.")
    private String UsernameDto;

    private List<PostDto> postDtoList;
    private List<FriendshipDto> FollowerDtoList;
    private List<FriendshipDto> FollowingDtoList;


}
