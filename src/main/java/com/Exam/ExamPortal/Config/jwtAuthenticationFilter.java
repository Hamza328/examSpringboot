package com.Exam.ExamPortal.Config;

import com.Exam.ExamPortal.Model.User;
import com.Exam.ExamPortal.service.Implementation.UserDetailServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class jwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    UserDetailServiceImpl userDetail;

    @Autowired
    jwtUtils jwtutils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("Authication Filter");
       String requesttoken=  request.getHeader("Authorization");
       String username =null;
       String jwtToken =null;

       if(requesttoken != null && requesttoken.startsWith("Bearer ")){

           jwtToken = requesttoken.substring(7);
           try {
                  username= jwtutils.extractUsername(jwtToken);
           }catch (ExpiredJwtException e){
               e.printStackTrace();
               System.out.println("Token is Expired");
           }catch (Exception e){
               e.printStackTrace();
               System.out.println("Error");
           }

       }else {
           System.out.println("Invalid Token Not Start With Bearer");
       }

       if(username != null && SecurityContextHolder.getContext().getAuthentication() ==null){
//           System.out.println("Security Context");
            UserDetails userdetail  = userDetail.loadUserByUsername(username);
           if(jwtutils.validateToken(jwtToken,userdetail)){
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =new UsernamePasswordAuthenticationToken(userdetail,null,userdetail.getAuthorities());
               usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
           }

       }else {
           System.out.println("Token Is Not Valid");
       }

       filterChain.doFilter(request,response);
    }
}
