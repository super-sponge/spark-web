package web.restful.model;

/**
 * Created by sponge on 2017/6/28.
 */
public class CallCdr {
    private String phone;
    private String info;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public CallCdr(String phone, String info) {
        this.phone = phone;
        this.info = info;
    }

    @Override
    public String toString() {
        return "CallCdr{" +
                "phone='" + phone + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
