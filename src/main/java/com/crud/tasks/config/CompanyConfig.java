package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CompanyConfig {

    @Value("${info.company.name}")
    String name;

    @Value("${info.company.goal}")
    String goal;

    @Value("${info.company.email}")
    String email;

    @Value("${info.company.phone}")
    String phone;
}
