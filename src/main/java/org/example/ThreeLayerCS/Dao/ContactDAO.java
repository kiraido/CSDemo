package org.example.ThreeLayerCS.Dao;

import java.sql.*;
// 数据访问层示例
public class ContactDAO {

    private Connection getConnection() throws SQLException {
        // 这里的数据库连接信息应当从配置文件中获取，为简化硬编码在这里
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/contact?useSSL=false&serverTimezone=UTC",
                "root",
                "123456"
        );
    }

    public boolean insertContact(Contact contact) {
        String sql = "INSERT INTO contacts (name, address, tel) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getAddress());
            preparedStatement.setDouble(3, contact.getTel());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteContactByName(String name) {
        String sql = "DELETE FROM contacts WHERE name = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Contact findContactByName(String name) {
        String sql = "SELECT * FROM contacts WHERE name = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Contact(
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getDouble("tel")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateContact(Contact contact) {
        String sql = "UPDATE contacts SET address = ?, tel = ? WHERE name = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, contact.getAddress());
            preparedStatement.setDouble(2, contact.getTel());
            preparedStatement.setString(3, contact.getName());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

