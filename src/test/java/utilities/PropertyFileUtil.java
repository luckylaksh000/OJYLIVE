package utilities;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil {
	 public static String getValueForKey(String key) throws Throwable {
		 Properties config = new Properties();
		 config.load(new FileInputStream("C:\\OJTLiveProject\\ERP_StockAccountingHybridFrame\\PropertyFile\\Environment.Properties"));
		return config.getProperty(key);
	 }

}
