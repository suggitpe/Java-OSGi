/*
 * BundleData.java created on 10 Jul 2009 19:53:36 by suggitpe for project OSGi Bundle Release Tool
 * 
 */
package org.suggs.osgitools.bundlereleasetool;

/**
 * Bean object for the bundle domain layer.
 * 
 * @author suggitpe
 * @version 1.0 10 Jul 2009
 */
public class BundleData {

    private Long id;
    private String state;
    private String location;
    private String bundleName;

    /**
     * Constructs a new instance.
     * 
     * @param aId
     * @param aState
     * @param aLocation
     * @param aBundleName
     */
    public BundleData( long aId, String aState, String aLocation, String aBundleName ) {
        id = Long.valueOf( aId );
        state = aState;
        location = aLocation;
        bundleName = aBundleName;
    }

    /**
     * Returns the value of id.
     * 
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the value of state.
     * 
     * @return Returns the state.
     */
    public String getState() {
        return state;
    }

    /**
     * Returns the value of location.
     * 
     * @return Returns the location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns the value of bundleName.
     * 
     * @return Returns the bundleName.
     */
    public String getBundleName() {
        return bundleName;
    }

}
