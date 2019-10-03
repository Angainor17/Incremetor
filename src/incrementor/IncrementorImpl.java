package incrementor;

import exceptions.WrongMaxNumberException;

/**
 * Сущность, которая позволяет инкрементировать целочисленное значение
 * Даёт возможность устанавливать максимальное значение
 */
public class IncrementorImpl implements Incrementor {

    private int value = 0;
    private int maximumValue = Integer.MAX_VALUE;

    /**
     * Возвращает текущее число. В самом начале это ноль.
     */
    @Override
    public int getNumber() {
        return value;
    }

    /**
     * Увеличивает текущее число на один. После каждого вызова этого
     * метода {@link #getNumber()} будет возвращать число на один больше.
     */
    @Override
    public void incrementNumber() {
        value++;

        if (isValueOverflow()) {
            zeroing();
        }
    }

    /**
     * Устанавливает максимальное значение текущего числа.
     * Когда при вызове {@link #incrementNumber()} текущее число достигаетэтого значения, оно обнуляется
     * <p>
     * По умолчанию максимум - {@link Integer#MAX_VALUE}
     * Если при смене максимального значения число начинает превышать максимальное
     * значение, то число надо обнулить.
     *
     * @param maximumValue - Максимальное значение, должно быть больше или равно 0
     */
    @Override
    public void setMaximumValue(int maximumValue) throws WrongMaxNumberException {
        if (maximumValue <= 0) throw new WrongMaxNumberException(maximumValue);

        this.maximumValue = maximumValue;

        if (isValueOverMax()) {
            zeroing();
        }
    }

    /**
     * Значение {@link #value} превышает {@link #maximumValue}
     */
    private boolean isValueOverMax() {
        return value >= maximumValue;
    }

    /**
     * Значение {@link #value} достигло {@link #maximumValue}
     */
    private boolean isValueOverflow() {
        return value == maximumValue;
    }

    /**
     * Обнуление текущего значения {@link #value}
     */
    private void zeroing() {
        value = 0;
    }
}
