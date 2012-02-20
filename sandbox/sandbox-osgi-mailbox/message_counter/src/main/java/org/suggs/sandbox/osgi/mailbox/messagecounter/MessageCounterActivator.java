/*
 * MessageCounterActivator.java created on 2 Apr 2009 07:02:06 by suggitpe for project SandBox_OSGI_Mailbox - message_counter
 * 
 */
package org.suggs.sandbox.osgi.mailbox.messagecounter;

import org.suggs.sandbox.osgi.mailbox.api.Mailbox;
import org.suggs.sandbox.osgi.mailbox.api.MailboxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Activator that when run will access the mailbox service and will
 * get a count of all the messages therein.
 * 
 * @author suggitpe
 * @version 1.0 2 Apr 2009
 */
public class MessageCounterActivator implements BundleActivator
{

    private static final Logger LOG = LoggerFactory.getLogger( MessageCounterActivator.class );
    private BundleContext mCtx_;

    /**
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start( BundleContext ctx ) throws Exception
    {
        LOG.debug( "Starting Message Counter" );
        mCtx_ = ctx;
        printMsgCount();
    }

    /**
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop( BundleContext ctx ) throws Exception
    {
        LOG.debug( "Stopping Message Counter" );
    }

    /**
     * Private mechanism for printing out the count of the messages.
     */
    private void printMsgCount() throws MailboxException
    {
        ServiceReference ref = mCtx_.getServiceReference( Mailbox.class.getName() );

        if ( ref != null )
        {
            Mailbox mb = (Mailbox) mCtx_.getService( ref );
            if ( mb != null )
            {
                try
                {
                    int count = mb.getAllMessages().length;
                    LOG.info( "There are [" + count + "] messages in the mailbox" );
                }
                finally
                {
                    mCtx_.ungetService( ref );
                }
            }
            else
            {
                LOG.warn( "Could not get service Mailbox" );
            }
        }
        else
        {
            LOG.warn( "Counld not obtain reference to Mailbox" );
        }
    }

}
