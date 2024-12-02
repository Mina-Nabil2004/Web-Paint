package com.Paint.Paint.services.shapes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,include = JsonTypeInfo.As.PROPERTY,visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Circle.class, name = "Circle"),
    @JsonSubTypes.Type(value = Square.class, name = "square"),
    @JsonSubTypes.Type(value = Rectangle.class, name = "Rect"),
    @JsonSubTypes.Type(value = Line.class, name = "line"),
    @JsonSubTypes.Type(value = Triangle.class, name = "triangle"),
    @JsonSubTypes.Type(value = Polygon.class, name = "polygon"),
    @JsonSubTypes.Type(value = Hexagon.class, name = "hexagon"),
    @JsonSubTypes.Type(value = Ellipse.class, name = "ellipse"),
    @JsonSubTypes.Type(value = Pentagon.class, name = "pentagon")
 
})
@JsonIgnoreProperties(value = "attributes" ,ignoreUnknown = true)
public abstract class shape implements Cloneable{// clonable 
    private String id;
    private String name;
    private double x;
    private double y;
    private double rotation;
    private boolean draggable;
    private double scaleofY;
    private double scaleofX;
    private double scaleY;
    private double scaleX;
    private double skewY;
    private double skewX;
    private String stroke;
    private double strokeWidth;
    private double radiusX;
    private double radiusY;
    private String Konvaname;

    private String fill;
    public shape(ShapeDTO docreate) {
        this.x = docreate.x ;
        this.y = docreate.y ;
        this.id = docreate.id ;
        this.fill = docreate.fill ;
        this.name = docreate.name ;
        this.stroke = docreate.stroke ;
        this.Konvaname=docreate.Konvaname;
        this.strokeWidth = docreate.strokeWidth ;
        this.rotation = docreate.rotation ;
        this.draggable = docreate.draggable ;
        this.scaleofX = docreate.scaleofX ;
        this.scaleofY = docreate.scaleofY ;
        this.scaleX = docreate.scaleX ;
        this.scaleY = docreate.scaleY ;
        this.skewX = docreate.skewX ;
        this.skewY = docreate.skewY ;
        this.radiusX = docreate.radiusX ;
        this.radiusY = docreate.radiusY ;
    }
    public shape(shape s){ //copy
        this.id = s.id ;
        this.name = s.name ;
        this.Konvaname = s.Konvaname;
        this.x=s.x ;
        this.y=s.y ;
        this.draggable = s.draggable ;
        this.rotation = s.rotation ;
        this.scaleofX = s.scaleofX ;
        this.scaleofY = s.scaleofY ;
        this.stroke = s.stroke ;
        this.strokeWidth = s.strokeWidth ;
        this.fill = s.fill ;
        this.scaleX = s.scaleX ;
        this.scaleY = s.scaleY ;
        this.skewX = s.skewX ;
        this.skewY = s.skewY ;
        this.radiusX = s.radiusX ;
        this.radiusY = s.radiusY ;
    }
    public void update(ShapeDTO dto){
        this.id = dto.id ;
        this.name = dto.name ;
        this.Konvaname = dto.Konvaname;
        this.x = dto.x ;
        this.y = dto.y ;
        this.draggable = dto.draggable ;
        this.rotation = dto.rotation ;
        this.scaleofX = dto.scaleofX ;
        this.scaleofY = dto.scaleofY ;
        this.scaleX = dto.scaleX ;
        this.scaleY = dto.scaleY ;
        this.stroke = dto.stroke ;
        this.strokeWidth = dto.strokeWidth ;
        this.fill = dto.fill ;
        this.skewX = dto.skewX ;
        this.skewY = dto.skewY ;
        this.radiusX = dto.radiusX ;
        this.radiusY = dto.radiusY ;
    }
    public abstract shape clone(String cloneid)throws CloneNotSupportedException;

    public shape(){
    }
    public String getKonvaname() {
        return Konvaname;
    }
    public double getradiusX(){
        return radiusX;
    }
    public double getradiusY(){
        return radiusY;
    }
    public double setradiusX(){
        return radiusX;
    }
    public double setradiusY(){
        return radiusY;
    }

    public void setKonvaname(String stroke) {
        this.Konvaname = Konvaname;
    }
    public String getStroke() {
        return stroke;
    }
    public void setStroke(String stroke) {
        this.stroke = stroke;
    }
    public double getStrokeWidth() {
        return strokeWidth;
    }
    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getRotation() {
        return rotation;
    }
    public void setRotation(double rotation) {
        this.rotation = rotation;
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
    public boolean isDraggable() {
        return draggable;
    }
    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }
    public double getScaleofY() {
        return scaleofY;
    }
    public void setScaleofY(double scaleofY) {
        this.scaleofY = scaleofY;
    }
    public double getScaleofX() {
        return scaleofX;
    }
    public void setScaleofX(double scaleofX) {
        this.scaleofX = scaleofX;
    }
    public String getFill() {
        return fill;
    }

    public double getSkewY() {
        return skewY;
    }

    public void setSkewY(double skewY) {
        this.skewY = skewY;
    }

    public double getSkewX() {
        return skewX;
    }

    public void setSkewX(double skewX) {
        this.skewX = skewX;
    }

    public double getScaleX() {
        return scaleX;
    }

    public void setScaleX(double scaleX) {
        this.scaleX = scaleX;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public double getScaleY() {
        return scaleY;
    }

    public void setScaleY(double scaleY) {
        this.scaleY = scaleY;
    }
}
