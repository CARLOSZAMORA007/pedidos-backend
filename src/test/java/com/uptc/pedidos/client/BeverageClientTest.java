package com.uptc.pedidos.client;
import com.uptc.pedidos.model.Drink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BeverageClientTest {

    private BeverageClient beverageClient;

    @BeforeEach
    void setUp() {
        // Simulación del cliente usando mock interno de bebidas
        beverageClient = new BeverageClient(new RestTemplate()) {
            @Override
            public List<Drink> getAvailableDrinks() {
                return List.of(
                    createDrink("Café Latte", "Small,Medium,Large"),
                    createDrink("Té Verde", "Medium,Large"),
                    createDrink("Capuchino", "Small,Medium"),
                    createDrink("Chocolate Caliente", "Small,Large"),
                    createDrink("Espresso", "Small")
                );
            }
        };
    }

    private Drink createDrink(String name, String availableSizes) {
        Drink drink = new Drink();
        drink.setName(name);
        drink.setAvailableSizes(availableSizes);
        return drink;
    }

    @Test
    void testBebidaCorrecta() {
        assertTrue(beverageClient.checkDrinkExists("Café Latte", "Medium"));
    }

    @Test
    void testLetraModificada() {
        assertFalse(beverageClient.checkDrinkExists("Cafe Latte", "Medium")); 
    }

    @Test
    void testBebidaInexistente() {
        assertFalse(beverageClient.checkDrinkExists("Agua", "Small"));
    }

    @Test
    void testSinNombre() {
        assertFalse(beverageClient.checkDrinkExists("", "Small"));
    }

    @Test
    void testTamañoIncorrecto() {
        assertFalse(beverageClient.checkDrinkExists("Té Verde", "Small")); 
    }

    @Test
    void testNombreCorrectoTamañoCorrecto() {
        assertTrue(beverageClient.checkDrinkExists("Espresso", "Small"));
    }

    @Test
    void testNombreIncorrectoTamañoCorrecto() {
        assertFalse(beverageClient.checkDrinkExists("Espresoo0", "Small")); 
    }
}
