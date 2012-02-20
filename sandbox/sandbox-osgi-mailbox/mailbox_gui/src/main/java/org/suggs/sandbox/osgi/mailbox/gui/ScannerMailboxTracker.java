/*
 * ScannerMailboxTracker.java created on 21 Apr 2009 19:14:20 by suggitpe for project SandBox_OSGI_Mailbox - mailbox_gui
 * 
 */
package org.suggs.sandbox.osgi.mailbox.gui;

import org.suggs.sandbox.osgi.mailbox.api.Mailbox;
import org.suggs.sandbox.osgi.mailbox.api.MailboxException;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Tracker to scan for mailboxes within the OSGI framework.
 * 
 * @author suggitpe
 * @version 1.0 21 Apr 2009
 */
public class ScannerMailboxTracker extends ServiceTracker
{

    private static final Logger LOG = LoggerFactory.getLogger( ScannerMailboxTracker.class );

    private final JTabbedPane mTabbedPane_;
    private final BundleContext mCtx_;

    /**
     * Constructs a new instance.
     * 
     * @param ctx
     * @param pane
     */
    public ScannerMailboxTracker( BundleContext ctx, JTabbedPane pane )
    {
        super( ctx, Mailbox.class.getName(), null );
        mTabbedPane_ = pane;
        mCtx_ = ctx;
    }

    /**
     * Creates a future task to populate the discovered mailbox
     * service into a tab in the mailbox panel
     * 
     * @see org.osgi.util.tracker.ServiceTracker#addingService(org.osgi.framework.ServiceReference)
     */
    @Override
    public Object addingService( ServiceReference aRef )
    {
        final String mboxName = (String) aRef.getProperty( Mailbox.NAME_PROPERTY );
        final Mailbox mbox = (Mailbox) mCtx_.getService( aRef );

        Callable<MailboxPanel> callable = new Callable<MailboxPanel>()
        {

            public MailboxPanel call() throws Exception
            {
                MailboxPanel panel;
                try
                {
                    panel = new MailboxPanel( mbox );
                    String title = ( mboxName != null ) ? mboxName : "<unknown>";
                    LOG.debug( "Adding panel for [" + title + "]" );
                    mTabbedPane_.addTab( title, panel );
                }
                catch ( MailboxException me )
                {
                    JOptionPane.showMessageDialog( mTabbedPane_,
                                                   me.getMessage(),
                                                   "Error",
                                                   JOptionPane.ERROR_MESSAGE );
                    panel = null;
                }
                return panel;
            }
        };

        FutureTask<MailboxPanel> ft = new FutureTask<MailboxPanel>( callable );
        SwingUtilities.invokeLater( ft );

        return ft;
    }

    /**
     * @see org.osgi.util.tracker.ServiceTracker#removedService(org.osgi.framework.ServiceReference,
     *      java.lang.Object)
     */
    @Override
    public void removedService( ServiceReference ref, Object svc )
    {
        @SuppressWarnings("unchecked")
        final FutureTask<MailboxPanel> panelRef = (FutureTask<MailboxPanel>) svc;

        SwingUtilities.invokeLater( new Runnable()
        {

            public void run()
            {
                try
                {
                    MailboxPanel p = panelRef.get();
                    if ( p != null )
                    {
                        LOG.debug( "Removing tab from panel" );
                        mTabbedPane_.remove( p );
                    }
                }
                catch ( ExecutionException ee )
                {
                    // not an issue as the mailbox panel was not
                    // properly created
                }
                catch ( InterruptedException ie )
                {
                    // restore the interruption status
                    Thread.currentThread().interrupt();
                }
            }
        } );

        mCtx_.ungetService( ref );
    }
}
