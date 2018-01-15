package com.github.vlsidlyarevich.unity.domain.model;

import com.github.vlsidlyarevich.unity.web.dto.user.UserRequest;
import com.github.vlsidlyarevich.unity.web.security.model.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "users")
public class User extends DbModel implements UserDetails {

    private static final long serialVersionUID = 5336313491937469684L;

    @Id
    private String id;
    private List<Authority> authorities;
    private String username;
    private String password;
    private boolean linkedInLoginEnabled;
    private boolean twitterLoginEnabled;
    private boolean facebookLoginEnabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean isEnabled;

    public static User fromDTO(final UserRequest dto) {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(Authority.ROLE_USER);

        return User.builder()
                .authorities(authorities)
                .username(dto.getUsername())
                .password(dto.getPassword())
                .facebookLoginEnabled(dto.isFacebookLoginEnabled())
                .linkedInLoginEnabled(dto.isLinkedInLoginEnabled())
                .twitterLoginEnabled(dto.isTwitterLoginEnabled())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .isEnabled(true)
                .credentialsNonExpired(true)
                .build();
    }

    public void addAuthority(final Authority authority) {
        this.authorities.add(authority);
    }
}
