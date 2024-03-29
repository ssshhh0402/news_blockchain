package com.block.chain.news.service;

import com.block.chain.news.domain.follow.Follow;
import com.block.chain.news.domain.follow.FollowRepository;
import com.block.chain.news.domain.user.User;
import com.block.chain.news.domain.user.UserRepository;
import com.block.chain.news.web.dto.follow.FollowRequestDto;
import com.block.chain.news.web.dto.follow.FollowResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Transactional
    public String follow(FollowRequestDto requestDto){
        User fromUser = userRepository.findByEmail(requestDto.getFromUserEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. email =" + requestDto.getFromUserEmail()));

        User toUser = userRepository.findByEmail(requestDto.getToUserEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. email =" + requestDto.getToUserEmail()));


        List<Follow> followings = followRepository.findAllByFromUser(requestDto.getFromUserEmail());
        List<String> followingEmailList = new LinkedList<>();

        for (Follow following : followings) {
            followingEmailList.add(following.getToUser());
        }

        if(!followingEmailList.contains(requestDto.getToUserEmail())){
            followRepository.save(Follow.builder()
                    .fromUser(requestDto.getFromUserEmail())
                    .toUser(requestDto.getToUserEmail())
                    .build());
        }
//        List<Follow> following = followRepository.findAllByFromUser(requestDto.getFromUserEmail());
//
//        if(!following.contains(requestDto.getToUserEmail())){
//            followRepository.save(Follow.builder()
//                    .fromUser(requestDto.getFromUserEmail())
//                    .toUser(requestDto.getToUserEmail())
//                    .build());
//        }
        return requestDto.getToUserEmail();
    }

    @Transactional
<<<<<<< HEAD
    public String unFollow(FollowRequestDto requestDto){
        User fromUser = userRepository.findByEmail(requestDto.getFromUserEmail())   //Follow해지하는 사람
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. email =" + requestDto.getFromUserEmail()));

        User toUser = userRepository.findByEmail(requestDto.getToUserEmail())           //Follow 해지할 사람
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. email =" + requestDto.getFromUserEmail()));
        List<String> following = new FollowResponseDto(fromUser).getFollowing();
=======
    public String unFollow(String fromUserEmail, String toUserEmail){
        User fromUser = userRepository.findByEmail(fromUserEmail)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. email =" + fromUserEmail));

        User toUser = userRepository.findByEmail(toUserEmail)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. email =" + toUserEmail));

        List<Follow> followings = followRepository.findAllByFromUser(fromUserEmail);
        List<String> followingEmailList = new LinkedList<>();
>>>>>>> 6f52e01c217581ab96e4c6073db97366cacd1c68

        for (Follow following : followings) {
            followingEmailList.add(following.getToUser());
        }

        if(followingEmailList.contains(toUserEmail)){
            Follow follow = followRepository.findByFromUserAndToUser(fromUserEmail, toUserEmail)
                    .orElseThrow(() -> new IllegalArgumentException("해당 팔로우 정보가 없습니다."));
            followRepository.deleteFollow(follow.getFollowId());
        }
//        List<String> following = new FollowResponseDto(fromUser).getFollowing();

//        if(following.contains(toUserEmail)){
//            Follow follow = followRepository.findByFromUserAndToUser(fromUser, toUser)
//                    .orElseThrow(() -> new IllegalArgumentException("해당 팔로우 정보가 없습니다."));
//            System.out.println("follow id : " + follow.getFollowId());
//
//            followRepository.deleteFollow(follow.getFollowId());
//        }

        return toUserEmail;
    }

    @Transactional
    public FollowResponseDto GetFollow(String fromemail) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. email =" + email));

        List<Follow> followings = followRepository.findAllByFromUser(fromemail);
        List<Follow> followers = followRepository.findAllByToUser(fromemail);

        return new FollowResponseDto(followers, followings);
    }
}
