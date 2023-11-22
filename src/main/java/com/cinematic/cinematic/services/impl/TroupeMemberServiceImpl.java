package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.models.TroupeMember;
import com.cinematic.cinematic.repositories.TroupeMemberRepository;
import com.cinematic.cinematic.services.TroupeMemberService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TroupeMemberServiceImpl implements TroupeMemberService {

    private final TroupeMemberRepository troupeMemberRepository;

    @Autowired
    public TroupeMemberServiceImpl(TroupeMemberRepository troupeMemberRepository){this.troupeMemberRepository = troupeMemberRepository;}

    public List<TroupeMember> retrieveAllTroupeMembers(){
        log.info("Start - retrieveAllTroupeMembers - args:none");
        val troupeMembers = troupeMemberRepository.findAll();
        log.info("Start - retrieveAllTroupeMembers - out: {}", troupeMembers);
        return troupeMembers;
    }
}
