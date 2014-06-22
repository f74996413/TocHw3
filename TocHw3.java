import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.*;

public class TocHw3 
{
	public static String UrlData(String address)
	{
		URL url = null;
		InputStream input = null;
		InputStreamReader read = null;
		BufferedReader buff = null;
		StringBuffer msg = null;
		
		try
		{
			url = new URL(address);
			input = url.openStream();
			read = new InputStreamReader(input,"UTF-8");
			buff = new BufferedReader(read);
			
			String tmpstr = null;
			msg = new StringBuffer();
			
			while ((tmpstr = buff.readLine()) != null)
			{
				msg.append(tmpstr);
			}
		}
		catch (Exception e)
		{
			e.getStackTrace();
		}
		finally
		{
			try
			{
				url = null;
				input.close();
				read.close();
				buff.close();
			}
			catch (Exception e)
			{
				
			}
		}
		
		return msg.toString();
	}
	
	public static String FileData(String filename)
	{
		StringBuffer msg = new StringBuffer();
		try
		{
			InputStreamReader thefile = new InputStreamReader(new FileInputStream(filename) , "UTF-8");
			BufferedReader BufRead = new BufferedReader(thefile);
			
			try
			{
				do
				{
					String buffer = BufRead.readLine();
					if(buffer == null)
						break;
					
					msg.append(buffer);
				}
				while(true);
			}
			catch(Exception e)
			{
				
			}
			finally
			{
				BufRead.close();
			}
		}
		catch (Exception e)
		{
			
		}
		return msg.toString();
	}
	
	public static void main(String[] args) throws Exception
	{
		if(args.length != 4)
		{
			System.out.println("Input Error, you might need to input 4 resource plz...");
		}
		
		String URL = args[0];
		String location = args[1];
		String road = args[2];
		int year = Integer.valueOf(args[3])*100;
		
		String contents = UrlData(URL);
		
		JSONArray JSONArray = new JSONArray(contents);
		
		double tprice = 0;
		
		int count = 0;
		for(int i=0 ; i<JSONArray.length() ; i++)
		{
			JSONObject m = JSONArray.getJSONObject(i);
			
			try
			{
				if(m.getString("鄉鎮市區").contains(location) && m.getString("土地區段位置或建物區門牌").contains(road) && m.getInt("交易年月")>= year)
				{
					tprice += m.getInt("總價元");
					count++;
				}
			}
			catch(Exception e)
			{
				
			}
		}
		double average = tprice / count;
		System.out.println((int) average);
	}
}
