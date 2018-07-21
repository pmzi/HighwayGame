package sample.Controllers;

/**
 * Created by pmzi on 7/13/2018.
 */
public abstract class BaseController {

    public void insertData(){
        System.out.println("Default insert data method.");
    };

    public void sceneDidMount(){
        System.out.println("Default scene did mount method.");
    };

    public abstract void exit();

}
