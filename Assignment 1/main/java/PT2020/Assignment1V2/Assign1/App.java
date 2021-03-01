package PT2020.Assignment1V2.Assign1;

import controller.Controller;
import model.Operations;
import view.View;

/**
 * Hello world!
 *
 */
public class App{
    public static void main( String[] args ){
    	
        //System.out.println( "Hello World!" );
    	Operations model = new Operations();
    	View view = new View(model);
    	Controller controller = new Controller(model, view);
    	
    	view.setVisible(true);
    }
}
