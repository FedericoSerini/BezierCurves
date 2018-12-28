package it.federicoserini.beziercurves.model;

import it.federicoserini.beziercurves.enums.CurveType;

import java.util.Vector;

public class Curve {
    private Vector<Coordinates> coordinates;
    private CurveType curveType;

    public Curve(){

    }

    public Vector<Coordinates> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Vector<Coordinates> coordinates) {
        this.coordinates = coordinates;
    }

    public CurveType getCurveType() {
        return curveType;
    }

    public void setCurveType(CurveType curveType) {
        this.curveType = curveType;
    }
}
