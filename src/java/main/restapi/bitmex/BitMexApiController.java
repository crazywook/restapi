package restapi.bitmex;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restapi.external.ExternalApiService;
import restapi.url.BitmexUrl;
import restapi.util.TransFormer;

@RestController
@RequestMapping("/api/bitmex/v0")
public class BitMexApiController {

	Logger logger;

	final private String BASE_PATH = "/api/bitmex/v0";

	@Resource(name="bitMexApiService")
	ExternalApiService bitMexApiService;

	@PostConstruct
	public void init() {
		logger = Logger.getLogger(BitMexApiController.class);
		BasicConfigurator.configure();
	}

	@RequestMapping(value="/**", method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public String get(@RequestParam Map command, HttpServletRequest req, HttpSession session) throws Exception {

		String resources = req.getServletPath().substring(BASE_PATH.length()+1);
		resources = TransFormer.getBigUnderScore(resources);
		String responseBody = bitMexApiService.get(BitmexUrl.valueOf(resources), command);
		
		return responseBody;
	}

	@RequestMapping(value="/**", method=RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.CREATED)
	public String post(@RequestParam Map command, HttpServletRequest req, HttpSession session) throws Exception {
		
		String resources = req.getServletPath().substring(BASE_PATH.length()+1);
		resources = TransFormer.getBigUnderScore(resources);
		String responseBody = bitMexApiService.post(BitmexUrl.valueOf(resources), command);
		
		return responseBody;
	}

	@RequestMapping(value="/**", method=RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.CREATED)
	public String put(@RequestParam Map command, HttpServletRequest req, HttpSession session) throws Exception {
		
		String resources = req.getServletPath().substring(BASE_PATH.length()+1);
		resources = TransFormer.getBigUnderScore(resources);
		String responseBody = bitMexApiService.put(BitmexUrl.valueOf(resources), command);
		
		return responseBody;
	}

	@RequestMapping(value="/**", method=RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.CREATED)
	public String delete(@RequestParam Map command, HttpServletRequest req, HttpSession session) throws Exception {
		
		String resources = req.getServletPath().substring(BASE_PATH.length()+1);
		resources = TransFormer.getBigUnderScore(resources);
		String responseBody = bitMexApiService.delete(BitmexUrl.valueOf(resources), command);
		
		return responseBody;
	}
}
