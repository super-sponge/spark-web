package web.restful.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.restful.Service.UserService;
import web.restful.model.User;

import static  web.restful.render.JsonUtil.*;
import static spark.Spark.*;

/**
 * Created by sponge on 2017/6/28.
 */


public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    public UserController(final UserService userService) {

        //获取所有用户
        get("/users", (req, res) -> userService.getAllUsers(), JsonTrans());

        //获取单个用户
        get("/users/:id", (req, res) -> {
            String id = req.params(":id");
            User user = userService.getUser(id);
            if (user != null) {
                return user;
            }
            res.status(400);
            return new ResponseError("No user with id %s found", id);
        }, JsonTrans());

        //创建User
        post("/users", (req, res) -> userService.createUser(
                req.queryParams("id"),
                req.queryParams("name"),
                req.queryParams("email")
        ), JsonTrans());

        //更新User
        put("/users/:id", (req, res) -> userService.updateUser(
                req.params(":id"),
                req.queryParams("name"),
                req.queryParams("email")
        ), JsonTrans());


    }
}

