package Codemeditation.AgilezenApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonFactory implements IJsonFactory {

	public String Read(InputStream stream) {
		BufferedReader in = new BufferedReader(new InputStreamReader(stream));
		StringBuffer  sb = new StringBuffer("");
		String line = "";
		
		try {
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		in.close();
		return sb.toString();
	}

}
