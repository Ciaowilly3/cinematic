package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.TroupeMemberDto;
import com.cinematic.cinematic.mappers.TroupeMemberMapper;
import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.models.TroupeMember;
import com.cinematic.cinematic.services.impl.TroupeMemberServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("troupe-members")
@RestController
@RequiredArgsConstructor
@Tag(name = "controller for TroupeMember", description = "makes a bunch of staff for TroupeMember")
public class TroupeMemberController {

    private final TroupeMemberServiceImpl troupeMemberService;
    private final TroupeMemberMapper troupeMemberMapper;

    @Operation(summary = "Get a list of TroupeMember")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the list of TroupeMember",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TroupeMember.class)) }),
            @ApiResponse(responseCode = "404", description = "TroupeMember list not found",
                    content = @Content) })
    @GetMapping
    public List<TroupeMemberDto> retrieveAllTroupeMembers(){
        return troupeMemberMapper.toTroupeMembersDtos(troupeMemberService.retrieveAllTroupeMembers());
    }

    @Operation(summary = "Get a TroupeMember by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the TroupeMember",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TroupeMember.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "TroupeMember not found",
                    content = @Content) })
    @GetMapping(path = "/single-member/{id}")
    public TroupeMemberDto retrieveTroupeMemberById(@PathVariable Long id){
        return troupeMemberMapper.toTroupeMemberDto(troupeMemberService.retrieveTroupeMemberById(id));
    }

    @Operation(summary = "Make a TroupeMember")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "TroupeMember made",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TroupeMember.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid TroupeMember supplied",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<TroupeMemberDto> makeTroupeMember(@RequestBody TroupeMember troupeMember){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(troupeMemberMapper.toTroupeMemberDto(troupeMemberService.makeTroupeMember(troupeMember)));
    }
}
