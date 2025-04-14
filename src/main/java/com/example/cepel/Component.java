
package com.example.cepel;

public class Component {
    private String id;
    private String title;
    private String type;
    private Double baseKv; 
    private Double x;  
    private Double y;  
    private boolean hidden;
    private String sourcePosition;  
    private String targetPosition; 
   

    public Component(String id, String title, String type, Double baseKv, Double x, Double y, boolean hidden,String sourcePosition, String targetPosition) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.baseKv = baseKv;
        this.x = x;
        this.y = y;
        this.hidden = hidden;
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }



	
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getType() { return type; }
    public Double getBaseKv() { return baseKv; }
    public Double getX() { return x; }
    public Double getY() { return y; }
    public boolean isHidden() { return hidden; }
    public String getSourcePosition() { return sourcePosition; }
    public String getTargetPosition() { return targetPosition; }

    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setType(String type) { this.type = type; }
    public void setBaseKv(Double baseKv) { this.baseKv = baseKv; }
    public void setX(Double x) { this.x = x; }
    public void setY(Double y) { this.y = y; }
    public void setHidden(boolean hidden) { this.hidden = hidden; }
    public void setSourcePosition(String sourcePosition) { this.sourcePosition = sourcePosition; }
    public void setTargetPosition(String targetPosition) { this.targetPosition = targetPosition; }
}

