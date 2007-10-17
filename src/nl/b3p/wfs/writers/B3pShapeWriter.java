/*
 * B3pShapeWriter.java
 *
 * Created on 17 oktober 2007, 11:56
 *
 * Autor: Roy
 */

package nl.b3p.wfs.writers;

import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jump.feature.Feature;
import com.vividsolutions.jump.feature.FeatureCollection;
import com.vividsolutions.jump.feature.FeatureDataset;
import com.vividsolutions.jump.io.DriverProperties;
import com.vividsolutions.jump.io.IllegalParametersException;
import com.vividsolutions.jump.io.ShapefileWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Roy
 */
public class B3pShapeWriter {
    private static final Log log = LogFactory.getLog(B3pShapeWriter.class);

    private String folder;
    /** Creates a new instance of B3pShapeWriter */
    public B3pShapeWriter(){
        
    }
    /** Create a new ShapeWriter.
     *  @param folder the full path of the folder thats going to contain the shape files..
     */
    public B3pShapeWriter(String folder){
        this.setFolder(folder);
    }
    /**
     * writes the featurecollection to shapefiles. Use this function if the features in the featurecollection CAN be different types.
     *@fcAll The feature collection (can have different types)
     *@filename The filename to use bij writing the shape file.
     *
     *@return a list of file's of type java.io.File
     */
    public List writeToShape(FeatureCollection fcAll,String filename) throws IllegalParametersException, Exception{
        FeatureDataset allPoint = new FeatureDataset(fcAll.getFeatureSchema());
        FeatureDataset allPoly = new FeatureDataset(fcAll.getFeatureSchema());
        FeatureDataset allLine = new FeatureDataset(fcAll.getFeatureSchema());
        Iterator it = fcAll.iterator();
        while(it.hasNext()){
            Feature f = (Feature)it.next();
            if (f.getGeometry() instanceof Point){
                allPoint.add(f);                        
            }
            if (f.getGeometry() instanceof Polygon){
                allPoly.add(f);
            }
            if (f.getGeometry() instanceof LineString){
                allLine.add(f);
            }
        }
        ArrayList files= new ArrayList();
        if (allPoint.size()>0){
            if (allPoly.size()==0 && allLine.size()==0)
                files.add(writeShape(allPoint,filename));
            else
                files.add(writeShape(allPoint,filename+"_p.shp"));
        }
        if (allPoly.size()>0){
            if (allPoint.size()==0 && allLine.size()==0)
                files.add(writeShape(allPoly,filename));
            else
                files.add(writeShape(allPoly,filename+"_a.shp"));
        }
        if(allLine.size()>0){
            if (allPoint.size()==0 && allPoly.size()==0)
                files.add(writeShape(allLine,filename));
            else
                files.add(writeShape(allLine,filename+"_l.shp"));
        }
        return files;
    }
    /**
     * Writes a FeatureCollection to shape using the folder and the given name
     *@fc The feature collection (CAN'T have different types) 
     */
    public File writeShape(FeatureCollection fc,String name) throws IllegalParametersException, Exception{
        if (!name.endsWith(".shp")){
            name+=".shp";
        }
        DriverProperties dpo = new DriverProperties(getFolder()+name);
        ShapefileWriter sw = new ShapefileWriter();
        sw.write(fc,dpo);
        File file = new File(getFolder()+name);
        boolean a=file.canRead();
        return file;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
    
}