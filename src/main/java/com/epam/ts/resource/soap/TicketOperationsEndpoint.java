package com.epam.ts.resource.soap;

import com.epam.ts.entity.*;
import com.epam.ts.service.BookingFacade;
import com.epam.ts.service.TicketReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class TicketOperationsEndpoint {

    @Autowired
    private BookingFacade bookingFacade;

    @Autowired
    TicketReservationService ticketReservationService;

    @PayloadRoot(namespace = "http://epam.com/tickets-web-service", localPart = "addUserRequest")
    @ResponsePayload
    public
    AddUserResponse addUser(@RequestPayload AddUserRequest request) {
        bookingFacade.addUser(request.getUser());
        AddUserResponse response = new AddUserResponse();
        response.setStatus("Created");
        return response;
    }

    @PayloadRoot(namespace = "http://epam.com/tickets-web-service", localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest getUserRequest) {
        User user = null;
        if ("name".equals(getUserRequest.getBy())) {
            user = bookingFacade.getUser(getUserRequest.getValue());
        }
        if ("email".equals(getUserRequest.getBy())) {
            user = bookingFacade.getUserByEmail(getUserRequest.getValue());
        }
        GetUserResponse getUserResponse = new GetUserResponse();
        getUserResponse.setUser(user);
        return getUserResponse;
    }

    @PayloadRoot(namespace = "http://epam.com/tickets-web-service", localPart = "bookTicketRequest")
    @ResponsePayload
    public
    BookTicketResponse bookTicket(@RequestPayload BookTicketRequest request) {
        bookingFacade.reserveTicket(request.getUsername(), request.getTicketID());
        return new BookTicketResponse();
    }

    @PayloadRoot(namespace = "http://epam.com/tickets-web-service", localPart = "getTicketsByUserRequest")
    @ResponsePayload
    public
    GetTicketsByUserResponse getTicketsByUser(@RequestPayload GetTicketsByUserRequest request) {
        GetTicketsByUserResponse getTicketsByUserResponse = new GetTicketsByUserResponse();
        getTicketsByUserResponse.setTickets(bookingFacade.getTicketsByUserName(request.getUsername()));
        return getTicketsByUserResponse;
    }
}
