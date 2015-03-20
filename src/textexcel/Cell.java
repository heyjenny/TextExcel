package textexcel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cell {
	private CellValue val;
    private boolean recalculated;

    public boolean isRecalculated() {
        return recalculated;
    }

    public void setRecalculated(boolean recalculated) {
        this.recalculated = recalculated;
    }

    public Cell() {
        this.val = new CellValue();
	}
	
	public Cell(CellValue val) {
		this.val = val;
	}
	
	public CellValue getValue() {
		return val;
	}
	public CellValue setValue (CellValue newValue){
		val=newValue; 
		return val; 
	}
	public void clearValue () {
		val.setToEmpty(); 
	}
	
	public String toString() {
		return val.toString(); 
	}

    public void recalculate(Application application) {

        if ((val.getType() == CellValue.ValueType.FORMULA || val.getType() == CellValue.ValueType.FUNCTION) && !isRecalculated()) {
            List<String> dependencies = getDependencies();
            if (dependencies.size() != 0) {
                setRecalculated(true);
                for (String dependency : dependencies) {
                    application
                            .getSpreadsheet()
                            .getCell(CommandParser.lettersToNumbers(dependency))
                            .recalculate(application);
                }
            }
            double value = 0.f;
            switch (val.getType()) {
                case FORMULA:
                    value = (new textexcel.ExpressionSolver(application, val.getStringValue())).solve();
                    break;
                case FUNCTION:
                    value = (new textexcel.ExpressionSolver(application, val.getStringValue())).solveFunction();
                    break;
            }
            this.val = new CellValue(val.getStringValue(), value);
        }

        setRecalculated(true);
    }

    private List<String> getDependencies() {
        ArrayList<String> dependencies = new ArrayList<String>();
        if (val.getType() == CellValue.ValueType.FORMULA || val.getType() == CellValue.ValueType.FUNCTION) {
            Pattern regex = Pattern.compile("[A-Z]{1}\\d+");
            Matcher match = regex.matcher(val.getStringValue());
            while (match.find()) {
                dependencies.add(match.group());
            }
        }
        return dependencies;


    }
}