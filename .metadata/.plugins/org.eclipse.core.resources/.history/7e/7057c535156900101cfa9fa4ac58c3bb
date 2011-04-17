package Codemeditation.AgilezenApi;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import Codemeditation.Domain.Project;
import Codemeditation.Domain.ProjectsPage;

import com.google.gson.Gson;
import com.google.inject.Inject;

public class AgilezenApi implements IKanbanApi {

	private HttpClient httpclient;
	private IJsonFactory reader;
	private HttpGet httpget;
	private Collection<Project> projects;

	@Inject
	public AgilezenApi(HttpClient client, IJsonFactory reader, HttpGet httpget) {
		httpclient = client;
		this.reader = reader;
		this.httpget = httpget;
	}

	@Override
	public int GetProjectsCount() {		
		return GetProjects().size();
	}

	@Override
	public Collection<Project> GetProjects() {
		if (projects == null) Refresh();		

		return projects;
	}

	@Override
	public void Refresh() {
		FetchProjects();
	}
	
	private void FetchProjects() {
		try {
			httpget.setURI(new URI("http://agilezen.com/api/v1/projects?apikey=dabbb64a56a7454db2819405f2009b23"));
	
			HttpResponse response = httpclient.execute(httpget);
			InputStream stream = response.getEntity().getContent();
			
			String json = reader.Read(stream);

			stream.close();

			Gson  gson = new Gson();
			ProjectsPage  page = gson.fromJson(json, ProjectsPage.class);

			this.projects = page.items;
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {

			e.printStackTrace();
		}
	}

}
