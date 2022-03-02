package ru.iteco.teachbase.springjunior.account.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.iteco.teachbase.springjunior.account.aop.repository.User;

import java.util.List;

@Aspect
@Component
public class SecurityCheckAspect {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityCheckAspect.class);

    @Value("#{'${users-accept}'.split(',')}")
    private List<String> usersAccept;

    @Around(value = "allGetMethodWithUser(user)", argNames = "proceedingJoinPoint,user")
    public Object aroundAllGetMethodCheckAdvice(ProceedingJoinPoint proceedingJoinPoint, User user) throws Throwable {
        LOG.info("aroundAllGetMethodCheckAdvice: Security check method: {}, and User: {}",
            proceedingJoinPoint.getSignature().toShortString(),
            user);
        if (usersAccept.contains(user.getName())) {
            LOG.info("User: {} accept", user);
            return proceedingJoinPoint.proceed();
        } else {
            throw new RuntimeException("Access denied!");
        }
    }

    @Pointcut("execution(* get*(.., ru.iteco.teachbase.springjunior.account.aop.repository.User, ..)) && args(user, ..)")
    public void allGetMethodWithUser(User user) {}
}
