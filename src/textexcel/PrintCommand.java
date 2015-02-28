/**
 * Created by hey_jenny on 2/1/15.
 */
package textexcel;
public class PrintCommand extends GenericCommand implements Command {

    public PrintCommand(Application app) {
        super(app);
    }

    @Override
    public boolean run(String arguments) {
        application.getView().renderSpreadsheet();
        return true;
    }
}
