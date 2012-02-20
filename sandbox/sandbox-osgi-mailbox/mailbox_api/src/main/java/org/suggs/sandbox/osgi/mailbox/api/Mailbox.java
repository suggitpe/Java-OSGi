/*
 * Mailbox.java created on 16 Mar 2009 20:24:59 by suggitpe for project SandBox_OSGI_Mailbox - mailbox_api
 * 
 */
package org.suggs.sandbox.osgi.mailbox.api;

/**
 * High level interface to the mailbox itself
 * 
 * @author suggitpe
 * @version 1.0 16 Mar 2009
 */
public interface Mailbox
{

    public static final String NAME_PROPERTY = "mailboxName";

    /**
     * Retrieve all messages that exist in the mailbox.
     * 
     * @return an array of message ids
     * @throws MailboxException
     */
    long[] getAllMessages() throws MailboxException;

    /**
     * Retrieve all messages received after a specific time
     * 
     * @param id
     *            the message id
     * @return an array of message ids
     * @throws MailboxException
     */
    long[] getAllMessagesSince( long id ) throws MailboxException;

    /**
     * Mark the specific messages as read/unread on the back end
     * message source, where supported (IMAP supports this).
     * 
     * @param read
     *            whether the messages have been read
     * @param ids
     *            an array of message ids
     * @throws MailboxException
     */
    void markRead( boolean read, long[] ids ) throws MailboxException;

    /**
     * Retrieve the specified messages.
     * 
     * @param ids
     *            an array of message ids for the messages that we
     *            want to retrieve
     * @return an array of messages
     * @throws MailboxException
     */
    Message[] getMessages( long[] ids ) throws MailboxException;

}
