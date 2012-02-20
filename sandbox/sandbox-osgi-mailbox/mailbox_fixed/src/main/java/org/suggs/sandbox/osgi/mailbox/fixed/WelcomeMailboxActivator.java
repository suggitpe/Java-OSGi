/*
 * WelcomeMailboxActivator.java created on 1 Apr 2009 06:43:07 by suggitpe for project SandBox_OSGI_Mailbox - mailbox_fixed
 * 
 */
package org.suggs.sandbox.osgi.mailbox.fixed;

import org.suggs.sandbox.osgi.mailbox.api.Mailbox;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * OSGI Bundle activator for a mailbox
 * 
 * @author suggitpe
 * @version 1.0 1 Apr 2009
 */
public class WelcomeMailboxActivator implements BundleActivator
{

    private static final Logger LOG = LoggerFactory.getLogger( WelcomeMailboxActivator.class );

    /**
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start( BundleContext ctx ) throws Exception
    {
        LOG.debug( "Starting mailbox service" );
        Mailbox mbox = new FixedMailbox();

        Properties props = new Properties();
        props.put( Mailbox.NAME_PROPERTY, "Welcome" );

        ctx.registerService( Mailbox.class.getName(), mbox, props );
    }

    /**
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop( BundleContext ctx ) throws Exception
    {
        LOG.debug( "Stopping Mailbox service" );
    }

}
