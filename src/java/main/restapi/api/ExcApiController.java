package restapi.api;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import restapi.util.HtmlConverter;

@RestController
@RequestMapping("/api/v0")
public class ExcApiController {

	String baseUrl = "https://testnet.bitmex.com/api/v1";
	Logger logger;

	@PostConstruct
	public void init(){
		logger = Logger.getLogger(ExcApiController.class);
		BasicConfigurator.configure();
	}

	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<String> doGet(@ModelAttribute("command") HashMap command) {

		System.out.println("api order");
		String resourceUrl = (String)command.get("resourceUrl");
		String queryString = HtmlConverter.decode((String)command.get("queryString"));

		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> re = null;
		String url = baseUrl + resourceUrl + queryString;

		if(command.get("symbol") != null) {
			String symbol = HtmlConverter.decode((String)command.get("symbol"));
			re = rt.getForEntity(url, String.class, symbol);
		}else {
			logger.info("doGet: "+url);
			re = rt.getForEntity(url, String.class);
		}

		return re;
	}
}
