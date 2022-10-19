package com.mohammad.msm.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {
    private long totalPageDto;
    private long currentPageDto;
    private int pageSizeDto;
    private long totalListDto;
    private List<PageDto> listDto;
}
