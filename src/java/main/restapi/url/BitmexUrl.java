package restapi.url;

public enum BitmexUrl implements ApiUrl{

	BASE_URL("https://testnet.bitmex.com/api"),
	VERSION("v1"),
	PREFIX(BASE_URL.getUrl()+"/"+VERSION.getUrl()),
	TRADE(BASE_URL.getUrl()+"/"+VERSION.getUrl()+"/trade"),
	ORDER_BOOK_L2(PREFIX.getUrl()+"/orderBook/L2");
	
	final private String url;

	BitmexUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}