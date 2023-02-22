package com.cg.oam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.entity.Address;
import com.cg.oam.exception.EmptyInputException;
import com.cg.oam.exception.InvalidInputException;
import com.cg.oam.exception.NoSuchElementException;
import com.cg.oam.repository.IAddressRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AddressService {

	@Autowired
	private IAddressRepository addressRepository;

	

	public Address addAddress(Address adrs) {
		if (adrs.getLandmark().isEmpty() || adrs.getCity().isEmpty()) {
			throw new EmptyInputException();
		}
		if (adrs.getHouseNumber() < 0 && adrs.getPinCode() < 0) {
			throw new InvalidInputException();
		}
		return addressRepository.save(adrs);
	}

	public Address findAddressById(int id) {
		Optional<Address> address = addressRepository.findById(id);
		if (address == null) {
			throw new NoSuchElementException();
		}
		return addressRepository.findById(id).get();
	}

	public void deleteAddressById(int deptId) {
		addressRepository.deleteById(deptId);
	}
	
	
	/*
	 * @Transactional public List<Address> findAllAddress() { List<Address> adList =
	 * addressRepository.findAll(); return adList; }
	 */

}
