package com.epam.ts.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

@Component("jdbcUserDao")
public class JDBCUserDao implements UserDao{
    @Autowired
    private DataSource dataSource;

    @Override
    public String getUser(String name) {
        Locale.setDefault(Locale.ENGLISH);
                JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);
       String sql = "SELECT U.NAME,"+
                "U.EMAIL," +
                "R.RNAME," +
                "R.ACCESS_LEVEL " +
        "FROM T_USER U" +
             " LEFT JOIN T_USER_ROLE UR" +
                  " ON U.ID = UR.T_USER" +
             " LEFT JOIN T_ROLE R" +
                   " ON UR.T_ROLE = R.ID WHERE U.NAME = ?";
        JsonNode node = null;
       try {
           JsonNodeRowMapper jnrm = new JsonNodeRowMapper(new ObjectMapper());
           node = jdbcTemplateObject.query(sql,jnrm, new Object[]{name}).get(0);
       } catch (Exception e)
       {
           e.printStackTrace();
       }


            return node.toString();
    }
}
