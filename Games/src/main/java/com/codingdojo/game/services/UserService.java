package com.codingdojo.game.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.game.models.LoginUser;
import com.codingdojo.game.models.User;
import com.codingdojo.game.repositories.UserRepository;



@Service
public class UserService {
  @Autowired
  private UserRepository userRepo;

  public User register(User newUser, BindingResult result) {

    if (result.hasErrors()) {
      return null;
    }

    if (userRepo.findByEmail(newUser.getEmail()).isPresent()) {

      result.rejectValue("email", "Duplicate",
          "Email is already registered");
      return null;
    }

    if (!newUser.getUserName().matches("[a-zA-Z]+")) {

      result.rejectValue("userName", "not letters", "The User Name must only consist of letters! ");

    }


    if (!newUser.getPassword().equals(newUser.getConfirm())) {
      result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
      return null;
    }

    String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    newUser.setPassword(hashedPassword);
    return userRepo.save(newUser);

  }


  public User login(LoginUser newLoginObject, BindingResult result) {


    if (result.hasErrors()) {
      return null;
    }

    Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());

    if (potentialUser.isEmpty()) {
      result.rejectValue("email", "Not Found", "User not found");
      return null;
    }

    User user = potentialUser.get();

    if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
      result.rejectValue("password", "Invalid", "Invalid password");
      return null;
    }
    return user;
  }

  public User findUserById(Long id) {
    return userRepo.findById(id).get();
  }

}
