
import com.DominionDMS.SnakeGame.Model.SnakeModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static junit.framework.Assert.assertEquals;


class SnakeModelTests {

        private SnakeModel snake;


        @BeforeEach
        void setUp() {
            snake = new SnakeModel();
            snake.initialise(100, 100); // Assuming starting position (100, 100)
        }

        @Test
        void testInitialLength() {
            assertEquals("Initial length should be 1",1, snake.getLength() );
        }

        @Test
        void testInitialPosition() {
            assertEquals( "Initial X position should be 100",100, snake.getxPosition());
            assertEquals( "Initial Y position should be 100",100, snake.getyPosition());
        }


        @Test
        void testGrowOnEating() {
            int initialLength = snake.getLength();
            snake.setLength(initialLength + 1); // Simulate eating
            assertEquals("Length should increase after eating",initialLength + 1, snake.getLength());
        }
        @Test
        void testScoreIncrement() {
            int initialScore = snake.getScore();
            snake.addScore(10); // Assuming the score for eating food is 10
            assertEquals("Score should increase by 10 after eating", initialScore + 10, snake.getScore());
        }
    @Test
    void testSpeedChange() {
        int initialSpeed = snake.getSpeed();
        snake.setSpeed(initialSpeed + 1);
        assertEquals("Speed should increase", initialSpeed + 1, snake.getSpeed());
    }




}



