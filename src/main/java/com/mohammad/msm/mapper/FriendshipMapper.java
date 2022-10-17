package com.mohammad.msm.mapper;

import com.mohammad.msm.dto.FriendshipDto;
import com.mohammad.msm.model.Friendship;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = FriendshipDto.class )
public interface FriendshipMapper {
    @Mapping(target = "acceptedDto" ,source = "accepted" )
    FriendshipDto toFriendshipDto(Friendship friendship);

    List<FriendshipDto> toFriendshipDtoList(List<Friendship> friendshipList);

    @Mapping(target = "accepted" ,source = "acceptedDto" )
    Friendship toFriendship(FriendshipDto friendshipDto);

    List<Friendship> toFriendshipList (List<FriendshipDto> friendshipDtoList);
}
