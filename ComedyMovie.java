/* 
 ComedyMovie is a subclass of Movie super class.
*/
public class ComedyMovie extends Movie{

    public ComedyMovie(String title, int minutes, Movie nextMovie){
        super(title, minutes, nextMovie);
        super.hours = (super.minutes)/60;
        super.minutes = (super.minutes)%60;
    }

    public ComedyMovie(){
        super();
    }


    @Override
    public String playMovie(){
        return "Comedy Movie: " + title + ", Length: " + minutes + " minutes";
    }


}
