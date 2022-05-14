package com.example.TaxiV2.services;

import com.example.TaxiV2.models.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingManagement {
    List<Booking> allBookings();
    List<Booking> filteredBookings(LocalDate startDate,LocalDate endDate);
    Booking insertBooking(Booking booking,Long customerId,Long carId);
    Booking updateBooking(Booking booking, long bookingId);
    void deleteBooking(long bookingId);
    Booking getBooking(long bookingId);

}
