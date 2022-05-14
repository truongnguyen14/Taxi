package com.example.TaxiV2.controllers;


import com.example.TaxiV2.models.Booking;
import com.example.TaxiV2.services.BookingService;
import com.example.TaxiV2.services.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RequestMapping(path = "api/bookings")
@RestController
public class BookingController {
    private final BookingService bookingService;
    private final InvoiceService invoiceService;


    @GetMapping(path = "getAllBooking")
    public List<Booking> getBookings(){
        return bookingService.allBookings();
    }

    @GetMapping(path = "getAllBookingsBetweenDates")
    public List<Booking> findBookingsBetween(@RequestParam(name = "startDate")String startDate,@RequestParam(name = "endDate")String endDate){
        LocalDate beginningDate = LocalDate.parse(startDate);
        LocalDate endingDate = LocalDate.parse(endDate);
        return bookingService.filteredBookings(beginningDate,endingDate);
    }


    @PostMapping(path = "createBooking/{customer_Id}/{car_Id}")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking, @PathVariable Long customer_Id, @PathVariable Long car_Id){
        Booking newBooking = bookingService.insertBooking(booking,customer_Id,car_Id);
        if(newBooking == null){
            throw new IllegalStateException("Cannot insert Booking");
        }
        else {
            return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
        }
    }

    @PutMapping(path = "updateBooking/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable(value = "id")Long bookingId,@RequestBody Booking booking){
        return new ResponseEntity<>(bookingService.updateBooking(booking,bookingId),HttpStatus.OK);
    }

    @DeleteMapping(path = "cancelBooking/{id}")
    public void cancelBooking(@PathVariable(value = "id")Long bookingId){
        bookingService.deleteBooking(bookingId);
        invoiceService.deleteInvoice(bookingService.getBooking(bookingId).getInvoice().getInvoiceId());
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBooking(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Booking> list = bookingService.getAllBooking(pageNo, pageSize, sortBy);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
