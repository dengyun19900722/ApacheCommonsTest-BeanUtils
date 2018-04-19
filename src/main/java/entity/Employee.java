package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Employee {
    private Map<String,Address> addressMap=new HashMap<>();
    private List<Employee> employeeList=new ArrayList<>();
    private String firstName;
    private String lastName;
    private Integer age;

    public Address getAddress(String type){
        return addressMap.get(type);
    }

    public void setAddress(String type, Address address){
        addressMap.put(type,address);
    }

    public Employee getSubordinate(int index){
        return employeeList.get(index);
    }

    public void setSubordinate(int index, Employee subordinate){
        employeeList.add(index,subordinate);
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName=firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName=lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
