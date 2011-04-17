package Codemeditation.AgilezenApi;

import java.util.List;

import Codemeditation.Domain.Project;

public interface IKanbanApi {

	int GetProjectsCount();
	
	List<Project> GetProjects();
	
	void Refresh();
}
