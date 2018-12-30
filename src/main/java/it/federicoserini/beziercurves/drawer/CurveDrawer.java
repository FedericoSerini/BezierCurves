package it.federicoserini.beziercurves.drawer;

import it.federicoserini.beziercurves.enums.CurveType;
import it.federicoserini.beziercurves.model.Coordinates;
import it.federicoserini.beziercurves.model.Curve;
import it.federicoserini.beziercurves.model.PascalTriangle;
import it.federicoserini.beziercurves.model.VertexCoordinates;

import java.util.Vector;

public class CurveDrawer implements CurveDrawerInterface {

    @Override
    public Curve prepareCurve(int precision, double parameter, Vector<VertexCoordinates> vertices) {
        int numberOfPoints = vertices.size();
        Curve curve = new Curve();

        if(numberOfPoints == CurveType.SIMPLE.getNumberOfVertices()){
            curve.setCurveType(CurveType.SIMPLE);
            curve.setCoordinates(traceCurve(precision, parameter,vertices));
        } else if (numberOfPoints == CurveType.QUADRATIC.getNumberOfVertices()){
            System.out.println("DRAWING QUADRATIC CURVE ..");
            curve.setCurveType(CurveType.QUADRATIC);
            curve.setCoordinates(traceCurve(precision, parameter, vertices));
        } else if (numberOfPoints == CurveType.CUBIC.getNumberOfVertices()) {
            System.out.println("DRAWING CUBIC CURVE ..");
            curve.setCurveType(CurveType.CUBIC);
            curve.setCoordinates(traceCurve(precision, parameter, vertices));
        } else if (numberOfPoints >= CurveType.TH_GRADE.getNumberOfVertices()) {
            System.out.println("DRAWING "+(numberOfPoints-1)+" TH ORDER CURVE");
            curve.setCurveType(CurveType.TH_GRADE);
            curve.setCoordinates(traceCurve(precision, parameter,vertices));
        } else {
            System.out.println("NOT A VALID CURVE");
        }

        return curve;
    }

    private Vector<Coordinates> traceCurve(int precision, double parameter, Vector<VertexCoordinates> vertices){
        Vector<Coordinates> curveCoords = new Vector<>();
        double parameterB = 1 - parameter; // The point of the curve goes A (parameter) to B (parameterB)

        for (VertexCoordinates vertex : vertices){
            System.out.println("VERTEX "+vertex.getVertex()+": "+vertex.getX()+" , "+vertex.getY()+" , "+vertex.getZ());
        }

        for (int i = 0; i <= precision; i++){
            Vector<Double> calculatedBinomial = binomialCalculus(parameter, parameterB, vertices.size()-1);
            Coordinates curvePoint = new Coordinates(0,0,0);

            for (int j=0; j < vertices.size(); j++){ // DRAWING COORDINATES FOR EACH VERTEX
                curvePoint.setX(curvePoint.getX() + vertices.get(j).getX() * calculatedBinomial.get(j));
                curvePoint.setY(curvePoint.getY() + vertices.get(j).getY() * calculatedBinomial.get(j));
                curvePoint.setZ(curvePoint.getZ() + vertices.get(j).getZ() * calculatedBinomial.get(j));
            }

            System.out.println("CURVE X: "+curvePoint.getX());
            System.out.println("CURVE Y: "+curvePoint.getY());
            System.out.println("CURVE Z: "+curvePoint.getZ());
            parameter = parameter - (1.0/precision);
            parameterB = 1.0 - parameter;
            curveCoords.add(curvePoint);
        }
        return curveCoords;
    }

    /**
     * <p> Resolve all binomial of type (a+b) through Pascal Triangle </p>
     * @param parameter a
     * @param parameterB b
     * @param grade grade of the binomial
     * @return A Vector with calculated values of the binomial
     */
    private Vector<Double> binomialCalculus(double parameter, double parameterB, int grade){
        System.out.println("SOLVING "+grade+" GRADE BINOMIAL ..");
        Vector<Double> calculatedBinomial = new Vector<>();
        PascalTriangle pascalTriangle = pascalTriangle(grade);

        Vector<Integer> coefficients = pascalTriangle.getLayers().get(grade);

        for(int i = 0; i < coefficients.size(); i++){ // Calculating values of the binomial
            System.out.println(coefficients.get(i)+"* " +"a^"+(grade-i)+" * b^"+i);
            calculatedBinomial.add(coefficients.get(i) * (Math.pow(parameter, grade - i)) * Math.pow(parameterB, i));
        }

       return calculatedBinomial;
    }

    /**
     * <p> Build a Pascal Triangle</p>
     * @param grade The grade of the binomial
     * @return A PascalTriangle object with depth (rows) and a vector of vectors of layers (divided by grades)
     */
    private PascalTriangle pascalTriangle(int grade){
        System.out.println("SOLVING PASCAL TRIANGLE");
        PascalTriangle pascalTriangle = new PascalTriangle();
        Vector<Vector<Integer>> layers = new Vector<>();
        pascalTriangle.setDepth(grade);

        int coefficient = 1, i, j;

        for(i=0; i<=pascalTriangle.getDepth(); i++) {
            Vector<Integer> layer = new Vector<>();

            for(j=0; j <= i; j++) {
                if (j==0 || i==0)
                    coefficient = 1;
                else
                    coefficient = coefficient*(i-j+1)/j;

                layer.add(coefficient);
            }
            layers.add(layer);
        }
        System.out.println("PASCAL TRIANGLE RESOLVED");

        pascalTriangle.setLayers(layers);
        return pascalTriangle;
    }
}
