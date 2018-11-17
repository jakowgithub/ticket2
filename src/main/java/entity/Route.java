package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="route")
@Table
public class Route {

    @Id
    @GenericGenerator(name="increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
        private long idRoute;

    @Column(nullable = false, length = 20)
    @Basic
        private String nameRoute;

    @Column(nullable = false, length = 20)
    @Basic
        private String station1;

    @Column(nullable = false, length = 20)
    @Basic
        private String station2;

    @Column(nullable = false, length = 20)
    @Basic
        private String station3;

    @Column(nullable = false)
   // @Temporal(TemporalType.TIMESTAMP)
        private LocalDateTime arrivalStation1;
        //LocalDateTime dateTime=LocalDateTime.now();
        //LocalDateTime specificDate = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30);

    @Column(nullable = false)
   // @Temporal(TemporalType.TIMESTAMP)
        private LocalDateTime arrivalStation2;

    @Column(nullable = false)
    //@Temporal(TemporalType.TIMESTAMP)
        private LocalDateTime arrivalStation3;

    @Column()
    @Basic
        private int numberTrainRoute;

    @Column()
    @Basic
        private String remarkRoute;

    @Transient
    private int[][] fareStation = new int[5][5];

        //fare from Station1 to StationJ 0, 5,10,15,20
        //fare from Station2 to StationJ 5, 0, 5,10,15
        //fare from Station3 to StationJ 10,5, 0, 5,10
        //fare from Station4 to StationJ 15,10,5, 0, 5
        //fare from Station5 to StationJ 20,15,10, 5,0

    @Column()
    @Basic
    private  String fareStationString;

    @OneToOne
    private Train train;


        public Route() { }

        public Route(String nameRoute,
                     String station1,
                     String station2,
                     String station3,
                     LocalDateTime arrivalStation1,
                     LocalDateTime arrivalStation2,
                     LocalDateTime arrivalStation3) {
            super();
            this.nameRoute = nameRoute;
            this.station3 = station3;
            this.station1 = station1;
            this.station2 = station2;
            this.arrivalStation3 = arrivalStation3;
            this.arrivalStation1 = arrivalStation1;
            this.arrivalStation2 = arrivalStation2;

            StringBuilder tmpRow = new StringBuilder();

            for (int i = 0; i < 5; i++) {

                for (int j = 0; j < 5; j++) {

                    if (i == j) fareStation[i][j] = 0;

                    else fareStation[i][j] = 5 * ((i - j) < 0 ? (j - i) : (i - j));

                    tmpRow.append(fareStation[i][j]).append(" ");
                }
            }
            fareStationString = tmpRow.toString();
        }
        public long getIdRoute() {
        return idRoute;
    }

        public String getNameRoute() {
            return nameRoute;
        }
        public void setNameRoute(String nameRoute) {
            this.nameRoute = nameRoute;
        }
        public String getStation3() {
            return station3;
        }
        public void setStation3(String station3) {
            this.station3 = station3;
        }
        public String getStation1() {
            return station1;
        }
        public void setStation1(String station1) {
            this.station1 = station1;
        }
        public String getStation2() {
            return station2;
        }
        public void setStation2(String station2) {
            this.station2 = station2;
        }
        public LocalDateTime getArrivalStation3() {
            return arrivalStation3;
        }
        public void setArrivalStation3(LocalDateTime arrivalStation3) {
            this.arrivalStation3 = arrivalStation3;
        }
        public LocalDateTime getArrivalStation1() {
            return arrivalStation1;
        }
        public void setArrivalStation1(LocalDateTime arrivalStation1) {
            this.arrivalStation1 = arrivalStation1;
        }
        public LocalDateTime getArrivalStation2() {
            return arrivalStation2;
        }
        public void setArrivalStation2(LocalDateTime arrivalStation2) {
            this.arrivalStation2 = arrivalStation2;
        }
        public int getNumberTrainRoute() {
            return numberTrainRoute;
        }
        public void setNumberTrainRoute(int numberTrainRoute) {
            this.numberTrainRoute = numberTrainRoute;
        }
        public String getRemarkRoute() {
            return remarkRoute;
        }
        public void setRemarkRoute(String remarkRoute) {
            this.remarkRoute = remarkRoute;
        }
        public int[][] getFareStation() {
            return fareStation;
        }
        public void setFareStation(int[][] fareStation) {
            this.fareStation = fareStation;
        }
        public String getFareStationString() {
        return fareStationString;
    }
        public void setFareStationString(String nameRoute) {
        this.fareStationString = fareStationString;
    }
        public Train getTrain() {
        return train;
    }
        public void setTrain(Train train) {
        this.train = train;
    }
    }
