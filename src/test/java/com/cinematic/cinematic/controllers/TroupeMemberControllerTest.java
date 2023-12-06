package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.TroupeMemberDto;
import com.cinematic.cinematic.mappers.TroupeMemberMapper;
import com.cinematic.cinematic.models.TroupeMember;
import com.cinematic.cinematic.repositories.UserRepository;
import com.cinematic.cinematic.security.JwtService;
import com.cinematic.cinematic.security.MyUserDetailsService;
import com.cinematic.cinematic.services.impl.TroupeMemberServiceImpl;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TroupeMemberController.class)
@AutoConfigureMockMvc
@ContextConfiguration
class TroupeMemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ResourceLoader resourceLoader;

    @MockBean
    private TroupeMemberMapper troupeMemberMapper;
    @MockBean
    private TroupeMemberServiceImpl troupeMemberService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private MyUserDetailsService myUserDetailsService;
    @MockBean
    private JwtService jwtService;

    private final String path = "/troupe-members";
    @Test
    @WithMockUser
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
    @WithMockUser
    void retrieveTroupeMemberById() throws Exception {
        val memberId = 12L;
        val member = TroupeMember.builder().troupeMemberId(memberId).memberName("marco").build();
        when(troupeMemberService.retrieveTroupeMemberById(memberId)).thenReturn(member);
        val memberDto = TroupeMemberDto.builder().memberName("marco").build();
        when(troupeMemberMapper.toTroupeMemberDto(member)).thenReturn(memberDto);

        val resource = resourceLoader.getResource("classpath:troupe-member-single.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(get(path + "/single-member/{id}", memberId))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
        verify(troupeMemberService, times(1)).retrieveTroupeMemberById(memberId);
    }

    @Test
    @WithMockUser
    void makeTroupeMember() throws Exception {
        val member = TroupeMember.builder().memberName("marco").build();

        val resource = resourceLoader.getResource("classpath:troupe-member-single.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(post(path)
                .contentType("application/json")
                        .with(csrf())
                .content(expectedJson))
                .andExpect(status().isCreated());
        verify(troupeMemberService, times(1)).makeTroupeMember(member);
    }
}