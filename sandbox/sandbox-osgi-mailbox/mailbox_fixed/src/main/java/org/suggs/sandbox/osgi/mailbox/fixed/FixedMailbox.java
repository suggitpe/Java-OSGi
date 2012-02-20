/*
 * FixedMailbox.java created on 26 Mar 2009 08:12:21 by suggitpe for project SandBox_OSGI_Mailbox - mailbox_fixed
 * 
 */
package org.suggs.sandbox.osgi.mailbox.fixed;

import org.suggs.sandbox.osgi.mailbox.api.Mailbox;
import org.suggs.sandbox.osgi.mailbox.api.MailboxException;
import org.suggs.sandbox.osgi.mailbox.api.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a sham implementation of the mailbox api. It does not do
 * any dynamic message retention and merely creates a few messages to
 * use on startup.
 * 
 * @author suggitpe
 * @version 1.0 26 Mar 2009
 */
public class FixedMailbox implements Mailbox
{

    private final List<Message> mMessages_ = new ArrayList<Message>( 2 );

    /**
     * Constructs a new instance.
     */
    public FixedMailbox()
    {
        mMessages_.add( new StringMessage( 0, "Hello", "Welcome to OSGI" ) );
        mMessages_.add( new StringMessage( 1, "Getting started", "Get in there!" ) );

    }

    /**
     * @see org.suggs.sandbox.osgi.mailbox.api.Mailbox#getAllMessages()
     */
    public synchronized long[] getAllMessages() throws MailboxException
    {
        long[] ids = new long[mMessages_.size()];
        for ( int i = 0; i < ids.length; ++i )
        {
            ids[i] = mMessages_.get( i ).getId();
        }
        return ids;
    }

    /**
     * @see org.suggs.sandbox.osgi.mailbox.api.Mailbox#getAllMessagesSince(long)
     */
    public synchronized long[] getAllMessagesSince( long aId ) throws MailboxException
    {
        int first = (int) aId + 1;
        if ( first < 0 || first >= mMessages_.size() )
        {
            throw new MailboxException( "Invalid message ID: " + aId );
        }

        long ids[] = new long[mMessages_.size() - first];
        for ( int i = 0; i < ids.length; ++i )
        {
            ids[i] = i + first;
        }
        return ids;
    }

    /**
     * @see org.suggs.sandbox.osgi.mailbox.api.Mailbox#getMessages(long[])
     */
    public synchronized Message[] getMessages( long[] aIds ) throws MailboxException
    {
        Message[] res = new Message[aIds.length];
        for ( int i = 0; i < res.length; ++i )
        {
            long id = aIds[i];
            if ( id < 0 || id > mMessages_.size() )
            {
                throw new MailboxException( "Invalid message ID: " + id );
            }

            res[i] = mMessages_.get( (int) id );
        }
        return res;
    }

    /**
     * @see org.suggs.sandbox.osgi.mailbox.api.Mailbox#markRead(boolean,
     *      long[])
     */
    public void markRead( boolean aRead, long[] aIds ) throws MailboxException
    {
        // ignore
    }

}
