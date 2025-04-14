package com.example.cepel;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DiagramService {
    private static final Logger logger = Logger.getLogger(DiagramService.class.getName());
    private static final String TAG_HIDDEN = "hidden";
    private static final String TAG_TYPE = "type";
    private static final String TAG_ID = "id";
    private static final String TAG_DATA = "data";
    private static final String TAG_TITLE = "title";
    private static final String TAG_KV = "kv";
    private static final String TAG_POSITION = "position";
    private static final String TAG_X = "x";
    private static final String TAG_Y = "y";
    private static final String TAG_SOURCE_POS = "sourcePosition";
    private static final String TAG_TARGET_POS = "targetPosition";

    public static DiagramResponse parseXmlToDiagram(String xml) {
        try {
            logger.info("Iniciando análise do XML");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Configurações de segurança contra XXE
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream(xml.getBytes()));

            List<Component> components = extractComponents(document);
            List<Connection> connections = extractConnections(document);

            logger.info("Análise concluída. Encontrados " + components.size() + " componentes e " +
                    connections.size() + " conexões");

            return new DiagramResponse(components, connections);
        } catch (Exception e) {
            logger.severe("Erro ao processar o XML: " + e.getMessage());
            e.printStackTrace();
            return new DiagramResponse(new ArrayList<>(), new ArrayList<>());
        }
        
    }

    private static List<Component> extractComponents(Document document) {
        List<Component> components = new ArrayList<>();
        NodeList itemList = document.getElementsByTagName("item");
        
        for (int i = 0; i < itemList.getLength(); i++) {
            Element item = (Element) itemList.item(i);
            
            // Verificar se o item está oculto
            if (isItemHidden(item)) {
                continue;
            }
            
            // Obter informações básicas
            String type = getElementText(item, TAG_TYPE);
            String id = getElementText(item, TAG_ID);
            
            if (type.isEmpty() || id.isEmpty()) {
                logger.warning("Item sem type ou id: " + item.getTextContent());
                continue;
            }
            
            // Obter dados do componente
            Element data = getFirstChildElement(item, TAG_DATA);
            String title = data != null ? getElementText(data, TAG_TITLE) : "";
            
            // Obter kV se for uma barra (bus)
            Double baseKv = null;
            if ("bus".equals(type) && data != null) {
                baseKv = safeParseDouble(getElementText(data, TAG_KV));
            }
            
            // Obter posição
            Element position = getFirstChildElement(item, TAG_POSITION);
            Double x = position != null ? safeParseDouble(getElementText(position, TAG_X)) : null;
            Double y = position != null ? safeParseDouble(getElementText(position, TAG_Y)) : null;
            
            // Obter posições de conexão
            String sourcePosition = getElementText(item, TAG_SOURCE_POS);
            String targetPosition = getElementText(item, TAG_TARGET_POS);
            
            components.add(new Component(id, title, type, baseKv, x, y, false, sourcePosition, targetPosition));
            logger.fine("Adicionado componente: " + type + " - " + id);
        }
        
        return components;
    }
    
    private static boolean isItemHidden(Element item) {
        NodeList hiddenList = item.getElementsByTagName(TAG_HIDDEN);
        if (hiddenList.getLength() == 0) {
            return true; 
        }
        // Só está visível se explicitamente tiver <hidden>false</hidden>
        return !"false".equalsIgnoreCase(hiddenList.item(0).getTextContent().trim());
    }
    
    private static List<Connection> extractConnections(Document document) {
        List<Connection> connections = new ArrayList<>();
        NodeList edgeList = document.getElementsByTagName("edge");
        
        for (int i = 0; i < edgeList.getLength(); i++) {
            Element edge = (Element) edgeList.item(i);
            
            if (isEdgeHidden(edge)) {
                continue;
            }
            
            String source = getElementText(edge, "source");
            String target = getElementText(edge, "target");
            
            String sourceHandle = getElementText(edge, "sourcePosition");
            String targetHandle = getElementText(edge, "targetPosition");
            
            if (!source.isEmpty() && !target.isEmpty()) {
                connections.add(new Connection(
                    source, 
                    target, 
                    false, 
                    sourceHandle.isEmpty() ? null : sourceHandle,
                    targetHandle.isEmpty() ? null : targetHandle
                ));
            }
        }
        
        return connections;
    }

    private static boolean isEdgeHidden(Element edge) {
        NodeList hiddenList = edge.getElementsByTagName(TAG_HIDDEN);
        if (hiddenList.getLength() == 0) {
            return true; // Considera oculto se não tiver tag hidden
        }
        return !"false".equalsIgnoreCase(hiddenList.item(0).getTextContent().trim());
    }
    
    // Métodos auxiliares (mantidos iguais)
    private static String getElementText(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        return nodeList.getLength() > 0 ? nodeList.item(0).getTextContent().trim() : "";
    }
    
    private static Element getFirstChildElement(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        return nodeList.getLength() > 0 ? (Element) nodeList.item(0) : null;
    }
    
    private static Double safeParseDouble(String value) {
        if (value == null || value.trim().isEmpty()) return null;
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            logger.warning("Valor não numérico: " + value);
            return null;
        }
    }
}