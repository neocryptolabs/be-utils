package com.neocryptolabs.core.aop

import com.neocryptolabs.core.util.ConvertNtimes
import com.neocryptolabs.core.util.Log
import com.neocryptolabs.core.util.convertTime
import com.neocryptolabs.core.util.roundThreeDigit
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import java.util.*


@Aspect
class ServiceTimeAspect {
    companion object : Log

    @Around("CommonPointcut.allServiceTarget()")
    @Throws(Throwable::class)
    fun measureService(joinPoint: ProceedingJoinPoint): Any {
        val start = System.nanoTime()
        return try {
            joinPoint.proceed()
        } finally {
            val finish = System.nanoTime()
            val diffNano = finish - start
            val diffSec =
                convertTime(diffNano, ConvertNtimes.NANO_TO_SEC)
            val sig = joinPoint.signature
            if (diffSec >= 3.0) {
                logger.info(
                    "[Service] {}.{}({}) : {} sec. ({}ns)\n",
                    joinPoint.target.javaClass.simpleName,
                    sig.name,
                    Arrays.toString(joinPoint.args),
                    roundThreeDigit(diffSec),
                    diffNano
                )
            } else {
                logger.debug(
                    "[Service] {}.{}({}) : {} sec. ({}ns)\n",
                    joinPoint.target.javaClass.simpleName,
                    sig.name,
                    Arrays.toString(joinPoint.args),
                    roundThreeDigit(diffSec),
                    diffNano
                )
            }
        }
    }

    @Around("CommonPointcut.allApiTarget()")
    @Throws(Throwable::class)
    fun measureApi(joinPoint: ProceedingJoinPoint): Any {
        val start = System.nanoTime()
        return try {
            joinPoint.proceed()
        } finally {
            val finish = System.nanoTime()
            val diffNano = finish - start
            val diffSec =
                convertTime(diffNano, ConvertNtimes.NANO_TO_SEC)
            val sig = joinPoint.signature
            if (diffSec >= 3.0) {
                logger.info(
                    "[API] {}.{}({}) : {} sec. ({}ns)\n",
                    joinPoint.target.javaClass.simpleName,
                    sig.name,
                    Arrays.toString(joinPoint.args),
                    roundThreeDigit(diffSec),
                    diffNano
                )
            } else {
                logger.debug(
                    "[API] {}.{}({}) : {} sec. ({}ns)\n",
                    joinPoint.target.javaClass.simpleName,
                    sig.name,
                    Arrays.toString(joinPoint.args),
                    roundThreeDigit(diffSec),
                    diffNano
                )
            }
        }
    }
}
