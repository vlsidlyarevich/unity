package com.github.vlsidlyarevich.unity.web.converter.dto;

import com.github.vlsidlyarevich.unity.common.converter.Converter;
import com.github.vlsidlyarevich.unity.db.domain.UserSocial;
import com.github.vlsidlyarevich.unity.web.dto.UserSocialDTO;
import org.springframework.stereotype.Component;

@Component
public class UserSocialDTOConverter implements Converter<UserSocialDTO, UserSocial> {

    @Override
    public UserSocial convert(final UserSocialDTO dto) {
        return new UserSocial(null, dto.getFirstName(),
                dto.getLastName(), dto.getEmail(), dto.getSkype(),
                dto.getAdditional(), dto.getImage());
    }

    @Override
    public boolean canConvert(final Object o) {
        return o instanceof UserSocialDTO;
    }
}
