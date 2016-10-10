package models;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 * Created by kamilwrobel on 10.10.2016.
 */
public class ActionManager extends Observable implements Action {

    private List<Action> actionList;

    public ActionManager(){
        init();
    }

    public void init(){
        actionList = new LinkedList<Action>();
    }

    public void perform() throws Exception {
        for(Action action : actionList){
            action.perform();
        }
    }

    public void addAction(Action action){
        actionList.add(action);
        setChanged();
        notifyObservers();
    }

    public void removeAction(Action action){
        actionList.remove(action);
        setChanged();
        notifyObservers();
    }

    public List<Action> getActionList(){
        return actionList;
    }

    public void forceUpdate(){
        setChanged();
        notifyObservers();
    }

}
