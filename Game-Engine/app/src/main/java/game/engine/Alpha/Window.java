package game.engine.Alpha;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
// Singleton

public class Window {

    private int height, width;
    private String title;

    private static Window window = null;

    private Window (){
        this.height = 1080;
        this.width = 1920;
        this.title = "Test Engine";
    }

    public static Window get (){
        if (Window.window == null){
            Window.window = new Window();
        }

        return Window.window;
    }

    public void run (){
       //System.out.println("Hello LWJGL v=" + Version.getVersion);
    }

}
