package entity;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Address {
    private String addressStr;
    private List<String> addressList = new ArrayList<>();
    private Map<String,String> addressMap = new HashMap<>();
    private Point2D point = new Point2D(0,0);

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

    public List<String> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<String> addressList) {
        this.addressList = addressList;
    }

    public Map<String, String> getAddressMap() {
        return addressMap;
    }

    public void setAddressMap(Map<String, String> addressMap) {
        this.addressMap = addressMap;
    }

    public Point2D getPoint() {
        return point;
    }

    public void setPoint(Point2D point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return addressStr;
    }
}
