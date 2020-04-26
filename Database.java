import java.sql.*;
import java.util.*;

public class Database {
    Connection con = null;
    static final String username = "root";
    static final String password = "root";
    static final String database = "FoodOrdering";
    public Database() throws Exception {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/"+database;
            Class.forName(driver);
            con = DriverManager.getConnection(url,username,password);
            System.out.println("Connected!!");
        }
        catch (Exception e) { System.out.println(e); }
    }

    public void createTable(String statement) throws Exception {
        try {
            Statement create = con.createStatement();
            create.executeUpdate(statement);
            System.out.println("Table created.");
            create.close();
        }
        catch (Exception e) { System.out.println(e); }
    }

    public void insertData(String tableName, ArrayList<String> data) throws Exception {
        try {
            int i;
            Statement insert = con.createStatement();
            String query = "INSERT INTO "+tableName+" VALUES ('";
            for (i = 0; i < data.size() - 1; i++) {
                query += data.get(i)+"', '";
            }
            query += data.get(i)+"');";
            insert.executeUpdate(query);
            System.out.println("Data inserted.");
            insert.close();
        }
        catch (Exception e) { System.out.println(e); }

        //Note: Any data of type string in arraylist should be in single quotes.
    }

    public void insertDataWFunc(String tableName, ArrayList<String> data) throws Exception {
        try {
            int i;
            Statement insert = con.createStatement();
            String query = "INSERT INTO "+tableName+" VALUES (";
            for (i = 0; i < data.size() - 1; i++) {
                query += data.get(i)+", ";
            }
            query += data.get(i)+");";
            insert.executeUpdate(query);
            System.out.println("Data inserted.");
            insert.close();
        }
        catch (Exception e) { System.out.println(e); }

        //Note: Any data of type string in arraylist should be in single quotes.
    }

    public void deleteData(String tableName, String primaryKey, String primaryKeyValue) throws Exception {
        try {
            Statement delete = con.createStatement();
            String query = "DELETE FROM "+tableName+" WHERE "+primaryKey+"= '"+primaryKeyValue+"'";
            delete.executeUpdate(query);
            System.out.println("Record deleted.");
            delete.close();
        }
        catch (Exception e) { System.out.println(e); }
    }
    public String selectRow(String tableName, String primaryKey, String primaryKeyValue, String column) throws Exception {
        String dataCell="";
        try {
            int i;
            Statement select = con.createStatement();
            String query = "SELECT "+column+" FROM "+tableName+" WHERE "+primaryKey+"='"+primaryKeyValue+"';";
            ResultSet rs = select.executeQuery(query);
            if(rs.next()){
                dataCell= rs.getString(column);
                System.out.println("Data selected.");
            }
            select.close();
        }
        catch (Exception e) { System.out.println(e); }
        return  dataCell;
    }
    public ArrayList<String> selectColumn(String tableName, String column) throws Exception {
        ArrayList<String> result = new ArrayList<>();
        try {
            int i;
            Statement select = con.createStatement();
            String query = "SELECT "+column+" FROM "+tableName+";";
            ResultSet rs = select.executeQuery(query);
            while(rs.next()){
                result.add(rs.getString(column));
            }
            System.out.println("Data selected.");
            select.close();
        }
        catch (Exception e) { System.out.println(e); }
        return  result;
    }
    public void updateData(String tableName, String primaryKeyValue, ArrayList<String> data) throws Exception {
        try {
            int i;
            ArrayList<String> colNames = getColumnNames(tableName);
            String id = colNames.remove(0);//UserID first col
            Statement update = con.createStatement();
            String query = "UPDATE "+tableName+" SET ";
            for (i = 0; i < data.size()-1; i++) {
                query += colNames.get(i)+" = "+data.get(i)+", ";
            }
            query += colNames.get(i)+" = "+data.get(i);
            query += " WHERE "+id+" = "+primaryKeyValue;

            update.executeUpdate(query);
            System.out.println("Data updated.");
            update.close();
        }
        catch (Exception e) { System.out.println(e); }
    }

    public ArrayList<String> getColumnNames(String tableName) throws Exception {
        ArrayList<String> colNames = new ArrayList<>();

        try {
            Statement statement = con.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM "+tableName);

            ResultSetMetaData metadata = results.getMetaData();
            int columnCount = metadata.getColumnCount();

            for (int i=1; i<=columnCount; i++) {
                colNames.add(metadata.getColumnName(i));
            }
        }
        catch (Exception e) { System.out.println(e); }

        return colNames;
    }
    public void updateTable(String tableName,String primaryKey,String primaryKeyValue, ArrayList<String> data, ArrayList<String> columnNames) throws Exception {
        try {
            int i;
            Statement update = con.createStatement();
            String query = "UPDATE "+tableName+" SET ";
            for (i = 0; i < data.size()-1; i++) {
                query += columnNames.get(i)+" = "+data.get(i)+", ";
            }
            query += columnNames.get(i)+" = "+data.get(i);
            query += " WHERE "+primaryKey+" = "+primaryKeyValue;

            update.executeUpdate(query);
            System.out.println("Data updated.");
            update.close();
        }
        catch (Exception e) { System.out.println(e); }
    }
}