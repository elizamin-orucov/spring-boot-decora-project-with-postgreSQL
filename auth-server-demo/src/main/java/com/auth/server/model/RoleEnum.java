package com.auth.server.model;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    private String value;

    RoleEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
