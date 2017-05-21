package com.github.vlsidlyarevich.unity.web.converter.dto;

import com.github.vlsidlyarevich.unity.common.converter.Converter;
import com.github.vlsidlyarevich.unity.db.domain.UserSocial;
import com.github.vlsidlyarevich.unity.web.dto.UserSocialDTO;
import org.springframework.stereotype.Component;

@Component
public class UserSocialDTOConverter implements Converter<UserSocialDTO, UserSocial> {

    @Override
    public UserSocial convert(final UserSocialDTO source) {
        UserSocial userSocial = new UserSocial();
        userSocial.setFirstName(source.getFirstName());
        userSocial.setLastName(source.getLastName());
        userSocial.setEmail(source.getEmail());
        userSocial.setSkype(source.getSkype());
        userSocial.setAdditional(source.getAdditional());
        userSocial.setImage(source.getImage());

        return userSocial;
    }

    @Override
    public boolean canConvert(final Object o) {
        return o instanceof UserSocialDTO;
    }
}
