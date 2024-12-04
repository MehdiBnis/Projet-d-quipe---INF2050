package equipe5;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ClaimsTest {

    @Test
    void isCategoryIndexCorrect() {
        assertEquals(0, Claims.getCategoryIndex(0));
        assertEquals(1, Claims.getCategoryIndex(100));
        assertEquals(2, Claims.getCategoryIndex(150));
        assertEquals(3, Claims.getCategoryIndex(175));
        assertEquals(4, Claims.getCategoryIndex(200));
        assertEquals(6, Claims.getCategoryIndex(400));
        assertEquals(7, Claims.getCategoryIndex(500));
        assertEquals(8, Claims.getCategoryIndex(600));
        assertEquals(9, Claims.getCategoryIndex(700));

        for(int number = 300; number < 400; number++){
            assertEquals(5, Claims.getCategoryIndex(number));
        }

        assertNotEquals(3, Claims.getCategoryIndex(0));
        assertNotEquals(2, Claims.getCategoryIndex(100));
        assertNotEquals(1, Claims.getCategoryIndex(150));
        assertNotEquals(0, Claims.getCategoryIndex(175));
    }

    @Test
    void isContractIndexCorrect() {
        assertEquals(0, Claims.getContractIndex("A"));
        assertEquals(1, Claims.getContractIndex("B"));
        assertEquals(2, Claims.getContractIndex("C"));
        assertEquals(3, Claims.getContractIndex("D"));
        assertEquals(4, Claims.getContractIndex("E"));

        assertNotEquals(4, Claims.getContractIndex("A"));
        assertNotEquals(3, Claims.getContractIndex("B"));
        assertNotEquals(0, Claims.getContractIndex("C"));
        assertNotEquals(1, Claims.getContractIndex("D"));
        assertNotEquals(0, Claims.getContractIndex("E"));
    }

    @Test
    void getRefundPercentageforA() {
        assertEquals(25, Claims.getRefundPercentage(0,0));
        assertEquals(35, Claims.getRefundPercentage(1,0));
        assertEquals(0, Claims.getRefundPercentage(2,0));
        assertEquals(50, Claims.getRefundPercentage(3,0));
        assertEquals(25, Claims.getRefundPercentage(4,0));
        assertEquals(0, Claims.getRefundPercentage(5,0));
        assertEquals(0, Claims.getRefundPercentage(6,0));
        assertEquals(25, Claims.getRefundPercentage(7,0));
        assertEquals(40, Claims.getRefundPercentage(8,0));
        assertEquals(0, Claims.getRefundPercentage(9,0));

        assertNotEquals(10, Claims.getRefundPercentage(0,0));
        assertNotEquals(20, Claims.getRefundPercentage(1,0));
        assertNotEquals(30, Claims.getRefundPercentage(2,0));
        assertNotEquals(40, Claims.getRefundPercentage(3,0));
        assertNotEquals(50, Claims.getRefundPercentage(4,0));
    }

    @Test
    void getRefundPercentageforB() {
        assertEquals(50, Claims.getRefundPercentage(0,1));
        assertEquals(50, Claims.getRefundPercentage(1,1));
        assertEquals(0, Claims.getRefundPercentage(2,1));
        assertEquals(75, Claims.getRefundPercentage(3,1));
        assertEquals(100, Claims.getRefundPercentage(4,1));
        assertEquals(50, Claims.getRefundPercentage(5,1));
        assertEquals(0, Claims.getRefundPercentage(6,1));
        assertEquals(50, Claims.getRefundPercentage(7,1));
        assertEquals(100, Claims.getRefundPercentage(8,1));
        assertEquals(70, Claims.getRefundPercentage(9,1));

        assertNotEquals(10, Claims.getRefundPercentage(0,0));
        assertNotEquals(20, Claims.getRefundPercentage(1,0));
        assertNotEquals(30, Claims.getRefundPercentage(2,0));
        assertNotEquals(40, Claims.getRefundPercentage(3,0));
        assertNotEquals(50, Claims.getRefundPercentage(4,0));
    }

    @Test
    void getRefundPercentageforC() {
        assertEquals(90, Claims.getRefundPercentage(0,2));
        assertEquals(95, Claims.getRefundPercentage(1,2));
        assertEquals(85, Claims.getRefundPercentage(2,2));
        assertEquals(90, Claims.getRefundPercentage(3,2));
        assertEquals(90, Claims.getRefundPercentage(4,2));
        assertEquals(90, Claims.getRefundPercentage(5,2));
        assertEquals(90, Claims.getRefundPercentage(6,2));
        assertEquals(90, Claims.getRefundPercentage(7,2));
        assertEquals(90, Claims.getRefundPercentage(8,2));
        assertEquals(75, Claims.getRefundPercentage(9,2));

        assertNotEquals(10, Claims.getRefundPercentage(0,0));
        assertNotEquals(20, Claims.getRefundPercentage(1,0));
        assertNotEquals(30, Claims.getRefundPercentage(2,0));
        assertNotEquals(40, Claims.getRefundPercentage(3,0));
        assertNotEquals(50, Claims.getRefundPercentage(4,0));
    }

    @Test
    void getRefundPercentageforD() {
        assertEquals(100, Claims.getRefundPercentage(0,3));
        assertEquals(100, Claims.getRefundPercentage(1,3));
        assertEquals(100, Claims.getRefundPercentage(2,3));
        assertEquals(95, Claims.getRefundPercentage(3,3));
        assertEquals(100, Claims.getRefundPercentage(4,3));
        assertEquals(100, Claims.getRefundPercentage(5,3));
        assertEquals(100, Claims.getRefundPercentage(6,3));
        assertEquals(100, Claims.getRefundPercentage(7,3));
        assertEquals(100, Claims.getRefundPercentage(8,3));
        assertEquals(100, Claims.getRefundPercentage(9,3));

        assertNotEquals(10, Claims.getRefundPercentage(0,0));
        assertNotEquals(20, Claims.getRefundPercentage(1,0));
        assertNotEquals(30, Claims.getRefundPercentage(2,0));
        assertNotEquals(40, Claims.getRefundPercentage(3,0));
        assertNotEquals(50, Claims.getRefundPercentage(4,0));
    }

    @Test
    void getRefundPercentageforE() {
        assertEquals(15, Claims.getRefundPercentage(0,4));
        assertEquals(25, Claims.getRefundPercentage(1,4));
        assertEquals(15, Claims.getRefundPercentage(2,4));
        assertEquals(25, Claims.getRefundPercentage(3,4));
        assertEquals(12, Claims.getRefundPercentage(4,4));
        assertEquals(60, Claims.getRefundPercentage(5,4));
        assertEquals(25, Claims.getRefundPercentage(6,4));
        assertEquals(30, Claims.getRefundPercentage(7,4));
        assertEquals(15, Claims.getRefundPercentage(8,4));
        assertEquals(22, Claims.getRefundPercentage(9,4));

        assertNotEquals(10, Claims.getRefundPercentage(0,0));
        assertNotEquals(20, Claims.getRefundPercentage(1,0));
        assertNotEquals(30, Claims.getRefundPercentage(2,0));
        assertNotEquals(40, Claims.getRefundPercentage(3,0));
        assertNotEquals(50, Claims.getRefundPercentage(4,0));
    }

    @Test
    void getRefundMaxforA() {
        assertEquals(0, Claims.getRefundMax(0,0));
        assertEquals(0, Claims.getRefundMax(1,0));
        assertEquals(0, Claims.getRefundMax(2,0));
        assertEquals(0, Claims.getRefundMax(3,0));
        assertEquals(0, Claims.getRefundMax(4,0));
        assertEquals(0, Claims.getRefundMax(5,0));
        assertEquals(0, Claims.getRefundMax(6,0));
        assertEquals(0, Claims.getRefundMax(7,0));
        assertEquals(0, Claims.getRefundMax(8,0));
        assertEquals(0, Claims.getRefundMax(9,0));

        assertNotEquals(10000, Claims.getRefundMax(0,0));
        assertNotEquals(20000, Claims.getRefundMax(1,0));
        assertNotEquals(30000, Claims.getRefundMax(2,0));
        assertNotEquals(40000, Claims.getRefundMax(3,0));
        assertNotEquals(50000, Claims.getRefundMax(4,0));
    }

    @Test
    void getRefundMaxforB() {
        assertEquals(4000, Claims.getRefundMax(0,1));
        assertEquals(5000, Claims.getRefundMax(1,1));
        assertEquals(0, Claims.getRefundMax(2,1));
        assertEquals(0, Claims.getRefundMax(3,1));
        assertEquals(0, Claims.getRefundMax(4,1));
        assertEquals(0, Claims.getRefundMax(5,1));
        assertEquals(0, Claims.getRefundMax(6,1));
        assertEquals(5000, Claims.getRefundMax(7,1));
        assertEquals(0, Claims.getRefundMax(8,1));
        assertEquals(0, Claims.getRefundMax(9,1));

        assertNotEquals(10000, Claims.getRefundMax(0,0));
        assertNotEquals(20000, Claims.getRefundMax(1,0));
        assertNotEquals(30000, Claims.getRefundMax(2,0));
        assertNotEquals(40000, Claims.getRefundMax(3,0));
        assertNotEquals(50000, Claims.getRefundMax(4,0));
    }

    @Test
    void getRefundMaxforC() {
        assertEquals(0, Claims.getRefundMax(0,2));
        assertEquals(0, Claims.getRefundMax(1,2));
        assertEquals(0, Claims.getRefundMax(2,2));
        assertEquals(0, Claims.getRefundMax(3,2));
        assertEquals(0, Claims.getRefundMax(4,2));
        assertEquals(0, Claims.getRefundMax(5,2));
        assertEquals(0, Claims.getRefundMax(6,2));
        assertEquals(0, Claims.getRefundMax(7,2));
        assertEquals(0, Claims.getRefundMax(8,2));
        assertEquals(0, Claims.getRefundMax(9,2));

        assertNotEquals(10000, Claims.getRefundMax(0,0));
        assertNotEquals(20000, Claims.getRefundMax(1,0));
        assertNotEquals(30000, Claims.getRefundMax(2,0));
        assertNotEquals(40000, Claims.getRefundMax(3,0));
        assertNotEquals(50000, Claims.getRefundMax(4,0));
    }

    @Test
    void getRefundMaxforD() {
        assertEquals(8500, Claims.getRefundMax(0,3));
        assertEquals(7500, Claims.getRefundMax(1,3));
        assertEquals(15000, Claims.getRefundMax(2,3));
        assertEquals(0, Claims.getRefundMax(3,3));
        assertEquals(10000, Claims.getRefundMax(4,3));
        assertEquals(0, Claims.getRefundMax(5,3));
        assertEquals(6500, Claims.getRefundMax(6,3));
        assertEquals(0, Claims.getRefundMax(7,3));
        assertEquals(10000, Claims.getRefundMax(8,3));
        assertEquals(9000, Claims.getRefundMax(9,3));

        assertNotEquals(10000, Claims.getRefundMax(0,0));
        assertNotEquals(20000, Claims.getRefundMax(1,0));
        assertNotEquals(30000, Claims.getRefundMax(2,0));
        assertNotEquals(40000, Claims.getRefundMax(3,0));
        assertNotEquals(50000, Claims.getRefundMax(4,0));
    }

    @Test
    void getRefundMaxforE() {
        assertEquals(0, Claims.getRefundMax(0,4));
        assertEquals(0, Claims.getRefundMax(1,4));
        assertEquals(0, Claims.getRefundMax(2,4));
        assertEquals(2000, Claims.getRefundMax(3,4));
        assertEquals(0, Claims.getRefundMax(4,4));
        assertEquals(0, Claims.getRefundMax(5,4));
        assertEquals(1500, Claims.getRefundMax(6,4));
        assertEquals(2000, Claims.getRefundMax(7,4));
        assertEquals(0, Claims.getRefundMax(8,4));
        assertEquals(0, Claims.getRefundMax(9,4));

        assertNotEquals(10000, Claims.getRefundMax(0,0));
        assertNotEquals(20000, Claims.getRefundMax(1,0));
        assertNotEquals(30000, Claims.getRefundMax(2,0));
        assertNotEquals(40000, Claims.getRefundMax(3,0));
        assertNotEquals(50000, Claims.getRefundMax(4,0));
    }

    @Test
    void calculateClaim() throws RefundException {

        Money expected = new Money(8, 5);
        Money unexpected = new Money(7, 6);
        Money actual = Claims.calculateClaim(new Money(23,0), 1, 0);

        assertEquals(expected.toString(), actual.toString());
        assertNotEquals(unexpected.toString(), actual.toString());

    }
}