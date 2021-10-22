package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    @Autowired
    CompanyConfig companyConfig;

    @Autowired
    AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message){
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview", "Information about create task in trello card");
        context.setVariable("goodbye", "Have a nice day!");
        context.setVariable("company_name", companyConfig.getName());
        context.setVariable("company_goal", companyConfig.getGoal());
        context.setVariable("company_email", companyConfig.getEmail());
        context.setVariable("company_phone", companyConfig.getPhone());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
