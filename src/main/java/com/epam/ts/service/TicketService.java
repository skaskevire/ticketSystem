package com.epam.ts.service;

import com.epam.ts.dao.JDBCTicketDao;
import com.epam.ts.dao.JDBCUserDao;
import com.epam.ts.entity.Ticket;
import com.epam.ts.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

public class TicketService {
    @Autowired
    JDBCUserDao jdbcUserDao;
    @Autowired
    JDBCTicketDao jdbcTicketDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Ticket> getReservedTickets() {
        return jdbcTicketDao.getReservedTickets();
    }

    public void reserveTicket(String username, String ticketNumber) {
        User user = jdbcUserDao.getUsers(username).get(0);
        Ticket ticket = jdbcTicketDao.getTicketByNumber(ticketNumber);
        jdbcTicketDao.reserveTicket(ticket.getId(), user.getId());
    }

    public void addUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles("RESGISTERED_USER");
        jdbcUserDao.saveUser(user);
    }

    public User getUserByEmail(String email) {
        return jdbcUserDao.getUserByEmail(email);
    }

    public void addTicket(Ticket ticket) {
        jdbcTicketDao.saveTicket(ticket);
    }

    public User getUser(String name) {
        return jdbcUserDao.getUsers(name).get(0);
    }

    public void addTickets(List<Ticket> ticketList) {
        for (Ticket ticket : ticketList) {
            jdbcTicketDao.saveTicket(ticket);
        }
    }

    public List<Ticket> getTicketsByUserName(String username) {
        return jdbcTicketDao.getTicketsByUserName(username);
    }

    public List<Ticket> getTicketsByEvent(String event) {
        return jdbcTicketDao.getTicketsByName(event);
    }
}
