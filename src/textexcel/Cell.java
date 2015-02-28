package textexcel;

public class Cell {
	private CellValue val;
	
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
}