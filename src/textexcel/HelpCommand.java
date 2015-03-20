package textexcel;

/**
 * Created by hey_jenny on 3/20/15.
 */
public class HelpCommand extends GenericCommand implements Command {

    public HelpCommand(Application app) {
        super(app);
    }

    @Override
    public boolean run(String arguments) {
        System.out.println("HELP: " +
                "TO SET A CELL, ENTER THE VALUES IN THE FORMAT: A1 = value. " +
                "\nTO SEE WHAT THE CELL CONTAINS, SIMPLY TYPE ITS REFERENCE (ex.A1) " +
                "\nTYPE IN FORMULAS IN PARENTHESES AND TYPE IN (sum [range]) or (avg [range]) TO GET A SUM OR AVERAGE" +
                "\nTO EXIT, PRINT exit. TO PRINT THE TABLE OF VALUES, PRINT print.");

        return true;
    }
}
