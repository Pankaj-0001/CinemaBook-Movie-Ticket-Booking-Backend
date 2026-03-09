package com.Movieticket.MovieTicket.model;

import com.Movieticket.MovieTicket.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Show_seat")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatId;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private Double seatPrice;

    private Boolean is_available;


    @ManyToOne()
    @JoinColumn()
    @JsonBackReference
    private Show show;


}
