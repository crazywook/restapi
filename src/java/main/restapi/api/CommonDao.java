package restapi.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CommonDao {

	
	public void insert(String sqlId, Map command) throws Exception;
	
	public List<Map> selectList(String sqlId, Map command) throws Exception;
	
	public void update(String sqlId, Map command) throws Exception;
	
	public void delete(String sqlId, Map command) throws Exception;
}
