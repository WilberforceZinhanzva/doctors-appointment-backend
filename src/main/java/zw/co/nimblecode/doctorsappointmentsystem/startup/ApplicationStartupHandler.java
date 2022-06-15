package zw.co.nimblecode.doctorsappointmentsystem.startup;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupHandler implements ApplicationListener<ApplicationReadyEvent> {
    private StartupService startupService;

    public ApplicationStartupHandler(StartupService startupService) {
        this.startupService = startupService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        startupService.init();
    }
}
