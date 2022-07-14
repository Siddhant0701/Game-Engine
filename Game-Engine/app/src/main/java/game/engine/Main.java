package game.engine;

import game.engine.alpha.Window;

public class Main {
    public static void main (String [] args){
        System.out.println("Heloo");
        Window window = Window.get();
        window.run();
    }
}
