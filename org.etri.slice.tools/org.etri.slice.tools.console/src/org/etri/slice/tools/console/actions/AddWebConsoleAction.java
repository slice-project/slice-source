package org.etri.slice.tools.console.actions;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.statushandlers.StatusManager;
import org.etri.slice.tools.console.Activator;
import org.etri.slice.tools.console.dialog.WebConsoleInfoDialog;
import org.etri.slice.tools.console.model.ConsoleWebInfo;
import org.etri.slice.tools.console.views.SLICEConsoleView;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

public class AddWebConsoleAction extends Action  implements ISelectionListener{

	IWorkbenchWindow window;
	List<ConsoleWebInfo> input;
	TableViewer viewer;
	@SuppressWarnings("unused")
	private AddWebConsoleAction()
	{
		
	}
	
	public AddWebConsoleAction(IWorkbenchWindow window, TableViewer viewer, List<ConsoleWebInfo> input)
	{
		this.window = window;
		this.input = input;
		this.viewer = viewer;
	}
	
	@Override
	public void run() {
		WebConsoleInfoDialog dialog = new WebConsoleInfoDialog(window.getShell());
		int ret = dialog.open();
		
		if(ret == Dialog.CANCEL)
			return;
		
		ConsoleWebInfo added = dialog.getConsoleWebInfo();
		input.add(added);
		
		Preferences preferences = ConfigurationScope.INSTANCE.getNode(Activator.PLUGIN_ID);
		Preferences webConsoleUrlsNode = preferences.node(SLICEConsoleView.PREF_WEB_CONSOLE_URLS_NODE);
		
		Preferences consoleURLNode = webConsoleUrlsNode.node(added.getName());
		consoleURLNode.put(SLICEConsoleView.PREF_WEB_CONSOLE_NAME, added.getName());
		consoleURLNode.put(SLICEConsoleView.PREF_WEB_CONSOLE_IP, added.getIp());
		consoleURLNode.putInt(SLICEConsoleView.PREF_WEB_CONSOLE_PORT, added.getPort());
		
		try {
			consoleURLNode.flush();
		} catch (BackingStoreException e) {
			Status status= new Status(IStatus.ERROR, Activator.PLUGIN_ID, 
					"A preferences operation could not complete because of a failure in the backing store, or a failure to contact the backing store.");
			StatusManager.getManager().handle(status, StatusManager.LOG);
		}
				
		viewer.setInput(input);
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		
	}
}
