package com.sttx.bookmanager.util.tel;

import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

public class JSONUtil {
    // public static void main(String[] args) {
    // new JSONUtil().testjSON();
    // }

    public void testjSON() {
        String jsonStr = "{'qyyzcry':{'TABLES':'PROCESS_RYJBXX r,PRCOESS_RYKZXXB k,PROCESS_QYRYGXB g','TABLESLINK':'r.ID=k.RYID and g.RYID=r.ID and g.rylx=\"QYRYFXB009\"','PROCESS_RYJBXX':'r.ZWXM,r.ZGXL,r.ZJLX,r.ZJHM','PRCOESS_RYKZXXB':'k.ZW,k.ZC,k.CSZY','testa':{'testb':{'testc':'testctest'},'testb1':'testb1text'}}}";
        System.out.println(jsonStr);
        inputStr(jsonStr);
    }

    private static void inputStr(String jsonStr) {
        // JSONObject.fromObject(jsonObject);
        Map<String, Object> map = json2Map(jsonStr);
        for (Entry<String, Object> entry : map.entrySet()) {
            System.err.println("key: " + entry.getKey() + "---value:" + entry.getValue());
            Map<String, Object> m = (Map<String, Object>) entry.getValue();
            for (Entry<String, Object> e : m.entrySet()) {
                System.err.println("key: " + e.getKey() + "---value:" + e.getValue());
                if (e.getValue() instanceof Map) {
                    Map<String, Object> objmap = (Map<String, Object>) e.getValue();
                    for (Entry<String, Object> en : objmap.entrySet()) {
                        System.err.println("key: " + en.getKey() + "---value:" + en.getValue());
                    }
                }
            }
        }
    }

    /**
     * json字符串转map集合
     * 
     * @author ducc
     * @param jsonStr
     *            json字符串
     * @param map
     *            接收的map
     * @return
     */
    public static Map<String, Object> json2Map(String jsonStr) {
        Map<String, Object> map = JSONObject.fromObject(jsonStr);
        // 递归map的value,如果
        for (Entry<String, Object> entry : map.entrySet()) {
            json2map1(entry, map);
        }
        return map;
    }

    /**
     * json转map,递归调用的方法
     * 
     * @author ducc
     * @param entry
     * @param map
     * @return
     */
    public static Map<String, Object> json2map1(Entry<String, Object> entry, Map<String, Object> map) {
        if (entry.getValue() instanceof Map) {
            JSONObject jsonObject = JSONObject.fromObject(entry.getValue());
            Map<String, Object> map1 = JSONObject.fromObject(jsonObject);

            for (Entry<String, Object> entry1 : map1.entrySet()) {
                map1 = json2map1(entry1, map1);
                map.put(entry.getKey(), map1);
            }
        }
        return map;
    }
}
