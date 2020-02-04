package com.company;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoglShapes extends JPanel implements GLEventListener {

    private Timer animationTimer;
    private int frameNumber;

    public JoglShapes() {
        GLCapabilities caps = new GLCapabilities(null);
        GLJPanel display = new GLJPanel(caps);
        display.setPreferredSize(new Dimension(640,480));
        display.addGLEventListener(this);
        setLayout(new BorderLayout());
        add(display, BorderLayout.CENTER);

        //Start the animationTimer
        animationTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameNumber++;
                display.repaint();
            }
        });
        animationTimer.start();
    } //end of constructor

    // ---------------  Methods of the GLEventListener interface -----------
    /**
     * This is called when the GLJPanel is first created.  It can be used to initialize
     * the OpenGL drawing context.
     */
    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2(); //Called when the panel is created
        gl.glClearColor(0.3F, 0.3F, 0.3F, 1.0F); //Set background color
        gl.glEnable(GL2.GL_DEPTH_TEST); //Required for 3D drawing

        //TODO: add more code here for initialization??

    } //end of init()

    /**
     * This method is called when the OpenGL display needs to be drawn or redrawn.
     */
    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT); //Required (depth) for 3D drawing

        /**
         * If you want to start over at the origin, that's when you call glLoadIdentity(),
         * and then you can translate from the origin, or rotate in the default direction.
         */
        gl.glLoadIdentity();

        //TODO: add more code here for the display of shapes
        
    } //end of display()

    /**
     * This is called before the GLJPanel is destroyed.  It can be used to release OpenGL resources.
     */
    @Override
    public void dispose(GLAutoDrawable drawable) {

    } //end of dispose()

    /**
     * Called when the size of the GLJPanel changes.  Note:  glViewport(x,y,width,height)
     * has already been called before this method is called!
     */
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    } //end of reshape()
    // ---------------  End of Methods of the GLEventListener interface -----------

    public static void main(String[] args) {
	    JFrame myWindow = new JFrame("JOGL 3D Scene");
	    JoglShapes myPanel = new JoglShapes();
	    myWindow.setContentPane(myPanel);
	    myWindow.pack();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    myWindow.setLocation(screen.width - myWindow.getWidth()/2, screen.height - myWindow.getHeight()/2);
	    myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    myWindow.setVisible(true);
    } //end of main()

} //end of JoglShapes
