package pl.radekbonk.HADMHandlowcy.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
