/*
 * BundleReleaseToolContextCallback.java created on 9 Jul 2009 19:51:04 by suggitpe for project OSGi Bundle Release Tool
 * 
 */
package org.suggs.osgitools.bundlereleasetool;

import java.awt.event.WindowListener;
import java.util.List;

/**
 * Callback used by the GUI to encapsulate any of the
 * 
 * @author suggitpe
 * @version 1.0 9 Jul 2009
 */
public interface IBundleReleaseToolContextCallback {

    /**
     * Build a window listener so that we can do anything context related from the caller
     * 
     * @return a WindowListener
     */
    WindowListener buildWindowListener();

    /**
     * Installs a bundle to the bundle context
     * 
     * @param aUri
     *            the location of the bundle
     * @return true if the bundle was installed correctly
     */
    boolean installBundle( String aUri );

    /**
     * Extracts the bundle data from the bundle context
     * 
     * @return a List of bundle metadata
     */
    List<BundleData> getBundleData();

    /**
     * Starts a bundle with a given ID
     * 
     * @param aBundleId
     *            the id of the bundle
     * @throws BundleGuiException
     */
    void startBundle( Long aBundleId ) throws BundleGuiException;

    /**
     * Stops a bundle with agiven ID
     * 
     * @param aBundleId
     *            teh id of the bundle
     * @throws BundleGuiException
     */
    void stopBundle( Long aBundleId ) throws BundleGuiException;

    /**
     * Updates a bundle with a given ID
     * 
     * @param aBundleId
     *            the id of the bundle
     * @throws BundleGuiException
     */
    void updateBundle( Long aBundleId ) throws BundleGuiException;

    /**
     * Removes a bundle with a given ID
     * 
     * @param aBundleId
     *            the id of the bundle
     * @throws BundleGuiException
     */
    void removeBundle( Long aBundleId ) throws BundleGuiException;
}
