package singleton;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Singleton {

private volatile static Connection conn;
    
    private Singleton(){}
    
    public static synchronized void fecharConexao()
    {
        if(conn != null)
        {
            try
            {
                conn.close();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
    public static Connection getInstance()
    {
        if(conn == null)
        {
            synchronized (Singleton.class) 
            {
                if(conn == null)
                {
                    carregarDriver();
                    criarConexao();    
                }
            }
        }        
        
        return conn;
    }
    
    private static void carregarDriver()
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ex) 
        {
            ex.printStackTrace();
        }
    }
    
    private static void criarConexao()
    {
        try 
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ranking", "root", "root");
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
    }
}
