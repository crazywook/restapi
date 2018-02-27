package restapi.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restapi.util.TransFormer;

@RestController
@RequestMapping("/api/v0")
public class KoredexApiController {
	
	@Resource(name="koredexApiService")
	CommonDao commonDao;
	
	@RequestMapping(value="/{resources}", method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public void get(@PathVariable String resources, @RequestParam Map command) throws Exception {		
		
		System.out.println("resources: "+resources);
		System.out.println("command: "+command);
		List<Map> list = commonDao.selectList(getSqlId(resources), command);
//		System.out.println(list);
	}
	
	@RequestMapping(value="/{resources}", method=RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)
	public void post(@PathVariable String resources, @RequestParam Map command)  throws Exception {
		
//		commonDao.insert(getSqlId(command), command);
	}
	
	@RequestMapping(value="/{resources}", method=RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.CREATED)
	public void put(@PathVariable String resources, @RequestParam Map command) throws Exception {
		
		commonDao.update(getSqlId(resources), command);
	}
	
	@RequestMapping(value="/{resources}", method=RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.CREATED)
	public void delete(@PathVariable String resources, @RequestParam Map command) throws Exception {
		
		commonDao.delete(getSqlId(resources), command);
	}
	
	private String getSqlId(String resources) {
		
		String sqlId = "select"+TransFormer.upperInit(resources);
		return resources+"."+sqlId;
	}
}
