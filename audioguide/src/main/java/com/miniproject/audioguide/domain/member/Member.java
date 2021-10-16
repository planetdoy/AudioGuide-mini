package com.miniproject.audioguide.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String uuid;
    private String name;
    private String email;

    @OneToMany(mappedBy = "member")
    private List<MemberQuest> memberQuests;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File profilePic;

    public Member(String loginId, String password, String uuid, String name, String email) {
        this.loginId = loginId;
        this.password = password;
        this.uuid = uuid;
        this.name = name;
        this.email = email;
    }

//    private SexStatus sex;
//    private int age;
//    private int percentage;
//    private RoleStatus role;
}
