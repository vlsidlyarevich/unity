package com.github.vlsidlyarevich.unity.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by vladislav on 10/11/16.
 */
@Data
@ToString
public class Image extends BaseEntity {

    private MultipartFile multipartFile;

    public Image() {

    }

    public Image(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
