import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import enums.Coin;
import enums.Products;
import exception.NotFullPrice;
import exception.NotSufficientChange;
import exception.SoldeInsuffisant;
import model.Change;
import model.VendingMachine;

public class VendingMachineTest {

    private static VendingMachine vendingMachine;

    @BeforeAll
    public static void setUp() {
        vendingMachine = new VendingMachine();
    }

    @AfterAll
    public static void setNull() {
        vendingMachine = null;
    }

    @Test
    public void SelectProductWithMorePriceTest() throws NotSufficientChange, NotFullPrice, SoldeInsuffisant {
        double price = vendingMachine.selectProductAndGetPrice(Products.MIRENDINA);
        assertEquals(Products.MIRENDINA.getPrice(), price);

        vendingMachine.insertCoin(Coin.TENMAD);

        Change<Products, List<Coin>> change1 = vendingMachine.takeProductAndChange();
        Products products = change1.getProduit1();
        List<Coin> change2 = change1.getProduit2();

        assertEquals(Products.MIRENDINA, products);
        assertTrue(!change2.isEmpty());
        double total = 0;
        for (Coin coin : change2) {
            total = total + coin.getValueOfCoin();
        }
        assertEquals(10 - Products.MIRENDINA.getPrice(), total);

    }

    @Test
    public void RefundTest() throws NotSufficientChange, SoldeInsuffisant {
        double price = vendingMachine.selectProductAndGetPrice(Products.MIRENDINA);
        assertEquals(Products.MIRENDINA.getPrice(), price);
        vendingMachine.insertCoin(Coin.ONEMAD);
        vendingMachine.insertCoin(Coin.TWOMAD);
        vendingMachine.insertCoin(Coin.TWOMAD);
    }

    @Test
    public void SoldeInsuffisantTest() throws SoldeInsuffisant, NotSufficientChange, NotFullPrice {
        vendingMachine.selectProductAndGetPrice(Products.KITKAT);
        vendingMachine.insertCoin(Coin.TENMAD);
        assertThrows(NotFullPrice.class, () -> vendingMachine.takeProductAndChange());

    }

    @Test
    public void NotSufficientChangeTest() throws NotSufficientChange, NotFullPrice, SoldeInsuffisant {
        vendingMachine.selectProductAndGetPrice(Products.KITKAT);
        vendingMachine.insertCoin(Coin.ONEMAD);
        vendingMachine.insertCoin(Coin.TENMAD);
        vendingMachine.insertCoin(Coin.HALFMAD);
        assertThrows(NotFullPrice.class, () -> vendingMachine.takeProductAndChange());

    }

    @Test
    public void ResetTest() throws SoldeInsuffisant {

        vendingMachine.reset();

        assertThrows(SoldeInsuffisant.class, () -> vendingMachine.selectProductAndGetPrice(Products.TANGO));

    }

}
