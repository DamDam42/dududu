package com.heroku.java.controller;
import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.heroku.java.model.ticket;

@Controller
public class TicketController {
    private final DataSource dataSource;

    @Autowired
    public TicketController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

   @PostMapping("/addTicket")
    public String staffAddRoom(@ModelAttribute("ticket")ticket ticket ){
       

        try {
            Connection connection = dataSource.getConnection();
            String sql = "INSERT INTO public.ticket(tickettype,ticketprice) VALUES(?,?)";
            final var statement = connection.prepareStatement(sql);

            String tickettype= ticket.getTicketType();
            double ticketprice = ticket.getTicketPrice();
            
            
            statement.setString(1, tickettype);
            statement.setDouble(2, ticketprice);
           
            statement.executeUpdate();
            
            connection.close();
                
                } catch (Exception e) {
                    e.printStackTrace();
            
                  
                    return "redirect:/error";
                }
       
                 
            return "redirect:/index";
         }
}
