package com.cinematic.cinematic.services;

import com.cinematic.cinematic.models.TroupeMember;

import java.util.List;

public interface TroupeMemberService {

    List<TroupeMember> retrieveAllTroupeMembers();

    TroupeMember retrieveTroupeMemberById(Long id);

    TroupeMember makeTroupeMember(TroupeMember troupeMember);
}
