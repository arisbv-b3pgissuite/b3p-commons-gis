/*
 * OGCResponse.java
 *
 * Created on May 16, 2008, 9:32 AM
 *
 * Vergelijkbare klasse als OGCRequest.java maar dan om de response mee te verwerken die 
 * de serviceprviders stuurt na een request.
 */

package nl.b3p.ogc.utils;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import nl.b3p.xml.ows.v100.DCP;
import nl.b3p.xml.ows.v100.HTTP;
import nl.b3p.xml.ows.v100.Operation;
import nl.b3p.xml.wfs.v100.capabilities.DCPType;
import nl.b3p.xml.wfs.v100.capabilities.DCPType_DescribeFeatureTypeType;
import nl.b3p.xml.wfs.v100.capabilities.DCPType_FeatureTypeType;
import nl.b3p.xml.wfs.v100.capabilities.DCPType_GetCapabilitiesType;
import nl.b3p.xml.wfs.v100.capabilities.DCPType_TransactionType;
import nl.b3p.xml.wfs.v100.capabilities.HTTPTypeItem;
import nl.b3p.xml.wfs.v100.capabilities.RequestTypeItem;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.UnmarshalHandler;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.w3c.dom.CDATASection;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Jytte
 */
public class OGCResponse {
    
    private String httpHost;
    private String response;
    private String version;
    private nl.b3p.xml.wfs.v100.capabilities.WFS_Capabilities newWfsCapabilitiesV100;
    private nl.b3p.xml.wfs.v110.WFS_Capabilities newWfsCapabilitiesV110;
    private nl.b3p.xml.wfs.v100.FeatureCollection newFeatureCollectionV100;
    private nl.b3p.xml.wfs.v110.FeatureCollection newFeatureCollectionV110;
    private HashMap nameSpaces;
    private HashMap schemaLocations;
    
    /** Creates a new instance of OGCResponse */
    public OGCResponse() {
    }
    
    public void rebuildResponse(Element rootElement, String httpHost){
        this.httpHost = httpHost;
        nameSpaces = new HashMap();
        findNameSpace(rootElement);
        Unmarshaller um;
        Object o;
        try{
            if(rootElement.getTagName().equalsIgnoreCase(OGCConstants.WFS_CAPABILITIES)){
                response = OGCConstants.WFS_CAPABILITIES;
                version = rootElement.getAttribute(OGCConstants.VERSION.toLowerCase());
                
                if (version.equalsIgnoreCase(OGCConstants.WFS_VERSION_100)){
                    um = new Unmarshaller(nl.b3p.xml.wfs.v100.capabilities.WFS_Capabilities.class);
                    o = um.unmarshal(rootElement);              
                    nl.b3p.xml.wfs.v100.capabilities.WFS_Capabilities wfsCapabilities = (nl.b3p.xml.wfs.v100.capabilities.WFS_Capabilities)o;
                    
                    newWfsCapabilitiesV100 = replaceCapabilitiesV100Url(wfsCapabilities);
                }else{
                    um = new Unmarshaller(nl.b3p.xml.wfs.v110.WFS_Capabilities.class);
                    o = um.unmarshal(rootElement);              
                    nl.b3p.xml.wfs.v110.WFS_Capabilities wfsCapabilities = (nl.b3p.xml.wfs.v110.WFS_Capabilities)o;
                    
                    newWfsCapabilitiesV110 = replaceCapabilitiesV110Url(wfsCapabilities);
                }
            }else if(rootElement.getTagName().equalsIgnoreCase(OGCConstants.WFS_FEATURECOLLECTION)){
                response = OGCConstants.WFS_FEATURECOLLECTION;
                version = rootElement.getAttribute(OGCConstants.VERSION.toLowerCase());
                
                if(version.equalsIgnoreCase(OGCConstants.WFS_VERSION_100)){
                    um = new Unmarshaller(nl.b3p.xml.wfs.v100.FeatureCollection.class);
                    o = um.unmarshal(rootElement);              
                    nl.b3p.xml.wfs.v100.FeatureCollection featureCollection = (nl.b3p.xml.wfs.v100.FeatureCollection)o;
                    
                    newFeatureCollectionV100 = replaceFeatureCollectionV100Url(featureCollection);
                }else{
                    um = new Unmarshaller(nl.b3p.xml.wfs.v110.FeatureCollection.class);
                    o = um.unmarshal(rootElement);
                    nl.b3p.xml.wfs.v110.FeatureCollection featureCollection = (nl.b3p.xml.wfs.v110.FeatureCollection)o;
                    
                    newFeatureCollectionV110 = replaceFeatureCollectionV110Url(featureCollection);
                }
            }
        }catch (Exception e){
            throw new UnsupportedOperationException("Failed to replace Url's! Excepiton: " + e);
        }
    }
    
    public nl.b3p.xml.wfs.v100.capabilities.WFS_Capabilities replaceCapabilitiesV100Url(nl.b3p.xml.wfs.v100.capabilities.WFS_Capabilities wfsCapabilities){
        wfsCapabilities.getService().setOnlineResource(httpHost);
        int requestCount = wfsCapabilities.getCapability().getRequest().getRequestTypeItemCount();
        for(int i = 0; i < requestCount; i++){
            RequestTypeItem requestItem = wfsCapabilities.getCapability().getRequest().getRequestTypeItem(i);
            if(requestItem.getDescribeFeatureType() != null){
                int dcpCount = requestItem.getDescribeFeatureType().getDCPType_DescribeFeatureTypeTypeCount();
                for(int x = 0; x < dcpCount; x++){
                    DCPType_DescribeFeatureTypeType dcp = requestItem.getDescribeFeatureType().getDCPType_DescribeFeatureTypeType(x);
                    int httpItemCount = dcp.getHTTP().getHTTPTypeItemCount();
                    for(int y = 0; y < httpItemCount; y++){
                        replaceUrl(dcp.getHTTP().getHTTPTypeItem(y));
                    }
                }
            }else if(requestItem.getGetCapabilities() != null){
                int dcpCount = requestItem.getGetCapabilities().getDCPType_GetCapabilitiesTypeCount();
                for(int x = 0; x < dcpCount; x++){
                    DCPType_GetCapabilitiesType dcp = requestItem.getGetCapabilities().getDCPType_GetCapabilitiesType(x);
                    int httpItemCount = dcp.getHTTP().getHTTPTypeItemCount();
                    for(int y = 0; y < httpItemCount; y++){
                        replaceUrl(dcp.getHTTP().getHTTPTypeItem(y));
                    }
                }
            }else if(requestItem.getGetFeature() != null){
                int dcpCount = requestItem.getGetFeature().getDCPType_FeatureTypeTypeCount();
                for(int x = 0; x < dcpCount; x++){
                    DCPType_FeatureTypeType dcp = requestItem.getGetFeature().getDCPType_FeatureTypeType(x);
                    int httpItemCount = dcp.getHTTP().getHTTPTypeItemCount();
                    for(int y = 0; y < httpItemCount; y++){
                        replaceUrl(dcp.getHTTP().getHTTPTypeItem(y));
                    }
                }
            }else if(requestItem.getGetFeatureWithLock() != null){
                int dcpCount = requestItem.getGetFeatureWithLock().getDCPType_FeatureTypeTypeCount();
                for(int x = 0; x < dcpCount; x++){
                    DCPType_FeatureTypeType dcp = requestItem.getGetFeatureWithLock().getDCPType_FeatureTypeType(x);
                    int httpItemCount = dcp.getHTTP().getHTTPTypeItemCount();
                    for(int y = 0; y < httpItemCount; y++){
                        replaceUrl(dcp.getHTTP().getHTTPTypeItem(y));
                    }
                }
            }else if(requestItem.getLockFeature() != null){
                int dcpCount = requestItem.getLockFeature().getDCPTypeCount();
                for(int x = 0; x < dcpCount; x++){
                    DCPType dcp = requestItem.getLockFeature().getDCPType(x);
                    int httpItemCount = dcp.getHTTP().getHTTPTypeItemCount();
                    for(int y = 0; y < httpItemCount; y++){
                        replaceUrl(dcp.getHTTP().getHTTPTypeItem(y));
                    }
                }
            }else if(requestItem.getTransaction() != null){
                int dcpCount = requestItem.getTransaction().getDCPType_TransactionTypeCount();
                for(int x = 0; x < dcpCount; x++){
                    DCPType_TransactionType dcp = requestItem.getTransaction().getDCPType_TransactionType(x);
                    int httpItemCount = dcp.getHTTP().getHTTPTypeItemCount();
                    for(int y = 0; y < httpItemCount; y++){
                        replaceUrl(dcp.getHTTP().getHTTPTypeItem(y));
                    }
                }
            }
        }
        return wfsCapabilities;
    }
    public void replaceUrl(HTTPTypeItem httpItem){
        if(httpItem.getGet() != null){
            httpItem.getGet().setOnlineResource(httpHost);
        }else if(httpItem.getPost() != null){
            httpItem.getPost().setOnlineResource(httpHost);
        }
    }
    
    public nl.b3p.xml.wfs.v110.WFS_Capabilities replaceCapabilitiesV110Url (nl.b3p.xml.wfs.v110.WFS_Capabilities wfsCapabilities){
        int operationCount = wfsCapabilities.getOperationsMetadata().getOperationCount();
        for(int i = 0; i < operationCount; i++){
            Operation operation = wfsCapabilities.getOperationsMetadata().getOperation(i);
            int dcpCount = operation.getDCPCount();
            for(int n = 0; n < dcpCount; n++){
                DCP dcp = operation.getDCP(n);
                HTTP http = dcp.getHTTP();
                int httpCount = http.getHTTPItemCount();
                for(int x = 0; x < httpCount; x++){
                    if(http.getHTTPItem(x).getGet() != null){
                        http.getHTTPItem(x).getGet().setHref(httpHost + "?");
                    }
                    else if(http.getHTTPItem(x).getPost() != null){
                        http.getHTTPItem(x).getPost().setHref(httpHost);
                    }
                }
            }
        }
        wfsCapabilities.getServiceProvider().getServiceContact().getContactInfo().getOnlineResource().setHref(httpHost);
        return wfsCapabilities;
    }
    
    public nl.b3p.xml.wfs.v100.FeatureCollection replaceFeatureCollectionV100Url(nl.b3p.xml.wfs.v100.FeatureCollection featureCollection){
        String newSchemalocations = "";
        if(schemaLocations != null){
            Set keys = schemaLocations.keySet();
            Iterator it = keys.iterator();
            for (int i = 0; it.hasNext(); i++) {
                String prefix = (String) it.next();
                String location = (String) schemaLocations.get(prefix);
                String[] tokens = location.split(" ");
                for(int x = 0; x < tokens.length; x++){
                    String[] token = tokens[x].split("\\?", 2);
                    if(token.length == 2){
                        String test1 = token[0];
                        String test2 = token[1];
                        if(x != 0){
                            newSchemalocations = newSchemalocations + " " + httpHost + "?" + token[1];
                        }else{
                            newSchemalocations = newSchemalocations + httpHost + "?" + token[1];
                        }
                    }else{
                        if(x != 0){
                            newSchemalocations = newSchemalocations + " " + token[0];
                        }else{
                            newSchemalocations = newSchemalocations + token[0];
                        }
                    }
                }
                addOrReplaceSchemaLocation(prefix,newSchemalocations);
            }
        }
        return featureCollection;
    }
    
    public nl.b3p.xml.wfs.v110.FeatureCollection replaceFeatureCollectionV110Url(nl.b3p.xml.wfs.v110.FeatureCollection featureCollection){
        String newSchemalocations = "";
        if(schemaLocations != null){
            Set keys = schemaLocations.keySet();
            Iterator it = keys.iterator();
            for (int i = 0; it.hasNext(); i++) {
                String prefix = (String) it.next();
                String location = (String) schemaLocations.get(prefix);
                String[] tokens = location.split(" ");
                for(int x = 0; x < tokens.length; x++){
                    String[] token = tokens[x].split("\\?", 2);
                    if(token.length == 2){
                        String test1 = token[0];
                        String test2 = token[1];
                        if(x != 0){
                            newSchemalocations = newSchemalocations + " " + httpHost + "?" + token[1];
                        }else{
                            newSchemalocations = newSchemalocations + httpHost + "?" + token[1];
                        }
                    }else{
                        if(x != 0){
                            newSchemalocations = newSchemalocations + " " + token[0];
                        }else{
                            newSchemalocations = newSchemalocations + token[0];
                        }
                    }
                }
                addOrReplaceSchemaLocation(prefix,newSchemalocations);
            }
        }
        return featureCollection;
    }
    
    public String getResponseBody(){
        String body = null;
        Object castorObject = null;
        
        if(response == null || response.length() <= 0){
            return body;
        }
        if(response == OGCConstants.WFS_CAPABILITIES){
            if(version == OGCConstants.WFS_VERSION_100){
                castorObject = newWfsCapabilitiesV100;
            }else{
                castorObject = newWfsCapabilitiesV110;
            }
        }else if(response == OGCConstants.WFS_FEATURECOLLECTION){
            if(version == OGCConstants.WFS_VERSION_100){
                castorObject = newFeatureCollectionV100;
            }else{
                castorObject = newFeatureCollectionV110;
            }
        }
        
        try{
            StringWriter sw = new StringWriter();
            Marshaller m = new Marshaller(sw);
            
            if(nameSpaces != null){
                Set keys = nameSpaces.keySet();
                Iterator it = keys.iterator();
                for (int i = 0; it.hasNext(); i++) {
                    String prefix = (String) it.next();
                    String location = (String) nameSpaces.get(prefix);
                    m.setNamespaceMapping(prefix, location);
                }
            }
            if(schemaLocations != null){
                Set keys = schemaLocations.keySet();
                Iterator it = keys.iterator();
                for (int i = 0; it.hasNext(); i++) {
                    String prefix = (String) it.next();
                    String location = (String) schemaLocations.get(prefix);
                    m.setSchemaLocation(location);
                }
            }

            if(castorObject != null){
                m.marshal(castorObject);
                body = sw.toString();
            }
        }catch(Exception e){
            throw new UnsupportedOperationException("Failed to get body of XML!");
        }
        
        return body;
    }
    
    public void findNameSpace(Node node){ 
        NamedNodeMap map = node.getAttributes();
        if(map != null){
            for(int i = 0; map.getLength() > i; i++){          
                String name = map.item(i).getNodeName();
                String[] tokens = name.split(":");
                if(tokens[0].equalsIgnoreCase("xmlns")){
                    String value = map.item(i).getNodeValue();
                    addOrReplaceNameSpace(tokens[1],value);
                }
                else if(tokens.length == 2){
                    if(tokens[1].equalsIgnoreCase("SchemaLocation")){
                        String value = map.item(i).getNodeValue();
                        addOrReplaceSchemaLocation(tokens[1],value);
                    }
                }
            }
        }
        if(node.hasChildNodes()){
            NodeList lijst = node.getChildNodes();
            for(int i = 0; i < lijst.getLength(); i++){
                findNameSpace(lijst.item(i));
            }
        }
    }
    public void addOrReplaceNameSpace(String prefix, String nsUrl) {
        if (prefix != null && nsUrl != null) {
            if (nameSpaces == null) {
                nameSpaces = new HashMap();
            }
            nameSpaces.put(prefix, nsUrl);
        }
    }
    public void addOrReplaceSchemaLocation(String prefix, String location) {
        if (prefix != null && location != null) {
            if (schemaLocations == null) {
                schemaLocations = new HashMap();
            }
            schemaLocations.put(prefix, location);
        }
    }
}