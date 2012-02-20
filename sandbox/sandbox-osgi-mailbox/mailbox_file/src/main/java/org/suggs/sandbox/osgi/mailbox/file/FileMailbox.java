/*
 * FileMailbox.java created on 1 Apr 2009 17:36:14 by suggitpe for project SandBox_OSGI_Mailbox - mailbox_file
 * 
 */
package org.suggs.sandbox.osgi.mailbox.file;

import org.suggs.sandbox.osgi.mailbox.api.Mailbox;
import org.suggs.sandbox.osgi.mailbox.api.MailboxException;
import org.suggs.sandbox.osgi.mailbox.api.Message;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the Mailbox api that uses a flat file for its
 * message store.
 * 
 * @author suggitpe
 * @version 1.0 1 Apr 2009
 */
public class FileMailbox implements Mailbox
{

    private static final Logger LOG = LoggerFactory.getLogger( FileMailbox.class );
    private static final long[] EMPTY = new long[0];
    @SuppressWarnings("unused")
    private File mFile_;

    /**
     * Constructs a new instance.
     * 
     * @param file
     */
    public FileMailbox( File file )
    {
        LOG.debug( "Creating a file mailbox with file [" + file.getAbsolutePath() + "]" );
        mFile_ = file;
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
