package com.epam.ts.pdf;

import com.epam.ts.entity.Ticket;
import com.lowagie.text.Paragraph;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.util.List;
import java.util.Map;

public class PdfPage extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> map, com.lowagie.text.Document document, com.lowagie.text.pdf.PdfWriter pdfWriter, javax.servlet.http.HttpServletRequest rq, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        List<Ticket> tickets = (List) map.get("ticketList");
        document.add(new Paragraph("Name" + " " + "Date" + " " + "Number"));
        for (Ticket ticket : tickets) {
            document.add(new Paragraph(ticket.getName() + " " + ticket.getDate() + " " + ticket.getNumber()));
        }
    }
}
