package cn.itcast.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;

/**
 * 把String转换成java.util.Date的类型转换器
 * @author qdmmy6
 *
 */
public class DateConverter implements Converter {

	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Class type, Object value) {
		if(value == null) return null;//如果要转换成值为null，那么直接返回null
		if(!(value instanceof String)) {//如果要转换的值不是String，那么就不转换了，直接返回
			return value;
		}
		String val = (String) value;//把值转换成String
		
		// 使用SimpleDateFormat进行转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(val);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
