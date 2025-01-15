package com.codingdojo.game.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.game.models.Game;
import com.codingdojo.game.models.User;
import com.codingdojo.game.services.GameService;
import com.codingdojo.game.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {

	
	@Autowired
	UserService users;
	@Autowired
	GameService games;
	
	@GetMapping("/view/{id}/game")
	public String showGame(Model model, @PathVariable("id") Long id,  HttpSession session) {
		Long newUserId = (Long) session.getAttribute("newUser");

	    if (newUserId == null) {
	      return "redirect:/";
	    }

		model.addAttribute("game", games.findGame(id));
		return "showGame.jsp";
	}
	
	@GetMapping("/game/form")
	public String gameFormS(@ModelAttribute("game") Game game, HttpSession session) {
		Long newUserId = (Long) session.getAttribute("newUser");

	    if (newUserId == null) {
	      return "redirect:/";
	    }
		return "gameForm.jsp";
	}
	
	@GetMapping("/edit/{id}/game")
	public String editGame(@PathVariable("id") Long id, Model model,  HttpSession session) {
		Long newUserId = (Long) session.getAttribute("newUser");

	    if (newUserId == null) {
	      return "redirect:/";
	    }
		model.addAttribute("game", games.findGame(id));
		return "editGame.jsp";
	}
	
	@PutMapping("/update/{id}/game")
	public String updateGame(@Valid @ModelAttribute("game") Game game, BindingResult result,@PathVariable("id") Long id, Model model, HttpSession session ) {
		Long newUserId = (Long) session.getAttribute("newUser");

	    if (newUserId == null) {
	      return "redirect:/";
	    }
		if(result.hasErrors()) {
			model.addAttribute("game", games.findGame(id));
			return "editGame.jsp";
		}
		Long userId = (Long) session.getAttribute("newUser");
		User creator = users.findUserById(userId);
		game.setCreator(creator);
		games.updateGame(game);
		return "redirect:/home";
	}
	
	@PostMapping("/create/game")
	public String createGame(@Valid @ModelAttribute("game") Game game, BindingResult result, HttpSession session) {
		Long newUserId = (Long) session.getAttribute("newUser");

	    if (newUserId == null) {
	      return "redirect:/";
	    }
		if(result.hasErrors()) {
		return "gameForm.jsp";
		} 
		Long userId = (Long) session.getAttribute("newUser");
		User creator = users.findUserById(userId);
		game.setCreator(creator);
		games.createGame(game);
		return "redirect:/home";
	}
	
	
	@DeleteMapping("/delete/{id}/game")
	public String deleteGame(@PathVariable("id") Long id, HttpSession session) {
		Long newUserId = (Long) session.getAttribute("newUser");

	    if (newUserId == null) {
	      return "redirect:/";
	    }
		games.deleteGame(id);
		return "redirect:/home";
	}
}
