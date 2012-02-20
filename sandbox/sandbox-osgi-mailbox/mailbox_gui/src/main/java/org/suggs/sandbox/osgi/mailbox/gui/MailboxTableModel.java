/*
 * MailboxTableModel.java created on 21 Apr 2009 18:57:20 by suggitpe for project SandBox_OSGI_Mailbox - mailbox_gui
 * 
 */
package org.suggs.sandbox.osgi.mailbox.gui;

import org.suggs.sandbox.osgi.mailbox.api.Mailbox;
import org.suggs.sandbox.osgi.mailbox.api.MailboxException;
import org.suggs.sandbox.osgi.mailbox.api.Message;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * Table model to model the view of the data within the mailbox (ie
 * the messages)
 * 
 * @author suggitpe
 * @version 1.0 21 Apr 2009
 */
public class MailboxTableModel extends AbstractTableModel
{

    private static final String ERROR = "ERROR";
    private final Mailbox mMailbox_;
    private final List<Message> mMessages_;

    /**
     * Constructs a new instance.
     * 
     * @param mailbox
     * @throws MailboxException
     */
    public MailboxTableModel( Mailbox mailbox ) throws MailboxException
    {
        mMailbox_ = mailbox;
        long[] msgIds = mMailbox_.getAllMessages();
        mMessages_ = new ArrayList<Message>( msgIds.length );
        Message[] messageArray = mMailbox_.getMessages( msgIds );
        for ( Message m : messageArray )
        {
            mMessages_.add( m );
        }
    }

    /**
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount()
    {
        return 2;
    }

    /**
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public synchronized int getRowCount()
    {
        return mMessages_.size();
    }

    /**
     * @see javax.swing.table.AbstractTableModel#getColumnName(int)
     */
    @Override
    public String getColumnName( int col )
    {
        switch ( col )
        {
            case 0:
                return "ID";
            case 1:
                return "Subject";
        }
        return ERROR;
    }

    /**
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt( int row, int col )
    {
        Message msg = mMessages_.get( row );
        switch ( col )
        {
            case 0:
                return Long.toString( msg.getId() );
            case 1:
                return msg.getSummary();
        }
        return ERROR;
    }
}
