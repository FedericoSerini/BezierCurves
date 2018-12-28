package it.federicoserini.beziercurves.model;


public class Coordinates {
    private String vertex;
    private double x,y,z;

    public Coordinates(){}

    public Coordinates(double x, double y, double z, String vertex){
        this.x = x;
        this.y = y;
        this.z = z;
        this.vertex = vertex;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public String getVertex() { return vertex; }

    public void setVertex(String vertex) { this.vertex = vertex; }
}
