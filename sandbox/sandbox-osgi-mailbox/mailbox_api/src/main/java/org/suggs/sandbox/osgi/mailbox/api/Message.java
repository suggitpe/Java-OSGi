/*
 * Message.java created on 16 Mar 2009 20:21:16 by suggitpe for project SandBox_OSGI_Mailbox - mailbox_api
 * 
 */
package org.suggs.sandbox.osgi.mailbox.api;

import java.io.InputStream;

/**
 * Interface to represent a very generic message.
 * 
 * @author suggitpe
 * @version 1.0 16 Mar 2009
 */
public interface Message
{

    /**
     * @return The unique (within the mailbox) message ID.
     */
    long getId();

    /**
     * @return A human-readable text summary of the message. In some
     *         messaging systems this would map to the subject field.
     */
    String getSummary();

    /**
     * @return The internet MIME type of the message content.
     */
    String getMIMEType();

    /**
     * Access the content of the message
     * 
     * @return the message content
     * @throws MessageReaderException
     */
    InputStream getContent() throws MessageReaderException;

}
