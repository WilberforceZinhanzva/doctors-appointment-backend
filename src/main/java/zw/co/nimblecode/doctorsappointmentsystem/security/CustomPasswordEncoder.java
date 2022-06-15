package zw.co.nimblecode.doctorsappointmentsystem.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import zw.co.nimblecode.doctorsappointmentsystem.interfaces.ApplicationPasswordEncoder;

public class CustomPasswordEncoder extends BCryptPasswordEncoder implements ApplicationPasswordEncoder {
    @Override
    public String encode(String password) {
        return super.encode(password);
    }
}
