package com.example.TaxiV2.services;

import com.example.TaxiV2.models.*;
import com.example.TaxiV2.repositories.BookingRepository;
import com.example.TaxiV2.repositories.CustomerRepository;
import com.example.TaxiV2.repositories.DriverRepository;
import com.example.TaxiV2.repositories.InvoiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements BookingManagement{
    private final BookingRepository bookingRep;
    private final InvoiceRepository invoiceRep;
    private final CarService carService;
    private final CustomerService customerService;
    private final DriverService driverService;

    public BookingService(BookingRepository bookingRep, InvoiceRepository invoiceRep, CustomerRepository customerRep, DriverRepository driverRep, CarService carService, CustomerService customerService, DriverService driverService) {
        this.bookingRep = bookingRep;
        this.invoiceRep = invoiceRep;
        this.carService = carService;
        this.customerService = customerService;
        this.driverService = driverService;
    }

    @Override
    public List<Booking> allBookings() {
        return bookingRep.findAll(Sort.by(Sort.Direction.ASC,"bookingId"));

    }

    @Override
    public List<Booking> filteredBookings(LocalDate startDate, LocalDate endDate) {
        return bookingRep.findAllByBookedDateBetween(startDate,endDate);
    }

    @Override
    public Booking insertBooking(Booking booking,Long customerId,Long carId) {

        Customer newCustomer = customerService.getCustomer(customerId);
        Car newCar = carService.getCar(carId);
        Driver newDriver = driverService.getDriver(newCar.getDriverId());

        if(bookingRep.findByCustomerAndCar(newCustomer,newCar).isPresent()){
            throw new IllegalStateException("Booking is already been made for Customer: "+customerId);
        }
        if(newCar.getCarId() == null){
            throw new IllegalStateException("Car does not have a driver");
        }
        else {
            booking.setCar(newCar);
        }
        booking.setCustomer(newCustomer);
        newCar.setAvailability(false);
        booking.setBookedDate(booking.getBookedDate());
        booking.setStartPoint(booking.getStartPoint());
        booking.setEndPoint(booking.getEndPoint());
        booking.setPickUpTime(booking.getPickUpTime());
        booking.setDropOffTime(booking.getDropOffTime());
        booking.setDistance(booking.getDistance());
        booking.setTotalCharge(newCar.getPerKMRate() * booking.getDistance());
        
        Invoice newInvoice = new Invoice();

        newInvoice.setExtractedDate(booking.getBookedDate());
        newInvoice.setCustomer(newCustomer);
        newInvoice.setDriver(newDriver);
        newInvoice.setTotalPayment(booking.getTotalCharge());





        booking.setInvoice(newInvoice);

        bookingRep.save(booking);
        invoiceRep.save(newInvoice);
        return booking;

    }



    @Override
    public Booking updateBooking(Booking booking, long bookingId) {
        Booking newBooking = getBooking(bookingId);
                newBooking.setCustomer(booking.getCustomer());
                newBooking.setCar(booking.getCar());
                newBooking.setBookedDate(booking.getBookedDate());
                newBooking.setStartPoint(booking.getStartPoint());
                newBooking.setEndPoint(booking.getEndPoint());
                newBooking.setPickUpTime(booking.getPickUpTime());
                newBooking.setDropOffTime(booking.getDropOffTime());
                newBooking.setTotalCharge(booking.getTotalCharge());
                newBooking.setDistance(booking.getDistance());
                newBooking.setInvoice(booking.getInvoice());
        return newBooking;
    }


    @Override
    public void deleteBooking(long bookingId) {
        boolean exists = bookingRep.existsById(bookingId);
        if(!exists){
            throw new IllegalStateException("Booking with id: "+bookingId+" does not exist");
        }
        else {
            bookingRep.deleteById(bookingId);
        }
    }

    @Override
    public Booking getBooking(long bookingId) {
        Optional<Booking> bookingOptional = bookingRep.findById(bookingId);
        if(bookingOptional.isEmpty()){
            throw new IllegalStateException("Booking with ID "+bookingId+" is not found");
        }
        else {
            return bookingOptional.get();
        }
    }

    public List<Booking> getAllBooking(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Booking> pagedResult = bookingRep.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }
}
