package namuTree0345.test;

import java.sql.Connection;
import java.sql.ResultSet;

import namuTree0345.MySQLBase.ConnectEvent;

public class ConnectEEvent implements ConnectEvent {

	@Override
	public void onConnect(Connection conn) {
		System.out.println("connected!");
	}

	@Override
	public void failedToConnect(Exception ex, Connection conn) {
		System.out.println("err: " + ex.getMessage());
	}
	
	@Override
	public void onQueryRuned(Connection connect, ResultSet rs) {
		System.out.println("query runed!");
	}
	
	@Override
	public void onConnectionClosed(Connection conn) {
		System.out.println("connection closed!");
	}
	
	@Override
	public void onUpdateQueryRuned(Connection connect, int rs) {
		System.out.println("query runed! " + rs);
	}

}
