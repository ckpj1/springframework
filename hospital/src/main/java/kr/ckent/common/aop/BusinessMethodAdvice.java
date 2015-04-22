package kr.ckent.common.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class BusinessMethodAdvice {
	 
//	private final Logger logger = Logger.getLogger(getClass());

	
	@Pointcut("execution(public * kr.actsoft..*.service..impl..*(..))")
	public void businessMethod() { }

	@Before("businessMethod()")
	public void before(JoinPoint joinPoint) {
		System.out.println("@Before: before");

//		if(logger.isDebugEnabled()){
//			Object[] args = joinPoint.getArgs();
//			logger.debug("======================="+logger.getName()+" svcBefore Start=======================");
//			logger.debug("메소드 명 : "+joinPoint.getSignature().getName());
//			for (int i = 0; i < args.length; i++) {
//				Object o = args[i];
//				logger.debug("전달 인수 : "+o);
//			}
//			logger.debug("======================="+logger.getName()+" svcBefore End=========================");
//		}	        
	}

	@AfterReturning(pointcut = "businessMethod()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) throws Exception{
		System.out.println("@AfterReturning: afterReturning");

//		if(logger.isDebugEnabled()){
//			String methodNm = joinPoint.getSignature().getName();
//			String targetNm = joinPoint.getTarget().toString();
//
//			logger.debug("======================="+logger.getName()+" svcAfterReturning Start=======================");
//			logger.debug("메소드 명 : "+methodNm);
//			logger.debug("타겟 명 : "+targetNm);
//			if(result != null){
//				logger.debug("반환 값 : "+result);	
//			}
//			logger.debug("======================="+logger.getName()+" svcAfterReturning End=========================");
//		}		
	}
	
}