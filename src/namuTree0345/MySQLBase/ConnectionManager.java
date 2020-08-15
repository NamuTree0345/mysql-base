package namuTree0345.MySQLBase;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectionManager {
	
    static java.sql.Connection conn = null;
	static Statement stmt = null;
    static ResultSet rs = null;
    static ConnectEvent ce = null;
    static boolean isUpdated = false;
    static int num = 5;
	
    public static void registerConnectEvent(ConnectEvent coe) {
    	ce = coe;
    }
    
	public static java.sql.Connection ConnectToServer(String address, String username, String password, String database, int port) {
		
		try{
        	Class.forName("com.mysql.cj.jdbc.Driver");

        	String url = "jdbc:mysql://" + ":" + Integer.toString(port) + "/" + database +"?serverTimezone=UTC";
        	//String url = "jdbc:mysql://localhost/choculandtest?serverTimezone=UTC";
        
        	conn = DriverManager.getConnection(url, username, password);
        	//conn = DriverManager.getConnection(url, "root", "min76081234");
        	ce.onConnect(conn);
    	}
    	catch(ClassNotFoundException ex){
    		ce.failedToConnect(ex, conn);
    	}
    	catch(SQLException e){
    		ce.failedToConnect(e, conn);
    	}
    	finally{
        	/*try{
            	if( conn != null && !conn.isClosed()){
                	conn.close();
            	}
        	}
        	catch( SQLException e){
            	e.printStackTrace();
        	}*/
        	
    	}
    	
    	return conn;
		
	}
	
	public static ResultSet runExecuteQuery(String query, java.sql.Connection connect) {
	    
     	 try {
     		 Class.forName("com.mysql.cj.jdbc.Driver");
     		 
     		 stmt = connect.createStatement();
     		 
     		 rs = stmt.executeQuery(query);
     		 
     		 ce.onQueryRuned(connect, rs);
     	 }
      	catch(ClassNotFoundException ex){
         	ce.failedToConnect(ex, connect);
     	}
     	catch(Exception e){
     		ce.failedToConnect(e, connect);
     	}
     	finally{
         	/*try{
             	if( conn != null && !conn.isClosed()){
                 	conn.close();
             	}
         	}
         	catch( SQLException e){
             	e.printStackTrace();
         	}*/
         	
     	}
     	
     	return rs;
     	
     }
	
	public static int runUpdateQuery(String query, java.sql.Connection connect) {
	    
    	 try {
    		 Class.forName("com.mysql.cj.jdbc.Driver");
    		 
    		 stmt = connect.createStatement();
    		 
    		 num = stmt.executeUpdate(query);
    		 
    		 ce.onUpdateQueryRuned(connect, num);
    	 }
     	catch(ClassNotFoundException ex){
        	ce.failedToConnect(ex, connect);
    	}
    	catch(Exception e){
    		ce.failedToConnect(e, connect);
    	}
    	finally{
        	/*try{
            	if( conn != null && !conn.isClosed()){
                	conn.close();
            	}
        	}
        	catch( SQLException e){
            	e.printStackTrace();
        	}*/
        	
    	}
    	
    	return num;
    	
    }
	
	public static void closeConnect(java.sql.Connection con) {
		try{
         	if( con != null && !con.isClosed()){
             	con.close();
             	ce.onConnectionClosed(conn);
         	}
     	}
     	catch( SQLException e){
     		ce.failedToConnect(e, conn);
     	}
	}

}
