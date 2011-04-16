import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import Codemeditation.AgilezenApi.IKanbanApi;
import Codemeditation.DroidBoard.ProjectlistActivity;
import Codemeditation.DroidBoard.R;
import android.widget.TextView;

import com.google.inject.Inject;

@RunWith(GuiceTestRunner.class)
public class projectlistTest {
	private ProjectlistActivity projectlist_activity;
	private TextView projectlist_title;
	
	@Inject IKanbanApi mockKanbanApi;
	
	@Before
	public void setup() throws Exception {
		org.mockito.Mockito.when(mockKanbanApi.GetProjectsCount()).thenReturn(3);
		
		projectlist_activity = new ProjectlistActivity();
		projectlist_activity.onCreate(null);
		projectlist_title = ((TextView)projectlist_activity.findViewById(R.id.projectlist_title));

	};
	
    @Test
    public void should_show_project_List_title() throws Exception {
    	assertTrue(projectlist_title.getText().equals("3 active projects"));
    }
}
