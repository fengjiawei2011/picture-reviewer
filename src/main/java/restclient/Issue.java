package restclient;

import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class Issue {
	
	public void issueByJSON(JSONObject json, String movie_id){
		ClientConfig clientConfig = new DefaultClientConfig();

		clientConfig.getFeatures().put(
				JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

		Client client = Client.create(clientConfig);
		
		WebResource webResource = client.resource("http://115.28.254.112/api/title/"+movie_id+"/rideo");

		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, json.toString());

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}else{
			System.out.println(" success!!!!!!!!!!!!!!!!!!!!!!");
		}
	}

}
