package namuTree0345.MySQLBase;

import java.sql.Connection;
import java.sql.ResultSet;

public interface ConnectEvent {
	
	public void onConnect(Connection conn);
	public void failedToConnect(Exception ex, Connection conn);
	public void onQueryRuned(Connection connect, ResultSet rs);
	public void onConnectionClosed(Connection conn);
	public void onUpdateQueryRuned(Connection connect, int rs);

}
