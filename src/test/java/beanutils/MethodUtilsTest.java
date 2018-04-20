package beanutils;

import org.apache.commons.beanutils.MethodUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MethodUtilsTest {

    @Test
    public void clearCacheTest(){
        int i = MethodUtils.clearCache();
        assertEquals(0,i);
    }

    @Test
    public void  getAccessibleMethodTest(){

    }

}
