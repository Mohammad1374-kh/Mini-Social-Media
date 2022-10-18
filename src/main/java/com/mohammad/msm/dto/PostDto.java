package com.mohammad.msm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    //TODO: might be incomplete

    /*@NotBlank*/
    private String contentDto;

    /*@NotBlank*/
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date createdDateDto;
}
