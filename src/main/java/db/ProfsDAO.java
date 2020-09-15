package db;

import db.util.ProfsMapper;
import model.Prof;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(ProfsMapper.class)
public interface ProfsDAO {
    @SqlQuery("SELECT * FROM professionals")
    public List<Prof> getProfs();

    @SqlQuery("SELECT * FROM professionals WHERE id = :id")
    public Prof getProf(@Bind("id") final int id);

    @SqlUpdate("Insert into professionals (firstName, lastName, workEmail, schoolEmail, company, schoolName, jobTitle) VALUES (:firstName, :lastName, :workEmail, :schoolEmail, :company, :schoolName, :jobTitle)")
    public void addProf(@BindBean Prof prof);

    @SqlUpdate("Update professionals SET firstName = :firstName, lastName = :lastName, workEmail = :workEmail, schoolEmail = :schoolEmail, schoolName = :schoolName, company = :company, jobTitle = :jobTitle WHERE id = :id")
    public void updateProf(@BindBean Prof prof, @Bind("id") final int id);

    @SqlUpdate("Delete FROM professionals WHERE id = :id")
    public void deleteProf(@Bind("id") final int id);

}
