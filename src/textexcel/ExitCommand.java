package textexcel;

public class ExitCommand extends GenericCommand implements Command {

    public ExitCommand(Application app) {
        super(app);
    }

    @Override
    public boolean run(String arguments) {
        System.out.println ("Goodbye!");
        return false;
    }
}
