package it.federicoserini.beziercurves.drawer;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import it.federicoserini.beziercurves.model.Coordinates;
import it.federicoserini.beziercurves.model.Curve;

import javax.swing.*;
import java.util.Vector;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

public class LineDrawer extends GLCanvas implements GLEventListener {

    private CurveDrawer curveDrawer = new CurveDrawer();
    private Vector<Coordinates> vertexCoordinates;
    private Curve curveCoordinates;
    private GLU glu;
    private GLUT glut;

    public LineDrawer(Vector<Coordinates> vertexCoordinates) {
        this.vertexCoordinates = vertexCoordinates;
        this.curveCoordinates = curveDrawer.drawCurve(1000, 1, vertexCoordinates);
        this.addGLEventListener(this);
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();      // get the OpenGL graphics context
        glu = new GLU();
        glut = new GLUT();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
        gl.glLoadIdentity();  // reset the model-view matrix

        gl.glPointSize(8);
        gl.glColor3f(1,1,0);

        gl.glBegin(GL.GL_POINTS); // drawing vertices points
            for(Coordinates coordinates : vertexCoordinates){
                gl.glVertex3d(coordinates.getX(), coordinates.getY(), coordinates.getZ());
            }
        gl.glEnd();

        for(Coordinates coordinates : vertexCoordinates){
            gl.glRasterPos3d(coordinates.getX(), coordinates.getY(), coordinates.getZ());
            glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, coordinates.getVertex());
        }

       gl.glBegin(GL.GL_LINE_STRIP); // drawing bezier curve
            gl.glColor3f(255, 255, 255);
            for (Coordinates coordinates : curveCoordinates.getCoordinates()){
                gl.glVertex3d(coordinates.getX(), coordinates.getY(), coordinates.getZ());
            }
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {
       GL2 gl = glAutoDrawable.getGL().getGL2();  // get the OpenGL 2 graphics context



        // Set the view port (display area) to cover the entire window
        gl.glViewport(0, 0, 1024, 1024);


    }
}
