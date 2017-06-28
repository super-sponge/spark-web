package web.restful.render;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by sponge on 2017/6/28.
 */
public class JsonUtil {
    public static String toJson(Object object) {
        //这里添加定制化输出的数据格式,这里对输入的对象转换为Json格式输出
        return new Gson().toJson(object);
    }

    public static ResponseTransformer JsonTrans() {
        return JsonUtil::toJson;
    }
}
