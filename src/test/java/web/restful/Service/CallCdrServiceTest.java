package web.restful.Service;

import org.junit.Test;
import web.restful.model.CallCdr;

import static org.junit.Assert.*;

/**
 * Created by sponge on 2017/6/28.
 */
public class CallCdrServiceTest {
//    @Test
    public void getcdr() throws Exception {
        CallCdrService callCdrService = new CallCdrService();
        CallCdr callCdr =callCdrService.getcdr("18628353733");
        System.out.println(callCdr);
    }

}