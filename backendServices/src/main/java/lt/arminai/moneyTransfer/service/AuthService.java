package lt.arminai.moneyTransfer.service;

import javax.ejb.Local;

@Local
public interface AuthService {
    String createJwt(String username);
}
