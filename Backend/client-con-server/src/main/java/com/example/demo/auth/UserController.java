package com.example.demo.auth;

import com.example.demo.dao.*;
import com.example.demo.dto.Client;
import com.example.demo.order.OrderSOAPClient;


import java.io.IOException;
import java.io.StringWriter;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.json.simple.JSONObject;

@RestController
public class UserController {

	@Autowired
	HttpServletResponse httpresponse;
	@Autowired
	HttpServletRequest httprequest;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	OrderSOAPClient soapClient;

	@Autowired
	ClientDaoImpl clientservice;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(method = RequestMethod.POST, value = "/api/createuser" , consumes = "application/json")
	@ResponseBody
	public String createuser(@RequestBody User user) {

		// String token = httprequest.getHeader("Authorization");
		// System.out.print("authhhhhhhhhhh"+token);

		
			int min = 1;
			int max = 121345;
			int userid = (int) (Math.random() * (max - min + 1) + min);

			String hashpass = passwordEncoder.encode(user.getPassword());
			
			clientservice.createClient(userid, user.getEmail(), user.getFirstname(), hashpass, user.getLastname(),user.getFunds());
			
			
			System.out.print("passsssssss" + hashpass);

		

		    return "UserCreated";
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(method = RequestMethod.POST, value = "/api/login", consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Object> login(@RequestBody User user) throws IOException {
		String finaltoken;
		List<Client> response = clientservice.getPassword(user.getEmail());

		System.out.print("got..................."+user.getEmail()+"....."+user.getPassword());
		for (int i = 0; i < response.size(); i++) {
			System.out.println("loooooooooooooop"+   response.get(i).getClientid()  );
			System.out.println("loooooooooooooop"+   response.get(i).getEmail()  );
			System.out.println("loooooooooooooop"+   response.get(i).getPassword()  );
			System.out.println("loooooooooooooop"+   response.get(i).getFirstname()  );
			System.out.println("loooooooooooooop"+   response.get(i).getLastname()   );
			System.out.println("loooooooooooooop"+   response.get(i).getFunds()  );
		}

		boolean passwordValid = passwordEncoder.matches(user.getPassword(), response.get(0).getPassword());

		if (passwordValid == true) {
			System.out.println(" Your token is being generated");
		    finaltoken = user.getEmail() + "@" + generateToken();
			System.out.println(finaltoken + " Your final token is generated");
			clientservice.addToken(finaltoken);
			httpresponse.addHeader("UserToken", finaltoken);
		} else {
			System.out.println("User Password is not Valid.");

			try {
				httpresponse.sendError(401, "Invalid Credentials");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return ResponseEntity.badRequest().body("Invalid Credentials");

		}
		
		JSONObject obj = new JSONObject();

	      obj.put("message","sucess");
	      obj.put("Error","none");
	      obj.put("token",new String (finaltoken));
	    
	      StringWriter out = new StringWriter();
	      obj.writeJSONString(out);
	      
	      String jsonText = out.toString();
	      System.out.print(jsonText);
	    
		return ResponseEntity.ok().body(jsonText);
		
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(method = RequestMethod.POST, value = "/api/logout", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public String userlogout(@RequestBody User user) {
		clientservice.deleteClient(user.getClientid());
		return "success";
	}

	public boolean validateToken(String token) {
		int total = clientservice.getToken(token).size();
		if (total > 0) {
			return true;
		} else {
			return false;
		}

	}

	private static final SecureRandom secureRandom = new SecureRandom();
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

	public static String generateToken() {
		byte[] randomBytes = new byte[20];
		secureRandom.nextBytes(randomBytes);
		return base64Encoder.encodeToString(randomBytes);
	}

}
