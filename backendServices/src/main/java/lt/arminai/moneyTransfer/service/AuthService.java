package lt.arminai.moneyTransfer.service;

import lt.arminai.moneyTransfer.model.AuthPojo;

import javax.ejb.Local;

@Local
public interface AuthService {
    AuthPojo createJwt(String username);
}
