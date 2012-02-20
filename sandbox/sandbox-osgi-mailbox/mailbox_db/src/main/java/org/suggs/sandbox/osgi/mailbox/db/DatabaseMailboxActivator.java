/*
 * DatabaseMailboxActivator.java created on 3 Apr 2009 18:33:26 by suggitpe for project SandBox_OSGI_Mailbox - mailbox_db
 * 
 */
package org.suggs.sandbox.osgi.mailbox.db;

import org.suggs.sandbox.osgi.mailbox.api.Mailbox;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * Activator for a database mailbox. Utilises a ServiceTracker to
 * identify when a datasource is added or removed from the OSGI
 * context. The interesting thing here is that the tracker object will
 * actively track instances of DataSource being added or removed. This
 * takes away the hard work.
 * 
 * @author suggitpe
 * @version 1.0 3 Apr 2009
 */
public class DatabaseMailboxActivator implements BundleActivator
{

    private static final Logger LOG = LoggerFactory.getLogger( DatabaseMailboxActivator.class );
    private BundleContext mCtx_;
    private ServiceTracker mTracker_;

    /**
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start( BundleContext ctx ) throws Exception
    {
        mCtx_ = ctx;
        LOG.debug( "Installing a service tracker to track DataSources being added or removed from the OSGI context" );
        mTracker_ = new ServiceTracker( mCtx_,
                                        DataSource.class.getName(),
                                        new DataSourceCustomizer() );
        mTracker_.open();
    }

    /**
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop( BundleContext ctx ) throws Exception
    {
        mTracker_.close();
    }

    /**
     * DataSource customizer implementation. This is called by the
     * service tracker when a new object is added to the OSGI context.
     * 
     * @author suggitpe
     * @version 1.0 3 Apr 2009
     */
    private class DataSourceCustomizer implements ServiceTrackerCustomizer
    {

        /**
         * @see org.osgi.util.tracker.ServiceTrackerCustomizer#addingService(org.osgi.framework.ServiceReference)
         */
        public Object addingService( ServiceReference ref )
        {
            LOG.debug( "Adding Database mailbox service" );
            DataSource ds = (DataSource) mCtx_.getService( ref );

            DatabaseMailbox mbox = new DatabaseMailbox( ds );
            ServiceRegistration reg = mCtx_.registerService( Mailbox.class.getName(), mbox, null );
            return reg;
        }

        /**
         * @see org.osgi.util.tracker.ServiceTrackerCustomizer#modifiedService(org.osgi.framework.ServiceReference,
         *      java.lang.Object)
         */
        public void modifiedService( ServiceReference ref, Object svc )
        {
            // nadda
        }

        /**
         * @see org.osgi.util.tracker.ServiceTrackerCustomizer#removedService(org.osgi.framework.ServiceReference,
         *      java.lang.Object)
         */
        public void removedService( ServiceReference ref, Object svc )
        {
            LOG.debug( "Unregistering database mailbox service" );
            ServiceRegistration reg = (ServiceRegistration) svc;
            reg.unregister();
            mCtx_.ungetService( ref );
        }
    }

}
