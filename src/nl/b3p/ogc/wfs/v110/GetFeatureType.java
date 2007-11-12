/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.2</a>, using an XML
 * Schema.
 * $Id$
 */

package nl.b3p.ogc.wfs.v110;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.util.Collections;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * A GetFeature element contains one or more Query elements
 *  that describe a query operation on one feature type. In
 *  response to a GetFeature request, a Web Feature Service
 *  must be able to generate a GML3 response that validates
 *  using a schema generated by the DescribeFeatureType request.
 *  A Web Feature Service may support other possibly non-XML
 *  (and even binary) output formats as long as those formats
 *  are advertised in the capabilities document.
 *  
 * 
 * @version $Revision$ $Date$
 */
public class GetFeatureType extends nl.b3p.ogc.wfs.v110.BaseRequestType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The resultType attribute is used to indicate
     *  what response a WFS should return to user once
     *  a GetFeature request is processed.
     *  Possible values are:
     *  results - meaning that the full response set
     *  (i.e. all the feature instances) 
     *  should be returned.
     *  hits - meaning that an empty response set
     *  should be returned (i.e. no feature
     *  instances should be returned) but
     *  the "numberOfFeatures" attribute
     *  should be set to the number of feature
     *  instances that would be returned.
     *  
     */
    private java.lang.String _resultType = "results";

    /**
     * The outputFormat attribute is used to specify the output
     *  format that the Web Feature Service should generate in
     *  response to a GetFeature or GetFeatureWithLock element.
     *  The default value of 'text/xml; subtype=gml/3.1.1'
     *  indicates that the output is an XML document that
     *  conforms to the Geography Markup Language (GML)
     *  Implementation Specification V3.1.1.
     *  For the purposes of experimentation, vendor extension,
     *  or even extensions that serve a specific community of
     *  interest, other acceptable output format values may be
     *  used to specify other formats as long as those values
     *  are advertised in the capabilities document.
     *  For example, the value WKB may be used to indicate that a 
     *  Well Known Binary format be used to encode the output.
     *  
     */
    private java.lang.String _outputFormat = "text/xml; subtype=gml/3.1.1";

    /**
     * The maxFeatures attribute is used to specify the maximum
     *  number of features that a GetFeature operation should
     *  generate (regardless of the actual number of query hits).
     *  
     */
    private int _maxFeatures;

    /**
     * keeps track of state for field: _maxFeatures
     */
    private boolean _has_maxFeatures;

    /**
     * This attribute indicates the depth to which nested property
     *  XLink linking element locator attribute (href) XLinks are
     *  traversed and resolved if possible. A value of "1"
     *  indicates that one linking element locator attribute
     *  (href) Xlink will be traversed and the referenced element
     *  returned if possible, but nested property XLink linking
     *  element locator attribute (href) XLinks in the returned
     *  element are not traversed. A value of "*" indicates that
     *  all nested property XLink linking element locator attribute
     *  (href) XLinks will be traversed and the referenced elements
     *  returned if possible. The range of valid values for this
     *  attribute consists of positive integers plus "*".
     *  If this attribute is not specified then no xlinks shall be 
     *  resolved and the value of traverseXlinkExpiry attribute (if
     *  it specified) may be ignored.
     *  
     */
    private java.lang.String _traverseXlinkDepth;

    /**
     * The traverseXlinkExpiry attribute value is specified in
     *  minutes. It indicates how long a Web Feature Service
     *  should wait to receive a response to a nested GetGmlObject
     *  request. 
     *  This attribute is only relevant if a value is specified 
     *  for the traverseXlinkDepth attribute.
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

    public GetFeatureType() 
     {
        super();
        setResultType("results");
        setOutputFormat("text/xml; subtype=gml/3.1.1");
        _queryList = new java.util.ArrayList();
    } //-- nl.b3p.ogc.wfs.v110.GetFeatureType()


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
    public void addQuery(nl.b3p.ogc.wfs.v110.Query vQuery)
        throws java.lang.IndexOutOfBoundsException
    {
        _queryList.add(vQuery);
    } //-- void addQuery(nl.b3p.ogc.wfs.v110.Query) 

    /**
     * Method addQuery
     * 
     * 
     * 
     * @param index
     * @param vQuery
     */
    public void addQuery(int index, nl.b3p.ogc.wfs.v110.Query vQuery)
        throws java.lang.IndexOutOfBoundsException
    {
        _queryList.add(index, vQuery);
    } //-- void addQuery(int, nl.b3p.ogc.wfs.v110.Query) 

    /**
     * Method clearQuery
     * 
     */
    public void clearQuery()
    {
        _queryList.clear();
    } //-- void clearQuery() 

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
     * Returns the value of field 'maxFeatures'. The field
     * 'maxFeatures' has the following description: The maxFeatures
     * attribute is used to specify the maximum
     *  number of features that a GetFeature operation should
     *  generate (regardless of the actual number of query hits).
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
     * 'outputFormat' has the following description: The
     * outputFormat attribute is used to specify the output
     *  format that the Web Feature Service should generate in
     *  response to a GetFeature or GetFeatureWithLock element.
     *  The default value of 'text/xml; subtype=gml/3.1.1'
     *  indicates that the output is an XML document that
     *  conforms to the Geography Markup Language (GML)
     *  Implementation Specification V3.1.1.
     *  For the purposes of experimentation, vendor extension,
     *  or even extensions that serve a specific community of
     *  interest, other acceptable output format values may be
     *  used to specify other formats as long as those values
     *  are advertised in the capabilities document.
     *  For example, the value WKB may be used to indicate that a 
     *  Well Known Binary format be used to encode the output.
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
    public nl.b3p.ogc.wfs.v110.Query getQuery(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _queryList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (nl.b3p.ogc.wfs.v110.Query) _queryList.get(index);
    } //-- nl.b3p.ogc.wfs.v110.Query getQuery(int) 

    /**
     * Method getQuery
     * 
     * 
     * 
     * @return Query
     */
    public nl.b3p.ogc.wfs.v110.Query[] getQuery()
    {
        int size = _queryList.size();
        nl.b3p.ogc.wfs.v110.Query[] mArray = new nl.b3p.ogc.wfs.v110.Query[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (nl.b3p.ogc.wfs.v110.Query) _queryList.get(index);
        }
        return mArray;
    } //-- nl.b3p.ogc.wfs.v110.Query[] getQuery() 

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
     * 'resultType' has the following description: The resultType
     * attribute is used to indicate
     *  what response a WFS should return to user once
     *  a GetFeature request is processed.
     *  Possible values are:
     *  results - meaning that the full response set
     *  (i.e. all the feature instances) 
     *  should be returned.
     *  hits - meaning that an empty response set
     *  should be returned (i.e. no feature
     *  instances should be returned) but
     *  the "numberOfFeatures" attribute
     *  should be set to the number of feature
     *  instances that would be returned.
     *  
     * 
     * @return String
     * @return the value of field 'resultType'.
     */
    public java.lang.String getResultType()
    {
        return this._resultType;
    } //-- java.lang.String getResultType() 

    /**
     * Returns the value of field 'traverseXlinkDepth'. The field
     * 'traverseXlinkDepth' has the following description: This
     * attribute indicates the depth to which nested property
     *  XLink linking element locator attribute (href) XLinks are
     *  traversed and resolved if possible. A value of "1"
     *  indicates that one linking element locator attribute
     *  (href) Xlink will be traversed and the referenced element
     *  returned if possible, but nested property XLink linking
     *  element locator attribute (href) XLinks in the returned
     *  element are not traversed. A value of "*" indicates that
     *  all nested property XLink linking element locator attribute
     *  (href) XLinks will be traversed and the referenced elements
     *  returned if possible. The range of valid values for this
     *  attribute consists of positive integers plus "*".
     *  If this attribute is not specified then no xlinks shall be 
     *  resolved and the value of traverseXlinkExpiry attribute (if
     *  it specified) may be ignored.
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
     * 'traverseXlinkExpiry' has the following description: The
     * traverseXlinkExpiry attribute value is specified in
     *  minutes. It indicates how long a Web Feature Service
     *  should wait to receive a response to a nested GetGmlObject
     *  request. 
     *  This attribute is only relevant if a value is specified 
     *  for the traverseXlinkDepth attribute.
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
    public boolean removeQuery(nl.b3p.ogc.wfs.v110.Query vQuery)
    {
        boolean removed = _queryList.remove(vQuery);
        return removed;
    } //-- boolean removeQuery(nl.b3p.ogc.wfs.v110.Query) 

    /**
     * Sets the value of field 'maxFeatures'. The field
     * 'maxFeatures' has the following description: The maxFeatures
     * attribute is used to specify the maximum
     *  number of features that a GetFeature operation should
     *  generate (regardless of the actual number of query hits).
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
     * 'outputFormat' has the following description: The
     * outputFormat attribute is used to specify the output
     *  format that the Web Feature Service should generate in
     *  response to a GetFeature or GetFeatureWithLock element.
     *  The default value of 'text/xml; subtype=gml/3.1.1'
     *  indicates that the output is an XML document that
     *  conforms to the Geography Markup Language (GML)
     *  Implementation Specification V3.1.1.
     *  For the purposes of experimentation, vendor extension,
     *  or even extensions that serve a specific community of
     *  interest, other acceptable output format values may be
     *  used to specify other formats as long as those values
     *  are advertised in the capabilities document.
     *  For example, the value WKB may be used to indicate that a 
     *  Well Known Binary format be used to encode the output.
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
    public void setQuery(int index, nl.b3p.ogc.wfs.v110.Query vQuery)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _queryList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _queryList.set(index, vQuery);
    } //-- void setQuery(int, nl.b3p.ogc.wfs.v110.Query) 

    /**
     * Method setQuery
     * 
     * 
     * 
     * @param queryArray
     */
    public void setQuery(nl.b3p.ogc.wfs.v110.Query[] queryArray)
    {
        //-- copy array
        _queryList.clear();
        for (int i = 0; i < queryArray.length; i++) {
            _queryList.add(queryArray[i]);
        }
    } //-- void setQuery(nl.b3p.ogc.wfs.v110.Query) 

    /**
     * Sets the value of field 'resultType'. The field 'resultType'
     * has the following description: The resultType attribute is
     * used to indicate
     *  what response a WFS should return to user once
     *  a GetFeature request is processed.
     *  Possible values are:
     *  results - meaning that the full response set
     *  (i.e. all the feature instances) 
     *  should be returned.
     *  hits - meaning that an empty response set
     *  should be returned (i.e. no feature
     *  instances should be returned) but
     *  the "numberOfFeatures" attribute
     *  should be set to the number of feature
     *  instances that would be returned.
     *  
     * 
     * @param resultType the value of field 'resultType'.
     */
    public void setResultType(java.lang.String resultType)
    {
        this._resultType = resultType;
    } //-- void setResultType(java.lang.String) 

    /**
     * Sets the value of field 'traverseXlinkDepth'. The field
     * 'traverseXlinkDepth' has the following description: This
     * attribute indicates the depth to which nested property
     *  XLink linking element locator attribute (href) XLinks are
     *  traversed and resolved if possible. A value of "1"
     *  indicates that one linking element locator attribute
     *  (href) Xlink will be traversed and the referenced element
     *  returned if possible, but nested property XLink linking
     *  element locator attribute (href) XLinks in the returned
     *  element are not traversed. A value of "*" indicates that
     *  all nested property XLink linking element locator attribute
     *  (href) XLinks will be traversed and the referenced elements
     *  returned if possible. The range of valid values for this
     *  attribute consists of positive integers plus "*".
     *  If this attribute is not specified then no xlinks shall be 
     *  resolved and the value of traverseXlinkExpiry attribute (if
     *  it specified) may be ignored.
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
     * 'traverseXlinkExpiry' has the following description: The
     * traverseXlinkExpiry attribute value is specified in
     *  minutes. It indicates how long a Web Feature Service
     *  should wait to receive a response to a nested GetGmlObject
     *  request. 
     *  This attribute is only relevant if a value is specified 
     *  for the traverseXlinkDepth attribute.
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
    public static nl.b3p.ogc.wfs.v110.BaseRequestType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (nl.b3p.ogc.wfs.v110.BaseRequestType) Unmarshaller.unmarshal(nl.b3p.ogc.wfs.v110.GetFeatureType.class, reader);
    } //-- nl.b3p.ogc.wfs.v110.BaseRequestType unmarshal(java.io.Reader) 

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
