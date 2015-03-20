package textexcel;
import textexcel.Cell;
import textexcel.CellValue; 

public class Spreadsheet {
	  private int row;
      private int column; 
      private Cell [][] cells;
      private Application application;

	public Spreadsheet(Application app, int r, int c){
		application = app;
        row = r;
		column = c; 
		cells= new Cell [row][column];
	
			
			for (int i = 0; i < row; i++){
				for (int j = 0; j < column; j++){
				     cells [i][j] = new Cell();
				}
			}
		}
		
		public int getRowsCount() { return row; }
		public int getColumnsCount() { return column; }


    public void clearCell(int row, int column){
			cells [row][column].clearValue(); 
		}

        public void setCell(int[] coordinates, CellValue val){
            setCell(coordinates[0], coordinates[1], val);
        }
        public Cell getCell (int[] coordinates){
            return getCell(coordinates[0], coordinates[1]);
        }
		public void setCell(int row, int column, CellValue val){
			cells [row][column].setValue(val);
		}
		public Cell getCell (int row, int column){
			return cells[row][column];
		}

    public void clearRecalculated() {
        for (int i = 0; i < getRowsCount(); i++) {
            for (int k = 0; k < getColumnsCount(); k++) {
                getCell(i, k).setRecalculated(false);
            }
        }

    }

    public double calculateSum(int[] start, int[] end) throws IllegalArgumentException {
        int top = Math.min(start[0], end[0]);
        int bottom = Math.max(start[0], end[0]);
        int left = Math.min(start[1], end[1]);
        int right = Math.max(start[1], end[1]);

        double sum = 0;


        for (int i = top; i <= bottom; i++) {

            for (int j = left; j <= right; j++) {
                CellValue val = getCell(i, j).getValue();
                if (val.getType() != CellValue.ValueType.DOUBLE && val.getType() != CellValue.ValueType.FORMULA) {
                    throw new IllegalArgumentException("Wrong format");
                }
                sum += getCell(i, j).getValue().getDoubleValue();

            }
        }
        return sum;
    }

    public double calculateAvg(int[] start, int[] end) {

        int count = (Math.abs(start[0] - end[0]) + 1) * (Math.abs(start[1] - end[1]) + 1);
        double sum = calculateSum(start, end);
        return (sum / count);
    }

    public void recalculateCells() {

        clearRecalculated();
        for (int i = 0; i < getRowsCount(); i++) {
            for (int k = 0; k < getColumnsCount(); k++) {
                if (!getCell(i, k).isRecalculated()) {
                    getCell(i, k).recalculate(application);
                }
            }
        }

    }


	}
	
	
	
	
	
