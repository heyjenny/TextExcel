package textexcel;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionSolverTest {

    class ExpressionSolverStub extends ExpressionSolver {
        public ExpressionSolverStub(String str) {
            super(str);
        }

        @Override
        public double cellToValue(String cellRef) {
            if (cellRef.equals("A1")) return 3.0f;
            if (cellRef.equals("C3")) return 32.0f;
            return 0.0f;
        }
    }

    @Test
    public void testParseNumberOrCell() throws Exception {
        assert (new ExpressionSolverStub("3").parseNumberOrCell() == 3.0);
        assert (new ExpressionSolverStub("32").parseNumberOrCell() == 32.0);
        assert (new ExpressionSolverStub("75.4").parseNumberOrCell() == 75.4);

        assert (new ExpressionSolverStub("A1").parseNumberOrCell() == 3.0);
        assert (new ExpressionSolverStub("C3").parseNumberOrCell() == 32.0);
    }
}