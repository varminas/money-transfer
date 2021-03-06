package lt.arminai.moneyTransfer.service;

import lt.arminai.moneyTransfer.model.User;
import lt.arminai.moneyTransfer.persistence.UserRepository;

import javax.ejb.Local;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
@Local(value = UserService.class)
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Override
    public Optional<User> getUser(String userId) {
        return userRepository.getById(userId);
    }
}
