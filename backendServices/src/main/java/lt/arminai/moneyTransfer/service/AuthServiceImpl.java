package lt.arminai.moneyTransfer.service;

import com.ibm.websphere.security.jwt.Claims;
import com.ibm.websphere.security.jwt.JwtBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Local;
import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.Set;

@ApplicationScoped
@Local(value = AuthService.class)
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Override
    public String createJwt(String username) {
        try {
            return buildJwt(username, Collections.emptySet());
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
}
