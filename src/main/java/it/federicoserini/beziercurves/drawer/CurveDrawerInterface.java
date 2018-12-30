package it.federicoserini.beziercurves.drawer;

import it.federicoserini.beziercurves.model.Curve;
import it.federicoserini.beziercurves.model.VertexCoordinates;

import java.util.Vector;

interface CurveDrawerInterface {
    Curve prepareCurve(int precision, double parameter, Vector<VertexCoordinates> vertices);
}
