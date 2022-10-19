package com.mohammad.msm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("content")
    private String contentDto;

    /*@NotBlank*/
    @JsonProperty("createdDate")
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private Date createdDateDto;
}
