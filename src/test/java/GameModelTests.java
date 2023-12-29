import com.DominionDMS.SnakeGame.Model.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameModelTests {

    private GameModel gameModel;

    @BeforeEach
    void setUp() {
        gameModel = new GameModel();
    }

    @Test
    void testInitialLevel() {
        assertEquals(0, gameModel.getLevel(), "Initial level should be 0");
    }

    @Test
    void testSetAndGetLevel() {
        gameModel.setLevel(2);
        assertEquals(2, gameModel.getLevel(), "Level should be set to 2");
    }

    @Test
    void testInitialTheme() {
        assertEquals(0, gameModel.getTheme(), "Initial theme should be 0");
    }

    @Test
    void testSetAndGetTheme() {
        gameModel.setTheme(1);
        assertEquals(1, gameModel.getTheme(), "Theme should be set to 1");
    }



    @Test
    void testInitialEffectsStatus() {
        assertTrue(gameModel.getEfects(), "Initial effects status should be true");
    }

    @Test
    void testSetAndGetEffectsStatus() {
        gameModel.setEffects(false);
        assertFalse(gameModel.getEfects(), "Effects status should be set to false");
    }

    @Test
    void testSetAndGetName() {
        gameModel.setName("Player1");
        assertEquals("Player1", gameModel.getName(), "Player name should be 'Player1'");
    }



}
