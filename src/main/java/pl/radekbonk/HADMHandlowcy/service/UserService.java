package pl.radekbonk.HADMHandlowcy.service;

import pl.radekbonk.HADMHandlowcy.model.User;

public interface UserService {
    void saveUser(User user) throws Exception;

    User findByUsername(String username);
}
