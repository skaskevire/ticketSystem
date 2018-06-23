package com.epam.ts.dao;

import com.epam.ts.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Locale;

@Repository("jdbcUserAccountDao")
public class JDBCUserAccountDao {
    @Autowired
    private DataSource dataSource;
    private NamedParameterJdbcTemplate npjdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        npjdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void updatePrepaidAmount(final UserAccount userAccount) {
        final String sql = "UPDATE T_USER_ACCOUNT " +
                "SET PREPAID_AMOUNT = ? WHERE ID = ?";
            jdbcTemplate.update(sql, new Object[]{
                userAccount.getPrepaidAmount(),
                userAccount.getId()});
    }

    public void saveUserAccount(UserAccount userAccount) {
        Locale.setDefault(Locale.ENGLISH);
        String sql = "INSERT INTO T_USER_ACCOUNT (PREPAID_AMOUNT) VALUES (:amount)";
        PreparedStatement preparedStatement = null;

            KeyHolder key = new GeneratedKeyHolder();
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("amount", userAccount.getPrepaidAmount());
        npjdbcTemplate.update(sql,parameters, key,new String[]{"ID"});
         userAccount.setId(key.getKey().intValue());
    }
}
