package com.example.demo.dao;

import com.example.demo.dto.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<Client> getClient() {
		  return jdbcTemplate.query("select * from client", new ClientRowMapper());
	}
	
	@Override
	public void createClient(long clientid, String email, String firstname, String password ,String lastname, double funds) {
		 String sql = "Insert into client (clientid,firstname, lastname, email ,password ,funds ) values(?,?,?,?,?,?)";
	        jdbcTemplate.update(sql, new Object[]{clientid,firstname, lastname, email, password ,funds});
		
	}
	
	
	@Override
	public List<Client> getPassword(String email ) {
		 String sql = "select password from client where email = ? ";
		
		 return jdbcTemplate.query(sql, new PasswordRowMapper(), email);
		
	}

	@Override
	public void deleteClient(long id) {
		String sql = "delete from client where clientid =? ";
        jdbcTemplate.update(sql, new Object[]{id});
		
	}
	
	
	   private class ClientRowMapper implements RowMapper<Client>{
	        public Client mapRow(ResultSet rs, int rowNum) throws SQLException{
	            Client e = new Client();
	            e.setClientid(rs.getInt(1));
	            e.setFirstname(rs.getString(2));
	            e.setPassword(rs.getString(3));
	            e.setLastname(rs.getString(4));
	            e.setEmail(rs.getString(5));
	            return e;
	        }
	    }
	   private class PasswordRowMapper implements RowMapper<Client>{
	        public Client mapRow(ResultSet rs, int rowNum) throws SQLException{
	            Client e = new Client();
	            e.setPassword(rs.getString(1));
	            return e;
	        }
	    }

	@Override
	public void addToken(long clientid, String token) {
		String sql = "Insert into tokendata (id,access_token ) values(?,?)";
        jdbcTemplate.update(sql, new Object[]{clientid, token});
		
	}

	@Override
	public void deletetoken(String clientid) {
		String sql = "Delete * tokendata (id ) values(?)";
        jdbcTemplate.update(sql, new Object[]{ clientid});
		
	}

	public List<Client> getToken(String token) {
		 String sql = "select * from tokendata where access_token = ? ";	
		 return jdbcTemplate.query(sql, new ClientRowMapper(), token);
	}

	




	
}