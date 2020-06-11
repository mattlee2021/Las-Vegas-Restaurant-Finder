import java.lang.Math;
import java.math.*;

/**
 * The Place Object represents a restaurant or the place where you would like
 * to start the search (i.e the initial point)
 */

public class Place {
    String placeName;
    double longt;
    double latit;
    int stars;
    int index;
    double distanceFromInitial;

    //Constructor for Restaurants
    public Place (String place, double latitude, double longitude, int stars) {
        this.placeName=place;
        this.latit=latitude;
        this.longt=longitude;
        this.stars=stars;
    }
    //Constructor for initial point
    public Place (double latitude, double longitude, int stars, int index) {
        this.placeName="Your Position";
        this.latit=latitude;
        this.longt=longitude;
        this.distanceFromInitial=0.0; // Starting position
        this.stars=stars;
        this.index=0;

    }

    /**
     * Calculates the distance between two points given their latitude and longitudes
     * Distance is calculated with Haversine's Formula
     *
     * @param B The Place we would like to find the distance to (in miles)
      */
    public void coordDistance(Place B) {
        // Converting coordinates to radians
        double longtitArad= Math.toRadians(this.longt) ;
        double longtitBrad= B.longt / (57.29577951);
        double latArad = this.latit/(57.29577951);
        double latBrad = B.latit / (57.29577951);

        // Haversine's Formula
        double longtDiff = longtitBrad -  longtitArad;
        double latitDiff = latBrad - latArad;
        double a = Math.pow(Math.sin(latitDiff/2),2) + Math.pow( Math.sin(longtDiff/2), 2) *
                Math.cos(latArad) * Math.cos(latBrad);
        double c = 2 * Math.asin( Math.sqrt(a) );
        
        double miles = 3956;
        B.distanceFromInitial= miles*c;
    }

    /**
     * Method that prints the place name and the number of miles away (rounded to 3 decimal places)
     * it is from our initial position
     */
    public void printPlace() {

        BigDecimal b1 = new BigDecimal(this.distanceFromInitial);
        MathContext m = new MathContext(3);
        BigDecimal numRounded = b1.round(m);

        System.out.println(this.placeName + " is " + numRounded + " miles away." );


    }





}
