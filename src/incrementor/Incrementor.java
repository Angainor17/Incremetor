package incrementor;

import exceptions.WrongMaxNumberException;

/**
 * Сущность, которая позволяет инкрементировать целочисленное значение
 * Даёт возможность устанавливать максимальное значение
 */
public interface Incrementor {

    /**
     * Возвращает текущее число. В самом начале это ноль.
     */
    int getNumber();

    /**
     * Увеличивает текущее число на один. После каждого вызова этого
     * метода {@link Incrementor#getNumber()} будет возвращать число на один больше.
     */
    void incrementNumber();

    /**
     * Устанавливает максимальное значение текущего числа.
     * Когда при вызове {@link Incrementor#incrementNumber()} текущее число достигаетэтого значения, оно обнуляется
     *
     * По умолчанию максимум - {@link Integer#MAX_VALUE}
     * Если при смене максимального значения число начинает превышать максимальное
     * значение, то число надо обнулить.
     *
     * @param maximumValue - Максимальное значение, должно быть больше или равно 0
     */
    void setMaximumValue(int maximumValue) throws WrongMaxNumberException;
}
