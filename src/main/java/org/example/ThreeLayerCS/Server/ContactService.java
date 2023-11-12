package org.example.ThreeLayerCS.Server;

import org.example.ThreeLayerCS.Dao.Contact;
import org.example.ThreeLayerCS.Dao.ContactDAO;

// 业务逻辑层示例
public class ContactService {

    private ContactDAO contactDAO;

    public ContactService() {
        this.contactDAO = new ContactDAO();
    }

    public boolean addContact(String name, String address, double tel) {
        // 这里可以添加额外的业务逻辑，比如验证输入数据等
        Contact newContact = new Contact(name, address, tel);
        return contactDAO.insertContact(newContact);
    }

    public boolean deleteContact(String name) {
        // 这里可以添加额外的业务逻辑，比如验证联系人是否存在等
        return contactDAO.deleteContactByName(name);
    }

    public Contact queryContact(String name) {
        // 这里可以添加额外的业务逻辑，比如格式化输出等
        return contactDAO.findContactByName(name);
    }

    public boolean updateContact(String name, String newAddress, double newTel) {
        // 这里可以添加额外的业务逻辑，比如验证联系人是否存在等
        Contact contactToUpdate = new Contact(name, newAddress, newTel);
        return contactDAO.updateContact(contactToUpdate);
    }
}

