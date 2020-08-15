package namuTree0345.test;

import namuTree0345.MySQLBase.ConnectionManager;

public class Test1 {

	public static void main(String[] args) {
		
		ConnectionManager.registerConnectEvent(new ConnectEEvent());
		
		java.sql.Connection conn = ConnectionManager.ConnectToServer("localhost", "root", "min76081234", "choculandtest", 3306);
		//ConnectionManager.runUpdateQuery("insert into test(test) values ('asdf')", conn);
		ConnectionManager.runUpdateQuery("delete from test where test='asdf'", conn);

	}
}
