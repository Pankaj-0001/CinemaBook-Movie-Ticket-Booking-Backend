package com.Movieticket.MovieTicket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class  Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;

    private Double totalTicketsPrice;

    private String bookedSeats;

    @CreationTimestamp
    private Date bookedAt;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Show show;

    @ManyToOne
    @JoinColumn
    private User user;

}
