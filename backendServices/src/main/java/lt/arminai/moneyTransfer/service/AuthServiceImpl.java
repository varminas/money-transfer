package lt.arminai.moneyTransfer.service;

import com.ibm.websphere.security.jwt.Claims;
import com.ibm.websphere.security.jwt.JwtBuilder;
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
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Inject
    private SecurityContext securityContext;

    @Override
    public String createJwt(String username) {
        try {
            return buildJwt(username, getRoles());
        } catch (Exception e) {
            String errorMessage = String.format("Error occurred while generating JWT %s", e.getMessage());
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    private String buildJwt(String userName, Set<String> roles) throws Exception {
        return JwtBuilder.create("jwtFrontEndBuilder")
                .claim(Claims.SUBJECT, userName)
                .claim("upn", userName) // MP-JWT defined subject claim
                .claim("groups", roles.toArray(new String[roles.size()])) // MP-JWT builds an array from this
                .claim("customClaim", "customValue")
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
