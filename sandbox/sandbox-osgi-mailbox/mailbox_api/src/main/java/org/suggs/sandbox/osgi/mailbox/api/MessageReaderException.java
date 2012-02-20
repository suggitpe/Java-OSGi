/*
 * MessageReaderException.java created on 17 Mar 2009 07:37:17 by suggitpe for project SandBox_OSGI_Mailbox - mailbox_api
 * 
 */
package org.suggs.sandbox.osgi.mailbox.api;

/**
 * Exception for message related exceptions
 * 
 * @author suggitpe
 * @version 1.0 17 Mar 2009
 */
public class MessageReaderException extends Exception
{

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new instance.
     * 
     * @param message
     *            the exception message
     */
    public MessageReaderException( String message )
    {
        super( message );
    }

    /**
     * Constructs a new instance.
     * 
     * @param cause
     *            the exception that will be wrapped by this exception
     */
    public MessageReaderException( Throwable cause )
    {
        super( cause );
    }

    /**
     * Constructs a new instance.
     * 
     * @param message
     *            the exception message
     * @param cause
     *            the exception that will be wrapped by this exception
     */
    public MessageReaderException( String message, Throwable cause )
    {
        super( message, cause );
    }

}
