package com.epam.ts.controller;

import com.epam.ts.service.BookingFacade;
import com.epam.ts.service.TicketReservationService;
import com.epam.ts.entity.Ticket;
import com.epam.ts.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TicketOperationsController {
    @Autowired
    private BookingFacade bookingFacade;

    @Autowired
    TicketReservationService ticketReservationService;

    @PostMapping("/users/add")
    public ModelAndView addUser(User user) {
        bookingFacade.addUser(user);
        return new ModelAndView("Login");
    }

    @RequestMapping(value = "/users/get", method = RequestMethod.GET)
    public ModelAndView getUser(@RequestParam("by") String by, @RequestParam("value") String value) {
        Map<String, Object> params = new HashMap<>();
        if ("name".equals(by)) {
            params.put("user", bookingFacade.getUser(value));
        }
        if ("email".equals(by)) {
            params.put("user", bookingFacade.getUserByEmail(value));
        }

        return new ModelAndView("Index", params);
    }

    @RequestMapping(value = "/users/tickets/book", method = RequestMethod.POST)
    public void bookTicket(@RequestParam("name") String username, @RequestParam("ticketID") String id) {
        bookingFacade.reserveTicket(username, id);
    }

    @RequestMapping(value = "/users/tickets/get", method = RequestMethod.GET)
    public ModelAndView getTicketsByUser(@RequestParam("name") String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("ticketList", bookingFacade.getTicketsByUserName(username));
        return new ModelAndView("Index", params);
    }

    @RequestMapping(value = "/users/tickets/booked/get", method = RequestMethod.GET)
    public ModelAndView getBookedTickets() {
        Map<String, Object> params = new HashMap<>();
        params.put("bticketList", bookingFacade.getReservedTickets());
        return new ModelAndView("Index", params);
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("Error");
        return mav;
    }
}
