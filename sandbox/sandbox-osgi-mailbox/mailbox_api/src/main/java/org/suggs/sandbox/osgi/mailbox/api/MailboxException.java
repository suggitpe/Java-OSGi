/*
 * MailboxException.java created on 17 Mar 2009 07:40:55 by suggitpe for project SandBox_OSGI_Mailbox - mailbox_api
 * 
 */
package org.suggs.sandbox.osgi.mailbox.api;

/**
 * Exception for mailbox related issues.
 * 
 * @author suggitpe
 * @version 1.0 17 Mar 2009
 */
public class MailboxException extends Exception
{

    public static final long serialVersionUID = 1L;

    /**
     * Constructs a new instance.
     * 
     * @param message
     *            the exception message
     */
    public MailboxException( String message )
    {
        super( message );
    }

    /**
     * Constructs a new instance.
     * 
     * @param cause
     *            the exception that this will wrap
     */
    public MailboxException( Throwable cause )
    {
        super( cause );
    }

    /**
     * Constructs a new instance.
     * 
     * @param message
     *            the exception message
     * @param cause
     *            the exception that this will wrap
     */
    public MailboxException( String message, Throwable cause )
    {
        super( message, cause );
    }

}
