package com.telework.demo.services.strategy;

import com.telework.demo.domain.model.CreateUserForm;
import com.telework.demo.exception.InvalidOperationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StrategyContext {

    private BeanFactory beanFactory;
    private IStrategy strategy;
    private String context;

    @Autowired
    public StrategyContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object createUser(CreateUserForm userForm) {
        final String beanName = userForm.getRole();
        determinateContext(beanName);
        return strategy.createUser(userForm);
    }

    private void determinateContext(String context) {
        final String beanName = context + "Strategy";
        switch (context) {
            case "Admin":
                strategy = beanFactory.getBean(beanName, CreateAdmin.class);
                break;
            case "Developer":
                strategy = beanFactory.getBean(beanName, CreateDeveloper.class);
                break;
            case "Pole Manager":
                strategy = beanFactory.getBean(beanName, CreatePoleManager.class);
                break;
            case "Project Manager":
                strategy = beanFactory.getBean(beanName, CreateProjectManager.class);
                break;
            default:
                throw new InvalidOperationException("Contexte inconnue pour l'enregistrement de l'utilisateur");
        }
    }
}
