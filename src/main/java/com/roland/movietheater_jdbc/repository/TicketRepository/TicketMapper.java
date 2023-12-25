package com.roland.movietheater_jdbc.repository.TicketRepository;

import com.roland.movietheater_jdbc.model.Ticket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper implements RowMapper<Ticket> {

    @Override
    public Ticket mapRow(ResultSet resultSet, int i) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setTickedId(resultSet.getInt("ticket_id"));
        return ticket;
    }
}
