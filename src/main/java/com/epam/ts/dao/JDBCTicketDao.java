package com.epam.ts.dao;

import com.epam.ts.entity.Ticket;
import com.epam.ts.entity.User;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository("jdbcTicketDao")
public class JDBCTicketDao {
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void reserveTicket(Integer id, Integer userId) {
        String sql = "UPDATE T_TICKET " +
                "SET USER_ID = ? WHERE ID = ?";

        jdbcTemplate.update(sql, new Object[]{
                userId, id
        });

    }

    public void saveTicket(Ticket ticket) {
        Locale.setDefault(Locale.ENGLISH);
        String sql = "INSERT INTO T_TICKET (TDATE,DESCRIPTION,TNUMBER,TICKET_PRICE) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dataSource.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, ticket.getDate());
            preparedStatement.setString(2, ticket.getName());
            preparedStatement.setString(3, ticket.getNumber());
            preparedStatement.setDouble(3, ticket.getTicketPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> getTicketsByUserName(String username) {
        Locale.setDefault(Locale.ENGLISH);
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT T.ID,T.DESCRIPTION," +
                "T.TDATE," +
                "T.TNUMBER," +
                "U.NAME " +
                "FROM T_TICKET T" +
                " LEFT JOIN T_USER U ON T.USER_ID = U.ID WHERE U.NAME = ?";
        JsonNode node = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dataSource.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, username);


            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setDate(rs.getString("tdate"));
                ticket.setName(rs.getString("description"));
                ticket.setNumber(rs.getString("tnumber"));
                tickets.add(ticket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    public List<Ticket> getReservedTickets() {
        Locale.setDefault(Locale.ENGLISH);
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT ID,T.DESCRIPTION," +
                "T.TDATE," +
                "T.TNUMBER " +
                "FROM T_TICKET T" +
                " WHERE T.USER_ID IS NOT NULL";
        JsonNode node = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dataSource.getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setDate(rs.getString("tdate"));
                ticket.setName(rs.getString("description"));
                ticket.setNumber(rs.getString("tnumber"));
                tickets.add(ticket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    public List<Ticket> getTicketsByName(String name) {
        Locale.setDefault(Locale.ENGLISH);
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT ID,T.DESCRIPTION," +
                "T.TDATE," +
                "T.TICKET_PRICE," +
                "T.TNUMBER " +
                "FROM T_TICKET T" +
                " WHERE T.DESCRIPTION = ?";
        JsonNode node = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dataSource.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setDate(rs.getString("tdate"));
                ticket.setName(rs.getString("description"));
                ticket.setNumber(rs.getString("tnumber"));
                ticket.setTicketPrice(rs.getDouble("ticket_price"));
                tickets.add(ticket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    public Ticket getTicketByNumber(String number) {
        Locale.setDefault(Locale.ENGLISH);
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT T.ID,T.DESCRIPTION," +
                "T.TDATE," +
                "T.TICKET_PRICE," +
                "T.TNUMBER " +
                "FROM T_TICKET T" +
                " WHERE T.TNUMBER = ?";
        JsonNode node = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dataSource.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, number);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setDate(rs.getString("tdate"));
                ticket.setName(rs.getString("description"));
                ticket.setNumber(rs.getString("tnumber"));
                ticket.setTicketPrice(rs.getDouble("ticket_price"));
                tickets.add(ticket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets.get(0);
    }


}