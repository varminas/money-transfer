package lt.arminai.moneyTransfer.service;

import lt.arminai.moneyTransfer.model.User;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface UserService {
    Optional<User> getUser(String userId);
}
