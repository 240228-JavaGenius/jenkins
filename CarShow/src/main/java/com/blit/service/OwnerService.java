package com.blit.service;

import java.util.List;

import com.blit.entity.Owner;

public interface OwnerService {
	Owner getOwner(Long id);
    Owner getOwner(String firstName);
    List<Owner> getOwners();
    void deleteOwner(Long id);
    Owner saveOwner(Owner owner);
    Owner updateOwner(Long id, Owner owner);
}
