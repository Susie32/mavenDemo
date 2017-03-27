import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Susie on 2017/3/27.
 */
public class DBCPAction {
    public void update(String sql){
        //String sql = "insert into dbcp values ('101')";
        try {
            Connection conn = KCYDBCPUtil.getConnection();
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
