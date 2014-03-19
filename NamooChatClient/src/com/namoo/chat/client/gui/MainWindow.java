package com.namoo.chat.client.gui;

import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.namoo.chat.client.logic.ChatClient;

public class MainWindow extends ApplicationWindow {
	private Text nameInput;

	
	public MainWindow() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
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

	
	private void createActions() {
		// Create the actions
	}

	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	
	public static void main(String args[]) {
		try {
			MainWindow window = new MainWindow();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("New Application");
	}

	 
	@Override
	protected Point getInitialSize() {
		return new Point(331, 192);
	}

}
