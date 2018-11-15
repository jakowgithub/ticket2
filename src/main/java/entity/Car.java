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
    private int numberCar=3300000;

    @Column(nullable = false, length = 20)
    @Basic
    private String typeCar; //Platzkart, Cupe, SV

    @Column(nullable = false,  length = 20)
    @Basic
    private int numberSeats;

    @Column(nullable = false,  length = 20)
    @Basic
    private int numberTrain;

    @Column( length = 20)
    @Basic
    private String remarkCar;

    @Transient
    private  List <Place> places = new ArrayList<>(54);

    public Car () {}

    public Car(String typeCar, int numberTrain) {
        super();
        this.typeCar = typeCar;
        this.numberTrain = numberTrain;
        int  pricePlace;

        switch (this.typeCar) {

            case "Platzkart": numberSeats = 54; pricePlace=1; break;
            case "Cupe":      numberSeats = 36; pricePlace=2; break;
            case "SV":        numberSeats = 18; pricePlace=4; break;
            default:          numberSeats = 54; pricePlace=1; break;
        }
        numberCar++;

        for (int i=1; i<=numberSeats; i++) {
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
    public int getNumberSeats() {
        return numberSeats;
    }
    public void setNumberSeats(int numberSeats) {
        this.numberSeats = numberSeats;
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
    public List<Place> getPlaces() {
        return places;
    }
    public void setPlaces(List<Place> places) {
        this.places = places;
    }

}
