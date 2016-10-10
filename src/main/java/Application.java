import controllers.MainController;
import models.ActionManager;
import models.MouseClickAction;

/**
 * Created by kamilwrobel on 10.10.2016.
 */
public class Application {
    public static void main(String... args) throws Exception{
        ActionManager am = new ActionManager();
        MainController controller = new MainController(am);
        controller.startApplication();

        //am.addAction(new MouseClickAction());


    }
}
