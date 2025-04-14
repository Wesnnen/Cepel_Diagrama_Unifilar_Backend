
package com.example.cepel;

public class Connection {
    private String source;
    private String target;
    private boolean hidden;
    private String sourceHandle;  
    private String targetHandle;  

    public Connection(String source, String target, boolean hidden, 
                     String sourceHandle, String targetHandle) {
        this.source = source;
        this.target = target;
        this.hidden = hidden;
        this.sourceHandle = sourceHandle;
        this.targetHandle = targetHandle;
    }

  
    public String getSource() { return source; }
    public String getTarget() { return target; }
    public boolean isHidden() { return hidden; }
    public String getSourceHandle() { return sourceHandle; }
    public String getTargetHandle() { return targetHandle; }
    

    public void setSouce(String source) { this.source = source; }
    public void setTarget(String target) { this.target = target; }
    public void setHidden(boolean hidden) { this.hidden = hidden; }
    public void setSourceHandle(String SourceHandle) { this.sourceHandle = SourceHandle; }
    public void setTargetHandle(String TargetHandle) { this.targetHandle = TargetHandle; }
    
}
