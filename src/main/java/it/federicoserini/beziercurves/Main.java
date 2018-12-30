package it.federicoserini.beziercurves;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import it.federicoserini.beziercurves.drawer.LineDrawer;
import it.federicoserini.beziercurves.model.VertexCoordinates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String TITLE = "Bezier Curves";
            JFrame frame = new JFrame();

            FPSAnimator animator;

            Vector<VertexCoordinates> vertexCoordinates = new Vector<>();

            VertexCoordinates vertexA = new VertexCoordinates(rescale(0.72), rescale(0.34), 1.0, "A");
            VertexCoordinates vertexB = new VertexCoordinates(rescale(2.5), rescale(2.06), 1.0, "B");
            VertexCoordinates vertexC = new VertexCoordinates(rescale(0.27), rescale(2.96), -1.0, "C");
            VertexCoordinates vertexD = new VertexCoordinates(rescale(1.62), rescale(4.3), -1.0, "D");

            /* Coordinates vertexA = new Coordinates(0.2, 0.12,0, "A");
            Coordinates vertexB = new Coordinates(0.78, 0.79, 0, "B");
            Coordinates vertexC = new Coordinates(0.13, 1, 0, "C"); */

            /* Coordinates vertexA = new Coordinates(rescale(0.13), rescale(0.56), 1.0, "A");
            Coordinates vertexB = new Coordinates(rescale(1.8), rescale(4.28), 1.0, "B");
            Coordinates vertexC = new Coordinates(rescale(3.74), rescale(0.56), -1.0, "C");
            Coordinates vertexD = new Coordinates(rescale(5.25), rescale(3.54), -1.0, "D");
            Coordinates vertexE = new Coordinates(rescale(6.47), rescale(2.22), -1.0, "E");
            Coordinates vertexF = new Coordinates(rescale(5.25), rescale(0.54), -1.0, "F"); */



            vertexCoordinates.add(vertexA);
            vertexCoordinates.add(vertexB);
            vertexCoordinates.add(vertexC);
            vertexCoordinates.add(vertexD);
           // vertexCoordinates.add(vertexE);
            // vertexCoordinates.add(vertexF);



            GLCanvas canvas =  new GLCanvas();
            canvas.setPreferredSize(new Dimension(1024, 1024));
            animator = new FPSAnimator(canvas, 60, true);

            canvas.addGLEventListener(new LineDrawer(vertexCoordinates));
            frame.getContentPane().add(canvas);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    // Use a dedicate thread to run the stop() to ensure that the
                    // animator stops before program exits.
                    new Thread(() -> {
                        if (animator.isStarted()) animator.stop();
                            System.exit(0);
                    }).start();
                }
            });

            frame.setTitle(TITLE);
            frame.pack();
            frame.setVisible(true);
            animator.start();
        });

    }

    private static double rescale(double valueToScale){
        return Math.abs(0 + (valueToScale - 0) * ((0.0 - 0.10) / (1)));
    }
}
