//MovieList represents the LinkedList class, and has LinkedList related functions.

public class MovieList {
    private Movie head;
    private int count = 1; //set to 1 because head is always assumed to exist
    protected Movie getCurrentMovie;


public void add(Movie newMovie){
    newMovie.setNext(null);
    if(head == null){
        head = newMovie;
    }
    else{
        Movie getTail = head;
        while(getTail.getNext() != null){
            getTail = getTail.getNext();
        }
        getTail.setNext(newMovie);
    }
    this.count++;
}


public void removeLast(){
    if(head != null){
        Movie getTail = head;
        while(getTail.getNext().getNext() != null){
            getTail = getTail.getNext();
        }
        getTail.setNext(null);
    }
}

public void removeAt(int index){
    if(index == 0){
        head = head.getNext();
    }
    else{
        Movie indexMovie = head;
        Movie next = null;
        for(int i = 0; i < index-1; i++){
            indexMovie = indexMovie.getNext();
        }
        next = indexMovie.getNext();
        indexMovie.setNext(next.getNext());
        next = null;
        }
}

public Movie getTail(){
    Movie temp = head;
    while(temp.getNext() != null){
        temp = temp.getNext();
    }

    return temp;
}


//Getters & Setters
public Movie getHead() {
    return head;
}

public void setHead(Movie head) {
    this.head = head;
}

public int getLength(){
    return count;
}



}
