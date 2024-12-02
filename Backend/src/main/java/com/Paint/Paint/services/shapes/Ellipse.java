package com.Paint.Paint.services.shapes;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("ellipse")
public class Ellipse extends shape {
    private double radiusX;
    private double radiusY;
    private static final String Konvaname = "Ellipse";

    public Ellipse(ShapeDTO e) {
        super(e);
        this.radiusX = e.radiusX;
        this.radiusY = e.radiusY;
    }

    public Ellipse(Ellipse e) {
        super(e);
        this.radiusX = e.radiusX;
        this.radiusY = e.radiusY;
    }

    @Override
    public void update(ShapeDTO dto) {
        super.update(dto);
        if (dto.radiusX <= 0 || dto.radiusY <= 0) {
            throw new IllegalArgumentException("Radii must be positive.");
        }
        this.radiusX = dto.radiusX;
        this.radiusY = dto.radiusY;
    }

    public double getRadiusX() {
        return radiusX;
    }

    public double getRadiusY() {
        return radiusY;
    }

    public String getKonvaname() {
        return Konvaname;
    }

    public void setRadiusX(double radiusX) {
        this.radiusX = radiusX;
    }

    public void setRadiusY(double radiusY) {
        this.radiusY = radiusY;
    }

    @Override
    public shape clone(String cloneid) throws CloneNotSupportedException {
        Ellipse clonedEllipse = new Ellipse(this);
        clonedEllipse.setId(cloneid);
        return clonedEllipse;
    }
}
