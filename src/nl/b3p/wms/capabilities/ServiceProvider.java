/**
 * @(#)ServiceProvider.java
 * @author N. de Goeij
 * @version 1.00 2006/10/11
 *
 * Purpose: Bean representing a ServiceProvider.
 *
 * @copyright 2007 All rights reserved. B3Partners
 */

package nl.b3p.wms.capabilities;

import java.util.Date;
import java.util.Hashtable;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import javax.persistence.EntityManager;

public class ServiceProvider implements XMLElement, KBConstants {
    
    private Integer id;
    private String name;
    private String title;
    private String abstracts;
    private String fees;
    private String accessConstraints;
    private String givenName;
    private String url;
    private String wmsVersion;
    private Date updatedDate;
    private boolean reviewed;
    private ContactInformation contactInformation;
    private Set domainResource;
    private Set serviceProviderKeywordList;
    private Set exceptions;
    private Set layers;
    private Layer topLayer;
    private Set allRoles;
    private boolean isSynchronized = false;
    
    /** default ServiceProvider() constructor.
     */
    // <editor-fold defaultstate="" desc="default ServiceProvider() constructor">
    public ServiceProvider() {
        name                = SERVICEPROVIDER_NAME;
        title               = SERVICEPROVIDER_TITLE;
        abstracts           = SERVICEPROVIDER_ABSTRACT;
        fees                = SERVICEPROVIDER_FEES;
        accessConstraints   = SERVICEPROVIDER_ACCESSCONSTRAINTS;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="" desc="getter and setter methods.">
    public Integer getId() {
        return id;
    }
    
    private void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAbstracts() {
        return abstracts;
    }
    
    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }
    
    public String getFees() {
        return fees;
    }
    
    public void setFees(String fees) {
        this.fees = fees;
    }
    
    public String getAccessConstraints() {
        return accessConstraints;
    }
    
    public void setAccessConstraints(String accessConstraints) {
        this.accessConstraints = accessConstraints;
    }
    
    public String getGivenName() {
        return givenName;
    }
    
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public Date getUpdatedDate() {
        return updatedDate;
    }
    
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    public boolean isReviewed() {
        return reviewed;
    }
    
    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }
    
    public ContactInformation getContactInformation() {
        return contactInformation;
    }
    
    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
        if(contactInformation != null)
            contactInformation.setServiceProvider(this);
    }
    
    public Set getExceptions() {
        return exceptions;
    }
    
    public void setExceptions(Set exceptions) {
        this.exceptions = exceptions;
    }
    
    public void addException(String except) {
        if (exceptions == null) {
            exceptions = new HashSet();
        }
        exceptions.add(except);
    }
    
    public Set getDomainResource() {
        return domainResource;
    }
    
    public void setDomainResource(Set domainResource) {
        this.domainResource = domainResource;
    }
    
    public void addDomainResource(ServiceDomainResource dr) {
        if (domainResource == null) {
            domainResource = new HashSet();
        }
        domainResource.add(dr);
        dr.setServiceProvider(this);
    }
    
    public void setAllRoles(Set allRoles) {
        this.allRoles = allRoles;
    }
    
    public Set getAllRoles() {
        return allRoles;
    }
    
    public void addRole(Roles role) {
        if(allRoles == null) {
            allRoles = new HashSet();
        }
        allRoles.add(role);
    }
    
    public Set getAllLayers() {
        if (!isIsSynchronized()) {
            synchronizeServiceProvider();
        }
        return layers;
    }
    
    private void synchronizeServiceProvider() {
        if (topLayer != null) {
            setLayers(topLayer.buildLayerSet(null, this));
            setIsSynchronized(true);
        } else {
            topLayer = defineTopLayer();
            if (topLayer!=null)
                topLayer.buildLayerTree(layers);
            setIsSynchronized(true);
        }
    }
    
    private Layer defineTopLayer() {
        if (layers==null)
            return null;
        Iterator it = layers.iterator();
        Layer parent = null;
        while (it.hasNext()) {
            Layer layer = (Layer)it.next();
            if(layer.getParent() == null) {
                if(parent != null) {
                    throw new Error("inconsistente database, meerdere top layers! service provider id " + getId());
                }
                parent = layer;
            }
        }
        return parent;
    }
    
    //Er zijn drie opties
    //-er is een set aanwezig --> nog geen boomstructuur
    //-er is een boom aanwezig --> nog geen setstructuur
    //-er is een boom aanwezig --> oude setstructuur
    //bij deze laatste geldt eigenlijk dat de set aangepast wordt aan de nieuwe boomstructuur.....
    
    public Layer getTopLayer() {
        if(!isIsSynchronized()) {
            synchronizeServiceProvider();
        }
        return topLayer;
    }
    
    public void setTopLayer(Layer topLayer) {
        setIsSynchronized(false);
        this.topLayer = topLayer;
        synchronizeServiceProvider();
    }
    
    public Set getServiceProviderKeywordList() {
        return serviceProviderKeywordList;
    }
    
    public void setServiceProviderKeywordList(Set serviceProviderKeywordList) {
        this.serviceProviderKeywordList = serviceProviderKeywordList;
    }
    
    public void addKeyword(String keyword) {
        if(null == serviceProviderKeywordList) {
            serviceProviderKeywordList = new HashSet();
        }
        serviceProviderKeywordList.add(keyword);
    }
    // </editor-fold>
    
    /** Method that will overwrite the URL's stored in the database with the URL specified for Kaartenbalie.
     * This new URL indicate the link to the kaartenbalie, while the old link is used to indicate the URL
     * to the real location of the service. Because the client which is connected to kaartenbalie has to send
     * his requests back to kaartenbalie and not directly to the official resource, the URL has to be replaced.
     *
     * @param newUrl String representing the URL the old URL has to be replaced with.
     */
    // <editor-fold defaultstate="" desc="overwriteURL(String newUrl) method">
    public void overwriteURL(String newUrl) {
        this.url = newUrl;
        
        if (domainResource!=null) {
            Iterator it = domainResource.iterator();
            while (it.hasNext()) {
                ServiceDomainResource sdr = (ServiceDomainResource)it.next();
                sdr.overwriteURL(newUrl);
            }
        }
        Layer tl = getTopLayer();
        if (tl!=null)
            tl.overwriteURL(newUrl);
    }
    // </editor-fold>
    
    
    /** Method that will create piece of the XML tree to create a proper XML docuement.
     *
     * @param doc Document object which is being used to create new Elements
     * @param rootElement The element where this object belongs to.
     *
     * @return an object of type Element
     */
    // <editor-fold defaultstate="" desc="toElement(Document doc, Element rootElement) method">
    public Element toElement(Document doc, Element rootElement) {
        
        Element serviceElement = doc.createElement("Service");
        
        if(null != this.getName()) {
            Element nameElement = doc.createElement("Name");
            Text text = doc.createTextNode(this.getName());
            nameElement.appendChild(text);
            serviceElement.appendChild(nameElement);
        }
        
        if (null != this.getTitle()) {
            Element titleElement = doc.createElement("Title");
            Text text = doc.createTextNode(this.getTitle());
            titleElement.appendChild(text);
            serviceElement.appendChild(titleElement);
        }
        if (null != this.getAbstracts()) {
            Element abstractElement = doc.createElement("Abstract");
            Text text = doc.createTextNode(this.getAbstracts());
            abstractElement.appendChild(text);
            serviceElement.appendChild(abstractElement);
        }
        
        if(null != this.getServiceProviderKeywordList() && this.getServiceProviderKeywordList().size() != 0) {
            Element keywordListElement = doc.createElement("KeywordList");
            
            Iterator it = this.getServiceProviderKeywordList().iterator();
            while (it.hasNext()) {
                String keyword = (String)it.next();
                Element keywordElement = doc.createElement("Keyword");
                Text text = doc.createTextNode(keyword);
                keywordElement.appendChild(text);
                keywordListElement.appendChild(keywordElement);
            }
            
            serviceElement.appendChild(keywordListElement);
        }
        
        if (null != this.getUrl()) {
            Element onlineElement = doc.createElement("OnlineResource");
            onlineElement.setAttribute("xlink:href", this.getUrl());
            onlineElement.setAttribute("xlink:type", "simple");
            onlineElement.setAttribute("xmlns:xlink", "http://www.w3.org/1999/xlink");
            serviceElement.appendChild(onlineElement);
        }
        
        if (null != this.getContactInformation()) {
            serviceElement = this.getContactInformation().toElement(doc, serviceElement);
        }
        
        if (null != this.getFees()) {
            Element feesElement = doc.createElement("Fees");
            Text text = doc.createTextNode(this.getFees());
            feesElement.appendChild(text);
            serviceElement.appendChild(feesElement);
        }
        if (null != this.getAccessConstraints()) {
            Element accessElement = doc.createElement("AccessConstraints");
            Text text = doc.createTextNode(this.getAccessConstraints());
            accessElement.appendChild(text);
            serviceElement.appendChild(accessElement);
        }
        //End of Service
        
        //Start of Capability
        Element capabilityElement = doc.createElement("Capability");
        
        //De verschillende request mogelijkheden....
        Element requestElement = doc.createElement("Request");
        
        if (domainResource!=null) {
            Hashtable sdrhash = new Hashtable();
            ServiceDomainResource sdr = null;
            Iterator it = domainResource.iterator();
            while (it.hasNext()) {
                sdr = (ServiceDomainResource)it.next();
                sdrhash.put(sdr.getDomain(),sdr);
            }
            
            sdr = (ServiceDomainResource) sdrhash.get("GetCapabilities");
            if (sdr!=null)
                requestElement = sdr.toElement(doc, requestElement);
            sdr = (ServiceDomainResource) sdrhash.get("GetMap");
            if (sdr!=null)
                requestElement = sdr.toElement(doc, requestElement);
            sdr = (ServiceDomainResource) sdrhash.get("GetFeatureInfo");
            if (sdr!=null)
                requestElement = sdr.toElement(doc, requestElement);
            sdr = (ServiceDomainResource) sdrhash.get("DescribeLayer");
            if (sdr!=null)
                requestElement = sdr.toElement(doc, requestElement);
            sdr = (ServiceDomainResource) sdrhash.get("GetLegendGraphic");
            if (sdr!=null)
                requestElement = sdr.toElement(doc, requestElement);
            sdr = (ServiceDomainResource) sdrhash.get("GetStyles");
            if (sdr!=null)
                requestElement = sdr.toElement(doc, requestElement);
            capabilityElement.appendChild(requestElement);
        }
        
        Element exceptionElement = doc.createElement("Exception");
        if (exceptions!=null) {
            //De exception formaten
            Iterator it = exceptions.iterator();
            while (it.hasNext()) {
                Element formatElement = doc.createElement("Format");
                Text text = doc.createTextNode((String)it.next());
                formatElement.appendChild(text);
                exceptionElement.appendChild(formatElement);
            }
        }
        capabilityElement.appendChild(exceptionElement);
        
        //Vendor specifieke elementen
        Element vendorSpecificElement = doc.createElement("VendorSpecificCapabilities");
        if(getAllRoles() != null && !getAllRoles().isEmpty()) {
            Iterator it = getAllRoles().iterator();
            while (it.hasNext()) {
                Roles role = (Roles) it.next();
                Element roleElement = doc.createElement("Role");
                roleElement.setAttribute("id", role.getRole());
                vendorSpecificElement.appendChild(roleElement);
            }
        } else {
            Element roleElement = doc.createElement("Role");
            roleElement.setAttribute("id", KB_DEFAULT_ROLE);
            vendorSpecificElement.appendChild(roleElement);
        }
        capabilityElement.appendChild(vendorSpecificElement);
        
        //De beschikbare layers.
        if (topLayer!=null)
            capabilityElement = topLayer.toElement(doc, capabilityElement);
        
        //End of Capability
        
        rootElement.appendChild(serviceElement);
        rootElement.appendChild(capabilityElement);
        return rootElement;
    }
    // </editor-fold>
    
    public String getWmsVersion() {
        return wmsVersion;
    }
    
    public void setWmsVersion(String wmsVersion) {
        this.wmsVersion = wmsVersion;
    }
    
    private Set getLayers() {
        return layers;
    }
    
    public void setLayers(Set layers) {
        this.layers = layers;
        setIsSynchronized(false);
    }
    
    public boolean isIsSynchronized() {
        return isSynchronized;
    }
    
    public void setIsSynchronized(boolean isSynchronized) {
        this.isSynchronized = isSynchronized;
    }
    
    public void copyElements(ServiceProvider sp, EntityManager em) {
        Set oldlayers = this.getAllLayers();
        Set newlayers = sp.getAllLayers();
        this.setLayers(compareLayerSets(oldlayers, newlayers, em));
        
        Set oldDomain = this.getDomainResource();
        Set newDomain = sp.getDomainResource();
        this.setDomainResource(compareDomainSets(oldDomain, newDomain, em));
        
        this.setName(sp.getName());
        this.setTitle(sp.getTitle());
        this.setAbstracts(sp.getAbstracts());
        this.setFees(sp.getFees());
        this.setAccessConstraints(sp.getAccessConstraints());
        this.setGivenName(sp.getGivenName());
        this.setUrl(sp.getUrl());
        this.setWmsVersion(sp.getWmsVersion());
        if(this.getContactInformation() != null && sp.getContactInformation() != null)
            this.getContactInformation().copyElements(sp.getContactInformation());
    }
    
    private Set compareLayerSets(Set oldset, Set newset, EntityManager em) {
        Set tempRemoveSet = new HashSet();
        Iterator it = oldset.iterator();
        while (it.hasNext()) {
            Layer layer = (Layer) it.next();
            if(!layer.inList(newset)) {
                tempRemoveSet.add(layer);
            }
        }

        Iterator removeIt = tempRemoveSet.iterator();
        while (removeIt.hasNext()) {
            Layer removableLayer = (Layer) removeIt.next();
            oldset.remove(removableLayer);
            Layer parent = removableLayer.getParent();
            removeChildren(removableLayer, oldset);
            if (parent != null) {
                parent.getLayers().remove(removableLayer);
            }
            em.remove(removableLayer);
        }

        Iterator newit = newset.iterator();
        //eerst een boomstructuur maken waarbij de 
        //hoogste layers uiteindelijk in de set worden teruggegeven
        //deze vervolgens in een nieuwe set teruggeven
        //deze set vervolgens overheen lopen en toevoegen aan de parent
        //waar deze layer bij hoort...
        //Of misschien is het mogelijk om een aparte lijst met layers te creeren
        //die echt nieuw zijn, van deze layers vervolgens 
        
        //Er moet iets van een methode bedacht worden waarmee de sturtuuc opnieuw in
        //de set aangebracht wordt. De nieuwe set heeft natuurlijk wel een boomstructuur
        //maar de hoogste parent uit een nieuwe set met layers is nog steeds gekoppeld aan
        //een parent van de nieuwe serviceprovider, terwijl daar dus de relatie gelegd moet
        //worden met de oude parent layer.....
        //oftewel, de parents staan niet goed afgestemd op elkaar....
        //hetzelfde geldt waarschijnlijk voor de andere kant, bij het toevoegen aan de set
        //van de oude parent layer
        
        //Er hoeft dus alleen een methode geschreven te worden die van iedere parent in de huidige
        //layer bekijkt of deze parent gelijk is aan de parent in het oude sp object. Zoniet, dan
        //moet deze alsnog opgezocht worden en deze layer aan die parent toegevoegd worden.
        //Dus neem een layer:
        //-controleer of de parent verwijst naar een layer in de nieuwe set (dan child van een
        // layer die zelf ook nieuw is) -> verwijzingen staan dan goed
        //-anders zoek de parent uit de oude set die overeenkomt met de parent uit de nieuwe
        // en voeg deze layer aan die set toe
        //waarschijnlijk kan bovenstaande eenvoudiger door alleen te controleren of de parent van
        //de layer gelijk is aan een layer in de oldset, zoniet dan bestaat de parent van deze layer
        //niet in de oldset en is die parent zelf ook een nieuwe layer...
        
        while (newit.hasNext()) {
            Layer layer = (Layer) newit.next();
            layer.setServiceProvider(this);
            if(!layer.inList(oldset)) {
                Iterator oldsetit = oldset.iterator();
                while (oldsetit.hasNext()) {
                    Layer setlayer = (Layer)oldsetit.next();
                    Layer parent = layer.getParent();
                    if(parent != null) {
                        if (setlayer.equals(layer.getParent())) {
                            //parent layer bestaat in oldset
                            setlayer.addLayer(layer);
                        }
                    }
                }
                oldset.add(layer);
            }
        }
        return oldset;
    }
    
    private void removeChildren(Layer layerInQuestion, Set oldSet) {
        Set childLayers = layerInQuestion.getLayers();
        Iterator it = childLayers.iterator();
        while (it.hasNext()) {
            Layer childLayer = (Layer) it.next();
            if(!childLayer.getLayers().isEmpty()) {
                removeChildren(childLayer, oldSet);
                if(oldSet.contains(childLayer)) {
                    oldSet.remove(childLayer);
                }
            }                
        }        
    }    
    
    private Set compareDomainSets(Set oldset, Set newset, EntityManager em) {
        Set tempRemoveSet = new HashSet();
        Iterator it = oldset.iterator();
        while (it.hasNext()) {
            ServiceDomainResource sdr = (ServiceDomainResource) it.next();
            if(!sdr.inList(newset)) {
                tempRemoveSet.add(sdr);
            }
        }

        Iterator removeIt = tempRemoveSet.iterator();
        while (removeIt.hasNext()) {
            ServiceDomainResource removableSdr = (ServiceDomainResource) removeIt.next();
            oldset.remove(removableSdr);
            //em.remove(removableSdr);
        }

        Iterator newit = newset.iterator();
        while (newit.hasNext()) {
            ServiceDomainResource sdr = (ServiceDomainResource) newit.next();
            if(!sdr.inList(oldset)) {
                //if (sdr.getId() == null) {
                    //sdr.setServiceProvider(this);
                    //em.persist(sdr);
                //}
                oldset.add(sdr);
            }
        }
        return oldset;
    }
}