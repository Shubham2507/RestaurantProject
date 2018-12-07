package com.infogain.api.entity.security;

public class Constants {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = (long)24*60*60*24;
    public static final String SIGNING_KEY = "infogain";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";
}
