/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.2</a>, using an XML
 * Schema.
 * $Id$
 */

package nl.b3p.xml.ogc.v110;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Spatial_CapabilitiesType.
 * 
 * @version $Revision$ $Date$
 */
public class Spatial_CapabilitiesType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _geometryOperands
     */
    private nl.b3p.xml.ogc.v110.GeometryOperands _geometryOperands;

    /**
     * Field _spatialOperators
     */
    private nl.b3p.xml.ogc.v110.SpatialOperators _spatialOperators;


      //----------------/
     //- Constructors -/
    //----------------/

    public Spatial_CapabilitiesType() 
     {
        super();
    } //-- nl.b3p.xml.ogc.v110.Spatial_CapabilitiesType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'geometryOperands'.
     * 
     * @return GeometryOperands
     * @return the value of field 'geometryOperands'.
     */
    public nl.b3p.xml.ogc.v110.GeometryOperands getGeometryOperands()
    {
        return this._geometryOperands;
    } //-- nl.b3p.xml.ogc.v110.GeometryOperands getGeometryOperands() 

    /**
     * Returns the value of field 'spatialOperators'.
     * 
     * @return SpatialOperators
     * @return the value of field 'spatialOperators'.
     */
    public nl.b3p.xml.ogc.v110.SpatialOperators getSpatialOperators()
    {
        return this._spatialOperators;
    } //-- nl.b3p.xml.ogc.v110.SpatialOperators getSpatialOperators() 

    /**
     * Method isValid
     * 
     * 
     * 
     * @return boolean
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Sets the value of field 'geometryOperands'.
     * 
     * @param geometryOperands the value of field 'geometryOperands'
     */
    public void setGeometryOperands(nl.b3p.xml.ogc.v110.GeometryOperands geometryOperands)
    {
        this._geometryOperands = geometryOperands;
    } //-- void setGeometryOperands(nl.b3p.xml.ogc.v110.GeometryOperands) 

    /**
     * Sets the value of field 'spatialOperators'.
     * 
     * @param spatialOperators the value of field 'spatialOperators'
     */
    public void setSpatialOperators(nl.b3p.xml.ogc.v110.SpatialOperators spatialOperators)
    {
        this._spatialOperators = spatialOperators;
    } //-- void setSpatialOperators(nl.b3p.xml.ogc.v110.SpatialOperators) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Spatial_CapabilitiesType
     */
    public static nl.b3p.xml.ogc.v110.Spatial_CapabilitiesType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (nl.b3p.xml.ogc.v110.Spatial_CapabilitiesType) Unmarshaller.unmarshal(nl.b3p.xml.ogc.v110.Spatial_CapabilitiesType.class, reader);
    } //-- nl.b3p.xml.ogc.v110.Spatial_CapabilitiesType unmarshal(java.io.Reader) 

    /**
     * Method validate
     * 
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}