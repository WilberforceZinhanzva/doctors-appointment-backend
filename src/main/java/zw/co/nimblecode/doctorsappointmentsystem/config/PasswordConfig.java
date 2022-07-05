package zw.co.nimblecode.doctorsappointmentsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import zw.co.nimblecode.doctorsappointmentsystem.security.CustomPasswordEncoder;

@Component
public class PasswordConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomPasswordEncoder();
    }
}
