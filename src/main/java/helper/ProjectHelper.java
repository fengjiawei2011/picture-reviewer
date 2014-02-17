package helper;

/*import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;*/
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*import java.util.ArrayList;
import java.util.List;*/
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/*import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;*/

import com.google.gson.Gson;

public class ProjectHelper {

	private String dbDriver = "com.mysql.jdbc.Driver";
	private String username = "root";
	private String password = "111111";
	private String URL = "jdbc:mysql://192.168.1.55/Rideo";

	public Connection connectDatabase() {
		Connection connection = null;
		try {
			Class.forName(dbDriver).newInstance();
			connection = DriverManager.getConnection(URL, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void write(HttpServletResponse response,
			Map<String, Object> map) {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write(new Gson().toJson(map));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Gson error");
		}
	}


	/*public static void main(String[] arg) {
		// getImagelist();
		String url = "smb://10.185.195.178/SharedDocs/fjw/";
		String dest = "D:\\Documents\\workspace-tcl\\PictureReview\\WebContent\\images";
		downloadImagesLacked(url, dest);
	}

	// remote should be the database

	
	 * remote : smb://192.168.1.35/SharedDocs/fjw/ local :
	 * D:\\Documents\\workspace-tcl\\PictureReview\\WebContent\\images
	 
	public static void downloadImagesLacked(String remote, String local) {
		List<String> remoteList = getImagelistFromRemote(remote);
		List<String> localList = getImageListFromLocal(local);
		boolean isExist = true;

		if (localList.size() == 0) {
			for (String s : remoteList) {
				smbGet(remote + s, local);
			}
			System.out.println("downloading finished");
		} else {
			for (String imgName : remoteList) {
				for (String imgName1 : localList) {
					if(imgName1.equals(imgName)) {
						isExist = true;
						break;
					}
					else isExist = false;
				}
				if(!isExist){
					smbGet(remote + imgName, local);
				}
				
			}
		}

	}

	// D:\\Documents\\workspace-tcl\\PictureReview\\WebContent\\images
	public static List<String> getImageListFromLocal(String dir) {
		List<String> imageList = new ArrayList<String>();
		File imageDir = new File(dir);

		if (imageDir.isDirectory()) {
			File[] fileList = imageDir.listFiles();
			for (File f : fileList) {
				imageList.add(f.getName());
			}
		}

		return imageList;
	}

	// smb://192.168.1.35/SharedDocs/fjw/
	public static List<String> getImagelistFromRemote(String url) {
		// smb://xxx:xxx@192.168.2.188/testIndex/
		// xxx:xxx�ǹ����������û�������
		List<String> imageList = new ArrayList<String>();
		SmbFile file = null;
		// SmbFile dest = null;
		// String url_dest = "smb://localhost/";
		// File ff = new
		// File("D:\\Documents\\workspace-tcl\\PictureReview\\WebContent\\images");

		try {
			file = new SmbFile(url);
			// dest = new SmbFile(url_dest);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (file.exists()) {
				SmbFile[] files = file.listFiles();
				for (SmbFile f : files) {
					imageList.add(f.getName());

					// f.copyTo(dest);
					System.out.println(f.getName());
				}
			}
		} catch (SmbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageList;
	}

	*//**
	 * �ӹ���Ŀ¼�����ļ�������
	 * 
	 * @param remoteUrl
	 *            ����Ŀ¼�ϵ��ļ�·��
	 * @param localDir
	 *            ����Ŀ¼
	 *//*
	public static void smbGet(String remoteUrl, String localDir) {
		InputStream in = null;
		OutputStream out = null;
		try {
			SmbFile remoteFile = new SmbFile(remoteUrl);
			// ��һ�����Ҫ
			remoteFile.connect();
			if (remoteFile == null) {
				System.out.println("�����ļ�������");
				return;
			}
			String fileName = remoteFile.getName();
			File localFile = new File(localDir + File.separator + fileName);
			in = new BufferedInputStream(new SmbFileInputStream(remoteFile));
			out = new BufferedOutputStream(new FileOutputStream(localFile));
			byte[] buffer = new byte[1024];
			while (in.read(buffer) != -1) {
				out.write(buffer);
				buffer = new byte[1024];
			}
			System.out.println(" downloading "+fileName+" successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/

}