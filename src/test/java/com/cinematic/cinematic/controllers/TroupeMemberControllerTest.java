package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.TroupeMemberDto;
import com.cinematic.cinematic.mappers.TroupeMemberMapper;
import com.cinematic.cinematic.models.TroupeMember;
import com.cinematic.cinematic.services.impl.TroupeMemberServiceImpl;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TroupeMemberController.class)
@AutoConfigureMockMvc
class TroupeMemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ResourceLoader resourceLoader;

    @MockBean
    private TroupeMemberMapper troupeMemberMapper;
    @MockBean
    private TroupeMemberServiceImpl troupeMemberService;

    private final String path = "/troupe-members";
    @Test
    void retrieveAllTroupeMembers() throws Exception{
        val member1 = TroupeMember.builder().memberName("marco").build();
        val member2 = TroupeMember.builder().memberName("luca").build();
        val troupeMembers = List.of(member1,member2);
        when(troupeMemberService.retrieveAllTroupeMembers()).thenReturn(troupeMembers);
        val memberDto1 = TroupeMemberDto.builder().memberName("marco").build();
        val memberDto2 = TroupeMemberDto.builder().memberName("luca").build();
        val troupeMemberDtos = List.of(memberDto1,memberDto2);
        when(troupeMemberMapper.toTroupeMembersDtos(troupeMembers)).thenReturn(troupeMemberDtos);

        val resource = resourceLoader.getResource("classpath:troupe-members-list.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
        verify(troupeMemberService, times(1)).retrieveAllTroupeMembers();
    }

    @Test
    void retrieveTroupeMemberById() throws Exception {
        val memberId = 12L;
        val member = TroupeMember.builder().troupeMemberId(memberId).memberName("marco").build();
        when(troupeMemberService.retrieveTroupeMemberById(memberId)).thenReturn(member);
        val memberDto = TroupeMemberDto.builder().memberName("marco").build();
        when(troupeMemberMapper.toTroupeMemberDto(member)).thenReturn(memberDto);

        val resource = resourceLoader.getResource("classpath:troupe-members-list.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(get(path + "/single-member/{id}", memberId))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"memberName\" : \"marco\"}"));
        verify(troupeMemberService, times(1)).retrieveTroupeMemberById(memberId);
    }
}