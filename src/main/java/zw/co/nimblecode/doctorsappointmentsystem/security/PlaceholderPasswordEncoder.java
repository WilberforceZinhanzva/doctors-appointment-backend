package zw.co.nimblecode.doctorsappointmentsystem.security;

import zw.co.nimblecode.doctorsappointmentsystem.interfaces.ApplicationPasswordEncoder;

public class PlaceholderPasswordEncoder implements ApplicationPasswordEncoder {
    @Override
    public String encode(String password) {
        return "encoded" + password;
    }
}
