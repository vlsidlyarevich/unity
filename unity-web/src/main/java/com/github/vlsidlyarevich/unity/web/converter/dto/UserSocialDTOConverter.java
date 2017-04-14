package com.github.vlsidlyarevich.unity.web.converter.dto;

import com.github.vlsidlyarevich.unity.common.converter.Converter;
import com.github.vlsidlyarevich.unity.db.model.UserSocial;
import com.github.vlsidlyarevich.unity.web.dto.UserSocialDTO;
import org.springframework.stereotype.Component;

@Component
public class UserSocialDTOConverter implements Converter<UserSocialDTO, UserSocial> {

    @Override
    public UserSocial convert(UserSocialDTO source) {
        UserSocial userSocial = new UserSocial();
        userSocial.setUserId(source.getUserId());
        userSocial.setFirstName(source.getFirstName());
        userSocial.setLastName(source.getLastName());
        userSocial.setEmail(source.getEmail());
        userSocial.setSkype(source.getSkype());
        userSocial.setAdditional(source.getAdditional());

        return userSocial;
    }

    @Override
    public boolean canConvert(Object o) {
        return o instanceof UserSocialDTO;
    }
}
