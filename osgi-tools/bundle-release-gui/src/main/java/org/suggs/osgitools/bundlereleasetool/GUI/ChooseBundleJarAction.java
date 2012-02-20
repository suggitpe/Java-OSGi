/*
 * ChooseBundleJarAction.java created on 8 Jul 2009 06:54:25 by suggitpe for project OSGi Bundle Release Tool
 * 
 */
package org.suggs.osgitools.bundlereleasetool.GUI;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class will manage the choosing of a file and then making that file available to the caller.
 * 
 * @author suggitpe
 * @version 1.0 8 Jul 2009
 */
public class ChooseBundleJarAction extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger( ChooseBundleJarAction.class );

    private String bundleJar;

    /**
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory( new File( System.getProperty( "user.home" ) ) );
        chooser.setDialogTitle( "Please select bundle jar file to release" );
        chooser.setFileFilter( new FileFilter() {

            /**
             * Filter criterion.
             * 
             * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
             */
            @Override
            public boolean accept( File aPathname ) {
                if ( aPathname.isDirectory() || aPathname.getName().endsWith( ".jar" ) ) {
                    return true;
                }
                return false;
            }

            /**
             * Display for the dialog box selector.
             * 
             * @see javax.swing.filechooser.FileFilter#getDescription()
             */
            @Override
            public String getDescription() {
                return "*.jar";
            }
        } );

        int result = chooser.showOpenDialog( null );

        if ( result != JFileChooser.APPROVE_OPTION ) {
            LOG.debug( "Chooser cancelled" );
            return;
        }

        File jar = chooser.getSelectedFile();
        bundleJar = jar.toURI().toString();
    }

    /**
     * Getter for the bundle jar name. NB this will only work once you have called the run method and actually
     * proceeded the chooser dialog.
     * 
     * @return the name of the bundle jar
     */
    public String getBundleJarName() {
        return bundleJar;
    }

}
