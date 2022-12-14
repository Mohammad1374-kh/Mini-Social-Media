package com.mohammad.msm.mapper;

import com.mohammad.msm.dto.FriendshipDto;
import com.mohammad.msm.model.Friendship;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = FriendshipDto.class )
public interface FriendshipMapper {

    @Mapping(target = "createdDateDto" ,source = "createdDate" )
    FriendshipDto toFriendshipDto(Friendship friendship);

    List<FriendshipDto> toFriendshipDtoList(List<Friendship> friendshipList);

    @Mapping(target = "createdDate" ,source = "createdDateDto" )
    Friendship toFriendship(FriendshipDto friendshipDto);

    List<Friendship> toFriendshipList (List<FriendshipDto> friendshipDtoList);
}
