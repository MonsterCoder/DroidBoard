package Codemeditation.Domain;

import java.util.List;

public class StoriesPage {
	public int page;
	public int pageSize;
	public int totalPages;
	public int totalItems;
	
	public List<Story> items;
}
