/*
 * BundleReleaseToolGuiTest.java created on 9 Jul 2009 20:14:50 by suggitpe for project OSGi Bundle Release Tool
 * 
 */
package org.suggs.osgitools.bundlereleasetool.gui;

import org.suggs.osgitools.bundlereleasetool.BundleData;
import org.suggs.osgitools.bundlereleasetool.IBundleReleaseToolContextCallback;
import org.suggs.osgitools.bundlereleasetool.GUI.BundleReleaseToolGui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class so that we can open and test the GUI operations
 * 
 * @author suggitpe
 * @version 1.0 9 Jul 2009
 */
public class BundleReleaseToolGuiTest {

    // static logger
    private static final Logger LOG = LoggerFactory.getLogger( BundleReleaseToolGuiTest.class );

    /**
     * This is just so we can easily test it (not worth building a JUnit for a Swing layer)
     * 
     * @param args
     */
    public static void main( String[] args ) {

        javax.swing.SwingUtilities.invokeLater( new Runnable() {

            @Override
            public void run() {
                LOG.debug( "Executing Run to build GUI" );

                @SuppressWarnings("unused")
                BundleReleaseToolGui gui = new BundleReleaseToolGui( buildBundleCallback(), true );

                LOG.debug( "GUI Run execution complete" );
            }

        } );
    }

    /**
     * This will be used solely to create the bundle callback for the GUI
     * 
     * @return GUI callback to handle the bundle context
     */
    private static final IBundleReleaseToolContextCallback buildBundleCallback() {
        return new IBundleReleaseToolContextCallback() {

            /**
             * stubs out the installing of the bundle
             * 
             * @see org.suggs.osgitools.bundlereleasetool.IBundleReleaseToolContextCallback#buildWindowListener()
             */
            @Override
            public WindowListener buildWindowListener() {
                return new WindowAdapter() {

                    /**
                     * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
                     */
                    @Override
                    public void windowClosing( WindowEvent we ) {
                        LOG.debug( "Doing window closey things" );
                    }
                };
            }

            /**
             * @see org.suggs.osgitools.bundlereleasetool.IBundleReleaseToolContextCallback#installBundle(java.lang.String)
             */
            @Override
            public boolean installBundle( String uri ) {
                LOG.debug( "Pretending to install bundle" );
                return true;
            }

            /**
             * @see org.suggs.osgitools.bundlereleasetool.IBundleReleaseToolContextCallback#getBundleData()
             */
            @Override
            public Vector<BundleData> getBundleData() {
                Vector<BundleData> ret = new Vector<BundleData>();
                LOG.debug( "Adding in some fake bundle data" );
                ret.add( new BundleData( 0, "Active", "location of bundle 0", "System Bundle (1.4.1)" ) );
                ret.add( new BundleData( 7,
                                         "Active",
                                         "location of bundle 7",
                                         "Apache Felix Shell Service (1.0.2)" ) );
                ret.add( new BundleData( 8,
                                         "Active",
                                         "location of bundle 8",
                                         "Apache Felix Shell TUI (1.0.2)" ) );
                ret.add( new BundleData( 9,
                                         "Active",
                                         "location of bundle 9",
                                         "Apache Felix Bundle Repository (1.2.1)" ) );
                ret.add( new BundleData( 10,
                                         "Installed",
                                         "location of bundle 10",
                                         "Bundle Release GUI (1.0.0.SNAPSHOT)" ) );
                ret.add( new BundleData( 15,
                                         "Installed",
                                         "location of bundle 15",
                                         "Sandbox OSGI Mailbox - API (1.0.0.SNAPSHOT)" ) );

                return ret;
            }

            @Override
            public void removeBundle( Long bundleId ) {
                LOG.debug( "Pretending to remove a bundle with ID [" + bundleId + "]" );
            }

            @Override
            public void startBundle( Long bundleId ) {
                LOG.debug( "Pretending to start a bundle with ID [" + bundleId + "]" );
            }

            @Override
            public void stopBundle( Long bundleId ) {
                LOG.debug( "Pretending to stop a bundle with ID [" + bundleId + "]" );
            }

            @Override
            public void updateBundle( Long bundleId ) {
                LOG.debug( "Pretending to update a bundle with ID [" + bundleId + "]" );
            }

        };
    }
}
