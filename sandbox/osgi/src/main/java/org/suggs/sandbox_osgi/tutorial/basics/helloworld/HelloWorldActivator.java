/*
 * HelloWorldActivator.java created on 17 Feb 2009 08:07:03 by suggitpe for project SandBox_OSGI - OsgiTutorial
 * 
 */
package org.suggs.sandbox_osgi.tutorial.basics.helloworld;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Activator for the Hello World stuff
 * 
 * @author suggitpe
 * @version 1.0 17 Feb 2009
 */
public class HelloWorldActivator implements BundleActivator {

    private static final Logger LOG = LoggerFactory.getLogger( HelloWorldActivator.class );

    /**
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start( BundleContext aArg0 ) {
        LOG.debug( "Hello world" );
    }

    /**
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop( BundleContext aArg0 ) {
        LOG.debug( "Good-bye world" );
    }

}
