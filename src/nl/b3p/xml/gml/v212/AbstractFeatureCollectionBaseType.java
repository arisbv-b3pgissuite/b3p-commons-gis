/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.2</a>, using an XML
 * Schema.
 * $Id$
 */

package nl.b3p.xml.gml.v212;

/**
 * Class AbstractFeatureCollectionBaseType.
 * 
 * @version $Revision$ $Date$
 */
public abstract class AbstractFeatureCollectionBaseType extends nl.b3p.xml.gml.v212.AbstractFeatureType 
implements java.io.Serializable
{


      //----------------/
     //- Constructors -/
    //----------------/

    public AbstractFeatureCollectionBaseType() 
     {
        super();
    } //-- nl.b3p.xml.gml.v212.AbstractFeatureCollectionBaseType()


      //-----------/
     //- Methods -/
    //-----------/

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
