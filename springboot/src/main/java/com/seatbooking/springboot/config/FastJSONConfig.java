package com.seatbooking.springboot.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class FastJSONConfig {
    @Bean
    public HttpMessageConverters httpMessageConverters() {
        HttpMessageConverter<?> fastjson = fastJsonHttpMessageConverter();
        return new HttpMessageConverters(fastjson);
    }

    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter messageConverter = new FastJsonHttpMessageConverter();
        //set MediaTypes
        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        messageConverter.setSupportedMediaTypes(supportedMediaTypes);

        //fastJsonConfig
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //时区
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.QuoteFieldNames, // 双引号
                SerializerFeature.WriteMapNullValue, // 输入空值字段
                SerializerFeature.WriteEnumUsingToString, // 枚举输出STRING
                SerializerFeature.WriteNullBooleanAsFalse, // 布尔类型如果为null输出false
                SerializerFeature.WriteNullListAsEmpty, // List字段如果为null输出为[]
                //SerializerFeature.WriteNullNumberAsZero, // number类型如果为null输出0
                //SerializerFeature.WriteNullStringAsEmpty, // 字符串类型如果为null输出""
                SerializerFeature.SortField, // 按字段名称排序后进行输出
                SerializerFeature.WriteDateUseDateFormat); // 设置日期格式

        //SerializeConfig
        SerializeConfig config =new SerializeConfig();
        //Long -> String ，前端无法接收Long
        config.put(Long.class , ToStringSerializer.instance);
        config.put(Long.TYPE , ToStringSerializer.instance);
        //BigInteger -> String ，前端无法接收BigInteger
        config.put(BigInteger.class , ToStringSerializer.instance);
        fastJsonConfig.setSerializeConfig(config);

        messageConverter.setFastJsonConfig(fastJsonConfig);
        return messageConverter;
    }
}
