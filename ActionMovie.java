/* 
 ActionMovie is a subclass of Movie super class.
*/
public class ActionMovie extends Movie{

    public ActionMovie(String title, int minutes, Movie nextMovie){
        super(title, minutes, nextMovie);
        super.hours = (super.minutes)/60;
        super.minutes = (super.minutes)%60;
    }

    public ActionMovie(){
        super();
    }


    @Override
    public String playMovie(){
        return "Action Movie: " + title + ", Length: " + minutes + " minutes";
    }


}
