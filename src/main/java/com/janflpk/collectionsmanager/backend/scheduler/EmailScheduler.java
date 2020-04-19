package com.janflpk.collectionsmanager.backend.scheduler;

import com.janflpk.collectionsmanager.backend.domain.Mail;
import com.janflpk.collectionsmanager.backend.domain.MailGeneratorType;
import com.janflpk.collectionsmanager.backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Books: once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IsbndbController isbndbController;

    @Scheduled(fixedDelay = 10000000) //@Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        float size = IsbndbController.numberOfCalls;
        String t = " request";
        if (size != 1) t += "s";
        simpleEmailService.send(new Mail(
                MailGeneratorType.EMAIL_FROM_EMAIL_SCHEDULER,
                "y.neczek@gmail.com",
                SUBJECT,
                "Currently you have reached " + String.format("%.0f%%", size/50*100) + " of " + t
        ));
    }
}
