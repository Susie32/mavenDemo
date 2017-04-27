package main.webapp.webTestDome.dbcpConn;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Susie on 2017/4/18.
 */

public interface DBCAActionI{
    //update返回值为更新条数updateItemNo
    int update(String sql);

    //查询，返回值的格式为ResultSet()
    ResultSet query(String sql);

    List<HashMap> queryList(String sql);

    //返回值为Json
    String queryString(String sql);
}