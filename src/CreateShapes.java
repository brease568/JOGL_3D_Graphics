package com.company;

import com.jogamp.opengl.GL2;

public class CreateShapes {

    public void drawCube(GL2 gl) {
        gl.glPushMatrix();
        //gl.glScaled(size,size,size); // scale unit cube to desired size
        //Move to the offset 0,0
        gl.glTranslated(0,0,0);
        square(gl,1, 0, 0); // red front face

        gl.glPushMatrix();
        gl.glRotated(90, 0, 1, 0);

        square(gl,0, 1, 0); // green right face
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glRotated(-90, 1, 0, 0);
        square(gl,0, 0, 1); // blue top face
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glRotated(180, 0, 1, 0);
        square(gl,0, 1, 1); // cyan back face
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glRotated(-90, 0, 1, 0);
        square(gl,1, 0, 1); // magenta left face
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glRotated(90, 1, 0, 0);
        square(gl,1, 1, 0); // yellow bottom face
        gl.glPopMatrix();

        gl.glPopMatrix(); // Restore matrix to its state before cube() was called.
    } //end of drawCube()

    private void square(GL2 gl2, double r, double g, double b) {
        gl2.glColor3d(r,g,b);
        gl2.glBegin(GL2.GL_TRIANGLE_FAN);
        gl2.glVertex3d(-0.5, -0.5, 0.5);
        gl2.glVertex3d(0.5, -0.5, 0.5);
        gl2.glVertex3d(0.5, 0.5, 0.5);
        gl2.glVertex3d(-0.5, 0.5, 0.5);
        gl2.glEnd();
    } //end of square()

    public void drawTriangle(GL2 gl) {
        //Draws the triangle using only the edges
        gl.glBegin(GL2.GL_LINE_LOOP);

        // Front side
        gl.glColor3f( 1.0f, 0.0f, 0.0f ); // Red
        gl.glVertex3f( 0f, 1.5f, 0.0f ); // Top Of Triangle (Front)
        gl.glColor3f( 0.0f, 1.0f, 0.0f ); // Green
        gl.glVertex3f( -.5f, -.5f, .5f ); // Left Of Triangle (Front)
        gl.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
        gl.glVertex3f( .5f, -.5f, .5f ); // Right Of Triangle (Front)

        // Right side
        gl.glColor3f( 1.0f, 0.0f, 0.0f ); // Red
        gl.glVertex3f( 0f, 1.5f, 0.0f ); // Top Of Triangle (Right)
        gl.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
        gl.glVertex3f( .5f, -.5f, .5f ); // Left Of Triangle (Right)
        gl.glColor3f( 0.0f, 1.0f, 0.0f ); // Green
        gl.glVertex3f( .5f, -.5f, -.5f ); // Right Of Triangle (Right)

        // Back side
        gl.glColor3f( 1.0f, 0.0f, 0.0f ); // Red
        gl.glVertex3f( 0f, 1.5f, 0.0f ); // Top Of Triangle (Back)
        gl.glColor3f( 0.0f, 1.0f, 0.0f ); // Green
        gl.glVertex3f( .5f, -.5f, -.5f ); // Left Of Triangle (Back)
        gl.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
        gl.glVertex3f( -.5f, -.5f, -.5f ); // Right Of Triangle (Back)

        //Left side
        gl.glColor3f( 1.0f, 0.0f, 0.0f ); // Red
        gl.glVertex3f( 0f, 1.5f, 0.0f ); // Top Of Triangle (Left)
        gl.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
        gl.glVertex3f( -.5f, -.5f, -.5f ); // Left Of Triangle (Left)
        gl.glColor3f( 0.0f, 1.0f, 0.0f ); // Green
        gl.glVertex3f( -.5f, -.5f, .5f ); // Right Of Triangle (Left)
        gl.glEnd();
        gl.glFlush();
    } //end of drawTriangle()

    public void drawTetrahedron(GL2 gl) {
        // An array of all the vertices of the tetrahedron in no particular order.
        double[][] vertices = new double[][] {
                {1, -1, -1},
                {-1, -1, 1},
                {-1, 1, -1},
                {1, 1, 1}
        };

        int[][] faces = new int[][] {
                {0, 3, 2},
                {3, 0, 1},
                {0, 2, 1},
                {2, 3, 1}
        };

        double[][] faceColors = new double[][] {
                {1, 0, 0}, // red
                {0, 1, 0}, // green
                {0, 0, 1}, // blue
                {1, 1, 0} // yellow
        };

        gl.glPushMatrix();

        //color the faces of the tetrahedron
        for (int i = 0; i < faces.length; i++)
        {
            gl.glColor3dv(faceColors[i], 0 );

            gl.glBegin(GL2.GL_TRIANGLE_FAN);
            for (int j = 0; j < faces[i].length; j++)
            {
                int vertexNum = faces[i][j];
                gl.glVertex3dv( vertices[vertexNum], 0 );
            }
            gl.glEnd();
        }

        //draw edges colored in black
        gl.glColor3f(0,0,0);
        for (int i = 0; i < faces.length; i++)
        {
            gl.glBegin(GL2.GL_LINE_LOOP);
            for (int j = 0; j < faces[i].length; j++)
            {
                int vertexNum = faces[i][j];
                gl.glVertex3dv( vertices[vertexNum], 0 );
            }
            gl.glEnd();
        }
        gl.glPopMatrix();
        gl.glFlush();
    } //end of drawTetrahedron()

    public void drawRhombicDodecahedron(GL2 gl) {
        double[][] vertices = new double[][] {
                {0.000000,2.000000,0.000000},
                {-1.000000,-1.000000,-1.000000},
                {1.000000,-1.000000,-1.000000},
                {1.000000,1.000000,-1.000000},
                {-1.000000,1.000000,-1.000000},
                {-1.000000,-1.000000,1.000000},
                {1.000000,-1.000000,1.000000},
                {1.000000,1.000000,1.000000},
                {-1.000000,1.000000,1.000000},
                {0.000000,0.000000,2.000000},
                {0.000000,0.000000,-2.000000},
                {-2.000000,0.000000,0.000000},
                {2.000000,0.000000,0.000000},
                {0.000000,-2.000000,0.000000}
        };

        int[][] faces = new int[][] {
                {1,13,2,10},
                {2,12,3,10},
                {3,0,4,10},
                {4,11,1,10},
                {2,13,6,12},
                {3,12,7,0},
                {4,0,8,11},
                {1,11,5,13},
                {5,9,6,13},
                {6,9,7,12},
                {7,9,8,0},
                {8,9,5,11}
        };

        double[][] faceColors = new double[][] {
                {0.000000,0.707107,0.707107},
                {-0.707107,0.000000,0.707107},
                {0.000000,-0.707107,0.707107},
                {0.707107,0.000000,0.707107},
                {-0.707107,0.707107,0.000000},
                {-0.707107,-0.707107,0.000000},
                {0.707107,-0.707107,0.000000},
                {0.707107,0.707107,0.000000},
                {0.000000,0.707107,-0.707107},
                {-0.707107,0.000000,-0.707107},
                {0.000000,-0.707107,-0.707107},
                {0.707107,0.000000,-0.707107}
        };

        gl.glPushMatrix();

        //color the faces of the rhombicDodecahedron
        for (int i = 0; i < faces.length; i++)
        {
            gl.glColor3dv(faceColors[i], 0 );

            gl.glBegin(GL2.GL_TRIANGLE_FAN);
            for (int j = 0; j < faces[i].length; j++)
            {
                int vertexNum = faces[i][j];
                gl.glVertex3dv( vertices[vertexNum], 0 );
            }
            gl.glEnd();
        }

        //draw edges colored in black
        gl.glColor3f(0,0,0);
        for (int i = 0; i < faces.length; i++)
        {
            gl.glBegin(GL2.GL_LINE_LOOP);
            for (int j = 0; j < faces[i].length; j++)
            {
                int vertexNum = faces[i][j];
                gl.glVertex3dv( vertices[vertexNum], 0 );
            }
            gl.glEnd();
        }
        gl.glPopMatrix();
        gl.glFlush();
    } //end of drawRhombicDodecahedron()

    public void drawOctahedron(GL2 gl) {
        double[][] vertices = new double[][] {
                {-1.000000,0.000000,0.000000},
                {0.000000,0.000000,-1.000000},
                {0.000000,0.000000,1.000000},
                {0.000000,-1.000000,0.000000},
                {0.000000,1.000000,0.000000},
                {1.000000,0.000000,0.000000}
        };

        int[][] faces = new int[][] {
                {4,5,2},
                {0,4,2},
                {0,2,3},
                {5,3,2},
                {5,4,1},
                {4,0,1},
                {0,3,1},
                {3,5,1}
        };

        double[][] faceColors = new double[][] {
                {0,1,1},
                {1,0,0},
                {0.5,0.5,0.5},
                {1,1,0},
                {1,1,1},
                {0,1,0},
                {1,0,1},
                {0,0,1}
        };

        gl.glPushMatrix();

        //color the faces of the octahedron:
        for (int i = 0; i < faces.length; i++)
        {
            gl.glColor3dv(faceColors[i], 0 );

            gl.glBegin(GL2.GL_TRIANGLE_FAN);
            for (int j = 0; j < faces[i].length; j++)
            {
                int vertexNum = faces[i][j];
                gl.glVertex3dv( vertices[vertexNum], 0 );
            }
            gl.glEnd();
        }

        //draw edges colored in black
        gl.glColor3f(0,0,0);
        for (int i = 0; i < faces.length; i++)
        {
            gl.glBegin(GL2.GL_LINE_LOOP);
            for (int j = 0; j < faces[i].length; j++)
            {
                int vertexNum = faces[i][j];
                gl.glVertex3dv( vertices[vertexNum], 0 );
            }
            gl.glEnd();
        }
        gl.glPopMatrix();
        gl.glFlush();
    } //end of drawOctahedron()

    public void drawIcosahedron(GL2 gl) {
        double[][] vertices = new double[][] {
                {-1.000000,-0.618034,0.000000},
                {0.000000,1.000000,0.618034},
                {0.000000,1.000000,-0.618034},
                {1.000000,0.618034,0.000000},
                {1.000000,-0.618034,0.000000},
                {0.000000,-1.000000,-0.618034},
                {0.000000,-1.000000,0.618034},
                {0.618034,0.000000,1.000000},
                {-0.618034,0.000000,1.000000},
                {0.618034,0.000000,-1.000000},
                {-0.618034,0.000000,-1.000000},
                {-1.000000,0.618034,0.000000}
        };

        int[][] faces = new int[][] {
                {3,7,1},
                {4,7,3},
                {6,7,4},
                {8,7,6},
                {7,8,1},
                {9,4,3},
                {2,9,3},
                {2,3,1},
                {11,2,1},
                {10,2,11},
                {10,9,2},
                {9,5,4},
                {6,4,5},
                {0,6,5},
                {0,11,8},
                {11,1,8},
                {10,0,5},
                {10,5,9},
                {0,8,6},
                {0,10,11}
        };

        double[][] faceColors = new double[][] {
                {-0.577350,-0.577350,-0.577350},
                {-0.934172,0.000000,-0.356822},
                {-0.577350,0.577350,-0.577350},
                {0.000000,0.356822,-0.934172},
                {0.000000,-0.356822,-0.934172},
                {-0.934172,0.000000,0.356822},
                {-0.577350,-0.577350,0.577350},
                {-0.356822,-0.934172,0.000000},
                {0.356822,-0.934172,0.000000},
                {0.577350,-0.577350,0.577350},
                {0.000000,-0.356822,0.934172},
                {-0.577350,0.577350,0.577350},
                {-0.356822,0.934172,0.000000},
                {0.356822,0.934172,0.000000},
                {0.934172,0.000000,-0.356822},
                {0.577350,-0.577350,-0.577350},
                {0.577350,0.577350,0.577350},
                {0.000000,0.356822,0.934172},
                {0.577350,0.577350,-0.577350},
                {0.934172,0.000000,0.356822}
        };

        gl.glPushMatrix();

        //color the faces of the icosahedron:
        for (int i = 0; i < faces.length; i++)
        {
            gl.glColor3dv(faceColors[i], 0 );

            gl.glBegin(GL2.GL_TRIANGLE_FAN);
            for (int j = 0; j < faces[i].length; j++)
            {
                int vertexNum = faces[i][j];
                gl.glVertex3dv( vertices[vertexNum], 0 );
            }
            gl.glEnd();
        }

        //draw edges colored in black
        gl.glColor3f(0,0,0);
        for (int i = 0; i < faces.length; i++)
        {
            gl.glBegin(GL2.GL_LINE_LOOP);
            for (int j = 0; j < faces[i].length; j++)
            {
                int vertexNum = faces[i][j];
                gl.glVertex3dv( vertices[vertexNum], 0 );
            }
            gl.glEnd();
        }
        gl.glPopMatrix();
        gl.glFlush();
    } //end of drawIcosahedron()

} //end of CreateShapes
