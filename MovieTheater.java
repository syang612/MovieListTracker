import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;

public class MovieTheater {
   
    private static MovieList list = new MovieList();
    private static Scanner myScanner = new Scanner(System.in);
    private static int counter; //counter for TimerTask
    private static boolean switchPic = true; //for toggling movie image in GUI



    public static void main(String[] args){
        firstMovie(); //asks for first movie on list
        System.out.println();
        addToMovieList(); //adds more movies according to user input
        System.out.println();
        playMovies(); //"plays" the movies (just prints out movie list)
        System.out.println();
        removeList();

       /*
        I followed Java tutorials for TimerTask, because I wanted to implement some
        "countdown" functionality that could sort of simulate watching a movie.

        NOTE: I did not know how to implement TimerTask beforehand, so lines 43-62
        were learned from https://www.youtube.com/watch?v=QEF62Fm81h4 tutorial, with 
        minor adjustments. 

    */
    Timer timer = new Timer();
        
    TimerTask task = new TimerTask(){

        @Override
        public void run(){  
            Movie currentMovie = list.getHead();
            if (counter < list.getLength()){
                System.out.println("Playing: " + currentMovie.getTitle());
                currentMovie = currentMovie.getNext();
                counter++;
            }  
            else{
                System.out.println("Done all movies");
                timer.cancel();
            }
        }
    }; //end of TimerTask
  
    timer.scheduleAtFixedRate(task, 0, 5000); 
    
    //The following code demonstrates my usage of GUIs in this program
    //These integers outline necessary padding/position numbers for the buttons/labels in the code.
    int topPadding = 50;
    int xPadding = 50;
    int elementWidth = 400 - 35*2;
    int elementTopPadding = 35;
    int labelYPos = topPadding + 150 + elementTopPadding;

    //I initalized my window here
    JFrame myWindow = new JFrame("Movie Theater");
  
    //This is just a label for the first movie's title
    JLabel title = new JLabel("Playing: " + list.getHead().getTitle());
    title.setBounds(xPadding+50,labelYPos+150,elementWidth,100);
    title.setFont(new Font("SansSerif", Font.BOLD, 22));
    
    //Images simulating an "off" and "on" movie screen
    Image whiteScreen = new ImageIcon("/Users/sarahyang/Documents/Desktop/CMPINF0401/MovieList Tracker/src/Images/whitescreen.png").getImage();
    Image scaledImage = whiteScreen.getScaledInstance(150, 150, Image.SCALE_DEFAULT); 
    Icon icon = new ImageIcon(scaledImage);
   
    Image blackScreen = new ImageIcon("/Users/sarahyang/Documents/Desktop/CMPINF0401/MovieList Tracker/src/Images/blackscreen.png").getImage();
    Image scaledImage2 = blackScreen.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
    Icon icon2 = new ImageIcon(scaledImage2);

    // I initialized the button used to show movie screen image
    JButton myButton = new JButton(icon);
    myButton.setBounds(xPadding,labelYPos-elementTopPadding,elementWidth,200);
    myButton.setBorderPainted(false);
    myButton.setFocusPainted(false);
    myButton.setBackground(Color.DARK_GRAY);
  

    myButton.addActionListener(new ActionListener(){

    //The button action is displayed here. It basically toggles the movie screen image 
        @Override
        public void actionPerformed(ActionEvent e){
            
            if (switchPic == true){
                switchPic = false;
            }
            else {
                switchPic = true; 
            }

            if(switchPic){
                myButton.setIcon(icon);
            }
            else {
                myButton.setIcon(icon2);

            }
        }
    });

    

    myWindow.setSize(425,700);
    myWindow.setResizable(true);
    myWindow.setLayout(null);
    myWindow.getContentPane().setBackground(Color.RED);
    myWindow.add(title);
    myWindow.add(myButton);
    myWindow.setVisible(true);

    myScanner.close();
 }//end of main method
   


    //Essentially just prints out all the movies in the MovieList LinkedList
    public static void playMovies(){
        Movie head = list.getHead();
        while(head != null){ //gets to last node, since the last node's next link is null
            System.out.println(head.playMovie());
            head = head.getNext();
        }
    }

    //This is the method called to add the first movie to the movie list.
    public static void firstMovie(){
        System.out.println("First movie on your list...What genre? 1 - Horror, 2 - Comedy, 3 - Action ");
        int genre = myScanner.nextInt();
        System.out.println("What is the title? (no spaces)");
        String title = myScanner.next();
        System.out.println("How many minutes in total is the movie?");
        int minutes = myScanner.nextInt();

        Movie movie = returnGenre(genre);
        movie.setTitle(title);
        movie.setMinutes(minutes);
        movie.setNext(null);
        list.setHead(movie);
    }

    /*
    This is the method for user adding movies after the first movie in the list. It resembles
    the firstMovie() method, but differs slightly in print statements, the usage of a for loop, and
    use of the add() method.
    */

    public static void addToMovieList(){
            System.out.println("How many movies would you like to add?");
            int numMovies = myScanner.nextInt();
    
            for(int i = 0; i < numMovies; i++){
                System.out.println("What genre movie? 1 - Horror, 2 - Comedy, 3 - Action");
                int genre = myScanner.nextInt();
                System.out.println("What is the title?");
                String title = myScanner.next();
                System.out.println("How many minutes in total is the movie?");
                int minutes = myScanner.nextInt();
                System.out.println();
    
                Movie movie = returnGenre(genre);
                movie.setTitle(title);
                movie.setMinutes(minutes);
                movie.setNext(null);
                list.add(movie);
        }
    }

    //I wrote this method to make use of my LinkedList removeAt(int index) method.
    public static void removeList(){
        while(true){
            try {
                System.out.println("Would you like to remove a movie from the list? Type 1 for yes, 2 for no");
                int nextAction = myScanner.nextInt();
        
                if(nextAction == 1){ //checks for user input
                   System.out.println("At what index would you like to remove a movie?");
                   int index = myScanner.nextInt();
        
                    if(index >= 0 && index < list.getLength()){ //ensures that user input is within bounds
                        list.removeAt(index);
                        playMovies();
                        break;
                    }
                    else{ 
                        System.out.println("Index out of bounds");
                        break;
                    }
                }
                else { //if user inputs that they would not like to remove a movie from list
                    System.out.println("Movie list kept the same.");
                    break;
                }
            }
            catch (InputMismatchException e){
                System.out.println("Not an integer, please input 1 or 2");
                myScanner.nextLine();
            }

        }
    }

    /*I wrote this method for more reusability and readability. It 
    returns a Movie subclass object according to what is passed into the
    int parameter. */
    public static Movie returnGenre(int genre){

        switch(genre){
            case 1: 
            HorrorMovie newHorror = new HorrorMovie();
            return newHorror;
            case 2:
            ComedyMovie newComedy = new ComedyMovie();
            return newComedy;
            case 3:
            ActionMovie newAction = new ActionMovie();
            return newAction;
            default:
            return new HorrorMovie(); //returns horror movie on default

        }

    }

}

   



