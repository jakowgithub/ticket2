package entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table()
public class Place {

        @Id
        @GenericGenerator(name="increment", strategy = "increment")
        @GeneratedValue(generator = "increment")
        private long idPlace;

        @Column(nullable = false, unique = false, length = 10)
        @Basic
        private int numberPlace;

        @Column(nullable = false, unique = false, length = 10)
        @Basic
        private int numberTrain;

        @Column(nullable = false, unique = false, length = 10)
        @Basic
        private int numberCar;

        @Column(nullable = false, unique = false)
        @Basic
        private boolean isReal;

        @Column(nullable = false, unique = false, length = 10)
        @Basic
        private String typeCarPlace;

        @Column(nullable = true, unique = false, length = 40)
        @Basic
        private String remarkPlace;

        @Column(nullable = false, unique = false, length = 10)
        @Basic
        private int pricePlace;

        public Place (){};

        public Place ( int numberPlace,
                       int numberCar,
                       int numberTrain,
                       String typeCar,
                       int pricePlace){

            super();

            this.numberPlace=numberPlace;
            this.numberCar=numberCar;
            this.numberTrain=numberTrain;
            this.typeCarPlace=typeCar;
            this.pricePlace=pricePlace;
            this.isReal=true;

        }

        public long getIdPlace() {
        return idPlace;
    }

        public int getNumberPlace() {
            return numberPlace;
        }

        public void setNumberPlace(int numberPlace) {
            this.numberPlace = numberPlace;
        }

        public int getNumberTrain() {
            return numberTrain;
        }

        public void setNumberTrain(int numberTrain) {
            this.numberTrain = numberTrain;
        }

        public int getNumberCar() {
            return numberCar;
        }

        public void setNumberCar(int numberCar) {
            this.numberCar = numberCar;
        }

        public boolean getIsReal() {
            return isReal;
        }

        public void setIsReal(boolean isReal) {
            this.isReal = isReal;
        }

        public String getTypeCarPlace() {
            return typeCarPlace;
        }

        public void setTypeCarPlace(String typeCarPlace) {
            this.typeCarPlace = typeCarPlace;
        }

        public String getRemarkPlace() {
            return remarkPlace;
        }

        public void setRemarkPlace(String remarkPlace) {
            this.remarkPlace = remarkPlace;
        }

        public int getPricePlace() {
            return pricePlace;
        }

        public void setPricePlace(int pricePlace) {
            this.pricePlace = pricePlace;
        }

    }
