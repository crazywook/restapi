package restapi.bitmex;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import restapi.external.ExternalApiService;
import restapi.url.ApiUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service("bitMexApiService")
public class BitMexApiServiceImpl  implements ExternalApiService{

	Logger logger;

	@PostConstruct
	public void init() {
		logger = Logger.getLogger(BitMexApiServiceImpl.class);
		BasicConfigurator.configure();
	}

	@Autowired
	HttpSession session;

	@Autowired
	ServletContext context;

	@Override
	public String get(ApiUrl urlEnum, Map map) throws Exception {

		OkHttpClient client = new OkHttpClient();

		logger.info("ApiUrl is : "+urlEnum.getUrl());

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_DEFAULT);
		
		Request request = new Request.Builder()
		  .url(urlEnum.getUrl()+getQueryString(map))
		  .get()
		  .addHeader("content-type", "application/json")
		  .addHeader("cache-control", "no-cache")
		  .build();

		Response response = client.newCall(request).execute();
		logger.debug("responseStatus: "+response.message());

		String responseJSON = response.body().string();
		logger.debug("responseJSON: "+responseJSON);
		System.out.println("responseJSON: "+responseJSON);
		return responseJSON;
	}

	private String getQueryString(Map map) {
		String mapString = map.toString();
		String queryString = "?"+mapString.substring(1, mapString.length()-1)
			.replace(", ", "&");
		return queryString;
	}

	@Override
	public String post(ApiUrl urlEnum, Object obj) throws Exception {

		return execute(urlEnum, obj, HttpMethod.POST);
	}

	@Override
	public String put(ApiUrl urlEnum, Object obj) throws Exception {

		return execute(urlEnum, obj, HttpMethod.PUT);
	}

	@Override
	public String delete(ApiUrl urlEnum, Object obj) throws Exception {

		return execute(urlEnum, obj, HttpMethod.DELETE);
	}

	@Override
	public String execute(ApiUrl urlEnum, Object obj, HttpMethod httpMethod) throws Exception {

		OkHttpClient client = new OkHttpClient();

		logger.info("ApiUrl is : "+urlEnum.getUrl());

		ObjectMapper objectMapper = new ObjectMapper();
		String requestJSON = objectMapper.writeValueAsString(obj);
		logger.info("requestJSON: "+requestJSON);

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, requestJSON);
		Request request = new Request.Builder()
		  .url(urlEnum.getUrl())
		  .method(httpMethod.toString(), body)
		  .addHeader("content-type", "application/json")
		  .addHeader("cache-control", "no-cache")
		  .build();

		Response response = client.newCall(request).execute();
		String responseJSON = response.body().string();

		logger.info(response.message());
		logger.info(responseJSON);

		return responseJSON;
	}
}
