/*
 * ScannerFrameActivator.java created on 24 Apr 2009 17:21:39 by suggitpe for project SandBox_OSGI_Mailbox - mailbox_gui
 * 
 */
package org.suggs.sandbox.osgi.mailbox.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 * Activator so that we can initiate the GUI through the OSGI
 * framework.
 * 
 * @author suggitpe
 * @version 1.0 24 Apr 2009
 */
public class ScannerFrameActivator implements BundleActivator
{

    private static final Logger LOG = LoggerFactory.getLogger( ScannerFrameActivator.class );

    private ScannerFrame mFrame_;

    /**
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start( final BundleContext ctx ) throws Exception
    {
        LOG.debug( "Starting the scanner frame" );
        UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        mFrame_ = new ScannerFrame();
        mFrame_.pack();

        mFrame_.addWindowListener( new WindowAdapter()
        {

            /**
             * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
             */
            @Override
            public void windowClosing( WindowEvent we )
            {
                try
                {
                    ctx.getBundle().stop();
                }
                catch ( BundleException be )
                {
                    // nadda
                }
            }
        } );

        mFrame_.openTracking( ctx );
        mFrame_.setVisible( true );
    }

    /**
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop( BundleContext ctx ) throws Exception
    {
        LOG.debug( "Stopping the scanner frame" );
        mFrame_.setVisible( false );
        mFrame_.closeTracking();
    }

}
