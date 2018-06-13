package co.pietza.springbootstore.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object o) throws HibernateException {
        String prefix = "P";
        Connection connection = session.connection();

        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select count(product_id) as Id from Product");

            if (rs.next()) {
                int id = rs.getInt(1) + 101;
                String generatedId = prefix + new Integer(id).toString();
                return generatedId;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
