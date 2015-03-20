package textexcel;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    public static final String CELL_REFERENCE_REGEX = "^([A-Z]{1})(\\d+)$";
    public static final String SET_COMMAND_REGEX = "^([A-Z]\\d+)\\s*=\\s*(?:\"(?<string>[\\w\\s]*)\"|(?<number>\\d*\\.?\\d*)|\\((?<formula>[A-Z0-9 +-/*]*)\\)\\s*|\\((?<function>\\s*(sum|avg)\\s*[A-Z]{1}\\d+\\s*-\\s*[A-Z]{1}\\d+\\s*)\\))$";
    public static final String CLEAR_COMMAND_REGEX = "(^clear (?<cell>([A-Z]{1})(\\d+))|(^clear))";


    private Map<Pattern, Command> commands = new HashMap<Pattern, Command>();

    public CommandParser(Application app) {
        commands.put(Pattern.compile("^print$"), new PrintCommand(app));
        commands.put(Pattern.compile("^exit$"), new ExitCommand(app));
        commands.put(Pattern.compile(CELL_REFERENCE_REGEX), new GetCommand(app));
        commands.put(Pattern.compile(SET_COMMAND_REGEX), new SetCommand(app));
        commands.put(Pattern.compile(CLEAR_COMMAND_REGEX), new ClearCommand(app));
        commands.put(Pattern.compile("^help$"), new HelpCommand(app));
    }

    public static String numbersToLetters(int r, int c) {
        return (Character.toChars('A'+ c)[0] + Integer.toString(r + 1));
    }

    public static int [] lettersToNumbers (String input) {
        Pattern regex = Pattern.compile(CELL_REFERENCE_REGEX);
        Matcher match = regex.matcher(input);
        if (!match.find()) {
            throw new IllegalArgumentException("Wrong string");
        }

        String group1 = match.group(1);
        String group2 = match.group(2);

        int [] data = { Integer.parseInt(group2) - 1, Character.valueOf(group1.charAt(0)) - 'A'};

        return data;
    }


    public boolean parseInput(String input) {
        String command = this.sanitizeInput(input);
        return this.executeCommand(command);
    }

    private String sanitizeInput(String input) {
        return input.trim();
    }

    private boolean executeCommand(String command) {
        Iterator it = commands.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            Pattern pattern = (Pattern) pairs.getKey();
            Matcher match = pattern.matcher(command);
            if (match.find()) {
                return ((Command) pairs.getValue()).run(command);
            }
        }
        return true;
    }

    public static void main (String [] args) {
        String result = CommandParser.numbersToLetters(2, 3);
        if(result.equals("D3")) {
            System.out.println("Passed");
        } else {
            System.out.println("Failed - " + result);
        }

        result = CommandParser.numbersToLetters(2, 0);
        if(result.equals("A3")) {
            System.out.println("Passed");
        } else {
            System.out.println("Failed - " + result);
        }

        int [] result2 = CommandParser.lettersToNumbers("C2");
        int [] control2 = new int[] {1, 2};
        if (Arrays.equals(result2, control2)) {
            System.out.println("Passed");
        } else {
            System.out.println("Failed - " + result2[0] + ", " + result2[1]);
        }

        result2 = CommandParser.lettersToNumbers("B4");
        control2 = new int[] {3, 1};
        if (Arrays.equals(result2, control2)) {
            System.out.println("Passed");
        } else {
            System.out.println("Failed - " + result2[0] + ", " + result2[1]);
        }

        try {
            CommandParser.lettersToNumbers("$723");
            System.out.println("Failed");
        } catch (IllegalArgumentException ex) {
            System.out.println("Passed");
        }

        Pattern pattern = Pattern.compile(SET_COMMAND_REGEX);
        Matcher matcher = pattern.matcher("B4=\"mewow\"");
        if (matcher.find()) {
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }

    }
}