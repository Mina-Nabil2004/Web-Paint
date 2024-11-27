package com.Paint.Paint.services.shapes;

public class ShapeFactory {
    public shape createShape(ShapeDTO dto) {
        switch (dto.name) {
            case "square":
                return new Square(dto);
            case "Rect":
                return new Rectangle(dto);
            case "ellipse":
                return new Elipse(dto);
            case "Circle":
                return new Circle(dto);
            case "triangle":
                return new Triangle(dto);
            default:
                throw new IllegalArgumentException("Invalid shape name: " + dto.name);
        }
    }
    public shape createShape(shape s) {
        switch (s.getName()) {
            case "square":
                return new Square((Square) s);
            case "Rect":
                return new Rectangle((Rectangle) s);
            case "ellipse":
                return new Elipse((Elipse) s);
            case "Circle":
                return new Circle((Circle) s);
            case "triangle":
                return new Triangle((Triangle) s);
            default:
                throw new IllegalArgumentException("Invalid shape type: " + s.getName());
        }
    }
}
