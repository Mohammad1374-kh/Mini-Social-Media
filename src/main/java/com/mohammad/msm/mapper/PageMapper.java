package com.mohammad.msm.mapper;

import com.mohammad.msm.dto.PageDto;
import com.mohammad.msm.model.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = PageDto.class )
public interface PageMapper {


    @Mapping(target = "totalPageDto", source = "totalPage")
    @Mapping(target = "currentPageDto", source = "currentPage")
    @Mapping(target = "pageSizeDto", source = "pageSize")
    @Mapping(target = "totalListDto", source = "totalList")
    PageDto toPageDto (Page page);

    List<PageDto> toPageDtoList(List<Page> pageList);

    @Mapping(target = "totalPage", source = "totalPageDto")
    @Mapping(target = "currentPage", source = "currentPageDto")
    @Mapping(target = "pageSize", source = "pageSizeDto")
    @Mapping(target = "totalList", source = "totalListDto")
    Page toPage(PageDto pageDto);

    List<Page> toPageList(List<PageDto> pageDtoList);

}
