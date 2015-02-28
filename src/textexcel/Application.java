package textexcel;

public class Application {
    static final int COLUMNS = 7;
    static final int ROWS = 10;
    private Spreadsheet sheet;
    private SpreadsheetView view;

    public Application() {
        sheet = new Spreadsheet(this, ROWS, COLUMNS);
        view = new SpreadsheetView(this, sheet);
    }

    public Spreadsheet getSpreadsheet () {
        return sheet;
    }
    public SpreadsheetView getView (){
        return view;
    }
}
