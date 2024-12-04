package equipe5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    Money string;
    Money centun;
    Money zero;
    Money x;

    @BeforeEach
    void setUp() throws RefundException {

        String string2 = "255,55$";
        string = new Money(string2);
        centun = new Money(100,1);
        zero = new Money();
        x = new Money(376,89);

    }

    @AfterEach
    void tearDown() {

        string = null;
        centun = null;
        zero = null;
        x = null;

    }

    @Test
    @DisplayName("Retourne le nombre de dollars d'un montant total de monnaie")
    void getDollars() {

        assertEquals(255,string.getDollars());
        assertEquals(100,centun.getDollars());
        assertEquals(0,zero.getDollars());
        assertNotEquals(10,x.getDollars());
    }

    @Test
    @DisplayName("Retourne le nombre de cents d'un montant total de monnaie")
    void getCents() {
        assertEquals(55,string.getCents());
        assertEquals(0, zero.getCents());
        assertEquals(89, x.getCents());
        assertEquals(1, centun.getCents());
    }

    @Test
    @DisplayName("Retourne le montant total en int d'un objet Monnaie")
    void getTotalMoney() {
        assertEquals(25555,string.getTotalMoney());
        assertEquals(10001,centun.getTotalMoney());
        assertEquals(0, zero.getTotalMoney());
        assertNotEquals(376.89, x.getTotalMoney());
    }

    @Test
    @DisplayName("Transforme le montant total en int de this en un nombre passé en paramètre")
    void setTotalMoney() {
        string.setTotalMoney(3);
        centun.setTotalMoney(10000);
        zero.setTotalMoney(1);
        assertEquals(0,string.getDollars());
        assertEquals(100,centun.getDollars());
        assertNotEquals(0,zero.getCents());
        assertThrows(IllegalArgumentException.class, () -> {
            x.setTotalMoney(-37689);
        });
    }

    @Test
    @DisplayName("Additionne le montant total d'un objet Monnaie avec un autre passé en paramètre")
    void add() {
        string.add(centun);
        assertEquals(35556,string.getTotalMoney());
        centun.add(x);
        assertEquals(47690, centun.getTotalMoney());
        zero.add(x);
        assertNotEquals(0,zero.getTotalMoney());
    }

    @Test
    @DisplayName("Transforme le montant d'un objet Monnaie en string")
    void testToString() {
        assertEquals("255.55$",string.toString());
        assertEquals("0.00$", zero.toString());
        assertEquals("100.01$",centun.toString());
        assertNotEquals("376,89",x.toString());
    }
}