package restapi.external;

import java.util.Map;

import org.springframework.http.HttpMethod;

import restapi.url.ApiUrl;

public interface ExternalApiService {

	public String get(ApiUrl urlEnum, Map map) throws Exception;

	public String post(ApiUrl urlEnum, Object obj) throws Exception;

	public String put(ApiUrl urlEnum, Object obj) throws Exception;

	public String delete(ApiUrl urlEnum, Object obj) throws Exception;

	public String execute(ApiUrl urlEnum, Object obj, HttpMethod httpMethod) throws Exception;
}