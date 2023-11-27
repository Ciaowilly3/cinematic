package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.TroupeMemberDto;
import com.cinematic.cinematic.mappers.TroupeMemberMapper;
import com.cinematic.cinematic.models.TroupeMember;
import com.cinematic.cinematic.services.impl.TroupeMemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("troupe-members")
@RestController
@RequiredArgsConstructor
public class TroupeMemberController {

    private final TroupeMemberServiceImpl troupeMemberService;
    private final TroupeMemberMapper troupeMemberMapper;
    @GetMapping
    public List<TroupeMemberDto> retrieveAllTroupeMembers(){
        return troupeMemberMapper.toTroupeMembersDtos(troupeMemberService.retrieveAllTroupeMembers());
    }

    @GetMapping(path = "/single-member/{id}")
    public TroupeMemberDto retrieveTroupeMemberById(@PathVariable Long id){
        return troupeMemberMapper.toTroupeMemberDto(troupeMemberService.retrieveTroupeMemberById(id));
    }

    @PostMapping
    public ResponseEntity<TroupeMemberDto> makeTroupeMember(@RequestBody TroupeMember troupeMember){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(troupeMemberMapper.toTroupeMemberDto(troupeMemberService.makeTroupeMember(troupeMember)));
    }
}
