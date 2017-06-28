package web.restful.Service;

import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Constant;
import utils.HbaseUtils;
import utils.Props;
import web.restful.model.CallCdr;

import java.io.IOException;

/**
 * Created by sponge on 2017/6/28.
 */
public class CallCdrService {
    private static final Logger LOG = LoggerFactory.getLogger(CallCdrService.class);
    private static final String tableName = Props.getProperties().getString(Constant.TABLE_NAME_KEY,
            Constant.DEFAULT_TABLE_NAME);

    private static final byte[] CF = Bytes.toBytes("cf");
    private static final byte[] QUALIFIER = Bytes.toBytes("info");



    public CallCdr getcdr(String phone) {
        Get get = new Get(Bytes.toBytes(phone));
        get.addColumn(CF, QUALIFIER);
        Table table = null;
        try {
            table = HbaseUtils.getTable(tableName);
            Result data = table.get(get);
            if (data.isEmpty()) {
                LOG.info("no data with phone " + phone);
                return  null;
            } else {
                String info = new String(CellUtil.cloneValue(data.rawCells()[0]));
                LOG.info("Find data with phone " + phone);
                return new CallCdr(phone, info);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            return  null;
        }finally {
            if (table != null) {
                try {
                    table.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
