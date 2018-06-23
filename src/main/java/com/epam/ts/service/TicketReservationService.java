package com.epam.ts.service;

import com.epam.ts.dao.JDBCTicketDao;
import com.epam.ts.dao.JDBCUserAccountDao;
import com.epam.ts.dao.JDBCUserDao;
import com.epam.ts.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketReservationService {
    @Autowired
    JDBCUserDao jdbcUserDao;
    @Autowired
    JDBCTicketDao jdbcTicketDao;
    @Autowired
    JDBCUserAccountDao jdbcUserAccountDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void reserveTicket(Integer ticketId, Integer userId) {
        jdbcTicketDao.reserveTicket(ticketId, userId);
    }

    public void addTicket(Ticket ticket) {
        jdbcTicketDao.saveTicket(ticket);
    }


    public void addTickets(List<Ticket> ticketList) {
        for (Ticket ticket : ticketList) {
            jdbcTicketDao.saveTicket(ticket);
        }
    }

    public List<Ticket> getReservedTickets() {
        return jdbcTicketDao.getReservedTickets();
    }

    public List<Ticket> getTicketsByUserName(String username) {
        return jdbcTicketDao.getTicketsByUserName(username);
    }

    public List<Ticket> getTicketsByEvent(String event) {
        return jdbcTicketDao.getTicketsByName(event);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Ticket getTicketByNumber(String ticketNumber)
    {
        return jdbcTicketDao.getTicketByNumber(ticketNumber);
    }

}
