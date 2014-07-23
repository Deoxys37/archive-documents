package com.alagezia37.archivedocuments.model.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.alagezia37.archivedocuments.model.User;

/**
 * The class for accessing and modifying a 'users' table in your database.
 * @author 		deoxys
 * @created		08.07.2014
 */
@Component("usersDao")
public class UsersDAO {
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * Setting JDBC (Java Database Connectivity) with beans.xml file and autowired annotation.
	 * @param 	dataSource		an object that has username, password, url, driverClassName fields
	 * 							for establishing a connection to the database
	 */
	@Autowired
	public void setJdbc(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	/**
	 * Getting a user by his login from the users table.
	 * @param 		passport	passport identifier value of a user you want to get information about
	 * @return					user with passport put, if such exists in the table
	 * @about					Named parameters are used for preventing a possible SQL injections.
	 */
	public User getUser(String passport) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("passport", passport.toUpperCase());
		
		try {
			System.out.println(passport);
			return jdbc.queryForObject("select * from users where passport=:passport", params, 
					new RowMapper<User>() {

				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User();
					user.setName(rs.getString("name"));
					user.setSurname(rs.getString("surname"));
					user.setPassport(rs.getString("passport"));
					user.setPassword(rs.getString("password"));
					return user;
				}
			});
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Getting the all users from the users table.
	 * @return					list of users, that are stored in the database
	 */
	public List<User> getUsers() {
		return jdbc.query("select * from users", new RowMapper<User>() {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setPassport(rs.getString("passport"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
	}
	
	public boolean create(User user) {
		if (user.setRegistrationParameters()) {
			return addUser(user) && addAuthority(user);
		}
		else {
			return false;
		}
	}
	
	/**
	 * Adding a new user to the database.
	 * @param 		user		a user bean with name, surname, password, passport, enabled, authority values
	 * @return					true if the new user is created and added to the database, false if not
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 * @about					Named parameters are used for preventing a possible SQL injections.
	 */
	public boolean addUser(User user) {	
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("passport", user.getPassport());
		params.addValue("password", passwordEncoder.encode(user.getPassword()));
		params.addValue("name", user.getName());
		params.addValue("surname", user.getSurname());
		params.addValue("enabled", user.isEnabled());
		
		return (jdbc.update("insert into users (name, surname, password, passport, enabled) " + 
							"values (:name, :surname, :password, :passport, :enabled)", params) == 1);
	}
	
	public boolean addAuthority(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		
		return (jdbc.update("insert into authorities (passport, authority) " + 
			   	"values (:passport, :authority)", params) == 1);
	}
	
	/**
	 * Adding a batch of users to the database. Could be user for transporting one database to another.
	 * @param 		users		a list of user beans with name, surname, password, passport values
	 * @return					true if the new user is created and added to the database, false if not
	 * @about					Named parameters are used for preventing a possible SQL injections.
	 */
	public int[] create(List<User> users) {
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(users.toArray());
		
		return jdbc.batchUpdate("insert into users (name, surname, password, passport) " + 
						   		"values (:name, :surname, :password, :passport)", params);		
	}
	
	/**
	 * Updating an information about the user by its passport in the database.
	 * @param 		user 		a user bean with id, name, surname, password, passport values
	 * @return					true if an information about the user with such login was updated, false if not 
	 * @about					Named parameters are used for preventing a possible SQL injections.
	 */
	public boolean update(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		
		return (jdbc.update("update users set name = :name, surname = :surname, password := password, " + 
							"passport = :passport where id = :id", params)) == 1;
	}
	
	/**
	 * Deletes a user by its id from the database users table.
	 * @param 		id			id of a user to be deleted
	 * @return					true if one user was deleted, false if not
	 * @about					Named parameters are used for preventing a possible SQL injections.
	 */
	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		return (jdbc.update("delete from users where id = :id", params) == 1);
	}

	public boolean exists(String passport) {		
		return jdbc.queryForObject("select count(*) from users where passport=:passport",
				new MapSqlParameterSource("passport", passport), Integer.class) == 1;
	}
}