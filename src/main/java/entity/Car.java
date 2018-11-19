package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="car")
@Table
public class Car {

    @Id
    @GenericGenerator(name="increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long idCar;

    @Column(nullable = false, length = 20)
    @Basic
    private int numberCar;

    @Column(nullable = false, length = 20)
    @Basic
    private String typeCar; //Platzkart, Cupe, SV

    @Column(nullable = false,  length = 20)
    @Basic
    private int volumeSeats;

    @Column(nullable = false,  length = 20)
    @Basic
    private int numberTrain;

    @Column( length = 20)
    @Basic
    private String remarkCar;

    @Column(nullable = false,  length = 20)
    @Basic
    private int pricePlace;

    @Transient
    private  List <Place> places = new ArrayList<>(54);

    public Car () {}

    public Car(String typeCar, int numberTrain) {
        super();
        this.typeCar = typeCar;
        this.numberTrain = numberTrain;
        switch (this.typeCar) {

            case "Platzkart": volumeSeats = 54; pricePlace=1; break;
            case "Cupe":      volumeSeats = 36; pricePlace=2; break;
            case "SV":        volumeSeats = 18; pricePlace=4; break;
            default:          volumeSeats = 54; pricePlace=1; break;
        }


        for (int i=1; i<=volumeSeats; i++) {
            Place place = new Place (i, numberCar, numberTrain, typeCar, pricePlace);
            places.add(place);
        }
    }

    public long getIdCar() {
        return idCar;
    }
//    public void setIdCar(int idCar) {
//    this.idCar = idCar;
//    }
    public int getNumberCar() {
        return numberCar;
    }
    public void setNumberCar(int numberCar) {
        this.numberCar = numberCar;
    }
    public String getTypeCar() {
        return typeCar;
    }
    public void setTypeCar(String typeCar) {
        this.typeCar = typeCar;
    }
    public int getVolumeSeats() {
        return volumeSeats;
    }
    public void setVolumeSeats(int numberSeats) {
        this.volumeSeats = numberSeats;
    }
    public int getNumberTrain() {
        return numberTrain;
    }
    public void setNumberTrain(int numberTrain) {
        this.numberTrain = numberTrain;
    }
    public String getRemarkCar() {
        return remarkCar;
    }
    public void setRemarkCar(String remarkCar) {
        this.remarkCar = remarkCar;
    }
    public int getPricePlace() {
        return pricePlace;
    }
    public void setPricePlace(int pricePlace) {
        this.pricePlace = pricePlace;
    }
    public List<Place> getPlaces() {
        return places;
    }
    public void setPlaces(List<Place> places) {
        this.places = places;
    }

}
