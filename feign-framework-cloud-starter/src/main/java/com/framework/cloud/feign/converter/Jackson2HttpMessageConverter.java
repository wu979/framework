package com.framework.cloud.feign.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Feign converter
 *
 * @author wusiwei
 */
@AllArgsConstructor
public class Jackson2HttpMessageConverter implements HttpMessageConverterCustomizer {

    private final ObjectMapper objectMapper;

    @Override
    public void accept(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        converter.setSupportedMediaTypes(getMediaType());
    }

    private static List<MediaType> getMediaType() {
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        mediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        mediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        mediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        mediaTypes.add(MediaType.APPLICATION_PDF);
        mediaTypes.add(MediaType.APPLICATION_RSS_XML);
        mediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        mediaTypes.add(MediaType.APPLICATION_XML);
        mediaTypes.add(MediaType.IMAGE_GIF);
        mediaTypes.add(MediaType.IMAGE_JPEG);
        mediaTypes.add(MediaType.IMAGE_PNG);
        mediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        mediaTypes.add(MediaType.TEXT_HTML);
        mediaTypes.add(MediaType.TEXT_MARKDOWN);
        mediaTypes.add(MediaType.TEXT_PLAIN);
        mediaTypes.add(MediaType.TEXT_XML);
        return mediaTypes;
    }
}
