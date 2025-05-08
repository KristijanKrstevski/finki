package com.example.airbnb.security;

public class JwtConstants {
    public static final String SECRET_KEY = "52d9888e3731f2306ec3ba3a2a4d034cbfef212c1965533cae88c8ba2a37c811";
    public static final Long EXPIRATION_TIME = 864000000L;
    public static final String HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
}
