package com.cg.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.CustomerDto;
import com.cg.dto.SuccessMessage;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.CylinderNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;
import com.cg.exception.ValidateException;
import com.cg.service.ICustomerService;
import com.cg.util.CgUtil;

@RestController
public class CustomerRestController {

	@Autowired
	private ICustomerService customerService;

	@PostMapping("addcustomer")
	public SuccessMessage addCustomer(@Valid @RequestBody CustomerDto customerDto, BindingResult br)
			throws ValidateException, CylinderTypeMismatchException, CylinderNotFoundException {
		if (br.hasErrors()) {
			throw new ValidateException(br.getFieldErrors());
		}
		int custId = customerService.insertCustomer(customerDto);
		return new SuccessMessage(CgUtil.CUSTOMER_CREATED + custId);

	}

	@PutMapping("editcustomer/{customerid}")
	public SuccessMessage editCustomer(@Valid @RequestBody CustomerDto customerDto,
			@PathVariable("customerid") int customerId, BindingResult br)
			throws ValidateException, CustomerNotFoundException, CylinderTypeMismatchException {
		if (br.hasErrors()) {
			throw new ValidateException(br.getFieldErrors());
		}
		customerService.updateCustomer(customerDto, customerId);
		return new SuccessMessage(CgUtil.CUSTOMER_UPDATED);
	}

	@PutMapping("linkaadhar/{customerid}/{aadharno}")
	public SuccessMessage linkAadhar(@PathVariable("customerid") int customerId,
			@PathVariable("aadharno") String aadharno) throws CustomerNotFoundException {
		customerService.linkAadhar(customerId, aadharno);
		return new SuccessMessage(CgUtil.AADHAR_LINKED);
	}

}
