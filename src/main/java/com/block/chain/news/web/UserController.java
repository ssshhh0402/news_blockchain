package com.block.chain.news.web;

import com.block.chain.news.service.FollowService;
import com.block.chain.news.service.UserService;
import com.block.chain.news.web.dto.user.FollowRequestDto;
import com.block.chain.news.web.dto.user.UserResponseDto;
import com.block.chain.news.web.dto.user.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final FollowService followService;

    @PostMapping("/api/v1/user")
    public ResponseEntity<String> save(@RequestBody UserSaveRequestDto requestDto){
        log.info("User save email : {}", requestDto.getEmail());

        return new ResponseEntity<String>(userService.save(requestDto), HttpStatus.OK);
    }

    @GetMapping("/api/v1/user/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        log.info("User get info");

        return new ResponseEntity<UserResponseDto>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/api/v1/follow")
    public ResponseEntity<String> follow(@RequestBody FollowRequestDto requestDto){
        log.info("Follow email : {}", requestDto.getToUserEmail());

        return new ResponseEntity<String>(followService.follow(requestDto), HttpStatus.OK);
    }
}