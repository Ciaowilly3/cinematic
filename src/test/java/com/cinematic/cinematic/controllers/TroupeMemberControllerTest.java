package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.models.TroupeMember;
import com.cinematic.cinematic.models.User;
import com.cinematic.cinematic.services.impl.TroupeMemberServiceImpl;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TroupeMemberController.class)
@AutoConfigureMockMvc
class TroupeMemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TroupeMemberServiceImpl troupeMemberService;

    private final String path = "/api/troupeMembers";
    @Test
    void retrieveAllTroupeMembers() throws Exception{
        val member1 = TroupeMember.builder().memberName("marco").build();
        val member2 = TroupeMember.builder().memberName("luca").build();
        val troupeMembers = List.of(member1,member2);
        when(troupeMemberService.retrieveAllTroupeMembers()).thenReturn(troupeMembers);

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"memberName\" : \"marco\"}, {\"memberName\" : \"luca\"}]"));
        verify(troupeMemberService, times(1)).retrieveAllTroupeMembers();
    }

    @Test
    void retrieveTroupeMemberById() throws Exception {
        val memberId = 12L;
        val member = TroupeMember.builder().troupeMemberId(memberId).memberName("marco").build();

        when(troupeMemberService.retrieveTroupeMemberById(memberId)).thenReturn(member);

        mockMvc.perform(get(path + "/singleMember/{id}", memberId))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"memberName\" : \"marco\"}"));
        verify(troupeMemberService, times(1)).retrieveTroupeMemberById(memberId);
    }
}