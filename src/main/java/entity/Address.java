package entity;

public class Address {
    private String addressStr;

    public Address() {
    }

    public Address(String addressStr) {
        this.addressStr = addressStr;
    }

    public String getAddressStr() {
        return addressStr;
    }

    public void setAddressStr(String addressStr) {
        this.addressStr = addressStr;
    }
}
