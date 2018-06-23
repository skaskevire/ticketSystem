package com.epam.ts.service;

import com.epam.ts.entity.Ticket;
import com.epam.ts.entity.User;
import com.epam.ts.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BookingFacade {
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    UserService userService;
    @Autowired
    TicketReservationService ticketService;

    public List<Ticket> getReservedTickets() {
        return ticketService.getReservedTickets();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void reserveTicket(String username, String ticketNumber) {
        User user = userService.getUserByName(username);
        Ticket ticket = ticketService.getTicketByNumber(ticketNumber);
        if (user.getUserAccount().getPrepaidAmount() < ticket.getTicketPrice()) {
            throw new RuntimeException("Not Enough Funds");
        }
        user.getUserAccount().setPrepaidAmount(user.getUserAccount().getPrepaidAmount() - ticket.getTicketPrice());
        userAccountService.updatePrepaidAmount(user.getUserAccount());

        ticketService.reserveTicket(ticket.getId(), user.getId());

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addUser(User user) {
        user.setRoles("REGISTERED_USER");
        UserAccount userAccount = new UserAccount();
        userAccount.setPrepaidAmount(1200d);
        userAccountService.saveUserAccount(userAccount);
        user.setUserAccount(userAccount);
        userService.saveUser(user);
    }

    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    public void addTicket(Ticket ticket) {
        ticketService.addTicket(ticket);
    }

    public User getUser(String name) {
        return userService.getUserByName(name);
    }

    public void addTickets(List<Ticket> ticketList) {
        ticketService.addTickets(ticketList);
    }

    public List<Ticket> getTicketsByUserName(String username) {
        return ticketService.getTicketsByUserName(username);
    }

    public List<Ticket> getTicketsByEvent(String event) {
        return ticketService.getTicketsByEvent(event);
    }
}
