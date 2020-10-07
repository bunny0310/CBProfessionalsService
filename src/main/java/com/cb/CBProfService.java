package com.cb;

import com.cb.api.resources.ProfsResource;
import com.cb.business.services.ProfsService;
import db.ProfsDAO;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.skife.jdbi.v2.DBI;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class CBProfService extends Application<DatabaseConfiguration> {
    @Override
    public void initialize(Bootstrap<DatabaseConfiguration> bootstrap) {

    }

    @Override
    public void run(DatabaseConfiguration configuration, Environment environment) throws Exception {

        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        final DBIFactory dbiFactory = new DBIFactory();
        final DBI dbi = dbiFactory.build(environment, configuration.getDataSourceFactory(), "mysql");
        final ProfsDAO profsDAO = dbi.onDemand(ProfsDAO.class);
        ProfsService profsService = new ProfsService(profsDAO);
        ProfsResource profsResource = new ProfsResource(profsService);
        environment.jersey().register(profsResource);
    }

    public static void main(String ...args) throws  Exception{
        new CBProfService().run(args);
    }
}
