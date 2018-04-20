package beanutils;

import entity.Address;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BeanUtilsTest {

    @Test
    public void cloneBeanTest() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Address address = new Address("testAddress");
        Address cloneAddress = (Address)BeanUtils.cloneBean(address);
        assertEquals(address.getAddressStr(),cloneAddress.getAddressStr());
    }

    @Test
    public void copyPropertiesTest() throws InvocationTargetException, IllegalAccessException {
        Address origAddress = new Address("testAddress");
        Address destAddress = new Address();
        BeanUtils.copyProperties(destAddress,origAddress);
        assertEquals(destAddress.getAddressStr(),origAddress.getAddressStr());
    }

    @Test
    public void copyPropertyTest() throws InvocationTargetException, IllegalAccessException {
        Address origAddress = new Address("testAddress");
        Address destAddress = new Address();
        BeanUtils.copyProperty(destAddress,"addressStr",origAddress.getAddressStr());
        assertEquals(destAddress.getAddressStr(),destAddress.getAddressStr());
    }

    //WeakFastHashMap<K, V>
    @Test
    public void createCacheTest(){
        Map<String,String> cache = BeanUtils.createCache();
        cache.put("key","value");
        assertEquals("value",cache.get("key"));
    }

    @Test
    public void describeTest() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Address address = new Address("testAddress");
        Map<String, String> describe = BeanUtils.describe(address);
        System.out.println(describe);
    }

    @Test
    public void getArrayPropertyTest() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Address address = new Address("testAddress");
        String[] addressStrs = BeanUtils.getArrayProperty(address, "addressStr");
        System.out.println(Arrays.asList(addressStrs));
    }

    @Test
    public void  getCacheFastTest(){
        Map<String,String> normalMap = new HashMap<>();
        assertFalse(BeanUtils.getCacheFast(normalMap));

        //..............
        Map<String,String> fastMap = BeanUtils.createCache();
        assertFalse(BeanUtils.getCacheFast(fastMap));
    }

    @Test
    public void  getIndexedPropertyTest() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Address address = new Address();
        address.getAddressList().add("address0");
        address.getAddressList().add("address1");
        address.getAddressList().add("address2");

        String indexedProperty = BeanUtils.getIndexedProperty(address, "addressList[1]");
        assertEquals("address1",indexedProperty);

        indexedProperty = BeanUtils.getIndexedProperty(address, "addressList",2);
        assertEquals("address2",indexedProperty);
    }

    @Test
    public void getMappedPropertyTest() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Address address = new Address();
        address.getAddressMap().put("0","addrss0");
        address.getAddressMap().put("1","addrss1");
        address.getAddressMap().put("2","addrss2");

        String mappedProperty = BeanUtils.getMappedProperty(address, "addressMap(1)");
        assertEquals("addrss1",mappedProperty);

        mappedProperty = BeanUtils.getMappedProperty(address, "addressMap","2");
        assertEquals("addrss2",mappedProperty);
    }

    @Test
    public void  getNestedPropertyTest() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Address address = new Address();

        String nestedProperty = BeanUtils.getNestedProperty(address, "point.x");
        assertEquals("0.0",nestedProperty);
    }

    @Test
    public void  getPropertyTest() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Address address = new Address();

        String property = BeanUtils.getProperty(address, "point.x");
        assertEquals("0.0",property);
    }

    @Test
    public void  getSimplePropertyTest() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Address address = new Address("testAddress");

        String addressStr = BeanUtils.getSimpleProperty(address, "addressStr");
        assertEquals("testAddress",addressStr);
    }

    @Test
    public void  initCauseTest(){
        boolean result = BeanUtils.initCause(new Exception(),new ClassNotFoundException());
        assertTrue(result);
    }

    @Test
    public void  populateTest() throws InvocationTargetException, IllegalAccessException {
        Map<String,String> dataMap=new HashMap<>();
        dataMap.put("addressStr","testAddress");
        Address address = new Address();

        BeanUtils.populate(address,dataMap);
        assertEquals("testAddress",address.getAddressStr());
    }

    @Test
    public void setCacheFastTest(){
        Map<String,String> fastMap = BeanUtils.createCache();
        BeanUtils.setCacheFast(fastMap,true);
        assertTrue(BeanUtils.getCacheFast(fastMap));
    }

    @Test
    public void setPropertyTest() throws InvocationTargetException, IllegalAccessException {
        Address address = new Address();

        BeanUtils.setProperty(address,"addressStr","newAddress");
        assertEquals("newAddress",address.getAddressStr());
    }
}
