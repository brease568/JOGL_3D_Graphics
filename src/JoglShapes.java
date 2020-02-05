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

    private CreateShapes myShape = new CreateShapes();

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
        //gl.glClearColor(0.3F, 0.3F, 0.3F, 1.0F); //Set background color
        gl.glEnable(GL2.GL_DEPTH_TEST); //Required for 3D drawing

        gl.glLineWidth(2);
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
        gl.glClearColor(0.5F, 0.5F, 0.5F, 1.0F);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-10, 10, -10, 10, -5, 5);
        gl.glMatrixMode(GL2.GL_MODELVIEW);

        gl.glClearDepth(1.0f);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

        //Transparency on:
        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);

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

        //Draw a 3D cube:
        gl.glPushMatrix();
        gl.glLineWidth(1);
        gl.glTranslated(-8.0,1.0, 0);
        gl.glRotated(30, 1.5, -2, 3);
        gl.glScaled(1.5, 1.5, 1.5);
        gl.glRotated(-frameNumber*0.7,1,0,0);
        myShape.drawCube(gl);
        gl.glPopMatrix();

        //Draw a 3D triangle:
        gl.glPushMatrix();
        gl.glLineWidth(2);
        gl.glTranslated(-6.0, 5.0, 0);
        gl.glRotated(90, 1.5, 2, -3);
        gl.glScaled(1.5, 1.5, 1.5);
        gl.glRotated(frameNumber*0.7, 0, 0, 1);
        myShape.drawTriangle(gl);
        gl.glPopMatrix();

        //Draw a 3D tetrahedron:
        gl.glPushMatrix();
        gl.glLineWidth(1);
        gl.glTranslated(-6.0, -5.0, 0);
        gl.glRotated(30, 2.0, 1.0, 1.0);
        gl.glScaled(1.5, 1.5, 1.5);
        gl.glRotated(frameNumber*0.7, 1.0, 0, 1);
        myShape.drawTetrahedron(gl);
        gl.glPopMatrix();

        //Draw a 3D rhombicDodecahedron:
        gl.glPushMatrix();
        gl.glLineWidth(1);
        gl.glTranslated(3.0, -5.0, 0);
        gl.glRotated(30, 2.0, 1.0, 1.0);
        gl.glScaled(1.5, 1.5, 1.5);
        gl.glRotated(frameNumber*0.7, 1.0, 1, 1);
        myShape.drawRhombicDodecahedron(gl);
        gl.glPopMatrix();

        //Draw a 3D octahedron:
        gl.glPushMatrix();
        gl.glLineWidth(1);
        gl.glTranslated(4.0, 6.0, 0);
        gl.glRotated(30, 2.0, 1.0, 1.0);
        gl.glScaled(1.5, 1.5, 1.5);
        gl.glRotated(frameNumber*0.7, 0, 1, 0);
        myShape.drawOctahedron(gl);
        gl.glPopMatrix();

        //Draw a 3D icosahedron:
        gl.glPushMatrix();
        gl.glLineWidth(1);
        gl.glTranslated(2.0, 3.0, 4.0);
        gl.glRotated(30, 2.0, 1.0, 1.0);
        gl.glScaled(1.5, 1.5, 1.5);
        gl.glRotated(frameNumber*0.3, 0, 1, 1);
        myShape.drawIcosahedron(gl);
        gl.glPopMatrix();

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
