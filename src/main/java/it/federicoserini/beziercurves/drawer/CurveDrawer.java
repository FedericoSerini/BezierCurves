package it.federicoserini.beziercurves.drawer;

import it.federicoserini.beziercurves.enums.CurveType;
import it.federicoserini.beziercurves.model.Coordinates;
import it.federicoserini.beziercurves.model.Curve;

import java.awt.*;
import java.util.Vector;

public class CurveDrawer implements CurveDrawerInterface {

    @Override
    public Curve drawCurve(int precision, double parameter, Vector<Coordinates> vertices) {
        int numberOfPoints = vertices.size();
        Curve curve = new Curve();

        if(numberOfPoints == CurveType.SIMPLE.getNumberOfVertices()){
            curve.setCurveType(CurveType.SIMPLE);
            Vector<Coordinates> simpleCurveCoords = new Vector<>();

            for (int i = 0; i <= precision; i++){
                Coordinates simpleCurvePoint = new Coordinates();
                simpleCurvePoint.setX(0);
                simpleCurvePoint.setY(0);
                simpleCurvePoint.setZ(0);
                simpleCurveCoords.add(simpleCurvePoint);
            }
        } else if (numberOfPoints == CurveType.QUADRATIC.getNumberOfVertices()){
            curve.setCurveType(CurveType.QUADRATIC);
            Vector<Coordinates> quadraticCurveCoords = new Vector<>();
            double parameterB = 1 - parameter; // The point of the curve goes A (parameter) to B (parameterB)
            double Ax = vertices.get(0).getX(); double Ay = vertices.get(0).getY(); double Az = vertices.get(0).getZ();
            double Bx = vertices.get(1).getX(); double By = vertices.get(1).getY(); double Bz = vertices.get(1).getZ();
            double Cx = vertices.get(2).getX(); double Cy = vertices.get(2).getY(); double Cz = vertices.get(2).getZ();
            System.out.println("VERTEX A: "+Ax+" , "+Ay+" , "+Az);
            System.out.println("VERTEX B: "+Bx+" , "+By+" , "+Bz);
            System.out.println("VERTEX C: "+Cx+" , "+Cy+" , "+Cz);
            for (int i = 0; i <= precision; i++){
                Coordinates quadraticCurvePoint = new Coordinates();

                quadraticCurvePoint.setX(Ax * (Math.pow(parameter, 2.0))  + // solving (a+b)^3
                        Bx * 2 * parameter * parameterB +
                        Cx * (Math.pow(parameterB, 2.0)));

                System.out.println("CURVE X: "+quadraticCurvePoint.getX());

                quadraticCurvePoint.setY(Ay * (Math.pow(parameter, 2.0))  + // solving (a+b)^3
                        By * 2 * parameter * parameterB +
                        Cy * (Math.pow(parameterB, 2.0)));
                System.out.println("CURVE Y: "+quadraticCurvePoint.getY());

                quadraticCurvePoint.setZ(Az * (Math.pow(parameter, 2.0))  + // solving (a+b)^3
                        Bz * 2 * parameter * parameterB +
                        Cz * (Math.pow(parameterB, 2.0)));

                System.out.println("CURVE Z: "+quadraticCurvePoint.getZ());

                parameter = parameter - (1.0/precision);
                parameterB = 1.0 - parameter;
                quadraticCurveCoords.add(quadraticCurvePoint);
            }
            curve.setCoordinates(quadraticCurveCoords);
            return curve;
        } else if (numberOfPoints == CurveType.CUBIC.getNumberOfVertices()) {
            curve.setCurveType(CurveType.CUBIC);
            Vector<Coordinates> cubicCurveCoords = new Vector<>();
            double parameterB = 1 - parameter; // The point of the curve goes A (parameter) to B (parameterB)
             double Ax = vertices.get(0).getX(); double Ay = vertices.get(0).getY(); double Az = vertices.get(0).getZ();
            double Bx = vertices.get(1).getX(); double By = vertices.get(1).getY(); double Bz = vertices.get(1).getZ();
            double Cx = vertices.get(2).getX(); double Cy = vertices.get(2).getY(); double Cz = vertices.get(2).getZ();
            double Dx = vertices.get(3).getX(); double Dy = vertices.get(3).getY(); double Dz = vertices.get(3).getZ();

            System.out.println("VERTEX A: "+Ax+" , "+Ay+" , "+Az);
            System.out.println("VERTEX B: "+Bx+" , "+By+" , "+Bz);
            System.out.println("VERTEX C: "+Cx+" , "+Cy+" , "+Cz);
            System.out.println("VERTEX D: "+Dx+" , "+Dy+" , "+Dz);

            for (int i = 0; i < precision; i++){
                Coordinates cubicCurvePoint = new Coordinates();
                 cubicCurvePoint.setX(Ax * (Math.pow(parameter, 3.0))  + // solving (a+b)^3
                        Bx * 3 * (Math.pow(parameter, 2.0)) * parameterB +
                        Cx * 3 * parameter * (Math.pow(parameterB, 2.0)) +
                        Dx * (Math.pow(parameterB, 3.0)));

                System.out.println("CURVE X: "+cubicCurvePoint.getX());

                cubicCurvePoint.setY(Ay * (Math.pow(parameter, 3.0)) +
                        By * 3 * (Math.pow(parameter, 2.0)) * parameterB +
                        Cy * 3 * parameter * (Math.pow(parameterB, 2.0)) +
                        Dy * (Math.pow(parameterB, 3.0)));
                System.out.println("CURVE Y: "+cubicCurvePoint.getY());

                cubicCurvePoint.setZ(Az * (Math.pow(parameter, 3.0))  +
                        Bz * 3 * (Math.pow(parameter, 2.0)) * parameterB +
                        Cz * 3 * parameter * (Math.pow(parameterB, 2.0)) +
                        Dz * (Math.pow(parameterB, 3.0)));
                System.out.println("CURVE Z: "+cubicCurvePoint.getZ());

                parameter = parameter - (1.0/precision);
                parameterB = 1.0 - parameter;
                cubicCurveCoords.add(cubicCurvePoint);
            }

            curve.setCoordinates(cubicCurveCoords);
            return curve;
        } else if (numberOfPoints >= CurveType.TH_GRADE.getNumberOfVertices()) {
            curve.setCurveType(CurveType.TH_GRADE);
            Vector<Coordinates> thGradeCurveCoords = new Vector<>();

            for (int i = 0; i <= precision; i++){
                Coordinates thGradeCurvePoint = new Coordinates();
                thGradeCurvePoint.setX(0);
                thGradeCurvePoint.setY(0);
                thGradeCurvePoint.setZ(0);
                thGradeCurveCoords.add(thGradeCurvePoint);
            }
        } else {
            System.out.println("NOT A VALID CURVE");
        }

        return null;
    }
}
