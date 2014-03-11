package dao;

import helper.DBConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.MovieBean;

public class MovieDao {

	public List<MovieBean> getMovies(Connection con) {
		List<MovieBean> movies = new ArrayList<MovieBean>();
	
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		/* Connection con1 = dbHelper.connectDatabase(); */
		Statement s = null;
		ResultSet rs = null;
		String sql = "select DISTINCT(m_id), movie_name from " + dbHelper.getTable() + " m join "+dbHelper.getTable_movie()+" c on  m.m_id = c.movie_id ";

		System.out.println("sqk ----"+sql);
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			
			while (rs.next()) {
				MovieBean movie = new MovieBean();
				movie.setMovie_id(rs.getString("m_id"));
				movie.setMovie_name(rs.getString("movie_name"));

				movies.add(movie);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return movies;
	}

	public int getGroups(Connection con, String movie_id) {
		DBConnectionHelper dbHelper = new DBConnectionHelper();
		/* Connection con = dbHelper.connectDatabase(); */
		Statement s = null;
		ResultSet rs = null;
		int groups = 0;
		String sql = "select DISTINCT(`group_num`) from "  +dbHelper.getTable() + "  order by `group_num` desc";
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);

			if (rs.next()) {
				groups = rs.getInt("group_num");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return groups;
	}
}
