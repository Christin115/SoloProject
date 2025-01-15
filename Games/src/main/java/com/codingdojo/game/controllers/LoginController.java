package com.codingdojo.game.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.game.models.LoginUser;
import com.codingdojo.game.models.User;
import com.codingdojo.game.services.GameService;
import com.codingdojo.game.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LoginController {
	 @Autowired
	  private UserService userServ;
	 @Autowired
	 GameService game;

	  @RequestMapping("/")
	  public String showForm(@ModelAttribute("newUser") User user, Model model) {
	    model.addAttribute("newUser", new User());
	    model.addAttribute("newLogin", new LoginUser());
	    return "index.jsp";
	  }

	  @PostMapping("/register")
	  public String register(@Valid @ModelAttribute("newUser") User newUser,
	      BindingResult result, Model model, HttpSession session) {

	    User registeredUser = userServ.register(newUser, result);

	    session.setAttribute("newUser", registeredUser);
	    if (result.hasErrors()) {
	      model.addAttribute("newLogin", new LoginUser());
	      return "index.jsp";
	    }

	    session.setAttribute("newUser", registeredUser.getId());
	    return "redirect:/home";
	  }

	  @PostMapping("/login")
	  public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
	      BindingResult result, Model model, HttpSession session) {

	    User user = userServ.login(newLogin, result);

	    if (result.hasErrors()) {
	      model.addAttribute("newUser", new User());
	      return "index.jsp";
	    }

	    session.setAttribute("newUser", user.getId());
	    return "redirect:/home";
	  }

	  @GetMapping("/home")
	  public String homePage(Model model, HttpSession session) {
	    Long newUserId = (Long) session.getAttribute("newUser");

	    if (newUserId == null) {
	      return "redirect:/";
	    }

	    User thisUser = userServ.findUserById(newUserId);

	    if (thisUser == null) {
	      return "redirect:/";
	    }

	    model.addAttribute("thisUser", thisUser);
	    model.addAttribute("games",game.allGames());
	    return "home.jsp";
	  }

	  @GetMapping("/logout")
	  public String logout(HttpSession session) {
	    session.setAttribute("newUser",null);
	    return "redirect:/";
	  }

	}


