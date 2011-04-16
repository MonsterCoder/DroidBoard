
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

import Codemeditation.AgilezenApi.AgilezenApi;
import Codemeditation.AgilezenApi.IJsonFactory;

import com.xtremelabs.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class AgilezenApiTest {
	AgilezenApi sut;

	@Before
	public void Setup() throws ClientProtocolException, IOException {
		HttpClient client =  org.mockito.Mockito.mock(HttpClient.class);
		HttpResponse mockResponse  =  org.mockito.Mockito.mock(HttpResponse.class);
		
		TypeArgumentMatcher<HttpGet> clientmatch = new TypeArgumentMatcher(HttpGet.class);
		org.mockito.Mockito.when(client.execute(org.mockito.Mockito.argThat(clientmatch))).thenReturn(mockResponse);
		
	
		HttpEntity mockEntity = org.mockito.Mockito.mock(HttpEntity.class);
		org.mockito.Mockito.when(mockResponse.getEntity()).thenReturn(mockEntity);
		
		InputStream mockStream = org.mockito.Mockito.mock(InputStream.class);
		org.mockito.Mockito.when(mockEntity.getContent()).thenReturn(mockStream);
		
		IJsonFactory mockJsonFactory = org.mockito.Mockito.mock(IJsonFactory.class);
		org.mockito.Mockito.when(mockJsonFactory.Read(mockStream)).thenReturn("");
		
		sut = new AgilezenApi(client, mockJsonFactory);
	}
	
	@Test
	public void It_should_return_collection_of_projects() {
		
	}
	
	@Test
	public void It_should_return_projects_count() {
		assertTrue(sut.GetProjectsCount() == 7);
	}
}


