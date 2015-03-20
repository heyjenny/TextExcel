package textexcel; 
		import java.sql.*;

public class SpreadsheetView { 
	private Spreadsheet sheet;
    private Application application;
	public SpreadsheetView (Application app, Spreadsheet s){
		sheet = s; 
		}
	public void renderSpreadsheet () { 
		this.renderHeader();
		this.renderBorder();
		for (int row = 0; row < sheet.getRowsCount(); row++) {
			this.renderRowPrefix(row);
			this.renderRow(row);
			this.renderBorder(); 
		}
	}
		
	private void renderHeader() {
		System.out.println("            |     A      |     B      |     C      |     D      |     E      |     F      |     G      |");

        /*char letter = 'A';
	for (int column = 0; sheet.getColumnsCount(); column++){
	letter
		
	}*/
	}
	
	
	private void renderRowPrefix(int row) {
		int row_temp = row+1; 
		System.out.print(alignNumber(row_temp, 12));
		this.renderVBar();
	}
	
	private void renderRow(int row) {
		for (int column = 0; column < sheet.getColumnsCount(); column++) {
			this.renderCellValue(sheet.getCell(row, column).getValue());
			this.renderVBar(); 
		}
		System.out.println("");
	
	}
	private void renderBorder() {
		String hborder = repeatSymbol(12, '-') + "+";
		for (int column = 0; column < sheet.getColumnsCount()+1; column++) {
			System.out.print (hborder); 
		}
		System.out.println("");
	}
	private void renderVBar() {
		System.out.print ("|"); 
	}

    public void renderCellValue(CellValue val) {
        renderCellValue(val, true);
    }

	public void renderCellValue(CellValue val, boolean align) {
		String valueStr = "";
        switch (val.getType() ){
			case EMPTY: break;
            case STRING:
                valueStr = val.getStringValue(); break;
			case NUMBER:
                valueStr = Integer.toString(val.getIntValue()); break;
            case DOUBLE:
            case FORMULA:
            case FUNCTION:
                valueStr = Double.toString(val.getDoubleValue()); break;
		}

        valueStr = stringLengthChecker(valueStr, 12);
        String alignedString = align ? alignString(valueStr, 12) : valueStr;
        System.out.print(alignedString);
	}

    public void renderCellValueAsSetCommand(String coordinates, CellValue val) {
        System.out.print(coordinates + " = ");
        switch (val.getType() ){
            case EMPTY: System.out.print ("<empty>"); break;
            case FORMULA:
            case FUNCTION:
                System.out.print(" ( " + val.getStringValue() + " ) ");
                break;
            case STRING:
                System.out.print("\"" + val.getStringValue() + "\"");
                break;
            case NUMBER:
                System.out.print(val.getIntValue());
                break;
            case DOUBLE:
                System.out.print(val.getDoubleValue());
                break;
        }
    }

    public void renderCellValueAsSetCommand(int row, int column, CellValue val) {
        renderCellValueAsSetCommand(CommandParser.numbersToLetters(row, column), val);
    }
	
	private String repeatSymbol(int n, char sym){
		return (new String(new char[n]).replace('\0', sym));
	}
	
	private String alignNumber(int num, int width) {
		String numberAsString = Integer.toString(num);
		return alignString(numberAsString, width);
	}
    private String alignDouble (double num, int width) {
        String numberAsString = Double.toString(num);
        return alignString(numberAsString, width);
    }

    private String alignString (String str, int width) {
        int    numberLength = str.length();
        int    spacesLength = width - numberLength;
        int    prefixLength = spacesLength / 2;
        int    suffixLength = spacesLength - prefixLength;
        String prefix = repeatSymbol(prefixLength, ' ');
        String suffix = repeatSymbol(suffixLength, ' ');
        return prefix + str + suffix;
    }

    private String stringLengthChecker (String str, int maxWidth) {
        if (str.length() > maxWidth) {
             return str.substring(0, maxWidth-1) +">";
        } else {
            return str;
        }
    }
	
}