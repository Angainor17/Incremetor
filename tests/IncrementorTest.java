import exceptions.WrongMaxNumberException;
import incrementor.Incrementor;
import incrementor.IncrementorImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Тестирование реализации сущности {@link Incrementor}
 * {@link IncrementorImpl}
 */
public class IncrementorTest {

    private Incrementor incrementor;

    @Before
    public void init() {
        incrementor = new IncrementorImpl();
    }

    /**
     * Базовый сценарий
     */
    @Test
    public void base() throws WrongMaxNumberException {
        int iterationCount = 3;

        assertEquals(0, incrementor.getNumber());
        incrementor.setMaximumValue(1000);

        for (int i = 0; i < iterationCount; i++) {
            incrementor.incrementNumber();
        }

        assertEquals(iterationCount, incrementor.getNumber());
    }

    /**
     * Вызов {@link Incrementor#getNumber()} не изменяет значение
     */
    @Test
    public void getNumberIdempotency() {
        int iterationCount = 142;

        assertEquals(0, incrementor.getNumber());

        for (int incrementIteration = 1; incrementIteration < iterationCount; incrementIteration++) {
            incrementor.incrementNumber();

            for (int j = 0; j < 10; j++) {
                assertEquals(incrementIteration, incrementor.getNumber());
            }
        }
    }

    /**
     * Количество операций инкремента превышает дефолтный максимум
     */
    @Test
    public void overMaxIncrements() {
        final int defaultMax = Integer.MAX_VALUE;
        final long iterations = 3L * defaultMax;

        for (long i = 1; i < iterations; i++) {
            incrementor.incrementNumber();
            assertEquals(i % defaultMax, incrementor.getNumber());
        }
    }

    /**
     * Установка максимального значения и его превышение
     */
    @Test
    public void zeroing() throws WrongMaxNumberException {
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

    /**
     * Обнуление значения при достижении максимума
     *
     * @throws WrongMaxNumberException - Максимальное число задано не корректно
     */
    @Test
    public void zeroingAfterMaxSet() throws WrongMaxNumberException {
        int maxValue = 4242;

        for (int i = 0; i < maxValue; i++) {
            incrementor.incrementNumber();
        }

        incrementor.setMaximumValue(maxValue / 2);
        assertEquals(0, incrementor.getNumber());
    }

    /**
     * Проверка наличия Exception в случае некорректного параметра {@link Incrementor#setMaximumValue(int)}
     *
     * @throws WrongMaxNumberException - Максимальное число задано не корректно
     */
    @Test(expected = WrongMaxNumberException.class)
    public void negativeMaxValue() throws WrongMaxNumberException {
        incrementor.setMaximumValue(-1);
    }

    /**
     * Проверка maxNumber = 0.
     * Ожидается, что будет возвращён 0 после инкрмента
     *
     * @throws WrongMaxNumberException - Максимальное число задано не корректно
     */
    @Test(expected = WrongMaxNumberException.class)
    public void zeroMaxValue() throws WrongMaxNumberException {
        incrementor.setMaximumValue(0);

        int iterationCount = 111;

        for (int i = 0; i < iterationCount; i++) {
            incrementor.incrementNumber();
            assertEquals(0, incrementor.getNumber());
        }
    }
}