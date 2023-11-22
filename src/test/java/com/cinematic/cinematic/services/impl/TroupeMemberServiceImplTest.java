package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.models.TroupeMember;
import com.cinematic.cinematic.models.User;
import com.cinematic.cinematic.repositories.TroupeMemberRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TroupeMemberServiceImplTest {

    @Mock
    private TroupeMemberRepository troupeMemberRepository;

    @InjectMocks
    private TroupeMemberServiceImpl troupeMemberService;

    @Test
    void retrieveAllTroupeMembers() {
        val member1 = TroupeMember.builder().memberName("marco").build();
        val member2 = TroupeMember.builder().memberName("luca").build();
        val troupeMembers = List.of(member1,member2);
        when(troupeMemberRepository.findAll()).thenReturn(troupeMembers);

        val result = troupeMemberService.retrieveAllTroupeMembers();

        assertEquals(troupeMembers, result);
        verify(troupeMemberRepository, times(1)).findAll();
    }
}