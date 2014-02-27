package myrest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import restclient.Issue;
import dao.PicDao;

@Path("/jsonservice")
public class MyRest {
	
	@POST
	@Path("/getjson/{movie_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJSON(@PathParam("movie_id") String movie_id) throws Exception{
		
		System.out.println(" i am in my rest!");
		PicDao dao = new PicDao();
		JSONArray json = dao.getPicJSONArray(movie_id);
		
		JSONObject issue_json = new JSONObject();
		issue_json.put("videoId", movie_id);
		issue_json.put("objects", json);
		System.out.println(issue_json.toString());
		new Issue().issueByJSON(issue_json, movie_id);
		System.out.println(issue_json.toString());

//		issue_json.put("status", "i am in");
//		test();
		
		return Response.ok(issue_json.toString()).build();
	}
	
	
	public void test() throws JSONException{
		JSONObject jo = new JSONObject();
		JSONArray json = new JSONArray();
		JSONObject j1 = new JSONObject();
		j1.put("id", "03642203-9c0f-481e-a6bc-56b01ca7d535");
		j1.put("name", "feng");
		j1.put("description", "1111");
		json.put(j1);
		
		JSONObject j2 = new JSONObject();
		j1.put("id", "1");
		j1.put("name", "feng111");
		j1.put("description", "1111111");
		json.put(j2);
		
		jo.put("videoId", "867644b1-435e-481b-9896-fb49b1b1ae19");
		jo.put("objects", json);
		
		new Issue().issueByJSON(jo, "867644b1-435e-481b-9896-fb49b1b1ae19");
		 
	}

}
