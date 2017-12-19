package com.zhz.demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * descrition：
 *
 * @author: ZHZ
 * @create: 2017/12/19
 * @company: www.ideabinder.com
 */
public class JsonUtils {

    // 定义通用的 jackson对象
    private static final ObjectMapper COMMON_MAPPER = new ObjectMapper();
    //为移动端定义特殊的jackson对象(未使用)
    private static final ObjectMapper MOBILE_MAPPER = new ObjectMapper();

    private static final ObjectMapper SNAKE_MAPPER = new ObjectMapper();

    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);


    /**
     * 对象转化成json
     *
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        try {
            String string = COMMON_MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            logger.error("对象序列化成Json失败", e);
        }
        return null;
    }


    /**
     * 对象按照指定的格式转化成json
     * 这里用了一个新的objectMapper
     *
     * @param data
     * @return
     */
//    @Deprecated
//    public static String objectToJson(Object data, PropertyNamingStrategy propertyNamingStrategy) {
//        try {
//            MOBILE_MAPPER.setSerializerFactory(MOBILE_MAPPER.getSerializerFactory().withSerializerModifier
//                    (new MyBeanSerializerModifier()));
//            MOBILE_MAPPER.setPropertyNamingStrategy(propertyNamingStrategy);
//            String string = MOBILE_MAPPER.writeValueAsString(data);
//            return string;
//        } catch (JsonProcessingException e) {
//            logger.error("对象序列化成Json失败", e);
//        }
//        return null;
//    }


//    @Deprecated
//    public static String objectToJson(Object data, PropertyNamingStrategy propertyNamingStrategy) {
//        try {
//            COMMON_MAPPER.setPropertyNamingStrategy(propertyNamingStrategy);
//            String string = COMMON_MAPPER.writeValueAsString(data);
//            return string;
//        } catch (JsonProcessingException e) {
//            logger.error("对象序列化成Json失败", e);
//        }
//        return null;
//    }


    /**
     * json转化成对象
     *
     * @param jsonData
     * @param beanType
     * @return
     */

    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = COMMON_MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            logger.error("Json序列化成对象失败", e);
        }
        return null;
    }

//    /**
//     * json转化成对象
//     *
//     * @param jsonData
//     * @param beanType
//     * @return
//     */
//
//    public static <T> T jsonToPojoLine2Snake(String jsonData, Class<T> beanType) {
//        try {
//              SNAKE_MAPPER.setPropertyNamingStrategy(CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
//            T t = SNAKE_MAPPER.readValue(jsonData, beanType);
//            return t;
//        } catch (Exception e) {
//            logger.error("Json序列化成对象失败", e);
//        }
//        return null;
//    }

    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     *
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = COMMON_MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = COMMON_MAPPER.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            logger.error("Json序列化成List集合失败", e);
        }

        return null;
    }

    /**
     * map转pojo
     *
     * @param data
     * @param beanType
     * @param <T>
     * @return
     */
    public static <T> T objToPojo(Object data, Class<T> beanType) {
        try {
            String string = COMMON_MAPPER.writeValueAsString(data);
            T t = COMMON_MAPPER.readValue(string, beanType);
            return t;
        } catch (Exception e) {
            logger.error("Map转化成pojo对象失败", e);
        }
        return null;
    }

}

