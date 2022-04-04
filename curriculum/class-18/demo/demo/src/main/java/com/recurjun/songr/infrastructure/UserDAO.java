package com.recurjun.songr.infrastructure;

import com.recurjun.songr.data.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {

    private final DataSource dataSource;
    private final EntityManager em;

    public UserDAO(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    /**
     * Return all accounts owned by a given customer,given his/her external id
     *
     * @param name
     * @return
     */
    public List<User> unsafeFindAccountsByName(String name) {

        String sql = "select " + "* from User where name = '" + name + "'";

        try (Connection c = dataSource.getConnection();
             ResultSet rs = c.createStatement()
                     .executeQuery(sql)) {
            List<User> accounts = new ArrayList<>();
            while (rs.next()) {
                User acc = new User(rs.getString("name"));
                accounts.add(acc);
            }

            return accounts;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
