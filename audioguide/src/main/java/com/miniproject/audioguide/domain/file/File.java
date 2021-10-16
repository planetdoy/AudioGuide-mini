package com.miniproject.audioguide.domain.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class File {

    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Long id;

    private String originName;
    private String uuid;
    private String path;
    private String fileForm;

}
