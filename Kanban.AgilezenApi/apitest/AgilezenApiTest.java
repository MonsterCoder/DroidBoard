
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import Codemeditation.AgilezenApi.AgilezenApi;
import Codemeditation.AgilezenApi.IJsonFactory;

@RunWith(MockitoJUnitRunner.class)
public class AgilezenApiTest {
	AgilezenApi sut;
	String json = "{\"page\":1,\"pageSize\":100,\"totalPages\":1,\"totalItems\":7,\"items\":[{\"id\":6856,\"name\":\".NET Media Library \",\"description\":\"Media Library to store music, videos, books, games, etc...\nImplemented using ASP.NET MVC and many other goodies....\",\"createTime\":\"2010-02-16T04:31:41\",\"owner\":{\"id\":3602,\"name\":\"Amir Barylko\",\"userName\":\"amirci\",\"email\":\"amir@barylko.com\"}},{\"id\":14147,\"name\":\"Price Room Designer\",\"description\":\"Anvil True Displacement Room Designer\",\"createTime\":\"2010-10-04T19:28:28\",\"owner\":{\"id\":3602,\"name\":\"Amir Barylko\",\"userName\":\"amirci\",\"email\":\"amir@barylko.com\"}},{\"id\":17066,\"name\":\"Zenboard\",\"description\":\"Companion for AgileZen to provide additional information using the API\",\"createTime\":\"2010-12-29T12:17:11\",\"owner\":{\"id\":3602,\"name\":\"Amir Barylko\",\"userName\":\"amirci\",\"email\":\"amir@barylko.com\"}},{\"id\":20555,\"name\":\"TwitterStudio\",\"description\":\"Twitter studio is a Visual Studio extension that enables you to post code snippets and comments to Twitter within IDE\",\"createTime\":\"2011-03-21T14:30:00\",\"owner\":{\"id\":8880,\"name\":\"Yong Chen\",\"userName\":\"MonsterCoder\",\"email\":\"MonsterCoder@gmail.com\"}},{\"id\":20618,\"name\":\"Kindlehog\",\"description\":\"\",\"createTime\":\"2011-03-22T15:23:35\",\"owner\":{\"id\":8880,\"name\":\"Yong Chen\",\"userName\":\"MonsterCoder\",\"email\":\"MonsterCoder@gmail.com\"}},{\"id\":20619,\"name\":\"TFS_Kanban\",\"description\":\"\",\"createTime\":\"2011-03-22T15:24:08\",\"owner\":{\"id\":8880,\"name\":\"Yong Chen\",\"userName\":\"MonsterCoder\",\"email\":\"MonsterCoder@gmail.com\"}},{\"id\":20621,\"name\":\"2011\",\"description\":\"\",\"createTime\":\"2011-03-22T15:52:19\",\"owner\":{\"id\":8880,\"name\":\"Yong Chen\",\"userName\":\"MonsterCoder\",\"email\":\"MonsterCoder@gmail.com\"}}]}";
	
	@Mock   HttpClient client;
	@Mock   HttpResponse mockResponse; 
	@Mock   HttpGet httpget;
	@Mock   HttpEntity mockEntity;
	@Mock   InputStream mockStream;
	@Mock   IJsonFactory mockJsonFactory;

	@Before
	public void Setup() throws ClientProtocolException, IOException {
		org.mockito.Mockito.when(client.execute(httpget)).thenReturn(mockResponse);
		org.mockito.Mockito.when(mockResponse.getEntity()).thenReturn(mockEntity);
		org.mockito.Mockito.when(mockEntity.getContent()).thenReturn(mockStream);
		org.mockito.Mockito.when(mockJsonFactory.Read(mockStream)).thenReturn(json);
		
		sut = new AgilezenApi(client, mockJsonFactory, httpget);
	}
	
	@Test
	public void It_should_return_collection_of_projects() {
		/// change to sequence compare later
		assertTrue(sut.GetProjects().size() == 7);
	}
	
	@Test
	public void It_should_return_projects_count() {
		assertTrue(sut.GetProjectsCount() == 7);
	}
}


