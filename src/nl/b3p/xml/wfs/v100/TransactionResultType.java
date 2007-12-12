/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.2</a>, using an XML
 * Schema.
 * $Id$
 */

package nl.b3p.xml.wfs.v100;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class TransactionResultType.
 * 
 * @version $Revision$ $Date$
 */
public class TransactionResultType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _handle
     */
    private java.lang.String _handle;

    /**
     * The Status element contains an element indicating the
     *  completion status of a transaction. The SUCCESS element
     *  is used to indicate successful completion. The FAILED
     *  element is used to indicate that an exception was 
     *  encountered.
     *  
     */
    private nl.b3p.xml.wfs.v100.Status _status;

    /**
     * In the event that an exception was encountered while 
     *  processing a transaction, a Web Feature Service may
     *  use the Locator element to try and identify the part
     *  of the transaction that failed. If the element(s)
     *  contained in a Transaction element included a handle
     *  attribute, then a Web Feature Service may report the
     *  handle to identify the offending element.
     *  
     */
    private java.lang.String _locator;

    /**
     * The Message element may contain an exception report
     *  generated by a Web Feature Service when an exception
     *  is encountered.
     *  
     */
    private java.lang.String _message;


      //----------------/
     //- Constructors -/
    //----------------/

    public TransactionResultType() 
     {
        super();
    } //-- nl.b3p.xml.wfs.v100.TransactionResultType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'handle'.
     * 
     * @return String
     * @return the value of field 'handle'.
     */
    public java.lang.String getHandle()
    {
        return this._handle;
    } //-- java.lang.String getHandle() 

    /**
     * Returns the value of field 'locator'. The field 'locator'
     * has the following description: In the event that an
     * exception was encountered while 
     *  processing a transaction, a Web Feature Service may
     *  use the Locator element to try and identify the part
     *  of the transaction that failed. If the element(s)
     *  contained in a Transaction element included a handle
     *  attribute, then a Web Feature Service may report the
     *  handle to identify the offending element.
     *  
     * 
     * @return String
     * @return the value of field 'locator'.
     */
    public java.lang.String getLocator()
    {
        return this._locator;
    } //-- java.lang.String getLocator() 

    /**
     * Returns the value of field 'message'. The field 'message'
     * has the following description: The Message element may
     * contain an exception report
     *  generated by a Web Feature Service when an exception
     *  is encountered.
     *  
     * 
     * @return String
     * @return the value of field 'message'.
     */
    public java.lang.String getMessage()
    {
        return this._message;
    } //-- java.lang.String getMessage() 

    /**
     * Returns the value of field 'status'. The field 'status' has
     * the following description: The Status element contains an
     * element indicating the
     *  completion status of a transaction. The SUCCESS element
     *  is used to indicate successful completion. The FAILED
     *  element is used to indicate that an exception was 
     *  encountered.
     *  
     * 
     * @return Status
     * @return the value of field 'status'.
     */
    public nl.b3p.xml.wfs.v100.Status getStatus()
    {
        return this._status;
    } //-- nl.b3p.xml.wfs.v100.Status getStatus() 

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
     * Sets the value of field 'handle'.
     * 
     * @param handle the value of field 'handle'.
     */
    public void setHandle(java.lang.String handle)
    {
        this._handle = handle;
    } //-- void setHandle(java.lang.String) 

    /**
     * Sets the value of field 'locator'. The field 'locator' has
     * the following description: In the event that an exception
     * was encountered while 
     *  processing a transaction, a Web Feature Service may
     *  use the Locator element to try and identify the part
     *  of the transaction that failed. If the element(s)
     *  contained in a Transaction element included a handle
     *  attribute, then a Web Feature Service may report the
     *  handle to identify the offending element.
     *  
     * 
     * @param locator the value of field 'locator'.
     */
    public void setLocator(java.lang.String locator)
    {
        this._locator = locator;
    } //-- void setLocator(java.lang.String) 

    /**
     * Sets the value of field 'message'. The field 'message' has
     * the following description: The Message element may contain
     * an exception report
     *  generated by a Web Feature Service when an exception
     *  is encountered.
     *  
     * 
     * @param message the value of field 'message'.
     */
    public void setMessage(java.lang.String message)
    {
        this._message = message;
    } //-- void setMessage(java.lang.String) 

    /**
     * Sets the value of field 'status'. The field 'status' has the
     * following description: The Status element contains an
     * element indicating the
     *  completion status of a transaction. The SUCCESS element
     *  is used to indicate successful completion. The FAILED
     *  element is used to indicate that an exception was 
     *  encountered.
     *  
     * 
     * @param status the value of field 'status'.
     */
    public void setStatus(nl.b3p.xml.wfs.v100.Status status)
    {
        this._status = status;
    } //-- void setStatus(nl.b3p.xml.wfs.v100.Status) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return TransactionResultType
     */
    public static nl.b3p.xml.wfs.v100.TransactionResultType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (nl.b3p.xml.wfs.v100.TransactionResultType) Unmarshaller.unmarshal(nl.b3p.xml.wfs.v100.TransactionResultType.class, reader);
    } //-- nl.b3p.xml.wfs.v100.TransactionResultType unmarshal(java.io.Reader) 

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