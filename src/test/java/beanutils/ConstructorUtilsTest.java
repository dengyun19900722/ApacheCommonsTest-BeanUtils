package beanutils;

import entity.Address;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConstructorUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class ConstructorUtilsTest {

    @Test
    public void  getAccessibleConstructorTest() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<Address> addressConstructor = ConstructorUtils.getAccessibleConstructor(Address.class, String.class);
        System.out.println(addressConstructor);
        Address address = addressConstructor.newInstance("newAddress");
        assertEquals("newAddress",address.getAddressStr());

        Class[] parameterTypes = new Class[]{String.class};
        Constructor<Address> addressConstructor1 = ConstructorUtils.getAccessibleConstructor(Address.class, parameterTypes);
        System.out.println(addressConstructor1);
        Address address1 = addressConstructor1.newInstance("newAddress1");
        assertEquals("newAddress1",address1.getAddressStr());

        Constructor<Address> addressConstructor2 = ConstructorUtils.getAccessibleConstructor(addressConstructor1);
        System.out.println(addressConstructor2);
        Address address2 = addressConstructor1.newInstance("newAddress2");
        assertEquals("newAddress2",address2.getAddressStr());
    }

    @Test
    public void  invokeConstructorTest() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Address address = ConstructorUtils.invokeConstructor(Address.class, "newAddress");
        assertEquals("newAddress",address.getAddressStr());

        Object[] params = new Object[]{"newAddress1"};
        Address address1 = ConstructorUtils.invokeConstructor(Address.class, params);
        assertEquals("newAddress1",address1.getAddressStr());

        Object[] params2 = new Object[]{"newAddress2"};
        Class[] parameterTypes = new Class[]{String.class};
        Address address2 = ConstructorUtils.invokeConstructor(Address.class, params2,parameterTypes);
        assertEquals("newAddress2",address2.getAddressStr());
    }

    @Test
    public void invokeExactConstructorTest() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Address address = ConstructorUtils.invokeExactConstructor(Address.class, null);
        assertEquals(null,address.getAddressStr());

        Address address1 = ConstructorUtils.invokeExactConstructor(Address.class, "newAddress1");
        assertEquals("newAddress1",address1.getAddressStr());

        Object[] params2 = new Object[]{"newAddress2"};
        Address address2 = ConstructorUtils.invokeExactConstructor(Address.class, params2);
        assertEquals("newAddress2",address2.getAddressStr());

        Object[] params3 = new Object[]{"newAddress3"};
        Class[] parameterTypes = new Class[]{String.class};
        Address address3 = ConstructorUtils.invokeExactConstructor(Address.class, params3,parameterTypes);
        assertEquals("newAddress3",address3.getAddressStr());
    }

}
