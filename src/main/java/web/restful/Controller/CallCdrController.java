package web.restful.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.restful.Service.CallCdrService;
import web.restful.Service.UserService;
import web.restful.model.CallCdr;

import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.get;
import static web.restful.render.JsonUtil.JsonTrans;
import static web.restful.render.JsonUtil.toJson;

/**
 * Created by sponge on 2017/6/28.
 */
public class CallCdrController {
    private static final Logger LOG = LoggerFactory.getLogger(CallCdrController.class);

    public CallCdrController(final CallCdrService callCdrService) {

        //获取所有用户
        get("/calls/:phone", (req, res) -> {
            String phone = req.params(":phone");
            CallCdr callCdr = callCdrService.getcdr(phone);
            if (callCdr != null) {
                return  callCdr;
            }
            res.status(400);
            return new ResponseError("No info with phone  %s found", phone);
        }, JsonTrans());
    }

}
