package org.example.ThreeLayerCS.Dao;

public class Contact {
    //姓名、住址、电话
    public String name;
    public String Address;
    public double tel;

    public Contact() {
    }

    public Contact(String name, String address, double tel) {
        this.name = name;
        Address = address;
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public double getTel() {
        return tel;
    }

    public void setTel(double tel) {
        this.tel = tel;
    }


}
