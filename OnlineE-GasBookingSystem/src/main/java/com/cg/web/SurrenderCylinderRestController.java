package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.SuccessMessage;
import com.cg.entity.Customer;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.SurrenderCylinderNotFoundException;
import com.cg.service.ISurrenderCylinderService;
import com.cg.util.CgUtil;


/*********************************************************************************************
 *          @author          Debabrata Deb
 *          Description      It is a controller class that provides the services for surrendering 
                             a connection, fetching all the surrendered customer details and 
                             viewing surrendered customer details for a given year           
 *         Version           1.0     
 *         Created Date      20-MAY-2021
 **********************************************************************************************/



@RestController
public class SurrenderCylinderRestController {

	@Autowired
	private ISurrenderCylinderService surrenderCylinderService;
	
	/************************************************************************************
	 * 	Method: surrenderCylinder
     * 	Description: To surrender a connection
     * 	@PathVariable customerid - Customers ID
	 * 	@returns SuccessMessage  - surrender cylinder id, if customer found  
     *	Created By - Debabrata Deb
     *	Created Date - 20-MAY-2021                           
	
	 ************************************************************************************/


	@PostMapping("surrendercylinder/{customerid}")
	public SuccessMessage surrenderCylinder(@PathVariable("customerid") int customerId)
			throws CustomerNotFoundException {
		int surrenderId = surrenderCylinderService.surrenderCylinder(customerId);
		return new SuccessMessage(CgUtil.SURRENDER_CYLINDER + surrenderId);
	}
	
	
	/************************************************************************************
	 * 	Method: viewAllSurrenderedCustomer
     * 	Description: To view all surrendered customers
	 * 	@returns List<Customer>  - all available surrendered customers  
     *	Created By - Debabrata Deb
     *	Created Date - 20-MAY-2021                           
	
	 ************************************************************************************/

	

	@GetMapping("viewallsurrenderedcustomer")
	public List<Customer> viewAllSurrenderedCustomer()
			throws CustomerNotFoundException, SurrenderCylinderNotFoundException {
		return surrenderCylinderService.viewAllSurrenderedCustomer();
	}

	
	/************************************************************************************
	 * 	Method: viewSurrenderedCustomer
     * 	Description: To view surrendered customers for a given year
	 * 	@returns List<Customer>  - all surrendered customers for the given year  
     *	Created By - Debabrata Deb
     *	Created Date - 20-MAY-2021                           
	
	 ************************************************************************************/
	
	
	@GetMapping("viewsurrendercustomer/{year}")
	public List<Customer> viewSurrenderCustomer(@PathVariable("year") int year) throws CustomerNotFoundException {
		return surrenderCylinderService.viewSurrenderCustomer(year);
	}

}
