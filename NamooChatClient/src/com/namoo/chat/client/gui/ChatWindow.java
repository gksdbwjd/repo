package com.namoo.chat.client.gui;

import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
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
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.namoo.chat.client.logic.ChatClient;
import com.namoo.chat.core.Member;
import com.namoo.chat.core.MemberListMessage;
import com.namoo.chat.core.Message;
import com.namoo.chat.core.MessageType;

public class ChatWindow extends ApplicationWindow implements IChatMessage {
	private List chatting;
	private Text inputMessage;
	private Action action;
	private Action action_1;
	private List userList;
	private ChatClient chatClient;

	public ChatWindow() {
		super(null);
		chatClient = ChatClient.getInstance();
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

		chatting = new List(container, SWT.BORDER | SWT.READ_ONLY);
		chatting.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		GridData gd_chatting = new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1);
		gd_chatting.heightHint = 273;
		gd_chatting.widthHint = 276;
		chatting.setLayoutData(gd_chatting);

		userList = new List(container, SWT.BORDER);
		GridData gd_userList = new GridData(SWT.LEFT, SWT.CENTER, false, false,
				2, 1);
		gd_userList.widthHint = 145;
		gd_userList.heightHint = 273;
		userList.setLayoutData(gd_userList);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		inputMessage = new Text(container, SWT.BORDER);
		inputMessage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Button sendMessageBtn = new Button(container, SWT.NONE);
		GridData gd_sendMessageBtn = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_sendMessageBtn.widthHint = 145;
		sendMessageBtn.setLayoutData(gd_sendMessageBtn);
		sendMessageBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//
				try {

					chatClient.sendMessage(inputMessage.getText());
					inputMessage.setText("");

					// Thread thread = new Thread(new
					// MessageReceiver(chatClient, ChatWindow.this));
					// thread.start();

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		sendMessageBtn.setText("\uBCF4\uB0B4\uAE30");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		Thread thread = new Thread(new MessageReceiver(chatClient, this));
		thread.start();

		return container;
	}

	private void createActions() {
		// Create the actions
		{
			action = new Action("\uC2E0\uACE0") {

			};
			action.setImageDescriptor(ResourceManager
					.getImageDescriptor("C:\\\uC8FC\uAC04\uACFC\uC815\\workspace\\NamooChatClient\\src\\com\\namoo\\chat\\client\\Report.png"));
		}
		{
			action_1 = new Action("\uC811\uC18D\uBAA9\uB85D") {

			};
			action_1.setEnabled(false);
			action_1.setImageDescriptor(ResourceManager
					.getImageDescriptor("C:\\\uC8FC\uAC04\uACFC\uC815\\workspace\\NamooChatClient\\src\\com\\namoo\\chat\\client\\UserList.png"));
		}
	}

	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(SWT.FLAT | SWT.WRAP);
		toolBarManager.add(action);
		toolBarManager.add(action_1);
		return toolBarManager;
	}

	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	public static void main(String args[]) {
		try {
			ChatWindow window = new ChatWindow();
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
		return new Point(591, 415);
	}

	public void appendMessage(Message message) {
		//
		final String chatContents = ("[" + message.getSender() + "] : "
				+ message.getBody() + "\n");
		// swt가 관리하는 쓰레드에 부탁...
		Display.getDefault().asyncExec(new Runnable() {

			public void run() {
				chatting.add(chatContents);
			}
		});
		
		if(message.getType()==MessageType.RequestList){
			//
			
			//userList.add(message.);
		}

	}

	public void appendList(MemberListMessage message) {
		//
		java.util.List<Member> memberList = message.getMemberList();
		final Iterator iter = memberList.iterator();
		
		//final String chatContents = ("[" + message.getSender());
		Display.getDefault().asyncExec(new Runnable() {
		
			
		////getcurrent,,,getdefault 차이.... ///getCurrent는 현재 쓰레드의 Display를 리턴해준다. getDefault는 맨 처음에 생성된 Display를 리턴해준다. 여기서 멀티쓰레드를 돌렸기 때문에 getCurrent를 쓰면 null이 나오는 것
	
			
			
			public void run() {
				while (iter.hasNext()) {
					Member member = (Member) iter.next();
					userList.add(member.getNickName());
				}
				
			}

		});

	}
}
