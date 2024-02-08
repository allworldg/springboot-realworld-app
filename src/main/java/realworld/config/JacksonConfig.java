package realworld.config;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;
import java.util.Objects;

@Configuration

public class JacksonConfig {
    // 因为customerror序列化不需要返回类名，而其他类需要包裹一个rootname，所以统一enable wrapper麻烦
    // 暂时还不会别的设置，所以暂时disable this config ,使用jsontypeinfo和 jsontypename
    // 如果同时使用也会有报错问题
//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
//        return mapper;
//    }
}
