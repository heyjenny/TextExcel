package textexcel;

import org.junit.Test;

import static org.junit.Assert.*;

public class CellValueTest {

    @Test
    public void testSetToEmpty() throws Exception {
        String controlValue = "test";
        CellValue value = new CellValue(controlValue);
        assert(value.getType() == CellValue.ValueType.STRING);
        assert(value.getStringValue().equals(controlValue));
        value.setToEmpty();
        assert(value.getType() == CellValue.ValueType.EMPTY);
        assert(value.getStringValue().equals(""));
    }

    @Test
    public void testGetType() throws Exception {

    }

    @Test
    public void testGetIntValue() throws Exception {

    }

    @Test
    public void testGetStringValue() throws Exception {

    }

    @Test
    public void testGetDoubleValue() throws Exception {

    }
}