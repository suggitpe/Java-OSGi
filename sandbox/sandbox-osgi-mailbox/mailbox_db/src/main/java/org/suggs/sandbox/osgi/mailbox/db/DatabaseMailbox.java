/*
 * DatabaseMailbox.java created on 1 Apr 2009 17:36:14 by suggitpe for project SandBox_OSGI_Mailbox - mailbox_file
 * 
 */
package org.suggs.sandbox.osgi.mailbox.db;

import org.suggs.sandbox.osgi.mailbox.api.Mailbox;
import org.suggs.sandbox.osgi.mailbox.api.MailboxException;
import org.suggs.sandbox.osgi.mailbox.api.Message;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the Mailbox api that uses a database file for its
 * message store.
 * 
 * @author suggitpe
 * @version 1.0 1 Apr 2009
 */
public class DatabaseMailbox implements Mailbox
{

    private static final Logger LOG = LoggerFactory.getLogger( DatabaseMailbox.class );
    private static final long[] EMPTY = new long[0];
    @SuppressWarnings("unused")
    private DataSource mDatasource_;

    /**
     * Constructs a new instance.
     * 
     * @param ds
     *            a datasource
     */
    public DatabaseMailbox( DataSource ds )
    {
        LOG.debug( "Creating a database mailbox with datasource [" + ds + "]" );
        mDatasource_ = ds;
    }

    /**
     * @see org.suggs.sandbox.osgi.mailbox.api.Mailbox#getAllMessages()
     */
    public long[] getAllMessages() throws MailboxException
    {
        LOG.warn( "This is a stub implementation" );
        return EMPTY;
    }

    /**
     * @see org.suggs.sandbox.osgi.mailbox.api.Mailbox#getAllMessagesSince(long)
     */
    public long[] getAllMessagesSince( long aId ) throws MailboxException
    {
        LOG.warn( "This is a stub implementation" );
        return EMPTY;
    }

    /**
     * @see org.suggs.sandbox.osgi.mailbox.api.Mailbox#getMessages(long[])
     */
    public Message[] getMessages( long[] aIds ) throws MailboxException
    {
        LOG.warn( "This is a stub implementation" );
        return new Message[0];
    }

    /**
     * @see org.suggs.sandbox.osgi.mailbox.api.Mailbox#markRead(boolean,
     *      long[])
     */
    public void markRead( boolean aRead, long[] aIds ) throws MailboxException
    {
        LOG.warn( "This is a stub implementation" );
    }
}
