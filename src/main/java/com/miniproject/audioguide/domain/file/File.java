package com.miniproject.audioguide.domain.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
    private String path;
    private String contentType;

    public File(MultipartFile multipartFile, String imgUrl) {
        this.originName = multipartFile.getOriginalFilename();
        this.contentType = multipartFile.getContentType();
        this.path = imgUrl;
    }
}
