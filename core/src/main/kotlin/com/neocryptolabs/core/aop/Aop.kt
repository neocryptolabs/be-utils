package com.neocryptolabs.core.aop

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy

@Configuration
@EnableAspectJAutoProxy
class Aop {
    @Bean
    fun serviceTimeAspect(): com.neocryptolabs.core.aop.ServiceTimeAspect {
        return com.neocryptolabs.core.aop.ServiceTimeAspect()
    }
}
