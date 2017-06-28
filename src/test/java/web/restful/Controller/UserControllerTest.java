package web.restful.Controller;

import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import spark.Spark;
import spark.utils.IOUtils;
import web.restful.Service.UserService;
import web.restful.model.User;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by sponge on 2017/6/28.
 */
public class UserControllerTest {

    /**
     * 初始化， 并设定初始值
     * @throws Exception
     */
//    @Before
    public void setUp() throws Exception {
        UserService userService = new UserService();
        userService.createUser("1","张三", "zhangsan@163.com");
        userService.createUser("2","李四", "lisi@163.com");
        //启动服务
        new UserController(userService);
    }

    /**
     * 测试创建一条记录 ，POST 调用
     */
//    @Test
    public void addNewUserShouldBeCreated() {
        TestResponse res = request("POST", "/users?id=3&name=john&email=john@foobar.com");
        Map<String, String> json = res.json();
        assertEquals(200, res.status);
        assertEquals("john", json.get("name"));
        assertEquals("john@foobar.com", json.get("email"));
        assertNotNull(json.get("id"));
    }

    /**
     * 测试update 一条记录, PUT 调用
     */
//    @Test
    public void putUser() {
        TestResponse res = request("PUT", "/users/1?&name=jack&email=jack@163.com");
        testQuery("1", new User("1", "jack", "jack@163.com"));

    }

    /**
     * 测试查询一条记录, GET 调用
     */
//    @Test
    public void queryUser() {
        TestResponse res = request("GET", "/users/1");
        Map<String, String> json = res.json();
        assertEquals(200, res.status);
        assertEquals("张三", json.get("name"));
        assertEquals("zhangsan@163.com", json.get("email"));
    }

//    @After
    public void tearDown() throws Exception {
//        Spark.stop();
    }

    private void testQuery(String id, User user ) {
        TestResponse res = request("GET" , "/users/" + id);
        Map<String, String> json = res.json();
        assertEquals(200, res.status);
        assertEquals(user.getName(), json.get("name"));
        assertEquals(user.getEmail(), json.get("email"));
    }



    private TestResponse request(String method, String path) {
        try {
            URL url = new URL("http://localhost:4567" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }

    private static class TestResponse {

        public final String body;
        public final int status;

        public TestResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }

        public Map<String,String> json() {
            return new Gson().fromJson(body, HashMap.class);
        }
    }

}