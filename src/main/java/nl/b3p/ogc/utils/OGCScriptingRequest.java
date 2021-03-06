/*
 * B3P Kaartenbalie is a OGC WMS/WFS proxy that adds functionality
 * for authentication/authorization, pricing and usage reporting.
 *
 * Copyright 2006, 2007, 2008 B3Partners BV
 * 
 * This file is part of B3P Kaartenbalie.
 * 
 * B3P Kaartenbalie is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * B3P Kaartenbalie is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with B3P Kaartenbalie.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * @author Rachelle Scheijen
 */
package nl.b3p.ogc.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import nl.b3p.xml.wfs.WFS_Capabilities;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.w3c.dom.Element;

public class OGCScriptingRequest extends OGCRequest {
    public static final String COMMAND = "command";
    public static final String LOCATION = "location";
    public static final String UPDATE_SERVICES = "update_services";
    public static final String SERVICE_TYPE = "service_type";
    public static final String ABBR = "abbr";
    public static final String URL = "url";
    public static final String SLD = "sld";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ADD_SERVICE = "add_service";
    public static final String GROUPS = "groups";
    public static final String NAME = "name";
    public static final String UPDATE = "update";
    public static final String ADD_ALLOWED_SERVICES = "add_allowed_services";
    public static final String DELETE_ALLOWED_SERVICES = "delete_allowed_services";
    public static final String DELETE_ALL_ALLOWED_SERVICES = "delete_all_allowed_services";
    public static final String GET_GROUP_XML = "get_group_xml";
    
    
    protected HashSet<String> acceptable;
    
    /*Default constr.*/
    public OGCScriptingRequest() {
        super();
    }
    
    /** Main constructor
     *
     * @param url The url that fills the OGCUrl object
     *
     *  For HTTP GET
     */
    public OGCScriptingRequest(String url) {
        super(url);
    }
    
    private void populateAcceptable(){
        acceptable   = new HashSet();
        acceptable.add(OGCScriptingRequest.UPDATE_SERVICES);
    }
    
    /**
     * Not supported in OGScriptingRequest
     */
    public static String removeNamespace(String tagName) {
        return "Not supported";
    }
    
    /**
     * Not supported in OGScriptingRequest
     */
    @Override
    public LayerSummary splitLayerInParts(String fullLayerName) {
        return splitLayerInParts(fullLayerName, true, null, null);
    }

    /**
     * Not supported in OGScriptingRequest
     */
    @Override
    public LayerSummary splitLayerInParts(String fullLayerName, boolean splitName, String defaultSp, String defaultNs) {
        LayerSummary returnMap = new LayerSummary();
        returnMap.setError("Not supported");
        return returnMap;
    }    
        
    /**
     * Constructor
     * For HTTP POST
     */
    public OGCScriptingRequest(Element rootElement, String url) throws ValidationException, Exception {
        parameters = new HashMap();
        setUrl(url);
        
        Unmarshaller um;
        Object o;

        setFinalVersion(rootElement.getAttribute(OGCConstants.VERSION.toLowerCase()));
        String version = finalVersion;
    }
    
    /**
     * Not supported in OGScriptingRequest
     */
    @Override
    public String getXMLBody() throws Exception {
        throw new Exception("Not supported");
    }
    
    @Override
    public String getUrlWithNonOGCparams() {
        OGCScriptingRequest ogcu = (OGCScriptingRequest) this.clone();
        return ogcu.getUrl();
    }
    
    /*
     * Returns all nonOGC params
     */
    @Override
    public HashMap getNonOGCParameters() {
        OGCScriptingRequest o = (OGCScriptingRequest) this.clone();
        
        return o.getParameters();
    }
    
    
    
    /** Adds or replaces a param with a value
     *
     * @param param The definition of the param that needs to be added or replaced.
     * @param value The value that needs to be added
     *
     * @return previous value associated with specified param, or null  if there was no mapping for param
     */
    @Override
    public String addOrReplaceParameter(String param, String v) {
        String value;
        try {
            if (v != null) {
                value = URLDecoder.decode(v, "UTF-8");
            } else {
                value = v;
            }
        } catch (UnsupportedEncodingException ex) {
            value = v;
            log.error("Error deconding value. Try with the original value", ex);
        }
        if (param == null) {
            return null;
        }
        param = param.toUpperCase();
        Object o = parameters.get(param);
        if (value != null) {
            value = value.trim();
        }
        if (WFS_PARAM_TYPENAME.equals(param)) {
            int index1 = param.indexOf("{");
            int index2 = param.indexOf("}");
            if (index1 >= 0 && index2 >= 0) {
                String nameSpaceUri = param.substring(index1 + 1, index2);
                param = ":" + param.substring(index2 + 1);
            }
        }
        parameters.put(param, value);
        if (o == null) {
            return null;
        }
        return (String) o;
    }
    
    /** 
     * Removes all WMS parameters (version 1.1.1)
     * Not supported in OGScriptingRequest
     */
    @Override
    public void removeAllWMSParameters() {        
    }
    
     /** 
      * Removes all WFS parameters (version 1.0.0???)
      * Not supported in OGScriptingRequest
      */
    @Override
    public void removeAllWFSParameters() {        
    }
    
    /**
     * Not supported in OGScriptingRequest
     */
    @Override
    public String[] getNameSpacesArray() {
        return null;
    }
    
    /**
     * Not supported in OGScriptingRequest
     */
    @Override
    public void addOrReplaceNameSpace(String prefix, String nsUrl) {
    }
    
    /**
     * Not supported in OGScriptingRequest
     */
    @Override
    public String[] getSchemaLocationsArray() {
        return null;
    }
    
    /**
     * Not supported in OGScriptingRequest
     */
    @Override
    public void addOrReplaceSchemaLocation(String prefix, String location) {
    }
    
    @Override
    public Object clone() {
        OGCScriptingRequest returnv = new OGCScriptingRequest();
        returnv.fixHttpHost(new String(this.getHttpHost()));
        if (getPassword() != null) {
            returnv.setPassword(new String(this.getPassword()));
        }
        if (getUsername() != null) {
            returnv.setUsername(new String(this.getUsername()));
        }
        if (this.getFinalVersion() != null) {
            returnv.setFinalVersion(new String(this.getFinalVersion()));
        }
        if (this.getParameters() != null) {
            returnv.setParameters((HashMap) this.getParameters().clone());
        }
        if (this.getTransactionMap() != null) {
            returnv.setTransactionMap((HashMap) this.getTransactionMap().clone());
        }
        if (this.getAbbr() != null) {
            returnv.setAbbr(new String(this.getAbbr()));
        }

        if (this.getHttpMethod() != null) {
            returnv.setHttpMethod(new String(this.getHttpMethod()));
        }
        if (this.getGetFeatureFilterMap() != null) {
            returnv.setGetFeatureFilterMap((HashMap) this.getGetFeatureFilterMap().clone());
        }

        return returnv;
    }
    
    /**
     * Not supported in OGScriptingRequest
     */
    @Override
    public HashMap getNameSpaces() {
        return null;
    }
    
    /**
     * Not supported in OGScriptingRequest
     */
    @Override
    public HashMap getSchemaLocations() {
        return null;
    }

    /*
     * Checks if the url contains SERVICE_TYPE and COMMAND parameters, because these are required for a scripting request.
     */
    @Override
    public void checkRequestURL() throws Exception {
        if (parameters == null) {
            throw new UnsupportedOperationException("No parameters found in url!");
        }
        String serviceType = null;
        if (containsParameter(SERVICE_TYPE)) {
            serviceType = getParameter(SERVICE_TYPE);
            if (serviceType == null || serviceType.equals("")) {
                throw new UnsupportedOperationException("Empty serviceType parameter found in url!");
            }
        }

        if (containsParameter(COMMAND)) {
            String command = getParameter(COMMAND);
            if (command == null || command.equals("")) {
                throw new UnsupportedOperationException("Empty command parameter found in url!");
            }
        } else {
            throw new UnsupportedOperationException("No command parameter found!");
        }
    }
    
    /**
     * Not supported in OGScriptingRequest
     */
    @Override
    public WFS_Capabilities getCapabilities() {
        return null;
    }

    /**
     * Not supported in OGScriptingRequest
     */
    @Override
    public void setCapabilities(WFS_Capabilities capabilities) {
    }
    
    /**
     * Not supported in OGScriptingRequest
     */
    @Override
    public String getNameSpace(String param) {
        return null;
    }

    /**
     * Not supported in OGScriptingRequest
     */
    @Override
    public String getNameSpacePrefix(String namespaceUrl) {
        return null;
    }
    
    /**
     * @param layers the layers to set
     * Not supported in OGScriptingRequest
     */
    @Override
    public void setLayers(ArrayList layers) {
    }
}
