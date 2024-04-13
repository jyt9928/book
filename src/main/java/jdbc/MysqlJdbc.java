package jdbc;

import com.mysql.cj.exceptions.ConnectionIsClosedException;
import com.mysql.cj.protocol.ResultsetRow;
import day3.Day3;

import java.awt.font.FontRenderContext;
import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MysqlJdbc {
    static final String url = "jdbc:mysql://localhost:3306/booksystem";
    static String user = "root";
    static String password = "123456";

    public static ArrayList<Map<String, Object>> selectSql(String sql, Object... abc) {
        ArrayList<Map<String, Object>> arrayListMap = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement(sql);
            for (int x = 0; x < abc.length; x++) {
                preparedStatement.setObject(x + 1, "%" + abc[x] + "%");
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int x = 1; x <= columnCount; x++) {
                    String columnName = resultSetMetaData.getColumnName(x);
                    Object columnValue = resultSet.getObject(columnName);
                    map.put(columnName, columnValue);
                }
                arrayListMap.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrayListMap;
    }


    public static boolean joinSql(String tableName, String[] columnName, Object[] value) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            StringBuilder stringBuilder = new StringBuilder("insert into ");
            int i = 1;
            stringBuilder.append(tableName);
            stringBuilder.append("(");
            for (int x = 0; x < columnName.length; x++) {
                stringBuilder.append(columnName[x]);
                if (i < columnName.length) {
                    stringBuilder.append(",");
                }
                i++;
            }
            stringBuilder.append(") values(");
            i = 1;
            for (int x = 0; x < value.length; x++) {
                stringBuilder.append("?");
                if (i < value.length) {
                    stringBuilder.append(",");
                }
                i++;
            }
            stringBuilder.append(")");
            PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());
            for (int x = 0; x < value.length; x++) {
                preparedStatement.setObject(x + 1, value[x]);
            }
            int execute = preparedStatement.executeUpdate();
            if (execute == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean inspectSql(String tableName, String columnName, Object value) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(tableName);
            sql.append(" where ");
            sql.append(columnName);
            sql.append(" = ?");
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.setObject(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delSql(String tableName, String columnName, Object value) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM ");
            sql.append(tableName);
            sql.append(" where ");
            sql.append(columnName);
            sql.append(" = ");
            sql.append(" ? ");
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.setObject(1, value);
            int affected = preparedStatement.executeUpdate();
            if (affected != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int changeSql(String tableName, String columnName, Object value, Object changeValue, String columnName2) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql = "UPDATE " + tableName + " SET " + columnName + " = " + "?" + " WHERE " + columnName2 + " = " + "?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.setObject(1, changeValue);
            preparedStatement.setObject(2, value);
            int quantity = preparedStatement.executeUpdate();
            return quantity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static ArrayList<Map<String, Object>> allSql(String statement, Object... value) {
        ArrayList<Map<String,Object>>arrayListMap=new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            for (int x = 0; x < value.length; x++) {
                preparedStatement.setObject(x + 1, value[x]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<>();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnCount=resultSetMetaData.getColumnCount();
                for (int x = 1; x <= columnCount;x++) {
                    String columnName = resultSetMetaData.getColumnName(x);
                    Object columnValue = resultSet.getObject(columnName);
                    map.put(columnName, columnValue);
                }
                arrayListMap.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayListMap;
    }
    public static int number(String sql,Object...value){
        try {
            Connection connection=DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            for (int x=0;x<value.length;x++){
                preparedStatement.setObject(x+1,value[x]);
            }
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                int columnNumber =resultSet.getInt(1);
                return columnNumber;
            }

        }catch (SQLException e){

        }
            return 0;
    }
    public static Object single(String sql,String columnName,Object...value){
        try {
            Connection connection=DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            for (int x=0;x<value.length;x++){
                preparedStatement.setObject(x+1,value[x]);
            }
            ResultSet resultSet=preparedStatement.executeQuery();
            Object getValue=null;
            while (resultSet.next()){
                getValue=resultSet.getObject(columnName);
                return getValue;
            }
        }catch (SQLException e){

        }
    return 0;
    }

}