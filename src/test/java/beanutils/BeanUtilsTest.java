package beanutils;

import entity.Address;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

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

    @Test
    public void createCacheTest(){

    }

}
