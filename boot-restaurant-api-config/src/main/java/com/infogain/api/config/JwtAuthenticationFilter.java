package com.infogain.api.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import static com.infogain.api.entity.security.Constants.*; 

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenProvider jwtTokenUtil;
    String username = null;
    String authToken = null;
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
    	  String header = req.getHeader(HEADER_STRING);
    	  System.out.println("Header is = "+header);
         
          if (header != null && header.startsWith(TOKEN_PREFIX)) {
        	  System.out.println(req.getRequestURI()+"=============");
              authToken = header.replace(TOKEN_PREFIX,"");
              try {
                  username = jwtTokenUtil.getUsernameFromToken(authToken);
              } catch (IllegalArgumentException e) {
                  logger.error("an error occured during getting username from token", e);
              } catch (ExpiredJwtException e) {
                  logger.warn("the token is expired and not valid anymore", e);
              } catch(SignatureException e){
                  logger.error("Authentication Failed. Username or Password not valid.");
              }
          } else {
        	  System.out.println(req.getRequestURI()+"=============");
              logger.warn("couldn't find bearer string, will ignore the header");
          }
          if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

              UserDetails userDetails = userDetailsService.loadUserByUsername(username);
              
              try {
  				if (jwtTokenUtil.validateToken(authToken, userDetails)) {
  				    UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
  				    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
  				    logger.info("authenticated user " + username + ", setting security context");
  				    SecurityContextHolder.getContext().setAuthentication(authentication);
  				}
  			} catch (NullPointerException e) {
  				logger.error("Token can't be generated");
          }
      }

		chain.doFilter(req, res);
	}
}
