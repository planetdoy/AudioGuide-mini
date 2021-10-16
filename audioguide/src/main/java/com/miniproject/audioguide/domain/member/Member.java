package com.miniproject.audioguide.domain.member;

import com.miniproject.audioguide.domain.file.File;
import com.miniproject.audioguide.domain.memberQuest.MemberQuest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private int age;
    private SexStatus sex;
    private String email;
    private int percentage;
    private RoleStatus role;

    @OneToMany(mappedBy = "member")
    private List<MemberQuest> memberQuests;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File profilePic;

    enum RoleStatus {
        ADMIN, MEMBER
    }

    enum SexStatus{
        MAN, WOMAN
    }
}
