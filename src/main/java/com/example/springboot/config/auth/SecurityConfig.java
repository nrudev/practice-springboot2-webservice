package com.example.springboot.config.auth;

import com.example.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable() // h2-console 화면 사용 위해 해당 옵션들 disable
                .and()
                    .authorizeRequests() // URL별로 권한관리를 설정하는 옵션 시작점. 이 메서드가 선언되어야만 antMatchers 옵션 사용 가능.
                        .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // antMatcher : 권한 관리 대상 지정 옵션.
                        .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                        .anyRequest().authenticated() // anyRequest : 설정된 값들 이외 나머지 URL 가리킴
                .and()
                    .logout() // 로그아웃 기능에 대한 설정 진입점
                        .logoutSuccessUrl("/") // 로그아웃 성공시 / 주소로 이동
                .and()
                    .oauth2Login() // OAuth2 로그인 기능에 대한 설정 진입점
                        .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정 담당
                        .userService(customOAuth2UserService); // 소셜 로그인 성공시 후속조치를 진행할 UserService인터페이스의 구현체 등록.
    }
}
