/* 
 HorrorMovie is a subclass of Movie super class.
*/
public class HorrorMovie extends Movie{

    public HorrorMovie(String title, int minutes, Movie nextMovie){
        super(title, minutes, nextMovie);
        super.hours = (super.minutes)/60;
        super.minutes = (super.minutes)%60;
    }

    public HorrorMovie(){
        super();
    }


    @Override
    public String playMovie(){
        return "Horror Movie: " + title + ", Length: " + minutes + " minutes";
    }

}
