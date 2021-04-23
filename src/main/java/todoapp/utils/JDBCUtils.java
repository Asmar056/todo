package todoapp.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class JDBCUtils {
    private static final String jdbcURL = "jdbc:postgresql://localhost/demo";
    private static final String jdbcUsername = "asmar";
    private static final String jdbcPassword = "@Sm@r2020";

    public static Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("Everything Ok");
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("Database works");
        return connection;
    }

    public static void printSQLException(SQLException ex){
        for (Throwable e: ex){
            if (e instanceof SQLException){
                e.printStackTrace(System.err);
                System.out.println("SQLState: " + ((SQLException) e).getSQLState());
                System.out.println("Error Code" + ((SQLException) e).getErrorCode());
                System.out.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null){
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public static Date getSQLDate(LocalDate date){
        return java.sql.Date.valueOf(date);
    }

    public static LocalDate getUtilDate(Date sqlDate){
        return sqlDate.toLocalDate();
    }
}

