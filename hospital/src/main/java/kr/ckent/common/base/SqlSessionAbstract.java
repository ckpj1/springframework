package kr.ckent.common.base;

import java.util.List;

import javax.annotation.Resource;


/**
 * mybatis 사용시 사용.
 * 
 * @author Baek Chung Ki
 * @deprecated  mybatis->ibatis 변경
 *
 */
public class SqlSessionAbstract{}
//
//public class SqlSessionAbstract extends SqlSessionDaoSupport {
//
//	@Resource(name="sqlSessionFactory")
//	public void setSuperSqlSessionFactory(SqlSessionFactory sqlSession){
//		super.setSqlSessionFactory(sqlSession);
//		
//		
//	}
//	
//	public Object selectOne(String queryId){
//		
//		return getSqlSession().selectOne(queryId);
//	}
//	
//	public Object selectOne(String queryId, Object parmeterObject){
//		
//		return getSqlSession().selectOne(queryId,parmeterObject);
//	}		
//	
//	public List selectList(String queryId, Object parmeterObject){
//		return getSqlSession().selectList(queryId, parmeterObject);
//	}
//	
//	public Object insert(String queryId, Object parmeterObject){
//		return getSqlSession().insert(queryId, parmeterObject);
//	}
//	  
//	public Object update(String queryId, Object parmeterObject){
//		return getSqlSession().update(queryId, parmeterObject);
//	}
//	
//	public Object delete(String queryId, Object parmeterObject){
//		return getSqlSession().delete(queryId, parmeterObject);
//	}
//	
//}
