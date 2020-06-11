/**
 * This code will help you find some of the nearest restaurants to any given latitude and longitude
 * The code either uses a heap or an array based list data structure to store the CSV data passed in
 * Users can pass in any CSV file they would like and follow the prompts given when the code is run
 */
import com.sun.corba.se.impl.orbutil.graph.Graph;
import java.util.Scanner;
import java.lang.Integer;
import java.lang.Double;
import java.lang.Exception;

public class Main {
    public static void main(String[] args) throws Exception {
        /**
         * Code used to prompt user for input based on different criteria to find
         * restaurants in Las Vegas
         */
        Scanner in = new Scanner(System.in);
        System.out.println("This program will help you find some restaurants in Las Vegas.");
        System.out.println("Input a latitude and longitude in Las Vegas, the number of stars, and the number of the restaurant\n that you'd like to view.");
        System.out.println("For example, Caesar's Palace is @ latitude 36.1162 and latitude -115.1745.");
        System.out.println("Please remember that latitude coordinates above the equator are positive and that longitude \n coordinates west of the prime meridian are negative.");
        System.out.println("Please enter your starting latitude (North and South Coordinates).");
        double startLat = Double.parseDouble(in.nextLine());
        System.out.println("Please enter your starting longitude (West and East Coordinates).");
        double startLong = Double.parseDouble(in.nextLine());
        System.out.println("What star restaurant do you want to view? Please enter an int between 3-5 inclusive.");
        int star = Integer.parseInt(in.nextLine());

        // Initializing users starting position
        Place initial = new Place(startLat, startLong, star, 0);

        System.out.println("How many restaurants do you want to visit?\n There are about 680 3-star, 1000 4-star, and 100 5-star restaurants.");
        int numOfPlaces = Integer.parseInt(in.nextLine());

        //Counts the number of restaurants with the given star int passed into int stars
        int counter=0;

        /**
         * CSV File passed in called ResOpenYelp.csv
         */
        java.util.Scanner scan = new java.util.Scanner(new java.io.FileReader("ResOpenYelp.csv"));
        boolean done = false;

        /**
         * Initializing a heap and list (array based) data structures
         */

        Heap heap = new Heap(10);
        List list = new List(10);

        /**
         * Adding the initial starting point into the data structures
         */
        list.add(initial);
        heap.add(initial);

        /**
         * Code used to parse CSV File
         */


        while (!done) {
            try {
                String[] data = scan.nextLine().split(",");
                // In my CSV file the 4th column held the data with the number of stars,
                // hence the data[3] == star
                if (Integer.parseInt(data[3]) == star) {
                    Place toInsert = new Place(data[0], Double.parseDouble(data[1]),
                            Double.parseDouble(data[2]), Integer.parseInt(data[3]));
                    // Calculating the distance from the initial point
                    initial.coordDistance(toInsert);
                    counter++;

                    /* Comment out array.add(toInsert)  to run the heap;
                     * Comment out heap.add(toInsert) to run the graph;
                     */

                      list.add(toInsert);
                      //heap.add(toInsert);
                }
            } catch (java.util.NoSuchElementException e) {

                done = true;
            }
        }

        /**
         * Run 2 lines below to execute List Data Structure
         */

        list.bubbleSort();
        if (numOfPlaces > counter) {
            throw new Exception(("There are only " + counter + " number of restaurants that have "
                    + star +  " stars." + "\n Please enter a number less than or equal to " + counter));
        }
        list.print(numOfPlaces);


        /**
         * Run code in the line below to run the heap structure and
         * comment out the 2 lines of code above
         */

         //heap.printHeap(numOfPlaces);

    }


}





