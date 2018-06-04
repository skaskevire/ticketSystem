package com.epam.ts.controller;

import com.epam.ts.service.TicketService;
import com.epam.ts.entity.Ticket;
import com.epam.ts.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    private TicketService ticketService;

    @RequestMapping("/")
    public ModelAndView startup() {
        return new ModelAndView("Index");
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public ModelAndView addUser(@RequestParam("name") String username, @RequestParam("email") String email) {
        ticketService.addUser(new User(username, email));
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

    @RequestMapping(value = "users/tickets/get", method = RequestMethod.GET)
    public ModelAndView getTicketsByUser(@RequestParam("name") String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("ticketList", ticketService.getUser(username).getTicketList());
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
