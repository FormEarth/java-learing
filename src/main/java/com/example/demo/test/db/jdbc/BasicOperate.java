package com.example.demo.test.db.jdbc;

import com.example.demo.test.util.Util;
import org.springframework.util.StopWatch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class BasicOperate {

    private static ThreadLocalRandom random = ThreadLocalRandom.current();


    public static void batchInsert_() throws SQLException {

        Connection connection = getConnection();
        System.out.println("---auto commit:" + connection.getAutoCommit());
        PreparedStatement ps = connection.prepareStatement("insert into test(content,create_time) values (?,?)");
        for (int i = 0; i < 1000; i++) {
            ps.setString(1, Util.uuid());
            ps.setString(2, LocalDateTime.now().toString());
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
        connection.close();
    }

    /**
     * 该方法可以极大提高插入效率
     * @throws SQLException
     */
    public static void batchInsert() throws SQLException {

        Connection connection = getConnection();
        connection.setAutoCommit(false);
        System.out.println("---auto commit:" + connection.getAutoCommit());
        PreparedStatement ps = connection.prepareStatement("insert into test(content,create_time) values (?,?)");
        for (int i = 0; i < 10000; i++) {
            ps.setString(1, Util.uuid());
            ps.setString(2, LocalDateTime.now().toString());
            ps.addBatch();
            //每1000次提交一次
//            if (i % 1000 == 0) {
//                ps.executeBatch();
//                connection.commit();
//                ps.clearBatch();
//            }
        }
        ps.executeBatch();
        connection.commit();
        ps.close();
        connection.close();
    }

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "Jason0323.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {

        StopWatch watch = new StopWatch();
        watch.start();
//        batchInsert_(); // spend 41.7354003s
        batchInsert(); // spend 2.0303365s
        watch.stop();
        System.out.printf("spend %ss%n", watch.getTotalTimeSeconds());

        // 100  5.7263539s
        // 1000 39.8065311s
    }

}
