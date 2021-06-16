package com.Toucomex.Importation_Toucomex.Controllers;

import com.Toucomex.Importation_Toucomex.Auth.model.User;
import com.Toucomex.Importation_Toucomex.Auth.repository.UserRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import javax.servlet.ServletContext;

@CrossOrigin(origins ="*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserCtrl {

    @Autowired
    ServletContext context;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private UserRepository ur;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return ur.findAll();
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User u = ur.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id :" + id));
        return ResponseEntity.ok(u);
    }


    @GetMapping("/useru/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User u = ur.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with username :" + username));
        return ResponseEntity.ok(u);
    }

    @GetMapping("/role/{username}")
    public List<String> getUserRoleByUsername(@PathVariable String username) {
            List<String> r = ur.getUserRoleByUsername(username);

        return r;
    }

    @GetMapping("/avatar/{id}")
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {

        User user = ur.findById(id).get();
        return Files.readAllBytes(Paths.get(context.getRealPath("/Images/") + user.getPhoto()));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id,
                                                   @Valid @RequestBody User u) throws ResourceNotFoundException {
        User user = ur.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

        user.setName(u.getName());
        user.setPrenom(u.getPrenom());
        user.setDateOfBirth(u.getDateOfBirth());
        user.setPhone(u.getPhone());
        user.setRoles(u.getRoles());
        user.setDepartement(u.getDepartement());
        user.setPassword(encoder.encode(u.getPassword()));


        User updatedUser = ur.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        User user = ur.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

        ur.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
