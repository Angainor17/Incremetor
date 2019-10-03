package incrementor;

import exceptions.WrongMaxNumberException;

public class IncrementorImpl implements Incrementor {

    private int number = 0;
    private int maximumValue = Integer.MAX_VALUE;

    /**
     * Возвращает текущее число. В самом начале это ноль.
     */
    @Override
    public int getNumber() {
        return number;
    }

    /**
     * Увеличивает текущее число на один. После каждого вызова этого
     * метода getNumber() будет возвращать число на один больше.
     */
    @Override
    public void incrementNumber() {
        number++;
        if (number == maximumValue) {
            zeroing();
        }
    }

    /**
     * Устанавливает максимальное значение текущего числа.
     * Когда при вызове incrementNumber() текущее число достигает
     * этого значения, оно обнуляется, т.е. getNumber() начинает
     * снова возвращать ноль, и снова один после следующего
     * вызова incrementNumber() и так далее.
     * По умолчанию максимум -- максимальное значение int.
     * Если при смене максимального значения число резко начинает
     * превышать максимальное значение, то число надо обнулить.
     * Нельзя позволять установить тут число меньше нуля.
     */
    @Override
    public void setMaximumValue(int maximumValue) throws WrongMaxNumberException {
        if (maximumValue <= 0) throw new WrongMaxNumberException(maximumValue);
        this.maximumValue = maximumValue;

        if (maximumValue <= number) {
            zeroing();
        }
    }

    private void zeroing() {
        number = 0;
    }
}
