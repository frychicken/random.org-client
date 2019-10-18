/**
 * <h1>Generate true random client from random.org!</h1>
 * This uses random.org API
 * simply generates random integer, string, sequence and check quota
 * the output on console.
 * <p>
 * <b>Note:</b> Special thanks to random.org
 * It uses atmosphere noise to generate.
 *
 * @author  Bob Dinh
 * @version 1.0
 * @since   2019-10-2
 */

package xyz.null0verflow.librandomorgclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.net.HttpURLConnection;

public class GenerateTrueRandom {
	private ArrayList<String> arraylistcontain = new ArrayList<String>();
	private String email;
	private int statusCode;
	private String output = "";
	public GenerateTrueRandom(String email) {
		this.email = email;
		String source ="https://raw.githubusercontent.com/bobdinh139/HostImage/master/lib/currentVersion.txt";
		if (Integer.parseInt(htmlParse(source, true).trim()) > getVersion()) {
			output = "Library update(s) available";
			System.out.println("New version of the library available, please do update");
		}
		else {
			output = "No new updates available";
			System.out.println("No new updates available");

		}
	}

	private String htmlParse(String url2, boolean checkQuota) {
		BufferedReader br = null;
		StringBuilder getran = new StringBuilder();
		try {
			URL url = new URL(url2);
			URLConnection uc = url.openConnection();
			uc.addRequestProperty("User-Agent", email);
			if (!checkQuota) {
				statusCode = ((HttpURLConnection) uc).getResponseCode();
				System.out.println(statusCode);
				if (statusCode !=200) {
					output = "Too many requests";
					throw new TooManyRequest("Too many requests, wait for 10 mins to a day");
				}
			}
			long tStart = System.currentTimeMillis();
			br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String line;
			while (((line = br.readLine()) != null)) {
				long tEnd = System.currentTimeMillis();
				if ((tEnd - tStart)/1000.0 >= (60)) {
					output = "Operation timed out";
					throw new TooManyRequest("Operation timed out! Check your internet connection");
				}
				getran.append(line+" ");
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (!checkQuota)
			arraylistcontain = parseString(getran.toString());

		return getran.toString();
	}

	private ArrayList<String> parseString(String a) {
		arraylistcontain.clear();
		char c;    
		String d ="";
		ArrayList<String> arrli = new ArrayList<String>(); 
		for (int i =0; i< a.length(); i++){
			c = a.charAt(i);    
			if (c != ' '){
				d +=c;

			} else {
				arrli.add(d);
				d = "";
			}
		}
		return arrli;
	}
	
	public String getOutput() {
		return output;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public ArrayList<String> getArrayList(){
		return arraylistcontain;
	}
	/**
	 * This method is used to generate random integers
	 * @param totalnumber Number of integer you want
	 * @param min The minimum number [-1e9,1e9]
	 * @param max The maximum number [-1e9,1e9].
	 * @param base Base of the number 2 | 8 | 10 | 16
	 * @return string This is a string with random numbers separated with a space
	 */
	public String getRandomNumber(int totalnumber, int min, int max, int base) {
		String a = "https://www.random.org/integers/?num="+totalnumber+ "&min="+min +"&max="+max +"&col=1&base="+base+"&format=plain&rnd=new";
		if (Integer.valueOf(QuotaCheck()) >= 0) {
			return htmlParse(a, false);
		} else {
			output = "Too many requests";
			try {
				throw new TooManyRequest("Too many requests, wait for 10 mins to a day");
			} catch (TooManyRequest e) {
				e.printStackTrace();
			}
			return "-1";
		}
	}
	/**
	 * This method is used to random sequence
	 * @param min The minimum number [-1e9,1e9]
	 * @param max The maximum number [-1e9,1e9].
	 * @return string This is a string with random sequences separated with a space
	 */
	public String sequenceRandomGenerator(int min, int max) {
		String b = "https://www.random.org/sequences/?min="+min+"&max="+max+"&col=1&format=plain&rnd=new";
		if (Integer.valueOf(QuotaCheck()) >= 0) {
			return htmlParse(b, false);
		} else {
			output = "Too many requests";
			try {
				throw new TooManyRequest("Too many requests, wait for 10 mins to a day");
			} catch (TooManyRequest e) {
				e.printStackTrace();
			}
			return "-1";
		}	
	}
	/**
	 * This method is used to generate random strings
	 * @param totalString Number of String you want [1,1e4]	
	 * @param lengthofString The length of strings [1,20]	
	 * @param digitOnorOff true/false Determines whether digits (0-9) are allowed to occur in the strings.
	 * @param upper Determines whether uppercase alphabetic characters (A-Z) are allowed to occur in the strings.
	 * @param lower Determines whether lowercase alphabetic characters (A-Z) are allowed to occur in the strings. 
	 * @param unique Determines whether the strings picked should be unique (as a series of raffle tickets drawn from a hat) or not (as a series of die rolls). If unique is set to on, then there is the additional constraint that the number of strings requested (num) must be less than or equal to the number of strings that exist with the selected length and characters.
	 * @return string This is a string with random String separated with a space
	 */
	public String randomStringGenrator(int totalString, int lengthofString, Boolean digitOnorOff, Boolean upper, Boolean lower, Boolean unique) {
		String digit = digitOnorOff ? "on" : "off";
		String upperalpha = upper ? "on" : "off";
		String loweralpha = lower ? "on" : "off";
		String uniqueChar = unique ? "on" : "off";
		String c = "https://www.random.org/strings/?num="+totalString+"&len="+lengthofString +"&digits="+digit+"&upperalpha="+upperalpha+"&loweralpha="+loweralpha+"&unique="+uniqueChar+"&format=plain&rnd=new";
		if (Integer.valueOf(QuotaCheck()) >= 0) {
			return htmlParse(c, false);
		} else {
			output = "Too many requests";
			try {
				throw new TooManyRequest("Too many requests, wait for 10 mins to a day");
			} catch (TooManyRequest e) {
				e.printStackTrace();
			}
			return "-1";
		}
	}

	public String QuotaCheck(String ipadrr) {
		String d = "https://www.random.org/quota/?ip="+ipadrr+"&format=plain";
		return htmlParse(d, true).trim();
	}
	public String QuotaCheck() {
		String e = "https://www.random.org/quota/?format=plain";
		return htmlParse(e, true).trim();
	}
	public int getVersion() {
		return 6;
	}

}


