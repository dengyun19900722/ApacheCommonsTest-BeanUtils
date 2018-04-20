package converter;

import entity.Address;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.AbstractConverter;

import java.lang.reflect.InvocationTargetException;

public class AddressConverter extends AbstractConverter {

    @Override
    protected <T> T convertToType(Class<T> type, Object value) throws Throwable {
        if(value==null){
            return null;
        }

        if(Address.class.equals(type)){
            if(value.toString()!=null){
                return (T)(new Address(value.toString()));
            }
        }

        throw conversionException(type, value);
    }

    @Override
    protected Class<Address> getDefaultType() {
        return Address.class;
    }

}
