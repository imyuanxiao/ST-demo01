package test.exe2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataService implements AutoCloseable{
    private Connection c;
    public static String CS = "jdbc:mariadb://localhost:3306/elections?user=vagrant&localSocket=/var/run/mysqld/mysqld.sock";

    public DataService() {
        getConnection(CS);
    }

    @Override
    public void getConnection(String s) {
        try {
            this.c = DriverManager.getConnection(s);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Party> getParties(){
        String sql = "select * from Party";
        List<Party> list = new ArrayList<>();
        try {
            PreparedStatement statement = c.prepareStatement(sql);
            ResultSet set = statement.executeQuery();

            if(!set.next()){
                throw new DataServiceException("No result!");
            }

            while (set.next()) {
                Party party = new Party();
                party.setId(set.getInt("id"));
                party.setName(set.getString("name"));
                list.add(party);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Party getParty(int id){
        String sql = "select * from Party where id = ?";
        Party party = null;
        try {
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            if(!set.next()){
                throw new DataServiceException("No result!");
            }
            while (set.next()) {
                party = new Party();
                party.setId(set.getInt("id"));
                party.setName(set.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return party;
    }

    class  DataServiceException extends RuntimeException{
        public DataServiceException (){
            super();
        }
        public DataServiceException (String msg){
            super(msg);
        }
    }

}
