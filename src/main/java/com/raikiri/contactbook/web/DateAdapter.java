package com.raikiri.contactbook.web;

import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateAdapter extends XmlAdapter<String, Date>
{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd");

    @Override
    public String marshal(Date date) {
        return dateFormat.format(date);
    }

    @Override
    public Date unmarshal(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new WebApplicationException();
        }
    }
}
