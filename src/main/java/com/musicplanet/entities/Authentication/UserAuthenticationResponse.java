package com.musicplanet.entities.Authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserAuthenticationResponse {
    private final String jwt;
}
