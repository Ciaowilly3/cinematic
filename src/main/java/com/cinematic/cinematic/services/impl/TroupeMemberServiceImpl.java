package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.exceptions.NotFoundException;
import com.cinematic.cinematic.models.TroupeMember;
import com.cinematic.cinematic.repositories.TroupeMemberRepository;
import com.cinematic.cinematic.services.TroupeMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TroupeMemberServiceImpl implements TroupeMemberService {

    private final TroupeMemberRepository troupeMemberRepository;

    public List<TroupeMember> retrieveAllTroupeMembers(){
        log.info("Start - retrieveAllTroupeMembers - args:none");
        val troupeMembers = troupeMemberRepository.findAll();
        log.info("End - retrieveAllTroupeMembers - out: {}", troupeMembers);
        return troupeMembers;
    }

    public TroupeMember retrieveTroupeMemberById(Long id){
        log.info("Start - retrieveTroupeMemberById - args: id: {}", id);
        val member = troupeMemberRepository.findById(id);
        if (member.isEmpty()){
            throw new NotFoundException("not found member with id: " + id);
        }
        log.info("End - retrieveTroupeMemberById - out: {}", member.get());
        return member.get();
    }
}
//TODO: capire come utilizzare orElse throw per levare l'if