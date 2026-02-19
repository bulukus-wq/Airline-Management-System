package model;

import manager.CalculatePrice; 
import model.Seat.SeatClass;

public class Ticket {
    private String ticketID;
    private double price;             
    private Reservation reservation;
    private double bagageAllowance;  
    private Bagage bagage;            
    
    public Ticket(String ticketID, Reservation reservation, Bagage bagage) {
        this.ticketID = ticketID;
        this.reservation = reservation;
        this.bagage = bagage;
        calculateDetails();
    }


    private void calculateDetails() {
        CalculatePrice calculator = new CalculatePrice();
        SeatClass seatClass = this.reservation.getSeat().getSeatClass();
        
        int currentWeight = (int) this.bagage.getWeight(); 

        this.price = calculator.calculateTotalPrice(seatClass, currentWeight);

        if (seatClass == SeatClass.BUSINESS) {
            this.bagageAllowance = CalculatePrice.BUSINESS_BAGGAGE_LIMIT;
        } else {
            this.bagageAllowance = CalculatePrice.ECONOMY_BAGGAGE_LIMIT;
        }
    }


    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }

    public double getBagageAllowance() {
        return bagageAllowance;
    }

    public void setBagageAllowance(double bagageAllowance) {
        this.bagageAllowance = bagageAllowance;
    }

    public Bagage getBagage() {
        return bagage;
    }

    public void setBagage(Bagage bagage) {
        this.bagage = bagage;
        calculateDetails(); 
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
        calculateDetails();
    }
}