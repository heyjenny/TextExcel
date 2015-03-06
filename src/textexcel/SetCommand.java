
package textexcel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SetCommand extends GenericCommand implements Command {
    public SetCommand(Application app) {
        super(app);
    }

    @Override
    public boolean run(String arguments) {
        Pattern pattern = Pattern.compile(CommandParser.SET_COMMAND_REGEX);
        Matcher match = pattern.matcher(arguments);
        match.find();
        String cellReference = match.group(1);
        String newStrValue = match.group("string");
        String newDoubleValue = match.group("number");
        String newFormulaValue = match.group("formula");

        int [] coordinates = CommandParser.lettersToNumbers(cellReference);

        CellValue cellNewValue = null;
        if(newFormulaValue != null) {
            double value = (new textexcel.ExpressionSolver(application, newFormulaValue)).solve();
            cellNewValue = new CellValue(newFormulaValue, value);
        } else {
            cellNewValue = newDoubleValue != null ? new CellValue(Double.parseDouble(newDoubleValue)) :
                    new CellValue(newStrValue);
        }
        application.getSpreadsheet().setCell(coordinates, cellNewValue);
        return true;
    }
}
