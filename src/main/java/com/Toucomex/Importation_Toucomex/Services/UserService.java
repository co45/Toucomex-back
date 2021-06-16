package com.Toucomex.Importation_Toucomex.Services;
import com.Toucomex.Importation_Toucomex.Auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Toucomex.Importation_Toucomex.Auth.model.User;
import org.springframework.beans.factory.annotation.*;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean updateUsername(String email, String username) {
        Optional<User> opt = this.userRepo.findByUsername(username);
        User user;
        if(opt.isPresent()) {
            user =  opt.get();
            user.setUsername(username);
            this.userRepo.save(user);
            return true;
        }
        return false;
    }


    public boolean updatePassword(String username, String oldPass, String newPass) {
        Optional<User> opt = this.userRepo.findByUsername(username);
        User user;
        if(opt.isPresent()) {
            user =  opt.get();

            if(passwordEncoder.matches(oldPass, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPass));
                this.userRepo.save(user);
                return true;
            }

        }
        return false;
    }

}