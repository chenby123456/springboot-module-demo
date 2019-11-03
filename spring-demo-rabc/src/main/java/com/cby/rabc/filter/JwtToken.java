package com.cby.rabc.filter;

import org.apache.shiro.authc.HostAuthenticationToken;

public class JwtToken implements HostAuthenticationToken {
    private String host;

    private String token;

    public JwtToken(String host, String token) {
        this.host = host;
        this.token = token;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
