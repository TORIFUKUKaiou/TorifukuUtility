package jp.torifuku.util.torifukuutility.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * TorifukuHttpClient
 * Apache HTTP Client Wrapper Class
 *
 * @author torifuku.kaiou@gmail.com
 *
 */
/**
[Sample 1 get String]
TorifukuHttpClient.Decoder decorder = new TorifukuHttpClient.Decoder() {
	@Override
	public Object decode(TorifukuHttpClient client,
		InputStream is) {
		return client.convertString(is, "SJIS");
	}
};
TorifukuHttpClient client = new TorifukuHttpClient();
String text = (String) client.getContent("http://google.com", decorder);

[Sample 2 get Bitmap]
TorifukuHttpClient.Decoder decorder = new TorifukuHttpClient.Decoder() {
	@Override
	public Object decode(TorifukuHttpClient client,
		InputStream is) {
		return BitmapFactory.decodeStream(is);
	}
};
TorifukuHttpClient client = new TorifukuHttpClient();
Bitmap bmp = (Bitmap) client.getContent("https://lh4.googleusercontent.com/--dq8niRp7W4/URquVgmXvgI/AAAAAAAAAbs/-gnuLQfNnBA/s1024/A%252520Song%252520of%252520Ice%252520and%252520Fire.jpg", decorder);

 */

public class TorifukuHttpClient {

	/**
	 * Decoder
	 *
	 * @author torifuku.kaiou@gmail.com
	 *
	 */
	public interface Decoder {
		Object decode(TorifukuHttpClient client, InputStream is);
	}

	/**
	 * getContent
	 *
	 * @param uri
	 * @param d
	 * @return
	 */
	public Object getContent(String uri, Decoder d) {
		HttpClient httpClient = new DefaultHttpClient();
		return getContent(uri, d, httpClient);
	}

	/**
	 * getContent
	 * proxy setting version
	 *
	 * @param uri
	 * @param d
	 * @param hostName
	 * @param port
	 * @return
	 */
	public Object getContent(String uri, Decoder d, String hostName, int port) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpHost proxy = new HttpHost(hostName, port);
		httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		return getContent(uri, d, httpClient);
	}

	/**
	 * convertString
	 *
	 * @param is
	 * @param charsetName
	 * @return
	 */
	public String convertString(InputStream is, String charsetName) {
		if (is == null) {
			return null;
		}

		InputStreamReader isr = null;
		BufferedReader br = null;
		String retString = null;
		try {
			isr = new InputStreamReader(is, charsetName);
			br = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			retString = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) br.close();
				if (isr != null) isr.close();
				if (is != null) is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return retString;
	}

	/**
	 * getContent
	 *
	 * @param uri
	 * @param d
	 * @param httpClient
	 * @return
	 */
	private Object getContent(String uri, Decoder d, HttpClient httpClient) {
		HttpGet httpGet = new HttpGet(uri);
		InputStream inputStream = null;
		Object obj = null;
		try {
			HttpResponse response = httpClient.execute(httpGet);
			if (response != null) {
				int status = response.getStatusLine().getStatusCode();
				if (status == HttpStatus.SC_OK) {
					inputStream = response.getEntity().getContent();
					// android developers may convert InputStream to Bitmap.
					// android.graphics.BitmapFactory.decodeStream(InputStream, Rect, BitmapFactory.Options);
					obj = d.decode(this, inputStream);
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// releaseConnection() HTTP Client 3.1 API?
			// httpGet.releaseConnection();
			httpGet.abort();
		}
		return obj;
	}

}
