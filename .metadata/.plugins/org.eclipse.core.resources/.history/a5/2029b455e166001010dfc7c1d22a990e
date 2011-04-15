import org.junit.runners.model.InitializationError;

import roboguice.inject.ContextScope;
import Codemeditation.DroidBoard.DroidBoardApplication;
import android.app.Application;

import com.google.inject.Injector;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;


public class GuiceTestRunner extends RobolectricTestRunner {

	public GuiceTestRunner(Class<?> testClass) throws InitializationError {
		super(testClass);
		
	}
	
	@Override
	protected Application createApplication() {
		// TODO Auto-generated method stub
		DroidBoardApplication application = (DroidBoardApplication)super.createApplication();
		
		return application;
	}
	
	@Override
	public void prepareTest(Object test) {
		DroidBoardApplication application = (DroidBoardApplication)Robolectric.application;
		
		Injector injector = application.getInjector();
		ContextScope scope = injector.getInstance(ContextScope.class);
		scope.enter(application);
		injector.injectMembers(test);
	}

}
