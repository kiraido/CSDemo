package org.example.doubleCS.Dao;

import java.sql.ResultSet;

public interface contactDao {
    boolean addContact(String name, String address, double tel);
    boolean deleteContactByName(String name);
    boolean updateContact(String name, String newAddress, double newTel);
    ResultSet queryContact(String queryCondition);
}
