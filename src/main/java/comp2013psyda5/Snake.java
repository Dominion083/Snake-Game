package comp2013psyda5;

public class Snake {
	
		private static final long serialVersionUID = -3641221053272056036L;


    // TODO: "it requires renewal."

    public static int moving;

    public static int move(int x) {
        moving = x;
        return moving;
    }

    public static void stop() {
        moving = 0;
    }
}
