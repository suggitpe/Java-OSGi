/*
 * FileMailboxActivator.java created on 1 Apr 2009 17:23:11 by suggitpe for project SandBox_OSGI_Mailbox - mailbox_file
 * 
 */
package org.suggs.sandbox.osgi.mailbox.file;

import org.suggs.sandbox.osgi.mailbox.api.Mailbox;

import java.io.File;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * Activator for a file based mailbox. To get this working you need to
 * release the bundle to the OSGI framework and then you need to
 * create a file called messages.txt in your home dir.
 * 
 * @author suggitpe
 * @version 1.0 1 Apr 2009
 */
public class FileMailboxActivator implements BundleActivator
{

    private static final Logger LOG = LoggerFactory.getLogger( FileMailboxActivator.class );
    private Thread mThread_;
    private final long THREAD_SLEEP = 5000;

    /**
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start( BundleContext ctx ) throws Exception
    {
        LOG.debug( "Starting file mailbox" );
        File file = new File( System.getProperty( "user.home" ) + "/messages.txt" );
        RegistrationRunnable runnable = new RegistrationRunnable( ctx, file, null );
        mThread_ = new Thread( runnable );
        mThread_.start();
    }

    /**
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop( BundleContext ctx ) throws Exception
    {
        mThread_.interrupt();
    }

    /**
     * Private class to handle the registering and deregistering of
     * the file mailbox based on whether the messages file exists
     * 
     * @author suggitpe
     * @version 1.0 1 Apr 2009
     */
    private class RegistrationRunnable implements Runnable
    {

        private final BundleContext mCtx_;
        private final File mFile_;
        private final Properties mProps_;

        /**
         * Constructs a new instance.
         * 
         * @param ctx
         * @param file
         * @param props
         */
        public RegistrationRunnable( BundleContext ctx, File file, Properties props )
        {
            mCtx_ = ctx;
            mFile_ = file;
            mProps_ = props;
        }

        /**
         * @see java.lang.Runnable#run()
         */
        public void run()
        {
            ServiceRegistration reg = null;
            try
            {
                while ( !Thread.interrupted() )
                {
                    if ( mFile_.exists() )
                    {
                        if ( reg == null )
                        {
                            LOG.debug( "Registering new mailbox (file)" );
                            reg = mCtx_.registerService( Mailbox.class.getName(),
                                                         new FileMailbox( mFile_ ),
                                                         mProps_ );
                        }
                    }
                    else
                    {
                        if ( reg != null )
                        {
                            LOG.debug( "Unregistering mailbox "
                                       + reg.getReference().getBundle().getSymbolicName() );
                            reg.unregister();
                            reg = null;
                        }
                    }
                    Thread.sleep( THREAD_SLEEP );
                }
            }
            catch ( InterruptedException ie )
            {
                LOG.debug( "Interrupt received, ceasing file monitoring" );
                // nadda
            }
        }
    }
}
