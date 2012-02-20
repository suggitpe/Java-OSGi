/*
 * BundleCounterActivator.java created on 10 Mar 2009 07:32:57 by suggitpe for project SandBox_OSGI - OsgiTutorial
 * 
 */
package org.suggs.sandbox_osgi.tutorial.basics.bundlecounter;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bundle that will count the number of active bundles in the OSGI framework
 * 
 * @author suggitpe
 * @version 1.0 10 Mar 2009
 */
public class BundleCounterActivator implements BundleActivator, BundleListener {

    private static final Logger LOG = LoggerFactory.getLogger( BundleCounterActivator.class );
    private BundleContext context;

    /**
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start( BundleContext ctx ) {
        context = ctx;
        context.addBundleListener( this );
        printBundleCount();
    }

    /**
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop( BundleContext ctx ) {
        context.removeBundleListener( this );
        context = null;
    }

    /**
     * @see org.osgi.framework.BundleListener#bundleChanged(org.osgi.framework.BundleEvent)
     */
    @Override
    public void bundleChanged( BundleEvent evt ) {
        switch ( evt.getType() ) {
            case BundleEvent.INSTALLED:
                LOG.debug( "Bundle installed" );
                printBundleCount();
                break;
            case BundleEvent.UNINSTALLED:
                LOG.debug( "Bundle uninstalled" );
                printBundleCount();
                break;

        }
    }

    /**
     * Simple method that will print out the bundle count in the OSGI framework
     */
    private void printBundleCount() {
        LOG.debug( "There are currently " + context.getBundles().length + " bundles" );
    }

}
