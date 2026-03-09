package com.Movieticket.MovieTicket.service;

import com.Movieticket.MovieTicket.dto.ShowSeatRequest;
import com.Movieticket.MovieTicket.dto.ShowsDTO;
import com.Movieticket.MovieTicket.enums.SeatType;
import com.Movieticket.MovieTicket.model.Show;
import com.Movieticket.MovieTicket.model.ShowSeat;
import com.Movieticket.MovieTicket.model.Theater;
import com.Movieticket.MovieTicket.model.TheaterSeat;
import com.Movieticket.MovieTicket.repo.ShowsRepo;
import com.Movieticket.MovieTicket.util.ShowsMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ShowsService {

    private final ShowsRepo showsRepo;
    @Autowired
    private ShowsMapper showsMapper;




    public ShowsService(ShowsRepo showsRepo) {
        this.showsRepo = showsRepo;
    }

    public ShowsDTO addShow(ShowsDTO showsDTO){
        Show show = showsMapper.mapToshows(showsDTO);
        Show savedShow = showsRepo.save(show);
        return showsMapper.mapToshowDTO(savedShow);
    }

    public String associateSeat(ShowSeatRequest request){

        Optional<Show> shows = showsRepo.findById(request.getShow_id());

        if (shows.isEmpty()){
            throw new RuntimeException("Show Does not exist");
        }

        Show show = shows.get();
        Theater theater = show.getTheater();

        List<TheaterSeat> theaterSeats = theater.getTheaterSeatList();
        List<ShowSeat> showSeats = show.getShowSeatList();

        for (TheaterSeat theaterSeat : theaterSeats){
            ShowSeat showSeat = new ShowSeat();
            showSeat.setShow(show);
            showSeat.setSeatNo(theaterSeat.getSeat_number());
            showSeat.setSeatType(theaterSeat.getSeat_type());

            if (theaterSeat.getSeat_type()== SeatType.CLASSIC){
                showSeat.setSeatPrice(request.getPriceOfClassicSeat());
            }else {
                showSeat.setSeatPrice(request.getPriceOfPremiumSeat());
            }
            showSeat.setIs_available(Boolean.TRUE);

            showSeats.add(showSeat);
        }
        show.setShowSeatList(showSeats);
        showsRepo.save(show);

        return "The request for generation of Seats Have been successful";
    }
}
