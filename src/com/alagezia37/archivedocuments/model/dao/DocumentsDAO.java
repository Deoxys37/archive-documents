package com.alagezia37.archivedocuments.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.alagezia37.archivedocuments.model.Document;

@Component("DocumentsDao")
public class DocumentsDAO {
	private NamedParameterJdbcTemplate jdbc;

	public List<Document> getDocuments(String passport) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("passport", passport.toUpperCase());

		return jdbc.query("select * from documents where passport=:passport",
				new RowMapper<Document>() {

					public Document mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Document document = new Document();
						document.setFund(rs.getString("fund"));
						document.setAnagraph(rs.getString("anagraph"));
						document.setFile(rs.getString("file"));
						document.setPermission(rs.getBoolean("permission"));
						return document;
					}
				});
	}

	public boolean givePermission(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("permission", 1);
		params.addValue("id", id);

		return (jdbc.update("select documents set permission=:permission"
				+ "where id=:id", params) == 1);
	}

	public boolean create(Document document) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(document);
		
		return (jdbc.update("insert into documents (passport, fund, anagraph, file, permission) " + 
					"values (:passport, :fund, :anagraph, :file, :permission)", params) == 1);
	}
	
	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		return (jdbc.update("delete from documents where id:=id", params) == 1);
	}
}