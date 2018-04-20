package beanutils;

import entity.Address;
import entity.Employee;
import org.apache.commons.beanutils.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyUtilsTest {

    @Test
    public void getSimplePropertyTest() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Employee employee=new Employee();
        employee.setFirstName("FirstName");
        employee.setLastName("LastName");

        String firstName=(String)PropertyUtils.getSimpleProperty(employee,"firstName");
        String lastName=(String)PropertyUtils.getSimpleProperty(employee,"lastName");
        assertEquals("FirstName",firstName);
        assertEquals("LastName",lastName);
    }

    @Test
    public void setSimplePropertyTest() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Employee employee=new Employee();

        PropertyUtils.setSimpleProperty(employee,"firstName","NewFirstName");
        PropertyUtils.setSimpleProperty(employee,"lastName","NewLastName");
        assertEquals("NewFirstName",employee.getFirstName());
        assertEquals("NewLastName",employee.getLastName());
    }

    @Test
    public void getIndexedPropertyTest() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Employee employee=new Employee();
        for(int i=0;i<3;i++){
            Address tempAddress=new Address("TempAddress"+i);

            Employee tempEmployee=new Employee();
            tempEmployee.setFirstName(String.valueOf(i));
            tempEmployee.setAddress("tempAddress",tempAddress);

            employee.setSubordinate(i,tempEmployee);
        }

        int index=0;
        String indexStr="subordinate["+index+"]";
        Employee subordinate=(Employee)PropertyUtils.getIndexedProperty(employee,indexStr);
        assertEquals(employee.getSubordinate(0),subordinate);

        index++;
        Employee subordinate1=(Employee)PropertyUtils.getIndexedProperty(employee,"subordinate",index);
        assertEquals(employee.getSubordinate(1),subordinate1);
    }

    @Test
    public void getMappedPropertyTest() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Employee employee=new Employee();
        Address homeAddress=new Address("HomeAddress");
        employee.setAddress("home",homeAddress);

        Address getAddress=(Address)PropertyUtils.getMappedProperty(employee,"address","home");
        assertEquals(employee.getAddress("home"),getAddress);
    }

    @Test
    public void setMappedPropertyTest() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Employee employee=new Employee();
        Address newHomeAddress=new Address("NewHomeAddress");

        PropertyUtils.setMappedProperty(employee,"address","home",newHomeAddress);
        assertEquals(newHomeAddress,employee.getAddress("home"));
    }

    @Test
    public void getNestedPropertyTest() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Employee employee=new Employee();
        Address newHomeAddress=new Address("NewHomeAddress");

        PropertyUtils.setMappedProperty(employee,"address","home",newHomeAddress);
        String addressStr=(String)PropertyUtils.getNestedProperty(employee,"address(home).addressStr");
        assertEquals("NewHomeAddress",addressStr);
    }

    @Test
    public void getPropertyTest() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Employee employee=new Employee();
        for(int i=0;i<3;i++){
            Address tempAddress=new Address("TempAddress"+i);

            Employee tempEmployee=new Employee();
            tempEmployee.setFirstName(String.valueOf(i));
            tempEmployee.setAddress("tempAddress",tempAddress);

            employee.setSubordinate(i,tempEmployee);
        }
        String tempAddress=(String)PropertyUtils.getProperty(employee,"subordinate[0].address(tempAddress).addressStr");
        assertEquals("TempAddress0",tempAddress);
    }

    @Test
    public void dynaBeanTest() throws InstantiationException, IllegalAccessException {
        DynaProperty[] props=new DynaProperty[]{
                new DynaProperty("address",java.util.Map.class),
                new DynaProperty("subordinate",Employee.class),
                new DynaProperty("firstName",String.class)
        };
        BasicDynaClass dynaClass=new BasicDynaClass("employee",null,props);

        DynaBean dynaBean=dynaClass.newInstance();
        dynaBean.set("address",new HashMap<>());
        dynaBean.set("subordinate",new Employee());
        dynaBean.set("firstName","DynaFistName");

        assertEquals("DynaFistName",dynaBean.get("firstName"));
    }

    @Test
    public void WrapDynaBeanTest(){
        Employee employee=new Employee();
        employee.setFirstName("FirstName");
        employee.setLastName("LastName");

        DynaBean wrapDynaBean=new WrapDynaBean(employee);
        assertEquals("FirstName",wrapDynaBean.get("firstName"));
    }

    @Test
    public void LazyDynaBeanTest(){
        DynaBean lazyDynaBean=new LazyDynaBean();
        lazyDynaBean.set("age",11);
        lazyDynaBean.set("customer","firstName","CustomerFirstName");
        lazyDynaBean.set("customer","lastName","CustomerLastName");
        lazyDynaBean.set("address",0,"address0");
        lazyDynaBean.set("address",1,"address1");

        assertEquals(11,lazyDynaBean.get("age"));
    }

    @Test
    public void lazyDynaMapTest(){
        DynaBean lazyDynaMap=new LazyDynaMap();
        lazyDynaMap.set("age",11);
        lazyDynaMap.set("customer","firstName","CustomerFirstName");
        lazyDynaMap.set("address",0,"address0");

        Map resultMap=((LazyDynaMap) lazyDynaMap).getMap();
        assertEquals(11,resultMap.get("age"));
    }

}
