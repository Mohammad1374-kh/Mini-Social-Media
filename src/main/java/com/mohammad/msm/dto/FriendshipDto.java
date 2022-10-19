package com.mohammad.msm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipDto {
    /*@NotBlank*/
    @JsonProperty("accepted")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Boolean acceptedDto;
}
