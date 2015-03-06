
package textexcel;

public class ExpressionSolver {
    private char currChar;
    private int index;
    String str;
    private Application app;

    public ExpressionSolver(Application app, String str){
        index = 0;
        this.str = str;
        currChar = str.charAt(index);
        this.app = app;
    }
    public double solve () {
        return parseTerm();
    }

    public ExpressionSolver setStr(String str) { this.str = str; this.index = 0; this.currChar = str.charAt(index); return this; }

    public char getCurrChar() { return currChar;}

    public void consumeCharacter(){
        currChar = (++index < str.length()) ? str.charAt(index): (char)-1;
    }
    public void consumeSpace(){
        while (currChar == ' ') {
            consumeCharacter();
        }
    }

    public static void main(String[] args) {
        String test1 = "a b";
        String test2 = "a   b";
        String test3 = "a  ";

        ExpressionSolver solver = new ExpressionSolver(null, test1);
        solver.consumeCharacter();
        solver.consumeSpace();
        if (solver.getCurrChar() == 'b') {
            System.out.println("OK");
        } else {
            System.out.println("ERROR");
        }

        ExpressionSolver solver2 = new ExpressionSolver(null, test2);
        solver2.consumeCharacter();
        solver2.consumeSpace();
        if (solver2.getCurrChar() == 'b') {
            System.out.println("OK");
        } else {
            System.out.println("ERROR");
        }

        ExpressionSolver solver3 = new ExpressionSolver(null, test3);
        solver3.consumeCharacter();
        solver3.consumeSpace();
        if (solver3.getCurrChar() == (char) -1) {
            System.out.println("OK");
        } else {
            System.out.println("ERROR");
        }

        String numtest = "3";
        String numtest2 = "32";
        String numtest3 = "75.4";

        String termtest = "3 + 5.2";
        String termtest2 = "4  -   2.1";
        String termtest3 = " 5.3";

        solver.setStr(numtest);
        if (solver.parseNumberOrCell() == 3.0) {
            System.out.println("OK");
        } else {
            System.out.println("ERROR");
        }

        solver.setStr(numtest2);
        if (solver.parseNumberOrCell() == 32.0) {
            System.out.println("OK");
        } else {
            System.out.println("ERROR");
        }

        solver.setStr(numtest3);
        if (solver.parseNumberOrCell() == 75.4) {
            System.out.println("OK");
        } else {
            System.out.println("ERROR");
        }

        solver.setStr(termtest);
        if (solver.parseTerm() == 8.2) {
            System.out.println("OK");
        } else {
            System.out.println("ERROR");
        }

        solver.setStr(termtest2);
        if (solver.parseTerm() == 1.9) {
            System.out.println("OK");
        } else {
            System.out.println("ERROR");
        }

        solver.setStr(termtest3);
        if (solver.parseTerm() == 5.3) {
            System.out.println("OK");
        } else {
            System.out.println("ERROR");
        }

        String factortest = "3  * 4 * 2";
        String factortest2 = "72.3";
        String factortest3 = "3+4*2";
        String factortest4 = "4*2 +3";

        solver.setStr(factortest);
        if (solver.parseTerm() == 24) {
            System.out.println("OK");
        } else {
            System.out.println("ERROR");
        }
        solver.setStr(factortest2);
        if (solver.parseTerm() == 72.3) {
            System.out.println("OK");
        } else {
            System.out.println("ERROR");
        }
        solver.setStr(factortest3);
        if (solver.parseTerm() == 11) {
            System.out.println("OK");
        } else {
            System.out.println("ERROR");
        }
        solver.setStr(factortest4);
        if (solver.parseTerm() == 11) {
            System.out.println("OK");
        } else {
            System.out.println("ERROR");
        }


    }

    public double cellToValue(String cellRef) {
        return app.getSpreadsheet().getCell(CommandParser.lettersToNumbers(cellRef)).getValue().getDoubleValue();
    }

    public double parseNumberOrCell(){
        StringBuilder digits = new StringBuilder();

        if (Character.isAlphabetic(currChar)){
            digits.append(currChar);
            consumeCharacter();
            while (Character.isDigit(currChar) || currChar == '.') {
                digits.append(currChar);
                consumeCharacter();
            }
            return cellToValue(digits.toString());
        }

        while (Character.isDigit(currChar) || currChar == '.') {
            digits.append(currChar);
            consumeCharacter();
        }
        return Double.parseDouble(digits.toString());
    }
    public double parseTerm(){
        consumeSpace();
        double lvalue = parseFactor();

        for(;;) {
            consumeSpace();
            char operation = currChar;

            if (operation == '+') {
                consumeCharacter();
                consumeSpace();
                double rvalue = parseFactor();
                lvalue += rvalue;
            } else if (operation == '-') {
                consumeCharacter();
                consumeSpace();
                double rvalue = parseFactor();
                lvalue -= rvalue;
            } else {
                return lvalue;
            }
        }
    }
    public double parseFactor() {
        consumeSpace();
        double lvalue = parseNumberOrCell();

        for (;;) {
            consumeSpace();
            char operation = currChar;
            if (operation == '*') {
                consumeCharacter();
                consumeSpace();
                double rvalue = parseNumberOrCell();
                lvalue *= rvalue;
            } else if (operation == '/') {
                consumeCharacter();
                consumeSpace();
                double rvalue = parseNumberOrCell();
                lvalue /= rvalue;
            } else {
                return lvalue;
            }
        }
    }
}
