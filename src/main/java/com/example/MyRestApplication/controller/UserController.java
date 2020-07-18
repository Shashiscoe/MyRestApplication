package com.example.MyRestApplication.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.MyRestApplication.dto.UserDto;
import com.example.MyRestApplication.service.UserService;

@RequestMapping("/users/")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	public static List<UserDto> users = new ArrayList<UserDto>();

	{
		users.add(new UserDto(1, "ravi kumar", "Sasaram", new Date()));
		users.add(new UserDto(2, "amit kumar", "Delhi", new Date()));
		users.add(new UserDto(3, null, "", new Date()));
	}

	@GetMapping("{id}")
	public EntityModel<UserDto> getUser(@PathVariable int id) {

		if (users.size() - 1 < id) {
			throw new UserNotFoundException("User not found with id - " + id);
		}
		UserDto user = userService.getUser(id);

		// "all-users", SERVER_PATH + "/users"
		// getUsers
		EntityModel<UserDto> resource = EntityModel.of(user);

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUsers());

		resource.add(linkTo.withRel("all-users"));

		return resource;

	}

	@GetMapping
	public List<UserDto> getUsers() {

		return userService.getUsers();
	}

	@PostMapping
	public ResponseEntity<Object> saveUser(@Valid @RequestBody UserDto userDto) {
		int id = userService.saveUser(userDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable int id) {

		userService.deleteUser(id);
	}

	@PutMapping
	public UserDto updateUser(@Valid @RequestBody UserDto userDto) {
		return userService.updateUser(userDto);

	}

}
