package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="car")
@Table
public class Ticket {

    @Id
    @GenericGenerator(name="increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long idTicket;

    @Column(nullable = false, length = 40)
    @Basic
    private String passenger;

    @Column(nullable = false, length = 40)
    @Basic
    private String startStation;

    @Column(nullable = false, length = 40)
    @Basic
    private String terminalStation;

    @Column(nullable = false,  length = 20)
    @Basic
    private int numberTrainTicket;

    @Column(nullable = false,  length = 20)
    @Basic
    private int numberCarTicket;

    @Column(nullable = false,  length = 20)
    @Basic
    private int numberPlaceTicket;

    @Column(nullable = false)
    private LocalDateTime departure;
    //LocalDateTime dateTime=LocalDateTime.now();
    //LocalDateTime specificDate = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30);

    @Column(nullable = false)
    private LocalDateTime arrival;

    @Column(length = 20)
    @Basic
    private double priceTicket;

    @Column(length = 40)
    @Basic
    private String remarkTicket;


    @Column(nullable = false)
    private LocalDateTime timeOrder;

    public Ticket() {}

    public Ticket(String passenger, String startStation, String terminalStation, int numberTrainTicket, int numberCarTicket, int numberPlaceTicket) {
        this.passenger = passenger;
        this.startStation = startStation;
        this.terminalStation = terminalStation;
        this.numberTrainTicket = numberTrainTicket;
        this.numberCarTicket = numberCarTicket;
        this.numberPlaceTicket = numberPlaceTicket;
        timeOrder = LocalDateTime.now();
    }

    public long getIdTicket() { return idTicket; }

    public void setIdTicket(long idTicket) { this.idTicket = idTicket; }

    public String getPassenger() { return passenger; }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getTerminalStation() {
        return terminalStation;
    }

    public void setTerminalStation(String terminalStation) {
        this.terminalStation = terminalStation;
    }

    public int getNumberTrainTicket() {
        return numberTrainTicket;
    }

    public void setNumberTrainTicket(int numberTrainTicket) {
        this.numberTrainTicket = numberTrainTicket;
    }

    public int getNumberCarTicket() {
        return numberCarTicket;
    }

    public void setNumberCarTicket(int numberCarTicket) {
        this.numberCarTicket = numberCarTicket;
    }

    public int getNumberPlaceTicket() {
        return numberPlaceTicket;
    }

    public void setNumberPlaceTicket(int numberPlaceTicket) {
        this.numberPlaceTicket = numberPlaceTicket;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(double priceTicket) {
        this.priceTicket = priceTicket;
    }

    public String getRemarkTicket() {
        return remarkTicket;
    }

    public void setRemarkTicket(String remarkTicket) {
        this.remarkTicket = remarkTicket;
    }

    public LocalDateTime getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(LocalDateTime timeOrder) {
        this.timeOrder = timeOrder;
    }
}
