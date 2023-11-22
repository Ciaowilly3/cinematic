package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.TroupeMemberDto;
import com.cinematic.cinematic.models.TroupeMember;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TroupeMemberMapper {

    TroupeMemberMapper INSTANCE = Mappers.getMapper(TroupeMemberMapper.class);

    TroupeMemberDto troupeMemberToTroupeMemberDto(TroupeMember troupeMember);

    List<TroupeMemberDto> troupeMembersToTroupeMembersDtos(List<TroupeMember> troupeMembers);
}
