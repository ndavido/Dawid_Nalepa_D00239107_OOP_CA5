package project.deliverable2.Exceptions;

import java.sql.SQLException;

public class DAOException extends SQLException{
    public DAOException()
    {
        // not used
    }

    public DAOException(String aMessage)
    {
        super(aMessage);
    }
}
