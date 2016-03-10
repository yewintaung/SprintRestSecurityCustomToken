package com.ywa.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ywa.domain.Account;
import com.ywa.domain.Role;
import com.ywa.helper.RoleConstant;
import com.ywa.repository.AccountRepository;
import com.ywa.repository.RoleRepository;

/**
 * Data initialization service
 * 
 * @author maachou
 *
 */
@Service("initDataService")
public class InitDataService {


    @Autowired
    AccountRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    PasswordEncoder encoder;
    
    @PostConstruct
    public void init() {
        /* A user with admin right */
        Role adminRole = getRole(RoleConstant.ROLE_ADMIN);
        Role userRole = getRole(RoleConstant.ROLE_USER);
        Role editorRole = getRole(RoleConstant.ROLE_EDITOR);

        /* ADMIN Role */
        Account admin = new Account();
        admin.setFirstName("Admin");
        admin.setLastName("admin");
        admin.setEmail("admin@mail.com");
        admin.setPassword(encoder.encode("admin"));
        admin.setIsEnabled(true);
        this.userRepository.save(admin);
        admin.getRoles().add(adminRole);
        admin.getRoles().add(editorRole);
        admin.getRoles().add(userRole);
        this.userRepository.save(admin);
        
        /* EDITOR Role */
        Account editor = new Account();
        editor.setFirstName("Editor");
        editor.setLastName("editor");
        editor.setEmail("editor@mail.com");
        editor.setPassword(encoder.encode("editor"));
        editor.setIsEnabled(true);
        this.userRepository.save(editor);
        editor.getRoles().add(editorRole);
        editor.getRoles().add(userRole);
        this.userRepository.save(editor);
        
        /* USER Role */
        Account user = new Account();
        user.setFirstName("User");
        user.setLastName("user");
        user.setEmail("user@mail.com");
        user.setPassword(encoder.encode("user"));
        user.setIsEnabled(true);
        this.userRepository.save(user);
        user.getRoles().add(userRole);
        this.userRepository.save(user);
        
    }

    private Role getRole(final String roleId) {
        Role result = roleRepository.findByRoleName(roleId);
        if (result == null) {
            result = new Role();
            result.setRoleName(roleId);
            roleRepository.save(result);
        }
        return result;
    }
    
}
