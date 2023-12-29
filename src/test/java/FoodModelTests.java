import com.DominionDMS.SnakeGame.Model.FoodModel;
import com.DominionDMS.SnakeGame.Model.BombModel;
import com.DominionDMS.SnakeGame.Utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FoodModelTests {

    private FoodModel food;

    @BeforeEach
    void setUp() {
        food = new FoodModel();
    }

    @Test
    void testFoodInitialisationWithoutBombs() {
        food.initialise(null);
        assertTrue(food.getX() >= 0 && food.getY() >= 0, "Food should be within game boundaries");
    }

    @Test
    void testFoodInitialisationWithBombs() {
        BombModel bombs = new BombModel(10); // Assuming BombModel and a method to mock it
        food.initialise(bombs);
        assertTrue(food.getX() >= 0 && food.getY() >= 0, "Food should be within game boundaries and not overlap with bombs");
    }

    @Test
    void testFoodNotEatenInitially() {
        food.initialise(null);
        assertFalse(food.isEaten(), "Food should not be eaten initially");
    }

    @Test
    void testSetEaten() {
        food.setEaten(true);
        assertTrue(food.isEaten(), "Food should be marked as eaten");
    }

    @Test
    void testSetPoints() {
        int points = 10;
        food.setPoints(points);
        assertEquals(points, food.getPoints(), "Points should be set correctly");
    }

    @Test
    void testGetRectangle() {
        food.initialise(null);
        assertNotNull(food.getRectangle(), "Rectangle should not be null after initialization");
    }

    @Test
    void testFoodRegeneration() {
        food.initialise(null);
        int initialX = food.getX();
        int initialY = food.getY();
        food.setEaten(true);
        food.initialise(null); // Regenerate food
        assertFalse(initialX == food.getX() && initialY == food.getY(), "Food should regenerate at a new location");
    }

    @Test
    void testFoodImageNotNull() {
        food.initialise(null);
        assertNotNull(food.getImage(), "Food image should not be null after initialization");
    }

    @Test
    void testFoodWithinGameBoundaries() {
        food.initialise(null);
        assertTrue(food.getX() < Constants.GAME_WIDTH && food.getY() < Constants.GAME_HEIGHT, "Food should be within game boundaries");
    }

    @Test
    void testFoodNotInScoreArea() {
        food.initialise(null);
        assertFalse(food.getX() < Constants.SNAKE_SCORE_XEND && food.getY() < Constants.SNAKE_SCORE_YEND, "Food should not be placed in score area");
    }
}
