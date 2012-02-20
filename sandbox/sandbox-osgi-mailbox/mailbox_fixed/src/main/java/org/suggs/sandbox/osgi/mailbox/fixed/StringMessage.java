/*
 * StringMessage.java created on 26 Mar 2009 08:01:43 by suggitpe for project SandBox_OSGI_Mailbox - mailbox_fixed
 * 
 */
package org.suggs.sandbox.osgi.mailbox.fixed;

import org.suggs.sandbox.osgi.mailbox.api.Message;
import org.suggs.sandbox.osgi.mailbox.api.MessageReaderException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * This implementation is a bean to represent the underlying message
 * that is beig sent.
 * 
 * @author suggitpe
 * @version 1.0 26 Mar 2009
 */
public class StringMessage implements Message
{

    private static final String MIME_TYPE_TEXT = "text/plain";

    private final long mId_;
    private final String mSubject_;
    private final String mText_;

    /**
     * Constructs a new instance.
     * 
     * @param id
     * @param subject
     * @param text
     */
    public StringMessage( long id, String subject, String text )
    {
        mId_ = id;
        mSubject_ = subject;
        mText_ = text;
    }

    /**
     * @see org.suggs.sandbox.osgi.mailbox.api.Message#getContent()
     */
    public InputStream getContent() throws MessageReaderException
    {
        return new ByteArrayInputStream( mText_.getBytes() );
    }

    /**
     * @see org.suggs.sandbox.osgi.mailbox.api.Message#getId()
     */
    public long getId()
    {
        return mId_;
    }

    /**
     * @see org.suggs.sandbox.osgi.mailbox.api.Message#getMIMEType()
     */
    public String getMIMEType()
    {
        return MIME_TYPE_TEXT;
    }

    /**
     * @see org.suggs.sandbox.osgi.mailbox.api.Message#getSummary()
     */
    public String getSummary()
    {
        return mSubject_;
    }

}
