package utility;

import org.openqa.selenium.WebDriver;

public class SessionContext {
	private static SessionContext instance = new SessionContext();
	private String session_ID = null;

	private SessionContext() {
		// to enforce singleton
	}

	public static SessionContext get() {
		return instance;
	}

	public final String accuweather_url = "https://www.accuweather.com";
	public final String openweather_url = "http://api.openweathermap.org";

	public final String openweather_apiPath = "/data/2.5/weather";

	public final String openweather_apiKey = "7fe67bf08c80ded756e598d6f8fedaea";

}
