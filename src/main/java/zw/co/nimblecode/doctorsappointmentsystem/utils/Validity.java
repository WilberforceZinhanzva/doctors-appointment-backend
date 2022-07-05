package zw.co.nimblecode.doctorsappointmentsystem.utils;

public class Validity {
    private boolean valid;
    private String message;

    public Validity(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }
}
