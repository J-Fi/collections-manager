package com.janflpk.collectionsmanager.backend.scheduler;

import com.janflpk.collectionsmanager.backend.domain.Mail;
import com.janflpk.collectionsmanager.backend.domain.MailGeneratorType;
import com.janflpk.collectionsmanager.backend.repository.BookRepository;
import com.janflpk.collectionsmanager.backend.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Books: once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Scheduled(fixedDelay = 10000000) //@Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        simpleEmailService.send(new Mail(
                MailGeneratorType.EMAIL_FROM_EMAIL_SCHEDULER,
                "y.neczek@gmail.com",
                SUBJECT,
                "Currently you have reached [show number of requests to the external API"
        ));
    }
}
