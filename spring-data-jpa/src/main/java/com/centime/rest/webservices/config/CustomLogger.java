package com.centime.rest.webservices.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomLogger {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Around("execution(* *(..)) && @annotation(com.centime.rest.webservices.config.LogMethodCall)")
    public Object logMethods(ProceedingJoinPoint jp) throws Throwable {
        String methodName = jp.getSignature().getName();
        logMethodInvocationAndParameters(jp);
        Object result = jp.proceed(jp.getArgs());
        return result;
    }

    private void logMethodInvocationAndParameters(ProceedingJoinPoint jp) {
        String[] argNames = ((MethodSignature) jp.getSignature()).getParameterNames();
        Object[] values = jp.getArgs();
        Map<String, Object> params = new HashMap<>();
        if (argNames.length != 0) {
            for (int i = 0; i < argNames.length; i++) {
                params.put(argNames[i], values[i]);
            }
        }
        //Reporter.log("-> method " + jp.getSignature().getName() + " invocation", true);
        if (!params.isEmpty()) System.out.println((gson.toJson(params)));
    }

}
