package Codemeditation.DroidBoard;

import roboguice.config.AbstractAndroidModule;
import Codemeditation.AgilezenApi.AgilezenApi;
import Codemeditation.AgilezenApi.IKanbanApi;

public class DependencyModule extends AbstractAndroidModule {

	@Override
	public void configure() {
		bind(IKanbanApi.class).to(AgilezenApi.class);
	}
}
