package it.federicoserini.beziercurves.drawer;

import it.federicoserini.beziercurves.model.Coordinates;
import it.federicoserini.beziercurves.model.Curve;

import java.awt.*;
import java.util.Vector;

public interface CurveDrawerInterface {
    Curve drawCurve(int precision, double parameter, Vector<Coordinates> vertices);
}
