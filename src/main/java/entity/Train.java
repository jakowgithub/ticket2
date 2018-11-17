package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="train")
@Table()
public class Train {

    @Id
    @GenericGenerator(name="increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
        private long idTrain;

    @Column(nullable = false, length = 20)
    @Basic
        private int numberTrain;

    @Column(nullable = false, length = 20)
    @Basic
        private String nameTrain;

    @Column(nullable = false, length = 20)
    @Basic
        private int carePlatzkart;

    @Column(nullable = false, length = 20)
    @Basic
        private int careCupe;

    @Column(nullable = false, length = 20)
    @Basic
        private int careSV;

    //@OneToOne (mappedBy = "train", orphanRemoval = true, cascade = CascadeType.ALL)
    @Transient
        private Route route;

    @Column(nullable = false, length = 20)
    @Basic
    private long idRouteTrain;

    @Transient
        private List<Car> cars = new ArrayList<>(30);

    @Column(nullable = false, length = 20)
    @Basic
        private String remarkTrain;

        public Train(){}

        public Train(
                String nameTrain,
                int numberTrain,
                int carePlatzkart,
                int careCupe,
                int careSV,
                Route route) {

            super();

            this.nameTrain = nameTrain;
            this.numberTrain = numberTrain;
            this.carePlatzkart = carePlatzkart;
            this.careCupe = careCupe;
            this.careSV = careSV;
            this.route = route;
            this.idRouteTrain=route.getIdRoute();

            numberTrain++;

            for (int i=1; i<=carePlatzkart; i++) {
                Car car = new  Car ("Platzkart", numberTrain);
                cars.add(car);
            }
            for (int i=1; i<=careCupe; i++) {
                Car car = new  Car ("Cupe", numberTrain);
                cars.add(car);
            }
            for (int i=1; i<=careSV; i++) {
                Car car = new  Car ("SV", numberTrain);
                cars.add(car);
            }
}

    public long getIdTrain() {return idTrain; }
    public void setIdTrain(long idTrain) { this.idTrain = idTrain; }
    public int getNumberTrain() {return numberTrain; }
    public void setNumberTrain(int numberTrain) { this.numberTrain = numberTrain; }
    public String getNameTrain() { return nameTrain; }
    public void setNameTrain(String nameTrain) { this.nameTrain = nameTrain; }
    public int getCarePlatzkart() { return carePlatzkart; }
    public void setCarePlatzkart(int carePlatzkart) { this.carePlatzkart = carePlatzkart; }
    public int getCareCupe() { return careCupe; }
    public void setCareCupe(int careCupe) { this.careCupe = careCupe; }
    public int getCareSV() { return careSV; }
    public void setCareSV(int careSV) { this.careSV = careSV; }
    public Route getRoute() { return route; }
    public void setRoute(Route route) { this.route = route; }
    public long getIdRouteTrain() { return idRouteTrain; }
    public void setIdRouteTrain(long idRouteTrain) { this.idRouteTrain = idRouteTrain; }
    public List<Car> getCars() { return cars; }
    public void setCars(List<Car> cars) { this.cars = cars; }
    public String getRemarkTrain() { return remarkTrain; }
    public void setRemarkTrain(String remarkTrain) { this.remarkTrain = remarkTrain; }
}
