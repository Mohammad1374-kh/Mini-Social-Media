package com.mohammad.msm.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDto {
    private long totalPageDto;
    private long currentPageDto;
    private int pageSizeDto;
    private long totalListDto;
    private List<PageDto> listDto;
}
