public class Heap {
    // The Binary Heap of type Place
    Place [] array;
    // Keeps track of the number of elements in the array
    int actualSize;

    /**
     * Constructor
     * @param size the initial size of the binary heap, size is set to 10 arbitrarily in the
     * Main class
     */

    public Heap (int size) {
        array = new Place [size];
        actualSize=0;
    }

    public void add(Place placeToEnter) {
        if (2*actualSize < array.length)
            shrink();
        if (actualSize>=array.length)
            grow();
        placeToEnter.index=actualSize; // May be redundant
        array[placeToEnter.index]= placeToEnter;
        actualSize++;
        bubbleUp(placeToEnter);

    }

    /**
     * grow() increases the size of the binary heap
     */

    private void grow() {
        int size=2*array.length;
        Place [] newArray = new Place[size];
        for (int i=0; i<actualSize; i++) {
            newArray[i]=array[i];
        }
        array=newArray;
    }

    /**
     * shrink() decrease the size of the binary heap
     */

    private void shrink() {
        int size= array.length/2 + 1;
        Place [] newArray = new Place[size];
        for (int i=0; i<actualSize; i++) {
            newArray[i]=array[i];
        }
        array=newArray;

    }

    /**
     * The method bubbleUp helps sort the heap from smallest to largest and is called
     * when each Place is added to the heap
     * @param placeToMove is the Place that is put into the binary heap
     */

    public void bubbleUp(Place placeToMove){
        while (compareDist(array[placeToMove.index], array[parent(placeToMove).index] ) < 0 ) {
            int indexTemp = placeToMove.index;
            Place temp2 = parent(placeToMove);
            placeToMove.index=temp2.index;
            array[placeToMove.index]=placeToMove;
            temp2.index = indexTemp;
            array[indexTemp] = temp2;
        }

    }

    /**
     * This helper method compareDist is used in bubbleUp
     * @param A is a Place in the binary heap
     * @param B is a Place in the binary heap
     * @return -1 if the distance from A to initial place is less than the
     * distance from B to the initial place
     */

    private int compareDist(Place A, Place B) {
        if (A.distanceFromInitial < B.distanceFromInitial)
            return -1;
        else if (A.distanceFromInitial == B.distanceFromInitial)
            return 0;
        else
            return 1;
    }


    public Place parent (Place here) {
        int i = here.index;
        int indexParent = (i-1)/2;
        return array[indexParent];
    }

    public void printHeap(int numOfPlaces) {
        System.out.println("Here are the " + numOfPlaces + " places nearest to your location with " +
                array[0].stars + " stars.");
        for (int i=0; i<=numOfPlaces; i++) {
            array[i].printPlace();
        }
    }



    // Unused Code Below

    //Below you can find a method called bubbleTest() that checks if bubbleUp has been implemented correctly

    public boolean bubbleTest () {
        for (int i=1; i< actualSize; i++ ) {
            if (array[i].distanceFromInitial < parent(array[i]).distanceFromInitial)
                return false;
        }
        return true;
    }

    public Place leftChild (Place here) {
        int i = here.index;
        int indexLeft = 2*i + 1;
        return array[indexLeft];
    }

    public Place rightChild (Place here) {
        int i = here.index;
        int indexRight = 2*i + 2;
        return array[indexRight];
    }

    public Place [] heapPlacesToGo (int toVisit) {
        Place [] toReturn = new Place [toVisit+1];
        toReturn[0] = array[0];
        for (int i=1; i<=toVisit; i++) {
            if (array[i].distanceFromInitial < array[i+1].distanceFromInitial)
                toReturn[i]=array[i];
            else
                toReturn[i]=array[i+1];
        }

        return toReturn;
    }



}
