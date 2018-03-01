package restapi.internal;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0")
public class CommonApiController {
	
	@Resource(name="CommonDao")
	CommonDao commonDao;
	
	@RequestMapping(value="/{resources}", method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public List get(@PathVariable String resources, @RequestParam Map command) throws Exception {		
		
		List<Map> list = commonDao.selectList(resources, command);
		return list;
	}
	
	@RequestMapping(value="/{resources}", method=RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)
	public void post(@PathVariable String resources, @RequestParam Map command)  throws Exception {
		
		commonDao.insert(resources, command);
	}
	
	@RequestMapping(value="/{resources}", method=RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.CREATED)
	public void put(@PathVariable String resources, @RequestBody Map command) throws Exception {
		
		commonDao.update(resources, command);
	}
	
	@RequestMapping(value="/{resources}", method=RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.CREATED)
	public void delete(@PathVariable String resources, @RequestBody Map command) throws Exception {
		
		commonDao.delete(resources, command);
	}
	
}
