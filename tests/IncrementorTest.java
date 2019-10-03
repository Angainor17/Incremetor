import exceptions.WrongMaxNumberException;
import incrementor.Incrementor;
import incrementor.IncrementorImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class IncrementorTest {

    private Incrementor incrementor = new IncrementorImpl();

    @Test
    public void base() {
        int iterationCount = 1;
        incrementor = new IncrementorImpl();

        assertEquals(0, incrementor.getNumber());

        for (long i = 0; i < iterationCount; i++) {
            incrementor.incrementNumber();
        }

        assertEquals(iterationCount, incrementor.getNumber());
    }

    @Test
    public void eternalIncrement() {
        final long iterations = 2L * Integer.MAX_VALUE;
        for (long i = 0; i < iterations; i++) {
            incrementor.incrementNumber();
        }
        assertTrue(true);
    }

    @Test
    public void zeroing() throws WrongMaxNumberException {
        incrementor = new IncrementorImpl();

        int maxValue = 42;
        incrementor.setMaximumValue(maxValue);

        for (int i = 0; i < maxValue; i++) {
            incrementor.incrementNumber();

            if (i == maxValue - 2) {
                assertNotEquals(0, incrementor.getNumber());
            }
        }
        assertEquals(0, incrementor.getNumber());
    }

    @Test
    public void zeroingAfterMaxSet() throws WrongMaxNumberException {
        incrementor = new IncrementorImpl();

        int maxValue = 4242;

        for (int i = 0; i < maxValue; i++) {
            incrementor.incrementNumber();
        }

        incrementor.setMaximumValue(maxValue / 2);
        assertEquals(0, incrementor.getNumber());
    }

    @Test(expected = WrongMaxNumberException.class)
    public void wrongMaxValue() throws WrongMaxNumberException {
        incrementor.setMaximumValue(0);
        incrementor.setMaximumValue(2);

        incrementor.setMaximumValue(-1);
    }
}