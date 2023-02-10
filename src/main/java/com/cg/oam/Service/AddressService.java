package com.cg.oam.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.oam.Entity.Address;
import com.cg.oam.Repository.IAddressRepository;
import com.cg.oam.exception.EmptyInputException;
import com.cg.oam.exception.InvalidInputException;
import com.cg.oam.exception.NoSuchElementException;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AddressService {

	@Autowired
	private IAddressRepository addressRepository;

	@Transactional
	public List<Address> findAllAddress() {
		List<Address> adList = addressRepository.findAll();
		return adList;
	}

	public Address addAddress(Address adrs) {
		if (adrs.getLandmark() == null|| adrs.getLandmark().length() == 0 || adrs.getCity() == null
				|| adrs.getCity().length() == 0) {
			throw new EmptyInputException();
		}
		if (adrs.getHouseNumber() == 0 || adrs.getHouseNumber() < 0 || adrs.getPinCode() == 0
				|| adrs.getPinCode() < 0) {
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

}
