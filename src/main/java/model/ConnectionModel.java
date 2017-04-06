package main.java.model;

import main.java.Components.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import static main.java.model.Command.*;

/**
 * Created by thesinding on 3/18/17.
 */
public class ConnectionModel {
    private String dbUser, dbPassword, dbUrl;

    private Statement statement;

    private Connection dbConn;

    public ConnectionModel(String url, String user, String dbPass) throws SQLException  {
        this.dbUser     = user;
        this.dbPassword = dbPass;
        this.dbUrl      = url;

        // Create the connection to test the credentials
        this.dbConn     = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
        // Just close it, just needed to check if it could connect.
        this.dbConn.close();
    }

    public ArrayList<ComputerSystem> getComputerSystem(){
        HashMap<String, String> toComputerSystem = new HashMap<>();
        ArrayList<ComputerSystem> list = new ArrayList<>();
        String sql = "SELECT * FROM computersystems";

        try {
            this.dbConn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
            int columnIndex = 1;
            Statement smtm = this.dbConn.createStatement();
            ResultSet resultSet = smtm.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                while (columnIndex <= metaData.getColumnCount()) {
                    toComputerSystem.put(metaData.getColumnName(columnIndex), resultSet.getString(columnIndex));
                    columnIndex++;
                }
                columnIndex = 1;
                list.add(new ComputerSystem(toComputerSystem));
            }

            resultSet.close();
            statement.close();
            dbConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }

    public ArrayList<ComputerComponent> getComponents(){
        ArrayList<ComputerComponent> componentsList = new ArrayList<>();
        String sql = "SELECT * FROM ONLY components " +
        "NATURAL FULL JOIN cpu " +
        "NATURAL FULL JOIN systemcase " +
        "NATURAL FULL JOIN ram " +
        "NATURAL FULL JOIN graphicscard " +
        "NATURAL FULL JOIN motherboard" +
                " ORDER BY kind ASC ";

        try {
            // Open Connection
            this.dbConn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);
            // Create statement
            statement = this.dbConn.createStatement();
            // Execute the sql statement and put it in a Resultset
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();

            while (resultSet.next()){
                HashMap<String, String> temp = new HashMap<>();
                int columnIndex = 1;
                while (columnIndex <= metaData.getColumnCount()){
                    temp.put(metaData.getColumnName(columnIndex), resultSet.getString(columnIndex));
                    columnIndex++;
                }
                columnIndex = 1;


                switch (resultSet.getString("kind")){
                    case "cpu":
                        componentsList.add(new Cpu(temp));
                        break;
                    case "systemcase":
                        componentsList.add(new SystemCase(temp));
                        break;
                    case "ram":
                        componentsList.add(new Ram(temp));
                        break;
                    case "motherboard":
                        componentsList.add(new Motherboard(temp));
                        break;
                    case "graphicscard":
                        componentsList.add(new GraphicsCard(temp));
                        break;
                }
            }

            // Good idea to close all
            resultSet.close();
            statement.close();
            dbConn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return componentsList;
    }


    public ResultSet insert(String table, HashMap<String, String> values){
        try {
            // Generate SQL String
            String sql = this.createStatement(INSERT, table, values);

            //Open connection
            this.dbConn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);

            statement = this.dbConn.createStatement();
            statement.executeUpdate(sql);

            // Close the connections
            statement.close();
            this.dbConn.close();

            // Return the results
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(String table, HashMap<String, String> values) throws SQLException{
            // Generate SQL String
            String sql = this.createStatement(UPDATE, table, values);

//            Open connection
            this.dbConn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword);

            statement = this.dbConn.createStatement();
            statement.executeUpdate(sql);

            // Close the connections
            statement.close();
            this.dbConn.close();

//             Return the results
    }
    private String createStatement(Command type, String table, HashMap<String, String> values){
        String sql = "";
        int i = 0;
        int valuesLength = values.size() - 1;
        switch (type){
            case INSERT:
                sql += INSERT.getCommand();
                sql += table;
                sql += "(";

                for(String s : values.keySet()){
                    if(i++ != values.size() - 1){
                        sql += s + ", ";
                    } else {
                        sql += s + ")";
                    }
                }
                sql += " VALUES (";

                i = 0;
                for(String s : values.values()){
                    if(i++ != values.size() - 1){
                        sql += s + ", ";
                    } else {
                        sql += s + ")";
                    }
                }
                break;


            case UPDATE:
                sql += UPDATE.getCommand();
                sql += table;
                sql += " SET ";
                for(String s : values.keySet()){
                    sql += s + " = " + values.get(s);
                    if(values.size() > 1 && i != values.size() - 1){
                        i++;
                        sql += ",";
                    }
                }
                sql += " WHERE productid = ";
                sql += values.get("productid");
//                System.out.println(sql);
                break;

            case SELECT:
                sql += SELECT.getCommand();
                break;

            case DROP:
                sql += DROP.getCommand();
                break;
        }

        return sql;
    }
    public boolean isNumeric(String s) {
        return s.matches("[-+]?\\d*\\.?\\d+");
    }
}
