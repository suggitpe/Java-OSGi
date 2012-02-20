/*
 * MailboxPanel.java created on 21 Apr 2009 19:10:47 by suggitpe for project SandBox_OSGI_Mailbox - mailbox_gui
 * 
 */
package org.suggs.sandbox.osgi.mailbox.gui;

import org.suggs.sandbox.osgi.mailbox.api.Mailbox;
import org.suggs.sandbox.osgi.mailbox.api.MailboxException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Panel for the Mailbox so that we can house the data in the GUI.
 * 
 * @author suggitpe
 * @version 1.0 21 Apr 2009
 */
public class MailboxPanel extends JPanel
{

    private final MailboxTableModel mMailboxTable_;

    /**
     * Constructs a new instance.
     * 
     * @param mbox
     *            mailbox to show
     * @throws MailboxException
     *             if an issue is found
     */
    public MailboxPanel( Mailbox mbox ) throws MailboxException
    {
        mMailboxTable_ = new MailboxTableModel( mbox );
        JTable table = new JTable( mMailboxTable_ );
        JScrollPane pane = new JScrollPane( table );

        add( pane );
    }

}
