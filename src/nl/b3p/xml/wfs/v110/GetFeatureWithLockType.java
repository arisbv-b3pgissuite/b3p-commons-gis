/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.2</a>, using an XML
 * Schema.
 * $Id$
 */

package nl.b3p.xml.wfs.v110;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.util.Collections;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * A GetFeatureWithLock request operates identically to a
 *  GetFeature request expect that it attempts to lock the
 *  feature instances in the result set and includes a lock
 *  identifier in its response to a client. A lock identifier
 *  is an identifier generated by a Web Feature Service that 
 *  a client application can use, in subsequent operations,
 *  to reference the locked set of feature instances.
 *  
 * 
 * @version $Revision$ $Date$
 */
public class GetFeatureWithLockType extends nl.b3p.xml.wfs.v110.BaseRequestType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The expiry attribute is used to set the length
     *  of time (expressed in minutes) that features will
     *  remain locked as a result of a GetFeatureWithLock
     *  request. After the expiry period elapses, the
     *  locked resources must be released. If the 
     *  expiry attribute is not set, then the default
     *  value of 5 minutes will be enforced.
     *  
     */
    private int _expiry = 5;

    /**
     * keeps track of state for field: _expiry
     */
    private boolean _has_expiry;

    /**
     * See definition of wfs:GetFeatureType.
     *  
     */
    private nl.b3p.xml.wfs.v110.types.ResultTypeType _resultType = nl.b3p.xml.wfs.v110.types.ResultTypeType.valueOf("results");

    /**
     * See definition of wfs:GetFeatureType.
     *  
     */
    private java.lang.String _outputFormat = "text/xml; subtype=gml/3.1.1";

    /**
     * See definition of wfs:GetFeatureType.
     *  
     */
    private int _maxFeatures;

    /**
     * keeps track of state for field: _maxFeatures
     */
    private boolean _has_maxFeatures;

    /**
     * See definition of wfs:GetFeatureType.
     *  
     */
    private java.lang.String _traverseXlinkDepth;

    /**
     * See definition of wfs:GetFeatureType.
     *  
     */
    private int _traverseXlinkExpiry;

    /**
     * keeps track of state for field: _traverseXlinkExpiry
     */
    private boolean _has_traverseXlinkExpiry;

    /**
     * The Query element is used to describe a single query.
     *  One or more Query elements can be specified inside a
     *  GetFeature element so that multiple queries can be 
     *  executed in one request. The output from the various
     *  queries are combined in a wfs:FeatureCollection element
     *  to form the response document.
     *  
     */
    private java.util.ArrayList _queryList;


      //----------------/
     //- Constructors -/
    //----------------/

    public GetFeatureWithLockType() 
     {
        super();
        setResultType(nl.b3p.xml.wfs.v110.types.ResultTypeType.valueOf("results"));
        setOutputFormat("text/xml; subtype=gml/3.1.1");
        _queryList = new java.util.ArrayList();
    } //-- nl.b3p.xml.wfs.v110.GetFeatureWithLockType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addQuery
     * 
     * 
     * 
     * @param vQuery
     */
    public void addQuery(nl.b3p.xml.wfs.v110.Query vQuery)
        throws java.lang.IndexOutOfBoundsException
    {
        _queryList.add(vQuery);
    } //-- void addQuery(nl.b3p.xml.wfs.v110.Query) 

    /**
     * Method addQuery
     * 
     * 
     * 
     * @param index
     * @param vQuery
     */
    public void addQuery(int index, nl.b3p.xml.wfs.v110.Query vQuery)
        throws java.lang.IndexOutOfBoundsException
    {
        _queryList.add(index, vQuery);
    } //-- void addQuery(int, nl.b3p.xml.wfs.v110.Query) 

    /**
     * Method clearQuery
     * 
     */
    public void clearQuery()
    {
        _queryList.clear();
    } //-- void clearQuery() 

    /**
     * Method deleteExpiry
     * 
     */
    public void deleteExpiry()
    {
        this._has_expiry= false;
    } //-- void deleteExpiry() 

    /**
     * Method deleteMaxFeatures
     * 
     */
    public void deleteMaxFeatures()
    {
        this._has_maxFeatures= false;
    } //-- void deleteMaxFeatures() 

    /**
     * Method deleteTraverseXlinkExpiry
     * 
     */
    public void deleteTraverseXlinkExpiry()
    {
        this._has_traverseXlinkExpiry= false;
    } //-- void deleteTraverseXlinkExpiry() 

    /**
     * Method enumerateQuery
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateQuery()
    {
        return Collections.enumeration(_queryList);
    } //-- java.util.Enumeration enumerateQuery() 

    /**
     * Returns the value of field 'expiry'. The field 'expiry' has
     * the following description: The expiry attribute is used to
     * set the length
     *  of time (expressed in minutes) that features will
     *  remain locked as a result of a GetFeatureWithLock
     *  request. After the expiry period elapses, the
     *  locked resources must be released. If the 
     *  expiry attribute is not set, then the default
     *  value of 5 minutes will be enforced.
     *  
     * 
     * @return int
     * @return the value of field 'expiry'.
     */
    public int getExpiry()
    {
        return this._expiry;
    } //-- int getExpiry() 

    /**
     * Returns the value of field 'maxFeatures'. The field
     * 'maxFeatures' has the following description: See definition
     * of wfs:GetFeatureType.
     *  
     * 
     * @return int
     * @return the value of field 'maxFeatures'.
     */
    public int getMaxFeatures()
    {
        return this._maxFeatures;
    } //-- int getMaxFeatures() 

    /**
     * Returns the value of field 'outputFormat'. The field
     * 'outputFormat' has the following description: See definition
     * of wfs:GetFeatureType.
     *  
     * 
     * @return String
     * @return the value of field 'outputFormat'.
     */
    public java.lang.String getOutputFormat()
    {
        return this._outputFormat;
    } //-- java.lang.String getOutputFormat() 

    /**
     * Method getQuery
     * 
     * 
     * 
     * @param index
     * @return Query
     */
    public nl.b3p.xml.wfs.v110.Query getQuery(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _queryList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (nl.b3p.xml.wfs.v110.Query) _queryList.get(index);
    } //-- nl.b3p.xml.wfs.v110.Query getQuery(int) 

    /**
     * Method getQuery
     * 
     * 
     * 
     * @return Query
     */
    public nl.b3p.xml.wfs.v110.Query[] getQuery()
    {
        int size = _queryList.size();
        nl.b3p.xml.wfs.v110.Query[] mArray = new nl.b3p.xml.wfs.v110.Query[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (nl.b3p.xml.wfs.v110.Query) _queryList.get(index);
        }
        return mArray;
    } //-- nl.b3p.xml.wfs.v110.Query[] getQuery() 

    /**
     * Method getQueryCount
     * 
     * 
     * 
     * @return int
     */
    public int getQueryCount()
    {
        return _queryList.size();
    } //-- int getQueryCount() 

    /**
     * Returns the value of field 'resultType'. The field
     * 'resultType' has the following description: See definition
     * of wfs:GetFeatureType.
     *  
     * 
     * @return ResultTypeType
     * @return the value of field 'resultType'.
     */
    public nl.b3p.xml.wfs.v110.types.ResultTypeType getResultType()
    {
        return this._resultType;
    } //-- nl.b3p.xml.wfs.v110.types.ResultTypeType getResultType() 

    /**
     * Returns the value of field 'traverseXlinkDepth'. The field
     * 'traverseXlinkDepth' has the following description: See
     * definition of wfs:GetFeatureType.
     *  
     * 
     * @return String
     * @return the value of field 'traverseXlinkDepth'.
     */
    public java.lang.String getTraverseXlinkDepth()
    {
        return this._traverseXlinkDepth;
    } //-- java.lang.String getTraverseXlinkDepth() 

    /**
     * Returns the value of field 'traverseXlinkExpiry'. The field
     * 'traverseXlinkExpiry' has the following description: See
     * definition of wfs:GetFeatureType.
     *  
     * 
     * @return int
     * @return the value of field 'traverseXlinkExpiry'.
     */
    public int getTraverseXlinkExpiry()
    {
        return this._traverseXlinkExpiry;
    } //-- int getTraverseXlinkExpiry() 

    /**
     * Method hasExpiry
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasExpiry()
    {
        return this._has_expiry;
    } //-- boolean hasExpiry() 

    /**
     * Method hasMaxFeatures
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasMaxFeatures()
    {
        return this._has_maxFeatures;
    } //-- boolean hasMaxFeatures() 

    /**
     * Method hasTraverseXlinkExpiry
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasTraverseXlinkExpiry()
    {
        return this._has_traverseXlinkExpiry;
    } //-- boolean hasTraverseXlinkExpiry() 

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
     * Method removeQuery
     * 
     * 
     * 
     * @param vQuery
     * @return boolean
     */
    public boolean removeQuery(nl.b3p.xml.wfs.v110.Query vQuery)
    {
        boolean removed = _queryList.remove(vQuery);
        return removed;
    } //-- boolean removeQuery(nl.b3p.xml.wfs.v110.Query) 

    /**
     * Sets the value of field 'expiry'. The field 'expiry' has the
     * following description: The expiry attribute is used to set
     * the length
     *  of time (expressed in minutes) that features will
     *  remain locked as a result of a GetFeatureWithLock
     *  request. After the expiry period elapses, the
     *  locked resources must be released. If the 
     *  expiry attribute is not set, then the default
     *  value of 5 minutes will be enforced.
     *  
     * 
     * @param expiry the value of field 'expiry'.
     */
    public void setExpiry(int expiry)
    {
        this._expiry = expiry;
        this._has_expiry = true;
    } //-- void setExpiry(int) 

    /**
     * Sets the value of field 'maxFeatures'. The field
     * 'maxFeatures' has the following description: See definition
     * of wfs:GetFeatureType.
     *  
     * 
     * @param maxFeatures the value of field 'maxFeatures'.
     */
    public void setMaxFeatures(int maxFeatures)
    {
        this._maxFeatures = maxFeatures;
        this._has_maxFeatures = true;
    } //-- void setMaxFeatures(int) 

    /**
     * Sets the value of field 'outputFormat'. The field
     * 'outputFormat' has the following description: See definition
     * of wfs:GetFeatureType.
     *  
     * 
     * @param outputFormat the value of field 'outputFormat'.
     */
    public void setOutputFormat(java.lang.String outputFormat)
    {
        this._outputFormat = outputFormat;
    } //-- void setOutputFormat(java.lang.String) 

    /**
     * Method setQuery
     * 
     * 
     * 
     * @param index
     * @param vQuery
     */
    public void setQuery(int index, nl.b3p.xml.wfs.v110.Query vQuery)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _queryList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _queryList.set(index, vQuery);
    } //-- void setQuery(int, nl.b3p.xml.wfs.v110.Query) 

    /**
     * Method setQuery
     * 
     * 
     * 
     * @param queryArray
     */
    public void setQuery(nl.b3p.xml.wfs.v110.Query[] queryArray)
    {
        //-- copy array
        _queryList.clear();
        for (int i = 0; i < queryArray.length; i++) {
            _queryList.add(queryArray[i]);
        }
    } //-- void setQuery(nl.b3p.xml.wfs.v110.Query) 

    /**
     * Sets the value of field 'resultType'. The field 'resultType'
     * has the following description: See definition of
     * wfs:GetFeatureType.
     *  
     * 
     * @param resultType the value of field 'resultType'.
     */
    public void setResultType(nl.b3p.xml.wfs.v110.types.ResultTypeType resultType)
    {
        this._resultType = resultType;
    } //-- void setResultType(nl.b3p.xml.wfs.v110.types.ResultTypeType) 

    /**
     * Sets the value of field 'traverseXlinkDepth'. The field
     * 'traverseXlinkDepth' has the following description: See
     * definition of wfs:GetFeatureType.
     *  
     * 
     * @param traverseXlinkDepth the value of field
     * 'traverseXlinkDepth'.
     */
    public void setTraverseXlinkDepth(java.lang.String traverseXlinkDepth)
    {
        this._traverseXlinkDepth = traverseXlinkDepth;
    } //-- void setTraverseXlinkDepth(java.lang.String) 

    /**
     * Sets the value of field 'traverseXlinkExpiry'. The field
     * 'traverseXlinkExpiry' has the following description: See
     * definition of wfs:GetFeatureType.
     *  
     * 
     * @param traverseXlinkExpiry the value of field
     * 'traverseXlinkExpiry'.
     */
    public void setTraverseXlinkExpiry(int traverseXlinkExpiry)
    {
        this._traverseXlinkExpiry = traverseXlinkExpiry;
        this._has_traverseXlinkExpiry = true;
    } //-- void setTraverseXlinkExpiry(int) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return BaseRequestType
     */
    public static nl.b3p.xml.wfs.v110.BaseRequestType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (nl.b3p.xml.wfs.v110.BaseRequestType) Unmarshaller.unmarshal(nl.b3p.xml.wfs.v110.GetFeatureWithLockType.class, reader);
    } //-- nl.b3p.xml.wfs.v110.BaseRequestType unmarshal(java.io.Reader) 

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