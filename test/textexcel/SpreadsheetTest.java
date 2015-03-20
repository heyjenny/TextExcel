package textexcel;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by hey_jenny on 3/19/15.
 */
public class SpreadsheetTest {
    private Spreadsheet sheet;


    @Before
    public void prepareSpreadsheet() {
        sheet = new Spreadsheet(new Application(), 10, 10);
        double num = 0;
        double num1 = 1;
        double num2 = -1;
        CellValue val = new CellValue(num);
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 4; j++) {
                if (i == 0) {
                    num += 2;
                    sheet.setCell(0, j, new CellValue(num));
                } else if (i == 1) {


                    sheet.setCell(1, j, new CellValue(num1));

                    num1 /= 2;
                } else if (i == 2) {

                    sheet.setCell(2, j, new CellValue(num2));
                    num2--;
                }
            }
        }
    }


    @Test
    public void testSum() {
        assert (sheet.calculateSum(new int[]{0, 1}, new int[]{0, 3}) == 18.0);
        assert (sheet.calculateSum(new int[]{2, 0}, new int[]{2, 0}) == -1.0);
        assert (sheet.calculateSum(new int[]{0, 2}, new int[]{2, 2}) == 3.25);
        assert (sheet.calculateSum(new int[]{0, 1}, new int[]{2, 3}) == 9.875);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumWithSurprise() {
        sheet.setCell(0, 2, new CellValue("Ha ha ha!"));
        sheet.calculateSum(new int[]{0, 1}, new int[]{0, 3});
    }

    @Test
    public void testAverage() {
        assert (sheet.calculateAvg(new int[]{0, 1}, new int[]{0, 3}) == 6.0);
        assert (sheet.calculateAvg(new int[]{2, 0}, new int[]{2, 0}) == -1.0);
        assert (sheet.calculateAvg(new int[]{0, 2}, new int[]{2, 2}) == 1.0 + (1.0 / 12));
        assert (sheet.calculateAvg(new int[]{0, 1}, new int[]{2, 3}) == 9.875 / 9.0);

    }

    @Test
    public void testInversion() {
        assert (sheet.calculateAvg(new int[]{2, 2}, new int[]{0, 2}) == 1.0 + (1.0 / 12));
    }
}
