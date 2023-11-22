package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.TroupeMemberDto;
import com.cinematic.cinematic.mappers.TroupeMemberMapper;
import com.cinematic.cinematic.services.impl.TroupeMemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/troupeMembers")
@RestController
public class TroupeMemberController {

    private final TroupeMemberServiceImpl troupeMemberService;

    @Autowired
    public TroupeMemberController (TroupeMemberServiceImpl troupeMemberService){this.troupeMemberService = troupeMemberService;}

    @GetMapping
    public List<TroupeMemberDto> retrieveAllTroupeMembers(){
        return TroupeMemberMapper.INSTANCE.troupeMembersToTroupeMembersDtos(troupeMemberService.retrieveAllTroupeMembers());
    }
}
