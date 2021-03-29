package com.example.demo.order;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import com.example.consumingorderservice.wsdl.ValidationResponse;
import com.example.demo.auth.User;
import com.example.demo.dao.*;

import com.example.demo.dto.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;


@RestController
public class OrderController {
	@Autowired
    HttpServletResponse httpresponse;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
	OrderSOAPClient soapClient;
	
	@Autowired
	ClientDaoImpl clientservice;
	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(method=RequestMethod.POST, value="/api/order", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ValidationResponse sendOrder(@RequestBody Order order) {
		String productName = order.getProduct();
		int quantity = order.getQuantity();
		double price = order.getPrice();
		String side = order.getSide();
		
		 System.out.print( order);
		var response = soapClient.setOrder(productName, quantity, price, side,200.0,5,1);
		return response;
	}
	
	
	
}
