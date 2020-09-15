package db.util;

import model.Prof;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfsMapper implements ResultSetMapper<Prof> {
    @Override
    public Prof map(int index, ResultSet r, StatementContext ctx) throws SQLException {

        Prof prof = new Prof(
                r.getInt("id"),
                r.getTimestamp("createdAt"),
                r.getTimestamp("updatedAt"),
                r.getString("firstName"),
                r.getString("lastName"),
                r.getString("workEmail"),
                r.getString("schoolEmail"),
                r.getString("company"),
                r.getString("schoolName"),
                r.getString("jobTitle")
        );

        return prof;
    }
}
