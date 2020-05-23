public class List {
    // The array used in the List Data Structure
    Place [] col;
    // size of array (i.e number of objects that we are using)
    int length;
    // number of objects in the array; index variable
    int items;

    /**
     * Constructor
     * @param size the initial size of the array, size is set to 10 arbitrarily in the
     * Main class
     */

    public List (int size) {
        this.length=size;
        this.items=0;
        col = new Place [this.length];
    }

    public void add(Place toPush) {
        if (items >= col.length)
            grow();
        col[this.items] = toPush;
        toPush.index=items; // assigning the item number to the index in the Place object
        items++;
    }

    public void grow () {
        int newSize= col.length * 2;
        Place [] newArray = new Place[newSize];
        for (int i=0; i<col.length; i++) {
            newArray[i]=col[i];
        }
        col=newArray;
    }

    /**
     * bubbleSort sorts the Places in the array from smallest distanceFromInitial to
     * greatest distanceFromInitial
     */
    public void bubbleSort () {
        for (int i=0; i<this.items; i++) {
            for (int j=0; j<this.items-i-1; j++)
                if ( this.col[j].distanceFromInitial > this.col[j+1].distanceFromInitial) {
                    Place temp = this.col[j];
                    this.col[j]=this.col[j+1];
                    this.col[j+1]=temp;
                }
        }

    }


    public void print(int numOfPlaces) {
        System.out.println("Here are the " + numOfPlaces + " places nearest to your location with " +
                this.col[0].stars + " stars.");
        for (int i=0; i<numOfPlaces; i++) {
            col[i].printPlace();
        }
    }

    // Unused Code
    public void remove (Place toRemove) {
        int toRemoveIndex = toRemove.index;
        for (int i=toRemoveIndex; i<length-1; i++) {
            col[i]=col[i+1];
        }
        col[length-1]=null; // make last element equal to null because everything has been shifted
        items--;
    }



}







