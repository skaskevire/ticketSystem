package com.epam.ts.viewresolvers;

import java.util.Locale;

import com.epam.ts.pdf.PdfPage;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
 
public class PdfViewResolver implements ViewResolver {
 
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
 
        PdfPage view = new PdfPage();
        return view;
 
    }
 
}