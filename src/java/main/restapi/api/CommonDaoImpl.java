package restapi.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("koredexApiService")
public class CommonDaoImpl  implements CommonDao{

	Logger logger;
	
	@PostConstruct
	public void init() {
		logger = Logger.getLogger(CommonDaoImpl.class);
		BasicConfigurator.configure();
		
	}
	
	@Autowired
	SqlSessionFactory fac;

	@Override
	public void insert(String sqlId, Map command) throws Exception {
		
		logger.info("parameterMap : "+command);
		
		SqlSession sql = fac.openSession();
		sql.insert(sqlId, command);		
		sql.close();
	}

	@Override
	public List<Map> selectList(String sqlId, Map command) throws Exception {
		
		SqlSession sql = fac.openSession();
		List<Map> list = sql.selectList(sqlId, command);		
		sql.close();		
		return list;		
	}

	@Override
	public void update(String sqlId, Map command) throws Exception {

		SqlSession sql = fac.openSession();
		sql.update(sqlId, command);		
		sql.close();
	
	}

	@Override
	public void delete(String sqlId, Map command) throws Exception {
		
		SqlSession sql = fac.openSession();
		sql.delete(sqlId, command);		
		sql.close();
		
	}
	
}
