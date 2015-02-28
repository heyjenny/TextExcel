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


	}
	
	
	
	
	
