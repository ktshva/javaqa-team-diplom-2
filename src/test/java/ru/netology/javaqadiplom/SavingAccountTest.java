package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    // Тесты для конструктора
    @Test
    public void negativeRateConstructor() { // Отрицательные значения на сберегательном счете
        boolean wasException = false;
        try {
            SavingAccount account = new SavingAccount(
                    -100,
                    -500,
                    -10_000,
                    -10);

        } catch (IllegalArgumentException iae) {
            wasException = true;
        }
        Assertions.assertTrue(wasException);
    }

    @Test
    public void negativeInitialBalance() { // счет с отрицательным стартовым балансом
        boolean wasException = false;
        try {
            SavingAccount account = new SavingAccount(
                    -100,
                    500,
                    10_000,
                    10);

        } catch (IllegalArgumentException iae) {
            wasException = true;
        }
        Assertions.assertTrue(wasException, "Отрицательный стартовый баланс");
    }

    @Test
    public void negativeMinBalance() { //счет с отрицательным минимальным балансом
        boolean wasException = false;
        try {
            SavingAccount account = new SavingAccount(
                    100,
                    -500,
                    10_000,
                    10);

        } catch (IllegalArgumentException iae) {
            wasException = true;
        }
        Assertions.assertTrue(wasException);
    }

    @Test
    public void negativeMaxBalance() { //счет с отрицательным максимальным балансом
        boolean wasException = false;
        try {
            SavingAccount account = new SavingAccount(
                    100,
                    500,
                    -10_000,
                    10);

        } catch (IllegalArgumentException iae) {
            wasException = true;
        }
        Assertions.assertTrue(wasException);
    }

    @Test
    public void normalParams() { //минимальный баланс меньше максимального. Проверка корректного поведения системы
        try {
            SavingAccount account = new SavingAccount(
                    600,
                    500,
                    10_000,
                    10);
        } catch (IllegalArgumentException iae) {
            Assertions.fail();
        }
    }

    @Test
    public void minBalanceGreaterThanMax() { //минимальный баланс больше максимального. Проверка корректного поведения системы
        boolean wasException = false;
        try {
            SavingAccount account = new SavingAccount(
                    0,
                    11_000,
                    10_000,
                    10);
        } catch (IllegalArgumentException iae) {
            wasException = true;
        }
        Assertions.assertTrue(wasException);
    }

    @Test
    public void initialBalanceLessThanMin() { // стартовый баланс меньше минимального
        boolean wasException = false;
        try {
            SavingAccount account = new SavingAccount(
                    200,
                    500,
                    10_000,
                    10);
        } catch (IllegalArgumentException iae) {
            wasException = true;
        }
        Assertions.assertTrue(wasException);
    }

    @Test
    public void initialBalanceGreaterThanMax() { // стартовый баланс больше максимального
        boolean wasException = false;
        try {
            SavingAccount account = new SavingAccount(
                    11_000,
                    500,
                    10_000,
                    10);
        } catch (IllegalArgumentException iae) {
            wasException = true;
        }
        Assertions.assertTrue(wasException);
    }

////////////////////////////////////////////////////////
    //Конец тестов для конструктора

//Тесты для метода "pay"

    @Test
    public void correctPayment() { // корректное уменьшение баланса
        SavingAccount account = new SavingAccount(
                100_000,
                500,
                1_000_000,
                10);

        int startBalance = account.getBalance();
        boolean correctOperation = account.pay(10_000);
        Assertions.assertTrue(correctOperation);
        int endBalance = account.getBalance();
        Assertions.assertEquals(startBalance - 10_000, endBalance);
    }

    @Test
    public void negativePaymentAmount() { // отрицательная сумма платежа
        SavingAccount account = new SavingAccount(
                100_000,
                500,
                1_000_000,
                10);

        int startBalance = account.getBalance();
        boolean correctOperation = account.pay(-10_000);
        Assertions.assertFalse(correctOperation);
        int endBalance = account.getBalance();
        Assertions.assertEquals(startBalance, endBalance);

    }

    @Test
    public void amountGreaterThanBalance() { // сумма платежа больше баланса
        SavingAccount account = new SavingAccount(
                10_000,
                500,
                1_000_000,
                10);

        int startBalance = account.getBalance();
        boolean correctOperation = account.pay(100_000);
        Assertions.assertFalse(correctOperation);
        int endBalance = account.getBalance();
        Assertions.assertEquals(startBalance, endBalance);
    }

    @Test
    public void payMoreThanMinBalance() { // остаток по покупке не соответсвует минимальному балансу
        SavingAccount account = new SavingAccount(
                10_000,
                5_000,
                1_000_000,
                10);

        int startBalance = account.getBalance();
        boolean correctOperation = account.pay(7_000);
        Assertions.assertFalse(correctOperation);
        int endBalance = account.getBalance();
        Assertions.assertEquals(startBalance, endBalance);
    }

    //Конец тестов для метода "pay"

    //Тесты для метода "add"

    @Test
    public void shouldAddLessThanMaxBalance() { // положительная сумма пополнения
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(5_000);

        Assertions.assertEquals(5000, account.getBalance());
    }

    @Test
    public void negativeAdd() { // отрицательная сумма пополнения
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        boolean correctOperation = account.add(-3000);
        Assertions.assertFalse(correctOperation);
        Assertions.assertEquals(2000, account.getBalance());
    }

    @Test
    public void addingMoreThanMax() { // пополнение больше максимального
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        boolean correctOperation = account.add(11_000);
        Assertions.assertFalse(correctOperation);
        Assertions.assertEquals(2000, account.getBalance());
    }
    /////////////////////////////////
    //Конец тестов к методу Add

    // Тесты к методу YearChange

    @Test
    public void shouldRoundNumbers() { //округление дробных чисел
        SavingAccount account = new SavingAccount(
                2_001,
                1_000,
                10_000,
                80
        );

        int expected = 1600;
        int actual = account.yearChange();

        Assertions.assertEquals(expected, actual);
    }

    // конец тестов на YearChange
}
