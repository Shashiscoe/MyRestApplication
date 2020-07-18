package com.example.MyRestApplication.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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
import com.example.MyRestApplication.entity.User;
import com.example.MyRestApplication.repository.UserRepository;

@RequestMapping("/usersRep/")
@RestController
public class UserRepositoryController {

	@Autowired
	private UserRepository UserRepository;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("{id}")
	public EntityModel<UserDto> getUser(@PathVariable int id) {

		Optional<User> userdb = UserRepository.findById(id);
		UserDto user = null;
		if (userdb.isPresent()) {
			user = modelMapper.map(userdb.get(), UserDto.class);
		}

		// "all-users", SERVER_PATH + "/users"
		// getUsers
		EntityModel<UserDto> resource = EntityModel.of(user);

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUsers());

		resource.add(linkTo.withRel("all-users"));

		return resource;

	}

	@GetMapping
	public List<UserDto> getUsers() {

		return UserRepository.findAll().stream().map(user -> modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
	}

	@PostMapping
	public ResponseEntity<Object> saveUser(@Valid @RequestBody UserDto userDto) {
		User user = UserRepository.save(modelMapper.map(userDto, User.class));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable int id) {

		UserRepository.deleteById(id);
	}

	@PutMapping
	public UserDto updateUser(@Valid @RequestBody UserDto userDto) {
		return modelMapper.map(UserRepository.save(modelMapper.map(userDto, User.class)), UserDto.class);

	}

}
