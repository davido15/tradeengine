package com.example.demo.dao;

import com.example.demo.dto.Client;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ClientDao {
	
    List<Client> getClient();
  
    void   createClient(long clientid,String firstname, String lastname, String email, String password, double funds );
    List<Client> getPassword(String  email);
    void   deleteClient(long clientid);
    void addToken(long clientid, String token);
    void deletetoken(String token);
    List<Client> getToken(String  token);

	

}