package textexcel;

public class CellValue {
	public enum ValueType { 
		EMPTY, NUMBER, STRING, DOUBLE, FORMULA
		}
    private ValueType type; 
    private int intValue;
    private double doubleValue;
	private String stringValue; 

	public String toString () {
		return "_"; 
	}
	
	public CellValue (){
		this.setToEmpty(); 
	}
	
	public CellValue (CellValue val){
		type = val.getType();
		intValue = val.getIntValue();
		stringValue = val.getStringValue();
        doubleValue = val.getDoubleValue();
	}
	
	public CellValue (int val) {
        this.setToEmpty();
		type = ValueType.NUMBER; 
		intValue = val;
	}

    public CellValue (double val) {
        this.setToEmpty();
        type = ValueType.DOUBLE;
        doubleValue = val;
    }

    public CellValue (String val){
        this.setToEmpty();
		type = ValueType.STRING;
		stringValue = val; 
	}

    public CellValue(String formula, double value){
        this.setToEmpty();
        type = ValueType.FORMULA;
        stringValue = formula;
        doubleValue = value;
    }

	public void setToEmpty() {
		type = ValueType.EMPTY; 
		intValue = 0;
        doubleValue = 0.0f;
		stringValue = "";
	}
	
	public ValueType getType() {
		return type;
	}
	
	public int getIntValue() {
		return intValue;
	}
	
	public String getStringValue() {
		return stringValue;
	}

    public double getDoubleValue() { return doubleValue; }
}