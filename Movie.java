/* 
Movie is the Node class. It is also an abstract superclass, extended
by sub classes, HorrorMovie, ComedyMovie and ActionMovie 
*/

public abstract class Movie {
    protected String title;
    protected int minutes;
    protected Movie nextMovie;
    protected int hours;

    //default constructor
    public Movie(){
    }

    public Movie(String title, int minutes, Movie nextMovie){
        this.title = title;
        this.minutes = minutes;
        this.nextMovie = nextMovie;
    }

    public Movie getNext(){
        return nextMovie;
    }

    public void setNext(Movie nextMovie){
        this.nextMovie = nextMovie;
    }
                    
    // getters & setters

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
   
    //abstract method of playMovie() --> every subclass MUST implement
    public abstract String playMovie();

}
