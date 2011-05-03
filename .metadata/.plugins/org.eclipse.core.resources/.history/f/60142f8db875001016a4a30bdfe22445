package Codemeditation.DroidBoard;

import java.util.List;

import roboguice.application.RoboApplication;

import com.ericharlow.DragNDrop.DragDropManager;
import com.google.inject.Module;

public class DroidBoardApplication extends RoboApplication{
	private Module configModule = new DependencyModule();
	private DragDropManager _dragdropManager = new DragDropManager();
	@Override
	protected void addApplicationModules(List<Module> modules) {
		modules.add(configModule);
	}

	public void SetModule(
			Module targetModule) {
		configModule = targetModule;
	}
	
	public DragDropManager GetDragDropManager() {
		return this._dragdropManager;
	}
}
