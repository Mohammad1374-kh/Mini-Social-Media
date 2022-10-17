package com.mohammad.msm.mapper;

import com.mohammad.msm.dto.PostDto;
import com.mohammad.msm.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = PostDto.class )
public interface PostMapper {

    @Mapping(target = "contentDto" ,source = "content" )
    @Mapping(target = "createdDateDto" ,source = "createdDate" )
    PostDto toPostDto(Post post);

    List<PostDto> toPostDtoList(List<Post> postList);

    @Mapping(target = "content" ,source = "contentDto" )
    @Mapping(target = "createdDate" ,source = "createdDateDto" )
    Post toPost(PostDto postDto);

    List<Post> toPostList(List<PostDto> postDtoList);
}
