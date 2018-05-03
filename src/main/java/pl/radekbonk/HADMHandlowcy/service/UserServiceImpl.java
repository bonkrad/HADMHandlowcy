package pl.radekbonk.HADMHandlowcy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.radekbonk.HADMHandlowcy.model.User;
import pl.radekbonk.HADMHandlowcy.repository.RoleRepository;
import pl.radekbonk.HADMHandlowcy.repository.UserRepository;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void saveUser(User user) throws Exception{
        if(!String.valueOf(userRepository.findByUsername(user.getUsername())).equals("null")) {
            throw new Exception("UserExist");
        } else if(!user.getPasswordConfirm().equals(user.getPassword())){
            throw new Exception("PasswordError");
        }
            else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRoles(new HashSet<>(roleRepository.findAll()));
            userRepository.save(user);
        }
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
