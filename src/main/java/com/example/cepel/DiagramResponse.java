
package com.example.cepel;

import java.util.List;

public class DiagramResponse {
    private List<Component> components;
    private List<Connection> connections;

    public DiagramResponse(List<Component> components, List<Connection> connections) {
        this.components = components;
        this.connections = connections;
    }

    
    public List<Component> getComponents() { return components; }
    public List<Connection> getConnections() { return connections; }

    public void setComponents(List<Component> components) { this.components = components; }
    public void setConnections(List<Connection> connections) { this.connections = connections; }
}
