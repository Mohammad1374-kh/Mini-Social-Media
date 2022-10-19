package com.mohammad.msm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Lob;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {


    @Lob
    @JsonProperty("content")
    private String contentDto;


    @JsonProperty("createdDate")
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private Date createdDateDto;
}
