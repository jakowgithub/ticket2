package entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDateTime;

@DatabaseTable(tableName = "route")
public class Route {

        @DatabaseField(generatedId = true)
        private int idRoute;

        @DatabaseField()
        private String nameRoute;

        @DatabaseField()
        private String station0;

        @DatabaseField()
        private String station1;

        @DatabaseField()
        private String station2;

        @DatabaseField()
        private LocalDateTime arrivalStation0;
        //LocalDateTime dateTime=LocalDateTime.now();
        //LocalDateTime specificDate = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30);

        @DatabaseField()
        private LocalDateTime arrivalStation1;

        @DatabaseField()
        private LocalDateTime arrivalStation2;


        @DatabaseField()
        private int numberTrainRout;

        @DatabaseField()
        private String remarkRoute;

        @DatabaseField()
        private int[][] fareStation = new int[5][5];

        //fare from Station0 to StationJ 0, 5,10,15,20
        //fare from Station1 to StationJ 5, 0, 5,10,15
        //fare from Station2 to StationJ 10,5, 0, 5,10
        //fare from Station3 to StationJ 15,10,5, 0, 5
        //fare from Station4 to StationJ 20,15,10, 5,0

        Route() { };

        public Route(String nameRoute,
                     String station0,
                     String station1,
                     String station2,
                     LocalDateTime arrivalStation0,
                     LocalDateTime arrivalStation1,
                     LocalDateTime arrivalStation2) {
            super();
            this.nameRoute = nameRoute;
            this.station0 = station0;
            this.station1 = station1;
            this.station2 = station2;
            this.arrivalStation0 = arrivalStation0;
            this.arrivalStation1 = arrivalStation1;
            this.arrivalStation2 = arrivalStation2;

            for (int i = 0; i < 5; i++) {

                for (int j = 0; j < 5; j++) {

                    if (i == j) fareStation[i][j] = 0;

                    else fareStation[i][j] = 5 * ((i - j) < 0 ? (j - i) : (i - j));
                }
            }
        }

        public String getNameRoute() {
            return nameRoute;
        }

        public void setNameRoute(String nameRoute) {
            this.nameRoute = nameRoute;
        }

        public String getStation0() {
            return station0;
        }

        public void setStation0(String station0) {
            this.station0 = station0;
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

        public LocalDateTime getArrivalStation0() {
            return arrivalStation0;
        }

        public void setArrivalStation0(LocalDateTime arrivalStation0) {
            this.arrivalStation0 = arrivalStation0;
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

        public int getNumberTrainRout() {
            return numberTrainRout;
        }

        public void setNumberTrainRout(int numberTrainRout) {
            this.numberTrainRout = numberTrainRout;
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


    }
