package Codemeditation.AgilezenApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import Codemeditation.Domain.ProjectsPage;

import com.google.gson.Gson;
import com.google.inject.Inject;

public class AgilezenApi implements IKanbanApi {

	private HttpClient httpclient;
	private IJsonFactory reader;

	@Inject
	public AgilezenApi(HttpClient client, IJsonFactory reader) {
		httpclient = client;
		this.reader = reader;
	}

	@Override
	public int GetProjectsCount() {
		HttpGet getRequest = new HttpGet("http://agilezen.com/api/v1/projects?apikey=dabbb64a56a7454db2819405f2009b23");
		try {
			HttpResponse response = httpclient.execute(getRequest);
			InputStream stream = response.getEntity().getContent();
			
			String json = reader.Read(stream);

			Gson  gson = new Gson();
			ProjectsPage  page = gson.fromJson(json, ProjectsPage.class);

			return page.items.size();
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
