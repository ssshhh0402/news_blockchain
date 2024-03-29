package com.block.chain.news.domain.subjectList;

import com.block.chain.news.domain.BaseTimeEntity;
import com.block.chain.news.domain.subject.Subject;
import com.block.chain.news.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class SubjectList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long subjectListId;


    @Column(nullable=false)
    private Long subjectId;

    @Column(nullable=false)
    private Long postId;


    @Builder
    public SubjectList(Long subjectId, Long postId){
        this.subjectId = subjectId;
        this.postId= postId;
    }
}
