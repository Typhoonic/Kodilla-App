package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    CompanyConfig companyConfig;

    @Autowired
    AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String tasksInQueueEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can check and manage your tasks");
        functionality.add("Application allows delete or change tasks");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud/");
        context.setVariable("button", "Visit website");
        context.setVariable("preview", "Information about count of tasks in queue");
        context.setVariable("goodbye", "Have a nice day!");
        context.setVariable("company_name", companyConfig.getName());
        context.setVariable("company_goal", companyConfig.getGoal());
        context.setVariable("company_email", companyConfig.getEmail());
        context.setVariable("company_phone", companyConfig.getPhone());
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/count-task-mail", context);
    }

    public String buildTrelloCardEmail(String message){
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

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
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
