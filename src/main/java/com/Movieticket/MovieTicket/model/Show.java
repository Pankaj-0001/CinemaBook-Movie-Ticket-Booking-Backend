package com.Movieticket.MovieTicket.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shows")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"showSeatList", "ticketList"})
public class Show {
    @Id
    private String id;
    private Date date;
    private Time start;
    private Time end;

    @ManyToOne(fetch =FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @OneToMany(mappedBy = "show" , cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ShowSeat> showSeatList = new ArrayList<>();

    @OneToMany(mappedBy = "show" , cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Ticket> ticketList = new ArrayList<>();
}
