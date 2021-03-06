package lt.arminai.moneyTransfer.persistence;

import lt.arminai.moneyTransfer.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    @Override
    public Optional<User> getById(String userId) {
        User user = em.find(User.class, userId);

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getByUserName(String username) {
        String SQL = "SELECT u FROM User u WHERE u.username = :username";

        try {
            return Optional.of(em.createQuery(SQL, User.class)
                    .setParameter("username", username)
                    .getSingleResult()
            );
        } catch (NoResultException e) {
            logger.warn("No data found for username {}", username);
            return Optional.empty();
        }
    }
}
