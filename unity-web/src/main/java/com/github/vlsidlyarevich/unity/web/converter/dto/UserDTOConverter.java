package com.github.vlsidlyarevich.unity.web.converter.dto;

import com.github.vlsidlyarevich.unity.common.converter.Converter;
import com.github.vlsidlyarevich.unity.db.model.Authority;
import com.github.vlsidlyarevich.unity.db.model.User;
import com.github.vlsidlyarevich.unity.web.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDTOConverter implements Converter<UserDTO, User> {

    @Override
    public User convert(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setAccountNonExpired(false);
        user.setCredentialsNonExpired(false);
        user.setEnabled(true);

        List<Authority> authorities = new ArrayList() {{
            add(Authority.ROLE_USER);
        }};
        user.setAuthorities(authorities);
        return user;
    }

    public boolean canConvert(Object o) {
        return o instanceof UserDTO;
    }
}
