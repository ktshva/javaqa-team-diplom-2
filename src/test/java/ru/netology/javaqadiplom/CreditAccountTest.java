package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() { // пополнение положительной суммы
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldAddNegativeBalance() { // пополнение на положительную сумму при отрицательном балансе
        CreditAccount account = new CreditAccount(
                -1000,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(2000, account.getBalance());
    }

    @Test
    public void shouldNotAddNOverLimitBalance() { // пополнение баланса сверх лимита
        CreditAccount account = new CreditAccount(
                1000,
                5_000,
                15
        );

        account.add(6_000);

        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void shouldAddNullBalance() { // нулевое пополнение
        CreditAccount account = new CreditAccount(
                1000,
                5_000,
                15
        );

        account.add(0);

        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void shouldNotAddNegativeBalance() { // отрицательное пополнение
        CreditAccount account = new CreditAccount(
                -1000,
                5_000,
                15
        );

        account.add(-3_000);

        Assertions.assertEquals(-1000, account.getBalance());
    }

    @Test
    public void shouldPayToLimit() { // покупка в пределах лимита при нулевом балансе
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.pay(3_000);

        Assertions.assertEquals(-3000, account.getBalance());
    }

    @Test
    public void shouldPayToLimitPositiveBalance() { // покупка в пределах лимита при положительном балансе
        CreditAccount account = new CreditAccount(
                3000,
                5_000,
                15
        );

        account.pay(2_000);

        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void shouldPayToLimitNegativeBalance() { // покупка в пределах лимита при отрицательном балансе
        CreditAccount account = new CreditAccount(
                -3000,
                5_000,
                15
        );

        account.pay(2_000);

        Assertions.assertEquals(-5000, account.getBalance());
    }

    @Test
    public void shouldPayToOverLimitPositiveBalance() { // Покупка сверх лимита при положительном балансе
        CreditAccount account = new CreditAccount(
                1000,
                5_000,
                15
        );

        account.pay(7000);

        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void shouldPayToOverLimitNegativeBalance() { // Покупка сверх лимита при отрицательном балансе
        CreditAccount account = new CreditAccount(
                -1000,
                5_000,
                15
        );

        account.pay(6_000);

        Assertions.assertEquals(-1000, account.getBalance());
    }

    @Test
    public void shouldRate0WhenPositiveBalance() { // рассчет процентов при положительном балансе
        CreditAccount account = new CreditAccount(
                200,
                5_000,
                15
        );

        account.yearChange();

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void shouldRateWhenNegativeBalance() { // рассчет процентов при отрицательном балансе
        CreditAccount account = new CreditAccount(
                -200,
                5_000,
                15
        );

        account.yearChange();

        Assertions.assertEquals(-30, account.yearChange());
    }

    @Test
    public void shouldRateWhenNullBalance() { // рассчет процентов при нулевом балансе
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.yearChange();

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void shouldRateWhenNullRate() { // рассчет процентов при нулевой ставке
        CreditAccount account = new CreditAccount(
                1000,
                5_000,
                0
        );

        account.yearChange();

        Assertions.assertEquals(0, account.yearChange());
    }
}
