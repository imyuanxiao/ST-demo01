package test.exe2;

import java.sql.Connection;

public interface AutoCloseable {

     void getConnection(String s);
     void close();
}
