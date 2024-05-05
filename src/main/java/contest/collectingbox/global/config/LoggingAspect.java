package contest.collectingbox.global.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    @Pointcut("within(contest.collectingbox.module..*Controller)")
    private void cut() {
    }

    @Around("cut()")
    public Object doLogging(final ProceedingJoinPoint joinPoint) throws Throwable {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        final String ipAddr = request.getRemoteAddr();
        final String method = request.getMethod();
        final String requestURI = request.getRequestURI();
        final Object[] args = joinPoint.getArgs();

        log.info("[REQUEST] {} {} {} args={}", ipAddr, method, requestURI, args);
        try{
            Object result = joinPoint.proceed();
            log.info("[RESPONSE] {}", result);
            return result;
        }catch(Exception e){
            log.error("[RESPONSE] exception message = {}", e.getMessage());
            throw e;
        }

    }
}
