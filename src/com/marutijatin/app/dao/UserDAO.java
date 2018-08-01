package com.marutijatin.app.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;

class B{
	static{
		System.out.println("B Static Block call");
	}
}
class A{
	A(){
		System.out.println("A Cons Call");
	}
	static{
		System.out.println("A Static Block call");
	}
}


public class UserDAO {
	private Connection getConnection() throws ClassNotFoundException, SQLException{
		ResourceBundle rb = ResourceBundle.getBundle("config");
		Class.forName(rb.getString("drivername"));
		Connection connection = DriverManager.
				getConnection(rb.getString("dburl"),rb.getString("userid"),rb.getString("password"));
		return connection;
	}
	public boolean isUserExist(String UID,String Password) throws ClassNotFoundException, SQLException{
		boolean isExist = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			con = getConnection();
			pstmt = con.prepareStatement("SELECT userid,password FROM userlogin WHERE userid=? and password=?");
//			pstmt.setString(1, userDTO.getUserid());
//			pstmt.setString(2, userDTO.getPassword());
			pstmt.setString(1, UID);
			pstmt.setString(2, Password);
			rs = pstmt.executeQuery();
			if(rs.next()){
				isExist = true;
			}
		}
		finally{
			if(rs!=null){
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(con!=null){
				con.close();
			}
		}
		return isExist;
	}
 public static void main(String[] args) throws ClassNotFoundException, SQLException {
	//java.util.Scanner s = new java.util.Scanner("");
	 // Step -1 
	 // Load a Driver
	// A1 obj = new A1(); //eager
	 UserDAO ud = new UserDAO();
	 Scanner s = new Scanner(System.in);
//	 System.out.println("Enter the Class Name to Load...");
//	 String className=s.next();
//	 String fullClassName = "com.brainmentors.app.dao."+className;
//	 Class.forName(fullClassName); //lazy
	// A obj = new A();
	 //A obj2 = new A();
	 System.out.println("Enter the Userid");
	 String userid = s.next();
	 System.out.println("Enter the Password");
	 String pwd = s.next();
	 
//	 Class.forName("org.postgresql.Driver");
	 
	 // Step - 2 Create a Connection
	 // http://
	 // jdbc:postgresql://localhost:5432/onlineshop
//	 Connection connection = DriverManager
//			 .getConnection("jdbc:postgresql://localhost:5432/juneonlineshop"
//					 ,"postgres","jaibajrangbali");
	 Connection connection = ud.getConnection();
	 if(connection!=null){
		 System.out.println("Connection Created....");
	 }
	 // Step-3 Do Query
//	 Statement stmt = connection.createStatement();
//	 ResultSet rs = stmt.executeQuery("SELECT userid, password "
//	 		+ "FROM userlogin where userid='"
//			 +userid+"' AND password='"+pwd+"'");
//	 if(rs.next()){
//		 System.out.println("Welcome "+rs.getString("userid"));
//	 }
//	 else{
//		 System.out.println("Invalid Userid or Password");
//	 }
//	 rs.close();
//	 stmt.close();
//	 connection.close();
	 System.out.println(ud.isUserExist(userid, pwd));
	 s.close();
	 
}

}
 