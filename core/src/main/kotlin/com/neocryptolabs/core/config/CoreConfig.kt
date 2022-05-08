package com.neocryptolabs.core.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.nio.charset.Charset
import java.time.Duration

@Configuration
class CoreConfig {
    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplateBuilder()
                .requestFactory { BufferingClientHttpRequestFactory(SimpleClientHttpRequestFactory()) }
                .setConnectTimeout(Duration.ofMillis(5000))
                .setReadTimeout(Duration.ofMillis(2000))
                .additionalMessageConverters(StringHttpMessageConverter(Charset.forName("UTF-8")))
                .build()
    }
    @Bean
    fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper().registerModule(KotlinModule())
        objectMapper.propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
        return objectMapper
    }
}