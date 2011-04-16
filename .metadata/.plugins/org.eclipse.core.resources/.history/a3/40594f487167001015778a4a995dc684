package Codemeditation.DroidBoard;

import roboguice.config.AbstractAndroidModule;
import Codemeditation.AgilezenApi.AgilezenApi;

public class DependencyModule extends AbstractAndroidModule {

	@Override
	public void configure() {
		bind(IKanbanApi.class).to(AgilezenApi.class);
	}
}
