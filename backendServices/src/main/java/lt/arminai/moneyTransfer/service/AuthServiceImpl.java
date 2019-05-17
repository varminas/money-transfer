package lt.arminai.moneyTransfer.service;

import com.ibm.websphere.security.jwt.Claims;
import com.ibm.websphere.security.jwt.JwtBuilder;
import lombok.NoArgsConstructor;
import lt.arminai.moneyTransfer.model.AuthPojo;
import lt.arminai.moneyTransfer.model.User;
import lt.arminai.moneyTransfer.persistence.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Local;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
@Local(value = AuthService.class)
@NoArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);


    private SecurityContext securityContext;
    private UserRepository userRepository;

    @Inject
    public AuthServiceImpl(SecurityContext securityContext, UserRepository userRepository) {
        this.securityContext = securityContext;
        this.userRepository = userRepository;
    }

    @Override
    public AuthPojo createJwt(String username) {
        User user = userRepository.getByUserName(username)
                .orElseThrow(() -> new RuntimeException(String.format("No user found for username %s", username)));

        try {
            String jwt = buildJwt(username, user.getId(), getRoles());
            return new AuthPojo(jwt, user.getId());
        } catch (Exception e) {
            String errorMessage = String.format("Error occurred while generating JWT %s", e.getMessage());
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    private String buildJwt(String userName, String userId, Set<String> roles) throws Exception {
        return JwtBuilder.create("jwtFrontEndBuilder")
                .claim(Claims.SUBJECT, userName)
                .claim("upn", userName) // MP-JWT defined subject claim
                .claim("groups", roles.toArray(new String[roles.size()])) // MP-JWT builds an array from this
                .claim("userId", userId)
                .buildJwt()
                .compact();
    }

    private Set<String> getRoles() {
        Set<String> roles = new HashSet<>();
        boolean isAdmin = securityContext.isCallerInRole("ADMIN");
        boolean isUser = securityContext.isCallerInRole("USER");
        if (isAdmin) {
            roles.add("ADMIN");
        }
        if (isUser) {
            roles.add("USER");
        }
        return roles;
    }
}
