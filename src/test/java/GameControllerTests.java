import com.DominionDMS.SnakeGame.Controllers.GameController;
import com.DominionDMS.SnakeGame.Model.SnakeModel;
import com.DominionDMS.SnakeGame.Model.FoodModel;
import com.DominionDMS.SnakeGame.Model.GameModel;
import com.DominionDMS.SnakeGame.Utils.Constants;
import com.DominionDMS.SnakeGame.View.GameView;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameControllerTests {
    private static GameView view;
    private static GameController controller;
    private static SnakeModel snake;
    private static FoodModel food;
    private static GameModel gameModel;

    @BeforeAll
    static void setUp() {
        snake = new SnakeModel();
        snake.initialise(100, 100);
        food = new FoodModel();
        gameModel = new GameModel();
        controller = new GameController();
        view = new GameView();
        controller.initialise(view, snake, food, gameModel);
    }

    @Test
    void testSnakeMovement() {
        controller.move();
        assertNotEquals(100, snake.getxPosition(), "Snake's X position should change after movement");
    }

    @Test
    void testSnakeEatsFood() {
        // Setup food at the snake's next position
        food.setEaten(false);
        food.initialise(null);
        food.setPoints(10);
        controller.checkIfFoodEaten();
        assertTrue(food.isEaten(), "Food should be eaten when snake intersects");
        assertEquals(10, snake.getScore(), "Score should increase when snake eats food");
    }

    @Test
    void testGamePauseAndResume() {
        controller.pause();
        // Assert game paused state
        // This might need a flag or method in GameController to check if the game is paused

        controller.resume();
        // Assert game resumed state
    }

    @Test
    void testGameOver() {
        // Simulate game over condition
        snake.setAlive(false);
        controller.Loop(); // Assuming Loop() can be called directly
        assertFalse(snake.isAlive(), "Snake should be dead on game over");
    }

    @Test
    void testResetGame() {
        controller.resetGame();
        // Check if snake and food are reset properly
        assertEquals(Constants.SNAKE_START_X, snake.getxPosition(), "Snake X position should reset");
        assertEquals(Constants.SNAKE_START_Y, snake.getyPosition(), "Snake Y position should reset");
    }


}
