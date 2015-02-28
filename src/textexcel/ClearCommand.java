package textexcel;

/**
 * Created by hey_jenny on 2/15/15.
 */
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class ClearCommand extends GenericCommand implements Command {
    public ClearCommand(Application app) {
        super(app);
    }


    @Override
    public boolean run(String arguments) {
        if (arguments.equals("clear")) {
            application.getSpreadsheet();
            for (int i = 0; i < application.getSpreadsheet().getRowsCount(); i++) {
                for (int k = 0; k < application.getSpreadsheet().getColumnsCount(); k++){
                    application.getSpreadsheet().getCell(i, k).clearValue();
                }
            }

        } else {
            Pattern pattern = Pattern.compile(CommandParser.CLEAR_COMMAND_REGEX);
            Matcher match = pattern.matcher(arguments);
            match.find();
            String cellReference = match.group("cell");

            application.getSpreadsheet().getCell(CommandParser.lettersToNumbers(cellReference)).clearValue();
        }
        return true;
    }
}
