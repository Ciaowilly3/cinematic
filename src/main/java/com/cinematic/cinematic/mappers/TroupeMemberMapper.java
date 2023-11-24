package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.TroupeMemberDto;
import com.cinematic.cinematic.models.TroupeMember;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TroupeMemberMapper {
    TroupeMemberDto toTroupeMemberDto(TroupeMember troupeMember);

    List<TroupeMemberDto> toTroupeMembersDtos(List<TroupeMember> troupeMembers);
}
