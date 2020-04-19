package com.janflpk.collectionsmanager.backend.service;

//import com.crud.tasks.config.AdminConfig;
//import com.crud.tasks.config.CompanyConfig;
import com.janflpk.collectionsmanager.backend.domain.Mail;
import com.janflpk.collectionsmanager.backend.domain.MailGeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    public static final String GOODBYE_MESSAGE = "Thank you for using our website!";

/*    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private AdminConfig adminConfig;*/

/*    @Autowired
    private MailGeneratorType mailGeneratorType;*/

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String chooseTemplate(Mail mail) {
        if (mail.getMailGeneratorType().equals(MailGeneratorType.EMAIL_FROM_TRELLO_CARD_CREATE)) {
            return buildTrelloCardEmail(mail.getMessage(), mail.getMailGeneratorType().getMailTemplateFileName());
        } else if (mail.getMailGeneratorType().equals(MailGeneratorType.EMAIL_FROM_EMAIL_SCHEDULER)){
            return buildEmailSchedulerEmail(mail.getMessage(), mail.getMailGeneratorType().getMailTemplateFileName());
        }
        return mail.getMailGeneratorType().values() + " nie istenieje.";
    }

    public String buildTrelloCardEmail (String message, String templatePath) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = commonContext(message);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process(templatePath, context);
    }

    public String buildEmailSchedulerEmail (String message, String templatePath) {
        List<String> emailSchedulerConfigurationOptions = new ArrayList<>();
        emailSchedulerConfigurationOptions.add("You can cancel this service any time");
        emailSchedulerConfigurationOptions.add("You can change frequency of sending this information any time");

        Context context = commonContext(message);
        context.setVariable("emailInformation", "This email is generated automatically. Please do not reply.");
        context.setVariable("email_scheduler_options", emailSchedulerConfigurationOptions);
        return templateEngine.process(templatePath, context);
    }

    private Context commonContext(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://j-fi.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", "Jan Filipek");
        context.setVariable("goodbye_message", GOODBYE_MESSAGE);
        context.setVariable("companyConfig", "Company name");
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        return context;
    }
}
