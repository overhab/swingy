package edu.school21.gui;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private final int width, height;
    private final String title;
    private long glfwWindow;

    private static Window WINDOW = null;

    private Window() {
        this.width = 680;
        this.height = 480;
        this.title = "Swingy";
    }

    public static Window get() {
        if (WINDOW == null) {
            WINDOW = new Window();
        }
        return WINDOW;
    }

    public void run() {
        System.out.println("Hello! " + Version.getVersion() + "!");

        init();
        loop();

    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW.");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
//        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);

        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create GLFW window.");
        }

        glfwMakeContextCurrent(glfwWindow);

        //V-Sync
        glfwSwapInterval(1);

        //Make visible
        glfwShowWindow(glfwWindow);

        GL.createCapabilities();
    }

    private void loop() {
        while (!glfwWindowShouldClose(glfwWindow)) {
            glfwPollEvents();

            glClearColor(0.1f, 0.0f, 0.2f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);



            glfwSwapBuffers(glfwWindow);
        }
    }
}
