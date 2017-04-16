package com.github.vlsidlyarevich.unity.web.converter.model;

import com.github.vlsidlyarevich.unity.common.converter.Converter;
import com.github.vlsidlyarevich.unity.db.model.UserSocial;
import com.github.vlsidlyarevich.unity.web.dto.UserSocialDTO;
import org.springframework.stereotype.Component;

@Component
public class UserSocialConverter implements Converter<UserSocial, UserSocialDTO> {

    @Override
    public UserSocialDTO convert(UserSocial source) {
        UserSocialDTO dto = new UserSocialDTO();
        dto.setFirstName(source.getFirstName() != null ? source.getFirstName() : "");
        dto.setLastName(source.getLastName() != null ? source.getLastName() : "");
        dto.setEmail(source.getEmail() != null ? source.getEmail() : "");
        dto.setSkype(source.getSkype() != null ? source.getSkype() : "");
        dto.setAdditional(source.getAdditional() != null ? source.getAdditional() : "");
        dto.setImage(source.getImage() != null ? source.getImage() : "");

        return dto;
    }

    @Override
    public boolean canConvert(Object o) {
        return o instanceof UserSocial;
    }
}