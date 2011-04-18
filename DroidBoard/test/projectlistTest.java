import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import Codemeditation.AgilezenApi.IKanbanApi;
import Codemeditation.Domain.Project;
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
		Project p1 = new Project();
		p1.name = "p1";
		p1.description="description1";
		
		Project p2 = new Project();
		p2.name = "p2";
		p2.description="description2";
	
		List<Project> projects = new ArrayList<Project>();
		projects.add(p1);
		projects.add(p2);
		
		org.mockito.Mockito.when(mockKanbanApi.GetProjects()).thenReturn(projects);

		
		projectlist_activity = new ProjectlistActivity();
		projectlist_activity.onCreate(null);
		projectlist_title = ((TextView)projectlist_activity.findViewById(R.id.projectlist_title));

	};
	
    @Test
    public void should_show_project_List_title() throws Exception {
    	assertTrue(projectlist_title.getText().equals("Active Projects: 2"));
    }
}
