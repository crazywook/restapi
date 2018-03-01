package restapi.internal;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restapi.util.TransFormer;

@Service("CommonDao")
public class CommonDaoImpl  implements CommonDao{

	@Autowired
	SqlSessionFactory fac;
	
	@Override
	public List<Map> selectList(String resources, Map command) throws Exception {
		
		String sqlId = resources+".select"+TransFormer.upperInit(resources);
		SqlSession sql = fac.openSession();
		List<Map> list = sql.selectList(sqlId, command);		
		sql.close();		
		return list;		
	}
	
	@Override
	public void insert(String resources, Map command) throws Exception {
		
		String sqlId = resources+".insert"+TransFormer.upperInit(resources);
		SqlSession sql = fac.openSession();
		sql.insert(sqlId, command);		
		sql.close();
	}

	@Override
	public void update(String resources, Map command) throws Exception {
		
		String sqlId = resources+".update"+TransFormer.upperInit(resources);
		SqlSession sql = fac.openSession();
		sql.update(sqlId, command);		
		sql.close();
	}

	@Override
	public void delete(String resources, Map command) throws Exception {
		
		String sqlId = resources+".delete"+TransFormer.upperInit(resources);
		SqlSession sql = fac.openSession();
		sql.delete(sqlId, command);		
		sql.close();
	}
	
}
