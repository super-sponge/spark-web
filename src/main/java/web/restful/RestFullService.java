package web.restful;

import org.apache.commons.configuration.PropertiesConfiguration;
import utils.Constant;
import utils.Props;
import web.restful.Controller.CallCdrController;
import web.restful.Controller.ResponseError;
import web.restful.Controller.UserController;
import web.restful.Service.CallCdrService;
import web.restful.Service.UserService;

import javax.servlet.http.HttpServletResponse;

import static spark.Spark.*;
import static web.restful.render.JsonUtil.toJson;

/**
 * Created by sponge on 2017/6/28.
 */
public class RestFullService {


    private static  void initEnv() {
        PropertiesConfiguration props = Props.getProperties();
        int pt = props.getInt(Constant.PORT, Constant.DEFAULT_PORT);
        int maxThreads = props.getInt(Constant.MAX_THREADS, Constant.DEFAULT_MAX_THREADS);
        int minThreads = props.getInt(Constant.MIN_THREADS, Constant.DEFAULT_MIN_THREADS);
        int timeOutMillis = props.getInt(Constant.TIMEOUTMILLIS, Constant.DEFAULT_TIMEOUTMILLIS);

        threadPool(maxThreads, minThreads, timeOutMillis);
        port(pt);

        //注册处理后，参数
        after((req, res) -> {
            res.type("application/json;charset=utf-8");
        });

        //注册通用异常处理
        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });
    }
    public static void main(String[] args) {

        initEnv();

        //为了测试， 插几条数据
        UserService userService = new UserService();
        userService.createUser("1", "张三", "user@163.com");
        userService.createUser("2", "李四", "lisi@163.com");

        //演示查询用户
        UserController userController = new UserController(userService);

        //演示查询hbase数据
        CallCdrController callCdrController = new CallCdrController(new CallCdrService());

    }
}
