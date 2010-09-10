/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.gis.writers;

import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import nl.b3p.ogc.utils.geotools.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.geotools.data.FeatureWriter;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.feature.IllegalAttributeException;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.TransformException;
import org.xml.sax.SAXException;

/**
 * B3partners B.V. http://www.b3partners.nl
 * @author Roy
 * Created on 9-sep-2010, 10:45:09
 */
public class StreamingShapeWriter {

    protected static final Log log = LogFactory.getLog(StreamingShapeWriter.class);
    private Map props = new HashMap();
    private HashMap<String, ShapefileDataStore> datastores = new HashMap();
    private HashMap<String, FeatureWriter> writers = new HashMap();
    private int featuresGiven = 0;
    private int featuresWritten = 0;
    private String workingDir = null;
    private boolean shapeIndex = true;
    private List<String> skipAttributeNames = null;

    /**
     * Constructor with shapeIndex=true
     */
    public StreamingShapeWriter(String workingDir) throws IOException {
        this(workingDir, true);
    }
    /**
     * Constructor for creating a StreamingShapeWriter. The writer wil stream the features
     * to different shapefiles in the given working dir.
     * @param workingDir The working dir where the shape writer is going to write the shape files
     * @param shapeIndex If true a index wil be created on the shape files.
     * @param skipAttributeNames a list of attribute names that needs to be skipped (default only boundedBy)
     */
    public StreamingShapeWriter(String workingDir, boolean shapeIndex,List<String> skipAttributeNames) throws IOException {
        this(workingDir,shapeIndex);
        this.skipAttributeNames=skipAttributeNames;
    }
    /**
     * Constructor for creating a StreamingShapeWriter. The writer wil stream the features
     * to different shapefiles in the given working dir.
     * @param workingDir The working dir where the shape writer is going to write the shape files
     * @param shapeIndex If true a index wil be created on the shape files.
     */
    public StreamingShapeWriter(String workingDir, boolean shapeIndex) throws IOException {
        props.put(ShapefileDataStoreFactory.URLP.key, "");
        props.put(ShapefileDataStoreFactory.CREATE_SPATIAL_INDEX.key, shapeIndex);

        this.workingDir = workingDir;
        File testFile = new File(workingDir);
        if (!testFile.exists()) {
            throw new IOException("Given path does not exists");
        }
        if (!testFile.isDirectory()) {
            throw new IOException("Given path is not a directory");
        }
        if (!testFile.canWrite()) {
            throw new IOException("Can't write in given path");
        }
        this.shapeIndex = shapeIndex;

        skipAttributeNames= new ArrayList<String>();
        skipAttributeNames.add("boundedBy");
    }

    /**
     * Writes a feature to a shape file. It also seperates the different GeometryTypes into different shapefiles.
     */
    public void write(SimpleFeature f) throws IOException, TransformerConfigurationException, TransformerException, ParserConfigurationException, SAXException, TransformException, FactoryException {

        featuresGiven++;
        Class newGeomClass = null;
        String suffix = "";
        if (f.getDefaultGeometry() instanceof Point || f.getDefaultGeometry() instanceof MultiPoint) {
            newGeomClass = MultiPoint.class;
            suffix = "_p";
        } else if (f.getDefaultGeometry() instanceof Polygon || f.getDefaultGeometry() instanceof MultiPolygon) {
            newGeomClass = MultiPolygon.class;
            suffix = "_v";
        } else if (f.getDefaultGeometry() instanceof LineString || f.getDefaultGeometry() instanceof MultiLineString) {
            newGeomClass = MultiLineString.class;
            suffix = "_l";
        } else if (f.getDefaultGeometry() == null) {
            log.error("No default geometry set.");
        } else {
            log.error("Geometry type not found: " + f.getDefaultGeometry().getClass().toString());
        }

        if (newGeomClass != null) {
            String hashKey = createHashKey(newGeomClass, f.getType().getTypeName());
            FeatureWriter writer = writers.get(hashKey);            
            if (writer == null) {
                writers.put(hashKey, createNewWriter(newGeomClass, f.getType(), suffix));
                writer = writers.get(hashKey);
            }
            if (writer != null) {
                write(writer, f);
                featuresWritten++;
            } else {
                log.error("writer for class: " + newGeomClass + " is not created");
            }
        }
    }

    /**
     * Function to close this writer. MUST BE CALLED!
     */
    public void close() {
        Iterator it = writers.keySet().iterator();
        while (it.hasNext()) {
            try {
                FeatureWriter writer = writers.get(it.next());
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ioe) {
                log.error("Error while closing writer: ", ioe);
            }
        }
        it = datastores.keySet().iterator();
        while (it.hasNext()) {
            ShapefileDataStore ds = datastores.get(it.next());
            if (ds != null) {
                ds.dispose();
            }
        }
    }

    /**
     * Function to write a feature to a writer.
     */
    private void write(FeatureWriter writer, SimpleFeature feature) throws IOException {
        // Write to datastore
        SimpleFeature newFeature = (SimpleFeature) writer.next();        
        try {
            newFeature.setAttributes(feature.getAttributes());
            newFeature.setDefaultGeometry(feature.getDefaultGeometry());
        } catch (IllegalAttributeException writeProblem) {
            throw new IllegalAttributeException("Could not create " + feature.getFeatureType().getTypeName() + " out of provided SimpleFeature: " + feature.getID() + "\n" + writeProblem);
        }
        writer.write();
    }
    /**
     * Creates a new FeatureWriter.
     * @param geomClass The Geometry class for which the writer must be created.
     * @param type The featureType for which the writer is created.
     * @param suffix A suffix after the shapefile name to indicate the difference between geometry
     * types. For example "_p" (for points)
     * @return a new featurewriter
     * @throws MalformedURLException
     * @throws IOException
     */
    private FeatureWriter createNewWriter(Class geomClass, SimpleFeatureType type, String suffix) throws MalformedURLException, IOException {
        //create the file path
        String filePath = "";
        filePath += getWorkingDir();
        if (type.getTypeName().indexOf(":") >= 0) {
            filePath += type.getTypeName().split(":")[1];
        } else {
            filePath += type.getTypeName();
        }
        filePath += suffix;
        filePath += ".shp";
        String hashKey = createHashKey(geomClass, type.getTypeName());
        //get the datastore and create the writer
        ShapefileDataStore ds = datastores.get(hashKey);
        if (ds == null) {
            ShapefileDataStoreFactory factory = new ShapefileDataStoreFactory();
            File newShape = new File(filePath);
            props.put(ShapefileDataStoreFactory.URLP.key, newShape.toURI().toURL());
            ds = (ShapefileDataStore) factory.createNewDataStore(props);
            ds.createSchema(Util.changeGeometryBinding(type, geomClass));
            datastores.put(hashKey, ds);
        }
        //create transaction and writer
        return ds.getFeatureWriterAppend(type.getTypeName(), Transaction.AUTO_COMMIT);
    }

    private String createHashKey(Class newGeomClass, String typeName) {
        return typeName + " " + newGeomClass.getName();
    }

    // <editor-fold defaultstate="collapsed" desc="comment">
    public int getFeaturesGiven() {
        return featuresGiven;
    }

    public int getFeaturesWritten() {
        return featuresWritten;
    }

    public String getWorkingDir() {
        return workingDir;
    }

    public boolean isShapeIndex() {
        return shapeIndex;
    }

    public List<String> getSkipAttributeNames() {
        return skipAttributeNames;
    }

    public void setSkipAttributeNames(List<String> skipAttributeNames) {
        this.skipAttributeNames = skipAttributeNames;
    }


    // </editor-fold>

}
