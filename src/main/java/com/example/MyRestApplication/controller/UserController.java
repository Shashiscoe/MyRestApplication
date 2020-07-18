package com.example.MyRestApplication.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.MyRestApplication.dto.User;

@RequestMapping("/users/")
@RestController
public class UserController {

	public static List<User> users = new ArrayList<User>();

	{
		users.add(new User(1, "ravi kumar", "Sasaram", new Date()));
		users.add(new User(2, "amit kumar", "Delhi", new Date()));
		users.add(new User(3, null, "", new Date()));
	}

	@GetMapping("{id}")
	public EntityModel<User> getUser(@PathVariable int id) {

		if (users.size() - 1 < id) {
			throw new UserNotFoundException("User not found with id - " + id);
		}
		User user = users.get(id);

		// "all-users", SERVER_PATH + "/users"
		// getUsers
		EntityModel<User> resource = EntityModel.of(user);

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUsers());

		resource.add(linkTo.withRel("all-users"));

		return resource;

	}

	@GetMapping
	public List<User> getUsers() {

		return users;
	}

	@PostMapping
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		users.add(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
