package Codemeditation.AgilezenApi;

import java.util.List;

import Codemeditation.Domain.Phase;
import Codemeditation.Domain.Project;
import Codemeditation.Domain.Story;

public interface IKanbanApi {

	int GetProjectsCount();
	
	void refreshProjects();

	List<Project> GetProjects();

	List<Phase> GetPhases(int projectId);
	
	List<Story> GetStories(int projectId, int phase_id);
}
