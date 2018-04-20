package beanutils;

import converter.AddressConverter;
import entity.Address;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.*;

public class ConvertUtilsTest {

    @Test
    public void convertTest(){
        Address address = new Address("testAddress");
        String convert = ConvertUtils.convert(address);
        assertEquals(address.toString(),convert);

        Integer i = 0;
        Long l = -1l;
        l = (Long) ConvertUtils.convert(i, Long.class);
        assertEquals(i.intValue(),l.intValue());

        String[] values = new String[]{"1"};
        Integer[] intArray = (Integer[]) ConvertUtils.convert(values, Integer.class);
        assertEquals(1,intArray[0].intValue());

        i = (Integer) ConvertUtils.convert("2", Integer.class);
        assertEquals(2,i.intValue());
    }

    @Test
    public void deregisterTest(){
        AddressConverter addressConverter = new AddressConverter();
        ConvertUtils.register(addressConverter,Address.class);
        Converter lookup = ConvertUtils.lookup(Address.class);
        assertEquals(lookup,addressConverter);

        ConvertUtils.deregister();
        lookup = ConvertUtils.lookup(Address.class);
        assertEquals(lookup,null);
    }

    @Test
    public void lookupTest(){
        AddressConverter addressConverter = new AddressConverter();
        ConvertUtils.register(addressConverter,Address.class);
        Converter lookup = ConvertUtils.lookup(Address.class);
        assertEquals(lookup,addressConverter);
    }

    @Test
    public void primitiveToWrapperTest(){
        Class<Integer> integerClass = ConvertUtils.primitiveToWrapper(int.class);
        assertEquals(Integer.class,integerClass);
    }

    @Test
    public void registerTest(){
        AddressConverter addressConverter = new AddressConverter();
        ConvertUtils.register(addressConverter,Address.class);
        Address address = (Address)ConvertUtils.convert("testAddress", Address.class);
        assertEquals("testAddress",address.getAddressStr());

        Address address1 = (Address)ConvertUtils.convert(new Integer(1), Address.class);
        assertEquals("1",address1.getAddressStr());
    }

}
