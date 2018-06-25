package com.epam.ts.pdf;

import com.epam.ts.entity.Ticket;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PdfHttpMessageConverter implements HttpMessageConverter<List<Ticket>> {
    @Override
    public boolean canRead(Class<?> aClass, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> aClass, MediaType mediaType) {
        return true;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(new MediaType("application","pdf"));
    }

    @Override
    public List<Ticket> read(Class<? extends List<Ticket>> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(List<Ticket> tickets, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        Document document = new Document();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, stream);



            document.open();

            document.add(new Paragraph("Name" + " " + "Date" + " " + "Number"));
            for (Ticket ticket : tickets) {
                document.add(new Paragraph(ticket.getName() + " " + ticket.getDate() + " " + ticket.getNumber()));
            }

            document.close();

            httpOutputMessage.getBody().write(stream.toByteArray());

        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
}
