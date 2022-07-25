package game.engine.rcsEngine;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
// Singleton

public class Window {

    private int height, width;
    private String title;
    private long glfwWindow;

    private static Window window = null;

    private Window(){
        this.height = 1080;
        this.width = 1920;
        this.title = "Test Engine";
    }

    public static Window get(){
        if (Window.window == null){
            Window.window = new Window();
        }

        return Window.window;
    }

    public void run(){
       System.out.println("Hello LWJGL version=" + Version.getVersion() + " !");

       init();
       loop();

       // Free Memory
       glfwFreeCallbacks(glfwWindow);
       glfwDestroyWindow(glfwWindow);

       // Terminate GLFW and free the error callback
       glfwTerminate();
       glfwSetErrorCallback(null).free();
    }

    public void init(){
        // Setup an Error Callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW

        if (!glfwInit()){
            throw new IllegalStateException("Unable to initalize GLFW");
        }

        //  Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // Create the Window
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (glfwWindow == NULL){
            throw new IllegalStateException("Failed to create the GLFW Window.");
        }

        // Callbacks to Listeners
        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);
        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);

        // Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);

        // Enable V-Sync
        glfwSwapInterval(1);

        // Make the Window Visable
        glfwShowWindow(glfwWindow);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
    }

    public void loop(){
        while (!glfwWindowShouldClose(glfwWindow)){
            // Poll Events
            glfwPollEvents();

            glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            // Testing Key and Mouse Listeners
            if (KeyListener.isKeyPressed(GLFW_KEY_SPACE)){
                System.out.println("Space Key is pressed");
            }

            if (MouseListener.mouseButtonDown(1)){
                System.out.println("Mouse 1 is pressed");
            }

            if (MouseListener.mouseButtonDown(0)){
                System.out.println("Mouse 0 is pressed");
            }

            glfwSwapBuffers(glfwWindow);
        }
    }

}
