package org.etri.slice.tools.console.dialog;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.etri.slice.tools.console.Activator;
import org.etri.slice.tools.console.model.ConsoleWebInfo;
import org.etri.slice.tools.console.views.SLICEConsoleView;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

public class WebConsoleInfoDialog extends Dialog{

	private static final int DIALOG_WIDTH = 500;
	private static final int DIALOG_HEIGHT = 200;
	
	ConsoleWebInfo consoleWebInfo = null;
	
	String name;
	String ip;
	int port;
	
	Text nameText;
	Text ipText;
	Text portText;
	
	public WebConsoleInfoDialog(Shell parentShell) {
		super(parentShell);
	}
	 
	public WebConsoleInfoDialog(Shell parentShell, ConsoleWebInfo consoleWebInfo) {
		super(parentShell);
		
		this.consoleWebInfo = consoleWebInfo;
	}

	@Override
	protected Control createButtonBar(Composite parent) {
		return super.createButtonBar(parent);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		initLayout(container);
		
		Label nameLabel = new Label(container, SWT.NONE);
		nameLabel.setText("Name: ");
		
		nameText = new Text(container, SWT.BORDER);
		GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gridData.horizontalSpan = 5;				
		nameText.setLayoutData(gridData);
		
		Label ipLabel = new Label(container, SWT.NONE);
		ipLabel.setText("IP/Host: ");
		
		ipText = new Text(container, SWT.BORDER);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gridData.horizontalSpan = 1;		
		ipText.setLayoutData(gridData);
		
		Label portLabel = new Label(container, SWT.NONE);
		portLabel.setText("Port: ");
		
		portText = new Text(container, SWT.BORDER);
		gridData = new GridData(SWT.NONE, SWT.CENTER, true, false);
		gridData.horizontalSpan = 1;		
		portText.setLayoutData(gridData);
		
		// ���� �� 
		if(null != consoleWebInfo)
		{
			nameText.setText(consoleWebInfo.getName());
			ipText.setText(consoleWebInfo.getIp());
			portText.setText(Integer.toString(consoleWebInfo.getPort()));
			
			nameText.setEditable(false);
			ipText.setFocus();
		}
		
		return container;
	}

	private void initLayout(Composite container) {
		GridLayout gridLayout = new GridLayout(6, false);
		gridLayout.marginWidth = 15;
		gridLayout.marginHeight = 25;
		gridLayout.horizontalSpacing = 10;
		gridLayout.verticalSpacing = 10;
		container.setLayout(gridLayout);
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(DIALOG_WIDTH, DIALOG_HEIGHT);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Felix Web Console Connection Info");
	}

	@Override
	protected void setShellStyle(int newShellStyle) {
		super.setShellStyle(SWT.CLOSE | SWT.MODELESS | SWT.BORDER | SWT.TITLE);
		setBlockOnOpen(false);
	}

	@Override
	protected void okPressed() {
		name = nameText.getText();
		ip = ipText.getText();
		String portString = portText.getText();
		
		if(name.trim().length() == 0 || ip.trim().length() == 0 || portString.trim().length() == 0)
		{
			MessageDialog.openError(getShell(), "Error - Repository name or IP/Port required", 
					"The repository name or IP/Port input fields are empty.\nPlease enter the repository name and ip/port.");
			return;
		}
		
		try
		{			
			port = Integer.parseInt(portString);
		}
		catch(NumberFormatException e)
		{
			MessageDialog.openError(getShell(), "Error - Invalid Repository Port", 
					"The repository port must be an positive integer value.\nPlease enter a positive integer value in the port input field.");
			return;
		}
								
		// ������Ʈ�� �ƴϰ� ������ �����ϴ� URL �̸� ������ üũ�Ѵ�.
		if(null == consoleWebInfo && isExist())
		{
			MessageDialog.openError(getShell(), "Error - Duplicated Names", 
					"The web console name already exists.\nPlease enter another name.");
			return;
		}
			
		super.okPressed();
	}

	private boolean isExist()
	{
		Preferences preferences = ConfigurationScope.INSTANCE.getNode(Activator.PLUGIN_ID);
		Preferences webConsoleUrlsNode = preferences.node(SLICEConsoleView.PREF_WEB_CONSOLE_URLS_NODE);
		
		try {
			return webConsoleUrlsNode.nodeExists(name);
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public ConsoleWebInfo getConsoleWebInfo() {
		if(null == consoleWebInfo)
		{
			return new ConsoleWebInfo(name, ip, port);
		}
		else
		{
			consoleWebInfo.setName(name);
			consoleWebInfo.setIp(ip);
			consoleWebInfo.setPort(port);
						
			return consoleWebInfo;
		}
	}
}
