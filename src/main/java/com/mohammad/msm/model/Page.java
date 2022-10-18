package com.mohammad.msm.model;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Page {

    private long totalPage;
    private long currentPage;
    private int pageSize;
    private long totalList;
    private List<Page> list;
}
