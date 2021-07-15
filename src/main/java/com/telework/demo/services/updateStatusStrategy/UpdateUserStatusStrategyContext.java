package com.telework.demo.services.updateStatusStrategy;

import com.telework.demo.domain.entity.User;
import com.telework.demo.domain.entity.enumeration.UserStatus;
import com.telework.demo.exception.InvalidOperationException;
import com.telework.demo.repository.IUserRepository;
import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.telework.demo.exception.ErrorMessages.USER_NOT_FOUND_BY_ID;

@Service
public class UpdateUserStatusStrategyContext {
    private BeanFactory beanFactory;

    private IUpdateStatusStrategy strategy;

    private final IUserRepository userRepository;
    @Setter
    private String context;

    @Autowired
    public UpdateUserStatusStrategyContext(BeanFactory beanFactory, IUserRepository userRepository) {
        this.beanFactory = beanFactory;
        this.userRepository = userRepository;
    }

    public Object updateStatus(Integer id, UserStatus userStatus) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new InvalidOperationException(USER_NOT_FOUND_BY_ID);
        }
        context = optionalUser.get().getRole().getRoleName();
        determinateContext(context);
        return strategy.updateStatus(id, userStatus);
    }

    private void determinateContext(String context) {
        final String beanName = "updateStatusStrategy" + context;

        switch (context) {
            case "Admin":
                strategy = beanFactory.getBean(beanName, UpdateStatusAdmin.class);
                break;
            case "Developer":
                strategy = beanFactory.getBean(beanName, UpdateStatusDeveloper.class);
                break;
            case "Pole Manager":
                strategy = beanFactory.getBean(beanName, UpdateStatusPoleManager.class);
                break;
            case "Project Manager":
                strategy = beanFactory.getBean(beanName, UpdateStatusProjectManager.class);
                break;

            default:
                throw new InvalidOperationException("Contexte inconnue pour la mise à jour de statut de l'utilisateur");
        }
    }
}
