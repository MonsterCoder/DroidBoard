package Codemeditation.AgilezenApi;

import java.util.List;

import Codemeditation.Domain.Phase;
import Codemeditation.Domain.Project;

public interface IKanbanApi {

	int GetProjectsCount();
	
	List<Project> GetProjects();
	
	void refreshProjects();

	List<Phase> GetPhases(int projectId);
}
