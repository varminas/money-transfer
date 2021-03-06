package lt.arminai.moneyTransfer.persistence;

import lt.arminai.moneyTransfer.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getById(String userId);

    Optional<User> getByUserName(String username);
}
