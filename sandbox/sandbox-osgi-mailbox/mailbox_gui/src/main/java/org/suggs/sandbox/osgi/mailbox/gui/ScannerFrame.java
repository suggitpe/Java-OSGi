/*
 * ScannerFrame.java created on 24 Apr 2009 17:02:51 by suggitpe for project SandBox_OSGI_Mailbox - mailbox_gui
 * 
 */
package org.suggs.sandbox.osgi.mailbox.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import org.osgi.framework.BundleContext;

/**
 * Main JFrame for the GUI.
 * 
 * @author suggitpe
 * @version 1.0 24 Apr 2009
 */
public class ScannerFrame extends JFrame
{

    private JTabbedPane mPane_;
    private ScannerMailboxTracker mTracker_;

    /**
     * Constructs a new instance.
     */
    public ScannerFrame()
    {
        super( "Mailbox scanner" );
        mPane_ = new JTabbedPane();
        mPane_.addTab( "Mailboxes", createIntroPanel() );
        mPane_.setPreferredSize( new Dimension( 400, 400 ) );

        getContentPane().add( mPane_, BorderLayout.CENTER );
    }

    /**
     * Creates an introductory panel
     * 
     * @return a new panel introducing you to the GUI
     */
    private Component createIntroPanel()
    {
        JLabel l = new JLabel( "Select a mailbox" );
        l.setHorizontalAlignment( SwingConstants.CENTER );
        return l;
    }

    /**
     * Start tracking for the mailboxes.
     * 
     * @param ctx
     *            teh bundle context for the link into the OSGI
     *            framework
     */
    protected void openTracking( BundleContext ctx )
    {
        mTracker_ = new ScannerMailboxTracker( ctx, mPane_ );
        mTracker_.open();
    }

    /**
     * Close the tracker
     */
    protected void closeTracking()
    {
        mTracker_.close();
    }
}
