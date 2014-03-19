package com.namoo.chat.client.gui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.namoo.chat.client.logic.ChatClient;

public class LoginDialog extends Dialog {

	private Text nameInput;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public LoginDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);

		container.setLayout(new GridLayout(5, false));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label nameLabel = new Label(container, SWT.NONE);
		GridData gd_nameLabel = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_nameLabel.widthHint = 36;
		nameLabel.setLayoutData(gd_nameLabel);
		nameLabel.setText("\uB300\uD654\uBA85");
		
		
		nameInput = new Text(container, SWT.BORDER);
		GridData gd_nameInput = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_nameInput.widthHint = 195;
		nameInput.setLayoutData(gd_nameInput);
		
		Button connectionBtn = new Button(container, SWT.NONE);
		connectionBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//
				try {
					ChatClient chatClient =ChatClient.getInstance();
					boolean access = chatClient.requestJoin(nameInput.getText());
					if(access == true){
						//
						ChatWindow chatWindow = new ChatWindow();
						chatWindow.open();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		GridData gd_connectionBtn = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_connectionBtn.widthHint = 43;
		connectionBtn.setLayoutData(gd_connectionBtn);
		connectionBtn.setText("\uC811\uC18D");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.widthHint = 130;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText("\uC774\uBBF8 \uC874\uC7AC\uD558\uB294 \uB2C9\uB124\uC784");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 194);
	}

}
