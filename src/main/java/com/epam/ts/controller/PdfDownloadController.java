package com.epam.ts.controller;

import com.epam.ts.entity.Ticket;
import com.epam.ts.service.BookingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class PdfDownloadController {
    @Autowired
    private BookingFacade bookingFacade;

    @RequestMapping(value = "/tickets/pdf/event", method = RequestMethod.GET)
    public ModelAndView getTicketsPdfByEvent(@RequestParam("name") String nameOfEvent) {
        Map<String, Object> params = new HashMap<>();
        List<Ticket> tickets = new ArrayList();
        tickets.addAll(bookingFacade.getTicketsByEvent(nameOfEvent));
        params.put("ticketList", tickets);

        return new ModelAndView("TicketsPdf", params);
    }

    @RequestMapping(value = "/tickets/pdf/user", method = RequestMethod.GET)
    public ModelAndView getTicketsPdfByUser(@RequestParam("name") String username) {
        Map<String, Object> params = new HashMap<>();
        List<Ticket> tickets = new ArrayList();
        tickets.addAll(bookingFacade.getTicketsByUserName(username));
        params.put("ticketList", tickets);

        return new ModelAndView("TicketsPdf", params);
    }

}
