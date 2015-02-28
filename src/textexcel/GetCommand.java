/**
 * Created by hey_jenny on 2/6/15.
 */
package textexcel;

public class GetCommand extends GenericCommand implements Command {
    public GetCommand(Application app) {
        super(app);
    }

    @Override
    public boolean run(String arguments) {
        int [] coordinates = CommandParser.lettersToNumbers(arguments);
        CellValue value = application.getSpreadsheet().getCell(coordinates).getValue();
        application.getView().renderCellValueAsSetCommand(arguments, value);
        System.out.println("");
        return true;
    }
}
