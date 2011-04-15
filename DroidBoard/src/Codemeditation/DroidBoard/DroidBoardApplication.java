package Codemeditation.DroidBoard;

import java.util.List;

import com.google.inject.Module;

import roboguice.application.RoboApplication;

public class DroidBoardApplication extends RoboApplication{
	private Module configModule = new DependencyModule();

	@Override
	protected void addApplicationModules(List<Module> modules) {
		modules.add(configModule);
	}

	public void SetModule(
			Module targetModule) {
		configModule = targetModule;
	}
}
