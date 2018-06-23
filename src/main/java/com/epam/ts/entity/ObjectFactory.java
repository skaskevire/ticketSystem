//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.23 at 04:20:18 AM PDT 
//


package com.epam.ts.entity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BookTicketRequest_QNAME = new QName("", "bookTicketRequest");
    private final static QName _GetTicketsResponse_QNAME = new QName("", "getTicketsResponse");
    private final static QName _GetUserResponse_QNAME = new QName("", "getUserResponse");
    private final static QName _BookTicketResponse_QNAME = new QName("", "bookTicketResponse");
    private final static QName _GetUserRequest_QNAME = new QName("", "getUserRequest");
    private final static QName _AddUserResponse_QNAME = new QName("", "addUserResponse");
    private final static QName _GetTicketsByUserRequest_QNAME = new QName("", "getTicketsByUserRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BookTicketRequest }
     * 
     */
    public BookTicketRequest createBookTicketRequest() {
        return new BookTicketRequest();
    }

    /**
     * Create an instance of {@link GetUserResponse }
     * 
     */
    public GetUserResponse createGetUserResponse() {
        return new GetUserResponse();
    }

    /**
     * Create an instance of {@link BookTicketResponse }
     * 
     */
    public BookTicketResponse createBookTicketResponse() {
        return new BookTicketResponse();
    }

    /**
     * Create an instance of {@link GetUserRequest }
     * 
     */
    public GetUserRequest createGetUserRequest() {
        return new GetUserRequest();
    }

    /**
     * Create an instance of {@link AddUserResponse }
     * 
     */
    public AddUserResponse createAddUserResponse() {
        return new AddUserResponse();
    }

    /**
     * Create an instance of {@link GetTicketsByUserRequest }
     * 
     */
    public GetTicketsByUserRequest createGetTicketsByUserRequest() {
        return new GetTicketsByUserRequest();
    }

    /**
     * Create an instance of {@link Ticket }
     * 
     */
    public Ticket createTicket() {
        return new Ticket();
    }

    /**
     * Create an instance of {@link UserAccount }
     * 
     */
    public UserAccount createUserAccount() {
        return new UserAccount();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookTicketRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "bookTicketRequest")
    public JAXBElement<BookTicketRequest> createBookTicketRequest(BookTicketRequest value) {
        return new JAXBElement<BookTicketRequest>(_BookTicketRequest_QNAME, BookTicketRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "getUserResponse")
    public JAXBElement<GetUserResponse> createGetUserResponse(GetUserResponse value) {
        return new JAXBElement<GetUserResponse>(_GetUserResponse_QNAME, GetUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookTicketResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "bookTicketResponse")
    public JAXBElement<BookTicketResponse> createBookTicketResponse(BookTicketResponse value) {
        return new JAXBElement<BookTicketResponse>(_BookTicketResponse_QNAME, BookTicketResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "getUserRequest")
    public JAXBElement<GetUserRequest> createGetUserRequest(GetUserRequest value) {
        return new JAXBElement<GetUserRequest>(_GetUserRequest_QNAME, GetUserRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "addUserResponse")
    public JAXBElement<AddUserResponse> createAddUserResponse(AddUserResponse value) {
        return new JAXBElement<AddUserResponse>(_AddUserResponse_QNAME, AddUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTicketsByUserRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "getTicketsByUserRequest")
    public JAXBElement<GetTicketsByUserRequest> createGetTicketsByUserRequest(GetTicketsByUserRequest value) {
        return new JAXBElement<GetTicketsByUserRequest>(_GetTicketsByUserRequest_QNAME, GetTicketsByUserRequest.class, null, value);
    }

}