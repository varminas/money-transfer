package lt.arminai.moneyTransfer.converter;

import lt.arminai.moneyTransfer.dto.AuthDto;
import lt.arminai.moneyTransfer.model.AuthPojo;

public final class AuthConverter {
    public static AuthDto toDto(AuthPojo authPojo) {
        if (authPojo == null) {
            return null;
        }

        return new AuthDto(authPojo.getJwt(), authPojo.getUserId());
    }
}
