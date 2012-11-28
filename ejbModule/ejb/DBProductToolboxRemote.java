package ejb;
import java.sql.ResultSet;

import javax.ejb.Remote;

@Remote
public interface DBProductToolboxRemote {
	public int getStockQuantity(int productId);
	public int getDelay(int productId);
	public ResultSet getRecord(int productId);
	public boolean updateRecord(int productId, int stockQuantity);
	public boolean deleteRecord(int productId);
	public void closeConn();
}
