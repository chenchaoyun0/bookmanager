package com.sttx.bookmanager.dictionary;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @Description
 * @author chenchaoyun[chenchaoyun@sttxtech.com]
 * @date 2017年8月26日 下午3:55:17
 */
public enum ImgLinkType {
    BookImg("01", "图书图片", "1"), //
    EBookImg("02", "电子书图片", "2"), //
    HeadImg("03", "人物头像", "3");//
    private final String idx;
    private final String name;
    private final String code;

    private ImgLinkType(String idx, String name, String code) {
        this.idx = idx;
        this.name = name;
        this.code = code;
    }

    public String getIdx() {
        return idx;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    private static Map<String, ImgLinkType> idxMap;
    private static Map<String, ImgLinkType> nameMap;
    private static Map<String, ImgLinkType> codeMap;
    static {
        idxMap = new HashMap<String, ImgLinkType>();
        nameMap = new HashMap<String, ImgLinkType>();
        codeMap = new HashMap<String, ImgLinkType>();

        for (ImgLinkType type : ImgLinkType.values()) {
            idxMap.put(type.idx, type);
            nameMap.put(type.name(), type);
            codeMap.put(type.getCode(), type);
        }
    }

    public static ImgLinkType getByIdx(String idx) {
        return idxMap.get(idx);
    }

    public static ImgLinkType getByName(String name) {
        return nameMap.get(name);
    }

    public static ImgLinkType getByCode(String code) {
        return codeMap.get(code);
    }

}
