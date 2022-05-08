package com.neocryptolabs.core.aop

import org.aspectj.lang.annotation.Pointcut

class CommonPointcut {
    @Pointcut("execution(public * com.dailycarelab.matcher.service..*(..) )")
    fun allServiceTarget() {
        // Do nothing
    }

    @Pointcut("execution(public * com.dailycarelab.matcher.controller..*(..) )")
    fun allApiTarget() {
        // Do nothing
    }
}