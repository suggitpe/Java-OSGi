/*
 * BundleReleaseToolActivator.java created on 1 Jul 2009 19:46:35 by suggitpe for project OSGi Bundle Release Tool
 * 
 */
package org.suggs.osgitools.bundlereleasetool;

import org.suggs.osgitools.bundlereleasetool.GUI.BundleReleaseToolGui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 * Activator for the Bundle Release Tool GUI so that we can activate it through the OSGi state change.
 * 
 * @author suggitpe
 * @version 1.0 1 Jul 2009
 */
public class BundleReleaseToolActivator implements BundleActivator {

    private static final Logger LOG = LoggerFactory.getLogger( BundleReleaseToolActivator.class );

    private BundleContext context;

    // this is where we encapsulate all of the bundle related
    // activity
    private IBundleReleaseToolContextCallback callback = new IBundleReleaseToolContextCallback() {

        /**
         * @see org.suggs.osgitools.bundlereleasetool.IBundleReleaseToolContextCallback#buildWindowListener()
         */
        @Override
        public WindowListener buildWindowListener() {
            return new WindowAdapter() {

                /**
                 * Stop the underlying bundle as the window closes.
                 * 
                 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
                 */
                @Override
                public void windowClosing( WindowEvent we ) {
                    try {
                        LOG.debug( "Stopping bundle" );
                        if ( context != null ) {
                            context.getBundle().stop();
                        }
                    }
                    catch ( BundleException be ) {
                        // nadda
                    }
                }
            };
        }

        /**
         * Installs the bundle with the given URI
         * 
         * @see org.suggs.osgitools.bundlereleasetool.IBundleReleaseToolContextCallback#installBundle(java.lang.String)
         */
        @Override
        public boolean installBundle( String uri ) {
            try {
                context.installBundle( uri );
                LOG.debug( "Installing bundle from [" + uri + "]" );
            }
            catch ( BundleException be ) {
                LOG.error( "Failed to release bundle", be );
                return false;
            }
            return true;
        }

        /**
         * Interrogates the bundle context and retrieves the bundles that are of use to us
         * 
         * @see org.suggs.osgitools.bundlereleasetool.IBundleReleaseToolContextCallback#getBundleData()
         */
        @Override
        public List<BundleData> getBundleData() {
            List<BundleData> ret = new ArrayList<BundleData>();

            for ( Bundle bundle : context.getBundles() ) {
                ret.add( new BundleData( bundle.getBundleId(),
                                         getBundleState( bundle.getState() ),
                                         bundle.getLocation(),
                                         bundle.getSymbolicName() ) );
            }

            return ret;
        }

        /**
         * Private method to get the correct state of the bundle from the state int
         * 
         * @param aState
         * @return
         */
        private String getBundleState( int aState ) {
            switch ( aState ) {
                case Bundle.UNINSTALLED:
                    return "Uninstalled";
                case Bundle.INSTALLED:
                    return "Installed";
                case Bundle.RESOLVED:
                    return "Resolved";
                case Bundle.STARTING:
                    return "Starting";
                case Bundle.STOPPING:
                    return "Stopping";
                case Bundle.ACTIVE:
                    return "Active";
                default:
                    return "Unknown";
            }
        }

        /**
         * @see org.suggs.osgitools.bundlereleasetool.IBundleReleaseToolContextCallback#removeBundle(java.lang.Long)
         */
        @Override
        public void removeBundle( Long bundleId ) throws BundleGuiException {
            Bundle b = context.getBundle( bundleId.longValue() );

            try {
                b.uninstall();
            }
            catch ( BundleException be ) {
                LOG.error( "Bundle Exception thrown when calling uninstall on Bundle with name ["
                                           + b.getSymbolicName() + "]",
                           be );
                throw new BundleGuiException( "Failed to uninstall bundle with ID [" + b.getBundleId() + "]",
                                              be );
            }
        }

        /**
         * @see org.suggs.osgitools.bundlereleasetool.IBundleReleaseToolContextCallback#startBundle(java.lang.Long)
         */
        @Override
        public void startBundle( Long bundleId ) throws BundleGuiException {
            Bundle b = context.getBundle( bundleId.longValue() );
            try {
                b.start();
            }
            catch ( BundleException be ) {
                LOG.error( "Bundle Exception thrown when calling start on Bundle with name ["
                                           + b.getSymbolicName() + "]",
                           be );
                throw new BundleGuiException( "Failed to start bundle with ID [" + b.getBundleId() + "]", be );
            }
        }

        /**
         * @see org.suggs.osgitools.bundlereleasetool.IBundleReleaseToolContextCallback#stopBundle(java.lang.Long)
         */
        @Override
        public void stopBundle( Long bundleId ) throws BundleGuiException {
            Bundle b = context.getBundle( bundleId.longValue() );
            try {
                b.stop();
            }
            catch ( BundleException be ) {
                LOG.error( "Bundle Exception thrown when calling stop on Bundle with name ["
                                           + b.getSymbolicName() + "]",
                           be );
                throw new BundleGuiException( "Failed to stop bundle with ID [" + b.getBundleId() + "]", be );
            }
        }

        /**
         * @see org.suggs.osgitools.bundlereleasetool.IBundleReleaseToolContextCallback#updateBundle(java.lang.Long)
         */
        @Override
        public void updateBundle( Long bundleId ) throws BundleGuiException {
            Bundle b = context.getBundle( bundleId.longValue() );
            try {
                b.update();
            }
            catch ( BundleException be ) {
                LOG.error( "Bundle Exception thrown when calling update on Bundle with name ["
                                           + b.getSymbolicName() + "]",
                           be );
                throw new BundleGuiException( "Failed to update bundle with ID [" + b.getBundleId() + "]", be );
            }
        }

    };

    /**
     * Start the GUI and listen for it being closed by the user.
     * 
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start( final BundleContext aCtx ) {
        LOG.debug( "Starting the Bundle Release Tool GUI" );
        context = aCtx;
        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        }
        catch ( Exception e ) {
            throw new IllegalStateException( e );
        }
        // bit confusing here as we are using the callback to pass in
        // all of the bundle related functionality
        @SuppressWarnings("unused")
        BundleReleaseToolGui gui = new BundleReleaseToolGui( callback );
    }

    /**
     * Stop the GUI
     * 
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop( final BundleContext aCtx ) {
        LOG.debug( "Stopping the Bundle Release Tool GUI" );
        context = null;
    }

}
