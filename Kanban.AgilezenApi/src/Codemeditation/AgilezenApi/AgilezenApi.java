package Codemeditation.AgilezenApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collection;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import Codemeditation.Domain.Project;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;

public class AgilezenApi implements IKanbanApi {

	private HttpClient httpclient;

	@Inject
	public AgilezenApi(HttpClient client) {
		httpclient = client;
	}

	@Override
	public int GetProjectsCount() {
		BufferedReader in = null;
		HttpGet getRequest = new HttpGet("http://agilezen.com/api/v1/projects?apikey=dabbb64a56a7454db2819405f2009b23");
		try {
			HttpResponse response = httpclient.execute(getRequest);
			
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer  sb = new StringBuffer("");
			String line = "";
			
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			
			in.close();
			
//			Gson gson = new Gson();
//			
//			Type collectionType = new TypeToken<Collection<Project>>() {}.getType();
//			Collection<Project> projects = gson.fromJson(sb.toString(), collectionType);
//			return projects.size();
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
