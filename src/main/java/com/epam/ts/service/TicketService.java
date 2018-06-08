package com.epam.ts.service;

import com.epam.ts.dao.UserDao;
import com.epam.ts.entity.Ticket;
import com.epam.ts.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TicketService {
    @Autowired
    private UserDao jdbcUserDao;
    @Autowired
    private UserDao mongoUserDao;

    private Map<String, Ticket> tickets = new HashMap<>();
    private Map<String, Ticket> reservedTickets = new HashMap<>();
    private Map<String, User> users = new HashMap<>();

    public Map<String, Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Map<String, Ticket> tickets) {
        this.tickets = tickets;
    }


    public Map<String, Ticket> getReservedTickets() {
        return reservedTickets;
    }

    public void setReservedTickets(Map<String, Ticket> reservedTickets) {
        this.reservedTickets = reservedTickets;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    public void reserveTicket(String user, String ticketNumber) {
        users.get(user).getTicketList().add(tickets.get(ticketNumber));
        reservedTickets.put(ticketNumber, tickets.get(ticketNumber));
        tickets.remove(ticketNumber);
    }

    public void addUser(User user) {
        users.put(user.getName(), user);
    }

    public User getUserByEmail(String email) {
        User user = null;
        for (User u : users.values()) {
            if (u.getEmail().equals(email)) {
                user = u;
            }
        }

        return user;
    }

    public void addTicket(Ticket ticket) {
        tickets.put(ticket.getNumber(), ticket);
    }

    public User getUser(String name) {
        System.out.println(jdbcUserDao.getUser("userr2"));
        System.out.println(mongoUserDao.getUser("userr2"));
        return users.get(name);
    }

    public void addTickets(List<Ticket> ticketList) {
        for (Ticket ticket : ticketList) {
            tickets.put(ticket.getNumber(), ticket);
        }
    }

    public List<Ticket> getTicketsByUserName(String username) {
        return users.get(username).getTicketList();
    }

    public List<Ticket> getTicketsByEvent(String event) {
        List<Ticket> result = new ArrayList<>();
        for (Ticket ticket : tickets.values()) {
            if (ticket.getName().equals(event)) {
                result.add(ticket);
            }
        }

        return result;
    }

}
