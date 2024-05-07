package com.blit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blit.entity.Owner;
import com.blit.service.OwnerService;

@RestController
@RequestMapping("/api/v1/owner")
public class OwnerController {
	@Autowired
	private OwnerService ownerService;

	@GetMapping("all")
	public ResponseEntity<List<Owner>> getOwners() {
		return new ResponseEntity<>(ownerService.getOwners(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Owner> getOwner(@PathVariable Long id) {
		return new ResponseEntity<>(ownerService.getOwner(id), HttpStatus.OK);
	}

	@GetMapping("name/{name}")
	public ResponseEntity<Owner> getOwner(@PathVariable String name) {
		return new ResponseEntity<>(ownerService.getOwner(name), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Owner> addOwner(@RequestBody Owner owner) {
		return new ResponseEntity<>(ownerService.saveOwner(owner), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner owner) {
		return new ResponseEntity<>(ownerService.updateOwner(id, owner), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Owner> deleteOwner(@PathVariable Long id) {
		ownerService.deleteOwner(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}