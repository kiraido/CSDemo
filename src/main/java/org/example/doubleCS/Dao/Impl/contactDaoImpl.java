package org.example.doubleCS.Dao.Impl;

import org.example.doubleCS.Dao.contactDao;

import java.sql.*;

public class contactDaoImpl implements contactDao {

    private Connection connectToDatabase() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/contact?useSSL=false&serverTimezone=UTC",
                    "root",
                    "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public boolean addContact(String name, String address, double tel) {
        Connection connection = connectToDatabase();
        if (connection != null) {
            try {
                String sql = "INSERT INTO contacts (name, address, tel) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, address);
                preparedStatement.setDouble(3, tel);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteContactByName(String name) {
        Connection connection = connectToDatabase();
        if (connection != null) {
            try {
                String sql = "DELETE FROM contacts WHERE name = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, name);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean updateContact(String name, String newAddress, double newTel) {
        Connection connection = connectToDatabase();
        if (connection != null) {
            try {
                String sql = "UPDATE contacts SET address = ?, tel = ? WHERE name = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, newAddress);
                preparedStatement.setDouble(2, newTel);
                preparedStatement.setString(3, name);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public ResultSet queryContact(String queryCondition) {
        Connection connection = connectToDatabase();
        if (connection != null) {
            try {
                String sql = "SELECT * FROM contacts WHERE name LIKE ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, "%" + queryCondition + "%");

                return preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
