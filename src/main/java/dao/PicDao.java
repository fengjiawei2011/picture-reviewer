package dao;

import helper.DBConnectionHelper;
import helper.ProjectHelper;
import helper.ToJSON;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import beans.PictureBean;

public class PicDao {

	public void setIsLike(String id, boolean isLike) {
		ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		String sql = "update " + dbHelper.getTable() + " set is_interesting = "
				+ isLike + "where id='" + id + "'";
		System.out.println(sql);
		try {
			Statement s = con.createStatement();
			s.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<PictureBean> getMoviesGroupEntries(String movie_id, int group_id) {
		List<PictureBean> pictures = new ArrayList<PictureBean>();
		// ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;

		String sql = "select * from " + dbHelper.getTable() + " m join "+dbHelper.getTable_movie()+" c on  m.m_id = c.movie_id where group_num="
				+ group_id + "and m_id = '" + movie_id + "'";
		System.out.println(sql);
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			pictures = getPicBeans(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				DBConnectionHelper.closeResultSet(rs);
			}
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return pictures;
	}

	public List<PictureBean> getPageEntries(int begin, int pages, String group, String movie_id) {
		List<PictureBean> pictures = new ArrayList<PictureBean>();
		// ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;

		String sql = "select * from " + dbHelper.getTable() +  " m join "+dbHelper.getTable_movie()+" c on  m.m_id = c.movie_id where `group_num`="
				+ group + " and m_id='"+movie_id+"' limit " + begin + "," + pages;
		System.out.println(sql);
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			pictures = getPicBeans(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				DBConnectionHelper.closeResultSet(rs);
			}
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return pictures;
	}
	
	public List<PictureBean> getPageEntries(int begin, int pages,  String movie_id) {
		List<PictureBean> pictures = new ArrayList<PictureBean>();
		// ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;

		String sql = "select * from " + dbHelper.getTable() + " m join "+dbHelper.getTable_movie()+" c on  m.m_id = c.movie_id where  m_id='"+movie_id+"' limit " + begin + "," + pages;
		System.out.println(sql);
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			pictures = getPicBeans(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				DBConnectionHelper.closeResultSet(rs);
			}
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return pictures;
	}
	
	public List<PictureBean> getPageEntries(int begin, int pages) {
		List<PictureBean> pictures = new ArrayList<PictureBean>();
		// ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;

		String sql = "select * from " + dbHelper.getTable() + " m join "+dbHelper.getTable_movie()+" c on  m.m_id = c.movie_id limit " + begin + "," + pages;
		System.out.println(sql);
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			pictures = getPicBeans(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				DBConnectionHelper.closeResultSet(rs);
			}
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return pictures;
	}

	public List<PictureBean> getAllEntries() {
		List<PictureBean> pictures = new ArrayList<PictureBean>();
		// ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;

		String sql = "select * from" + dbHelper.getTable()+" m join "+dbHelper.getTable_movie()+" c on  m.m_id = c.movie_id";
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			pictures = getPicBeans(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				DBConnectionHelper.closeResultSet(rs);
			}
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return pictures;
	}

/*	public List<MovieBean> getMovies(ResultSet rs) {
		 List<MovieBean> movies = new ArrayList<MovieBean>();
		 
		 
	}	
	*/
	

	
	
	public List<PictureBean> getPicBeans(ResultSet rs) {
		List<PictureBean> empty = new ArrayList<PictureBean>();
		try {
			while (rs.next()) {

				PictureBean pb = new PictureBean();
				pb.setFrom(rs.getString("source_type"));
				pb.setGroup(rs.getInt("group_num"));
				pb.setId(rs.getInt("id"));
				if (rs.getInt("is_interesting") == 0
						|| rs.getInt("is_interesting") == 1) {
					pb.setInteresting(rs.getInt("is_interesting"));
				} else {
					pb.setInteresting(0);
				}
				
				//String local_add = rs.getString("local_add").replace("rideo", "test_rideo");
				String local_add = rs.getString("local_add");
				pb.setLocal_add(local_add);
				pb.setSource(rs.getString("source_link"));
				pb.setTitle(rs.getString("title"));
				pb.setUrl(rs.getString("p_url"));
				pb.setAlt(rs.getString("description"));
				pb.setMovie_id(rs.getString("m_id"));
				pb.setMovie_name(rs.getString("movie_name"));
				/*
				 * pb.setImg_id(rs.getString("img_id"));
				 * pb.setImg_title(rs.getString("img_title"));
				 * pb.setImg_description(rs.getString("img_description"));
				 * pb.setImg_isLike(rs.getInt("islike"));
				 */
				empty.add(pb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empty;
	}

	public int getRecords(String movie_id, String group) {
		int num = 0;
		// ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;
		String sql = "select count(*) as num from " + dbHelper.getTable()
				+ " where `group_num`=" + group+" and m_id='"+movie_id+"'";
		System.out.println(sql);
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				num = rs.getInt("num");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return num;
	}
	
	public int getRecords() {
		int num = 0;
		// ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;
		String sql = "select count(*) as num from " + dbHelper.getTable();
		System.out.println(sql);
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				num = rs.getInt("num");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return num;
	}
	
	public int getRecords(String movie_id) {
		int num = 0;
		// ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;
		String sql = "select count(*) as num from "+ dbHelper.getTable()+" where m_id = '"+movie_id+"' ";
		System.out.println(sql);
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				num = rs.getInt("num");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return num;
	}

	public void updateLike(Connection con, String id, int islike) {
		ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		// Connection con = helper.connectDatabase();
		Statement s = null;
		String sql = "update " + dbHelper.getTable() + " set is_interesting = "
				+ islike + " where id = '" + id + "'";
		System.out.println(sql);
		try {
			s = con.createStatement();
			s.executeUpdate(sql);
			System.out.println("update like successful ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("update like error ");
		}

	}
	
	public JSONArray getPicJSONArray(String movie_id) throws Exception{
		
		JSONArray json = new JSONArray();
		//JSONObject j = new JSONObject();
		List<PictureBean> pictures = new ArrayList<PictureBean>();
		ToJSON tojson = new ToJSON();
		// ProjectHelper helper = new ProjectHelper();
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		Connection con = dbHelper.connectDatabase();
		Statement s = null;
		ResultSet rs = null;

		String sql = "select * from " + dbHelper.getTable() + "m join "+dbHelper.getTable_movie()+" c on  m.m_id = c.movie_id where movie_id='" + movie_id+"' and interesting = 1 limit 0, 100"  ;
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			int i = 0 ; 
			while(rs.next()){
				JSONObject issue_json = new JSONObject();
				issue_json.put("id", i+"");
				issue_json.put("name", rs.getString("title"));
				issue_json.put("description", rs.getString("description"));
				issue_json.put("destUrl", rs.getString("source_link"));
				JSONArray temp = new JSONArray(); 
			//	JSONObject obj = new JSONObject();
				temp.put(rs.getString("p_url"));
				issue_json.put("images",temp);
				issue_json.put("category", "");
				//issue_json.put("tags", "");
				issue_json.put("levelOfInterest",1);
				i++;
				json.put(issue_json);
			}
			System.out.println("the number of data ---->"+i);
			//System.out.println(json.toString());
			//json = tojson.toJSONArray(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				DBConnectionHelper.closeResultSet(rs);
			}
			if (s != null) {
				DBConnectionHelper.closeStatement(s);
			}
			if (con != null) {
				DBConnectionHelper.closeConnection(con);
			}

		}
		return json;
		
		
	}

}
