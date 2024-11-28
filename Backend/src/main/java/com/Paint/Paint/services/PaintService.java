package com.Paint.Paint.services;

import java.io.IOException;
import java.util.*;

import com.Paint.Paint.services.shapes.ShapeDTO;
import com.Paint.Paint.services.shapes.ShapeFactory;
import org.springframework.stereotype.Service;

import com.Paint.Paint.services.shapes.shape;

@Service

public class PaintService {

    private static PaintService paintService =null;

    private PaintService() {
        // Private constructor to prevent instantiation from outside
    }

    public static PaintService getInstance(){
        if(paintService == null){
            paintService = new PaintService();
        }
        return paintService;
    }
    private Stack<List<shape>> allshapes=new Stack<>();
    private Stack<List<shape>> undo=new Stack<>();
    private Stack<List<shape>> redo=new Stack<>();
    private HashMap<String,shape> shapesMap=new HashMap<>();
    private List<shape> getcurrentShapes(){
        if (!allshapes.empty()) {
            return new ArrayList<>(allshapes.peek());
        }
        return new ArrayList<>();
    }
    private void saveState(List<shape> shapes){
        allshapes.push(new ArrayList<>(shapes));
        redo.clear();
    }
    public void changedmap(){
        List<shape> currentShapes=getcurrentShapes();
        for(int i=0;i<currentShapes.size();i++){
            shape shape = currentShapes.get(i);
            shapesMap.put(shape.getId(), shape);
        }
    }
    public List<shape> clearAll() {
        List<shape> currShapes = new ArrayList<>();
        saveState(currShapes);
        shapesMap.clear();
        return currShapes;
    }
    public void addShape(shape shape){
        List<shape> currentShapes=getcurrentShapes();
        currentShapes.add(shape);
        shapesMap.put(shape.getId(), shape);
        saveState(currentShapes);
        undo.push(currentShapes);
        printShapeStack();
    }

    public void addShape(shape shape, boolean flag){
        List<shape> currentShapes=getcurrentShapes();
        currentShapes.add(shape);
        shapesMap.put(shape.getId(), shape);
        saveState(currentShapes);
        printShapeStack();
    }

    public List<shape> removShapes(String id){
        List<shape> currentShapes=getcurrentShapes();
        shape shapeToRemove=shapesMap.get(id);
        if(shapeToRemove!=null){
            currentShapes.remove(shapeToRemove);
            shapesMap.remove(id);
        }
        saveState(currentShapes);
        undo.push(currentShapes);
        return currentShapes;
    }
    public  shape getShapeById(String shapeId) {
        return shapesMap.get(shapeId);
    }
    public void clearAllshapes() {
        saveState(new ArrayList<>());
    }
    public List<shape> redo() {
        if (redo.isEmpty()) {
            System.out.println("Redo stack is empty");
            return null;
        }
        allshapes.push(redo.peek());
        undo.push(redo.pop());
        changedmap();
        printShapeStack();
        return undo.peek();
    }
    public List<shape> undo() {
        if (undo.isEmpty()) {
            System.out.println("Undo empty");
            return null;
        }
        redo.push(undo.pop());
        allshapes.pop();
        changedmap();
        printShapeStack();
        return undo.isEmpty() ? new ArrayList<>() : undo.peek(); // Return the last state or an empty list
    }
    public void printShapeStack() {
        System.out.println("Printing all shapes ");
        for (List<shape> shapes : undo) {
            System.out.println("Element no:");
            for (shape shape : shapes) {
                System.out.println(shape.toString());
            }
        }
    }
    public void updateshape(shape updated , boolean flag){
        List<shape> currentshapes =getcurrentShapes();
        for(int i=0;i<currentshapes.size();i++){
            shape shape = currentshapes.get(i);
            if(shape.getId().equals(updated.getId())){
                currentshapes.set(i, updated);
                shapesMap.put(updated.getId(), updated);
                break;
            }
        }
        changedmap();
        if(flag){
            undo.push(currentshapes);
        }
        saveState(currentshapes);
        printShapeStack();
    }

    public void endUpdate(boolean endUpdate){
        List<shape> currentshapes =getcurrentShapes();
        if(endUpdate) {
            saveState(currentshapes);
            undo.push(currentshapes);
            System.out.println("ahlaaaaaaan");
        }
        printShapeStack();
    }

    public ShapeDTO updateDTO(ShapeDTO dto, double newX, double newY){
        List<shape> currentshapes =getcurrentShapes();
        shape shape = currentshapes.get(0);
        int i;
        for(i = 0;i<currentshapes.size();i++){
            shape = currentshapes.get(i);
            if(shape.getId().equals(dto.id)){
                break;
            }
        }
        if(dto.name.equals("Rect")){
            dto.height = newY - shape.getY();
            dto.width = newX - shape.getX();
        }
        else if(dto.name.equals("square")) {
            dto.height = newY - shape.getY();
            dto.width = dto.height;
        }
        else if(dto.name.equals("Circle") || dto.name.equals("triangle") || dto.name.equals("polygon") || dto.name.equals("hexagon")){
            dto.radius = Math.sqrt(Math.pow(newY - shape.getY(),2)+ Math.pow(newX - shape.getX(),2));
        }
        dto.x = shape.getX();
        dto.y = shape.getY();
        return dto;
    }
    public void savetoxml(String path) throws IOException{
        Savefiles savefiles = new Savefiles();
        savefiles.setShapetosaved(getcurrentShapes());
        savefiles.saveToXML(path);
    }
    public Savefiles loadfromxml(String path) throws IOException{
        Savefiles loadfiles = new Savefiles();
        if (loadfiles!=null) {
            List<shape> currShapes =getcurrentShapes();
            currShapes.addAll(loadfiles.getShapetosaved());
            saveState(currShapes);
            changedmap();
            return loadfiles;
        }
        else{
            return null;
        }
    }
    public void savejson(String path)throws IOException {
        Savefiles savefiles = new Savefiles();
        savefiles.setShapetosaved(getcurrentShapes());
        savefiles.savejson(path);
    }
    public Savefiles loadfromjson(String path) throws IOException {
        Savefiles loadfiles = new Savefiles();
        if (loadfiles!=null) {
            List<shape> currShapes =getcurrentShapes();
            currShapes.addAll(loadfiles.getShapetosaved());
            saveState(currShapes);
            changedmap();
            return loadfiles;
        }
        else{
            return null;
        }
    }
    public String savechoice(String path) throws IOException {
        if (path.endsWith("json")) {
            savejson(path);
            return "saved in" + path;
        }else if (path.endsWith("xml")){
            savetoxml(path);
            return "saved in" + path;
        }
        else{
            return null;
        }
    }
    public Savefiles loadfiles(String path) throws IOException{
        if (path.endsWith("json")) {
            return loadfromjson(path);
        } else if (path.endsWith("xml")){
            return loadfromxml(path);
        }
        else{
            return null;
        }
    }

}