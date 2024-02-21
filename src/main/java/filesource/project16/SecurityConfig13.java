//package filesource.project16;
////#버전오류로 css버그있음
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity  //모든요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 애너테이션
//@Configuration       //스프링의 환경설정 파일임을 의미하는 애너테이션
//public class SecurityConfig13 {
//
//
//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//
//                .authorizeHttpRequests(requests -> requests
//                                .requestMatchers("/**").permitAll()     //비로그인 접근가능페이지
////                        .requestMatchers("/", "/login", "/join").permitAll()
////                        .requestMatchers("/admin/**").hasRole("ADMIN")        //허용조건
//                                .anyRequest().authenticated()       //이상제외는 로그인필수
//                )
//
//                .formLogin(form -> form
//                        .loginPage("/login")       	//자동적으로 로그인페이지로감. 로그인페이지로 취급될주소
//                        .defaultSuccessUrl("/main", true)   //로그인 성공시 루트 url
//                        .permitAll()
//                )
//                .logout(logout -> logout.permitAll());
////                .logout((logout) -> logout.logoutSuccessUrl("/"));
//
//        return http.build();
//    }
//
//
//    @Bean
//    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }   //AuthenticationManager는 스프링 시큐리티의 인증을 담당. 사용자 인증시 앞에서 작성한 UserSecurityService와 PasswordEncoder를 사용
//
//
//}