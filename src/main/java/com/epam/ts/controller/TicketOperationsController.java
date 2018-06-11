package com.epam.ts.controller;

import com.epam.ts.service.TicketService;
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
import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TicketOperationsController {
    @Autowired
    private TicketService ticketService;

    @RequestMapping("/")
    public ModelAndView startup() {
        return new ModelAndView("Index");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("Login");
    }

    @PostMapping("/users/add")
    public ModelAndView addUser(User user) {
        ticketService.addUser(user);
        return new ModelAndView("Index");
    }

    @RequestMapping(value = "/users/get", method = RequestMethod.GET)
    public ModelAndView getUser(@RequestParam("by") String by, @RequestParam("value") String value) {
        Map<String, Object> params = new HashMap<>();
        if ("name".equals(by)) {
            params.put("user", ticketService.getUser(value));
        }
        if ("email".equals(by)) {
            params.put("user", ticketService.getUserByEmail(value));
        }

        return new ModelAndView("Index", params);
    }

    @RequestMapping(value = "/users/tickets/book", method = RequestMethod.POST)
    public ModelAndView bookTicket(@RequestParam("name") String username, @RequestParam("ticketID") String id) {
        ticketService.reserveTicket(username, id);
        return new ModelAndView("Index");
    }

    @RequestMapping(value = "/users/tickets/get", method = RequestMethod.GET)
    public ModelAndView getTicketsByUser(@RequestParam("name") String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("ticketList", ticketService.getTicketsByUserName(username));
        return new ModelAndView("Index", params);
    }

    @RequestMapping(value = "/users/tickets/booked/get", method = RequestMethod.GET)
    public ModelAndView getBookedTickets() {
        Map<String, Object> params = new HashMap<>();
        params.put("bticketList", ticketService.getReservedTickets());
        return new ModelAndView("Index", params);
    }

    @RequestMapping(value = "/uploadData", method = RequestMethod.POST)
    public ModelAndView uploadData(@RequestParam("file") MultipartFile file) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final List<Ticket> ticketList = (List<Ticket>) mapper.readValue(file.getInputStream(), new TypeReference<List<Ticket>>() {
        });
        ticketService.addTickets(ticketList);
        return new ModelAndView("Index");
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
