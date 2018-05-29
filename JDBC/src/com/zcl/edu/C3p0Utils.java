package com.zcl.edu;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2018-05-10.
 */
public class C3p0Utils  {
    /*
   //这个是默认配置的
   private static Connection conn;
   private static ComboPooledDataSource ds = new ComboPooledDataSource();

   public static Connection getConnection() {
       try {
           conn = ds.getConnection();
       } catch (SQLException e) {
           e.printStackTrace();
       } catch (PropertyVetoException e) {
           e.printStackTrace();
       }
       return conn;
   }*/

    /*****************************************************************************************/
    //方法一 使用c3p0-config.xml文件
    /*//定义全局变量
    private static ComboPooledDataSource cpds;
    //静态代码块，命名配置
    static {
        cpds = new ComboPooledDataSource("c3p0Ds");
    }
    //获取数据源
    public static DataSource getDataSource() {
        return cpds;
    }
    //获取连接
    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }*/

    /*****************************************************************************************/
    //方法二 使用c3p0.properties文件
    @Test
    public void getConnection() throws SQLException {
        DataSource ds = new ComboPooledDataSource();//c3p0自己去读配置文件了，我们啥也不干
        Connection con = ds.getConnection();//我们直接拿一条鱼
        System.out.println("con:" + con);

        String sql = "select * from user";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getString(5));
        }
        //关闭连接
        con.close();//这是把连接还给连接池，而不是关闭连接
        rs.close();
        ps.close();
    }

    /*****************************************************************************************/
    //方法三 使用settings的方式
    /*@Test
    public void getConnection() throws Exception {
        Properties prop = new Properties();
        InputStream in = C3p0Utils.class.getClassLoader().getResourceAsStream("db.properties");
        prop.load(in);//加载信息

        ComboPooledDataSource comb = new ComboPooledDataSource();//下面就是开始配置
        comb.setDriverClass(prop.getProperty("jdbcdriver"));
        comb.setJdbcUrl(prop.getProperty("url"));
        comb.setUser(prop.getProperty("username"));
        comb.setPassword(prop.getProperty("password"));

        Connection con = comb.getConnection();//从c3p0拿一条鱼，啊呸！什么鱼啊，拿一条数据库连接
        //执行查询语句
        String sql = "select * from user";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        //打印数据库信息
        while(rs.next())
        {
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getString(5));

        }
        //关闭连接
        con.close();//这是把连接还给连接池，而不是关闭连接
        rs.close();
        ps.close();
    }*/
}
