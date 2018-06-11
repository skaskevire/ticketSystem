package com.epam.ts.dao;

import com.epam.ts.entity.User;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component("jdbcUserDao")
public class JDBCUserDao {
    @Autowired
    private DataSource dataSource;

    public List<User> getUsers(String name) {
        Locale.setDefault(Locale.ENGLISH);
        List<User> users = new ArrayList<>();
        String sql = "SELECT ID,NAME," +
                "EMAIL," +
                "PASSWORD," +
                "ROLES " +
                "FROM T_USER U" +
                " WHERE U.NAME = ?";
        JsonNode node = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);


            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setRoles(rs.getString("roles"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return users;
    }

    public User getUserByEmail(String email) {
        Locale.setDefault(Locale.ENGLISH);
        List<User> users = new ArrayList<>();
        String sql = "SELECT ID,NAME," +
                "EMAIL," +
                "PASSWORD," +
                "ROLES " +
                "FROM T_USER U" +
                " WHERE U.EMAIL = ?";
        JsonNode node = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dataSource.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, email);


            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setRoles(rs.getString("roles"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users.get(0);
    }

    public void saveUser(User user) {
        Locale.setDefault(Locale.ENGLISH);
        String sql = "INSERT INTO T_USER (NAME,EMAIL,PASSWORD,ROLES) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dataSource.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRoles());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}