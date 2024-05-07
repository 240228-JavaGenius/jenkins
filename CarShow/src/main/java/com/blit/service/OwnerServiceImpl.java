package com.blit.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.blit.CarShowApplication;
import com.blit.entity.Owner;
import com.blit.repository.OwnerRepository;

@Service
public class OwnerServiceImpl implements OwnerService {
	@Autowired
	private OwnerRepository ownerRepository;
	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);

	@Override
	public Owner getOwner(Long id) {
		return ownerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Owner not found with id: " + id));
	}

	@Override
	public Owner getOwner(String firstName) {
		return ownerRepository.findOwnerByFirstName(firstName)
				.orElseThrow(() -> new ResourceNotFoundException("Owner not found with name " + firstName));
	}

	@Override
	public List<Owner> getOwners() {
		return (List<Owner>) ownerRepository.findAll();
	}

	@Override
	public void deleteOwner(Long id) {
		ownerRepository.deleteById(id);
	}

	@Override
	public Owner saveOwner(Owner owner) {
		return ownerRepository.save(owner);
	}

	@Override
	public Owner updateOwner(Long id, Owner owner) {
		
		Optional<Owner> optionalOwner = ownerRepository.findById(id);
		logger.warn(owner.toString());
		if (optionalOwner.isPresent()){
			logger.warn(optionalOwner.get().toString());
			optionalOwner.get().setOwnerId(id);
			optionalOwner.get().setFirstName(owner.getFirstName());
			optionalOwner.get().setLastName(owner.getLastName());
			return ownerRepository.save(optionalOwner.get());
		} else
			throw new ResourceNotFoundException("Owner not found with id: " + id);

	}
}