/*
 * MessageCounterWithTrackerActivator.java created on 3 Apr 2009 06:52:09 by suggitpe for project SandBox_OSGI_Mailbox - message_counter
 * 
 */
package org.suggs.sandbox.osgi.mailbox.messagecounter;

import org.suggs.sandbox.osgi.mailbox.api.Mailbox;
import org.suggs.sandbox.osgi.mailbox.api.MailboxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

/**
 * This impl of the message counter program, utilises the service
 * tracker rather than directly registering with a service.
 * 
 * @author suggitpe
 * @version 1.0 3 Apr 2009
 */
public class MessageCounterWithTrackerActivator implements BundleActivator
{

    private static final Logger LOG = LoggerFactory.getLogger( MessageCounterWithTrackerActivator.class );
    private ServiceTracker mTracker_;

    /**
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start( BundleContext ctx ) throws Exception
    {
        mTracker_ = new ServiceTracker( ctx, Mailbox.class.getName(), null );
        mTracker_.open();
        printMessageCount();
    }

    /**
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop( BundleContext ctx ) throws Exception
    {
        mTracker_.close();
    }

    /**
     * Internal methid for printing out the message counts from the
     * Mailbox service
     */
    private void printMessageCount() throws MailboxException, InterruptedException
    {
        // here we wait for 5 secs for the service (in case it is temp
        // out of action)
        Mailbox mb = (Mailbox) mTracker_.waitForService( 5000 );
        if ( mb != null )
        {
            int count = mb.getAllMessages().length;
            LOG.info( "There are [" + count + "] messages in the mailbox" );
        }
    }

}
