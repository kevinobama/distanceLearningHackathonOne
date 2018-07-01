package helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

import redis.clients.jedis.Jedis;

public class UtilityHelper {

	public static String md5String(String stringToHash) {		
        String generatedString = null;
        try {             
            MessageDigest md = MessageDigest.getInstance("MD5");
             
            md.update(stringToHash.getBytes());
             
            byte[] bytes = md.digest();
 
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            generatedString = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return generatedString;		
	}
	
	  /**
	   * True if there is a logged in user, false otherwise.
	   * @param ctx The context.
	   * @return True if user is logged in.
	   */
//	  public static boolean isLoggedIn() {
//		  String email = session("email");
//		  //HttpSession session = request.getSession(true);
//		  
//		  if (session.isNew()) {
//		         //title = "Welcome to my website";
//		         //session.setAttribute(userIDKey, userID);
//			  email = (String)session.getAttribute("email");
//	      } else {
//
//	    	  email = (String)session.getAttribute("email");
//	      }		  
//		  
//	    return (email != null);
//	  }
	
	/**
	 * 
	 * @param jedis
	 * @return
	 */
    public static Long chartDefinedColors(Jedis jedis)  {
    	//use sets
    	long result = 0;
    	Random random = new Random();
    	int maxCount = 150;    	
    	Long count = jedis.scard("chartDefinedColors");
    	if(count == 0) {
    		//this.jedis.del("chartDefinedColors");
        	for(int i= 1;i<=maxCount;i++) {
        		result = jedis.sadd("chartDefinedColors", String.format("#%06x", random.nextInt(256*256*256)));
        	}    		
    	}
    	return count;
    }	
	
	/**
	 * 
	 * @param jedis
	 * @return
	 */
    public static Long chartDefinedColorsList(Jedis jedis)  {
    	//use sets
    	long result = 0;
    	Random random = new Random();
    	int maxCount = 300;    	
    	Long count = jedis.llen("chartDefinedColors");
    	if(count == 0) {    		
        	for(int i= 1;i<=maxCount;i++) {
        		result = jedis.lpush("chartDefinedColors", String.format("#%06x", random.nextInt(256*256*256)));
        	}    		
    	}
    	return count;
    }
    
    public static String logContent() {
    	String content = "Introduction\n" + 
    			"NoSQL databases are often used when high writes and reads on a big amount of data is required or when the structure of data needs to be dynamic. Adopting a NoSQL database  needs careful considerations, especially regarding concurrency, isolation and consistency.\n" + 
    			"In the following section, a brief analysis of concurrency in MongoDB will be provided, to continue with an overview on consistency in different NoSQL databases.\n" + 
    			"\n" + 
    			"Discussion\n" + 
    			"This week App is based on PHP and MySQL. The application is a simple web app, which allows users to insert comments into the database. There are two PHP pages interacting with the database: form.php which after connection, selects all the rows in the comments table to display them, and comments.php, which inserts new rows.\n" + 
    			"\n" + 
    			"MySQL is a relational database system developed by Oracle Corporation. In MySQL, the tables are pre-defined and it is possible to interact with the database using the SQL language. Being a relational database, it is easy to store data in different tables related using joins. This allows avoiding data duplication.\n" + 
    			"\n" + 
    			"MongoDB is a NoSQL database that stores data in JSON-like format documents, which can have different structure. It is not a relational database and it uses dynamic schema. It is possible to change the structure of the tables and fields easily compared to RDBMS databases. The data are not normalized. MongoDB is designed for high availability and scalability.\n" + 
    			"\n" + 
    			"When choosing a database, it is important to define which the requirements for the application are. In particular, following the CAP theorem, it is important to check if consistency, availability or partition-tolerance are required. According to the CAP theorem, it is impossible to have the three at the same time: a database can be CA, AP or CP.\n" + 
    			"\n" + 
    			"In the case of our application, there might be several reasons why we would want to implement MongoDB. I am taking as example one: no rigid schema. Often a comment system of a blog does not need a rigid relational schema in place. The comments are usually always related to a post, so they can be inserted in a document type database. In addition, different fields can exist for different situations, without having to deal with NULL values in case they are not always present. For this reasons, using a NoSQL system, could help to increase performance and give more flexibility in case the structure should change.\n" + 
    			"\n" + 
    			"To convert our application to MongoDB, several steps would be required. I will consider that we are not going through a migration, but starting from scratch.\n" + 
    			"\n" + 
    			"MongoDB would need to be installed on the Ubuntu machine along with the MongoDB driver for PHP. From the coding point of view, it is not more complex how to connect to the database and perform operations. We can create the collection with the following command:";
    	return content;
    }
    

    public static String paginationMongodb(int page, String sortBy, String order, String userName, String robotName, String logLevel) {
    	StringBuilder links = new StringBuilder("/monitoringsList?"); 
   	    	
    	links.append("p="+page);
    	if(sortBy != null && !sortBy.isEmpty()) links.append("&s="+sortBy);
    	if(order != null && !order.isEmpty()) links.append("&o="+order);
    	if(userName != null && !userName.isEmpty()) links.append("&userName="+userName);
    	if(robotName != null && !robotName.isEmpty()) links.append("&robotName="+robotName);
    	if(logLevel != null && !logLevel.isEmpty()) links.append("&logLevel="+logLevel);
    	    	
    	return links.toString();
    }

 //    public static Map<String, ArrayList> getMinutesOneDay() {
	// 	ArrayList minutesArray = new ArrayList();
	// 	int currentMinute = HoldingHelper.getCurrentMinute();

	// 	int k=currentMinute-15;

	// 	while(k<1440) {
	// 		String each15Minutes = HoldingHelper.getDateTimePlusMinute(-k);
	// 		minutesArray.add(each15Minutes);

	// 		k = k + 15;
	// 	}
	// 	Collections.reverse(minutesArray);
	// 	Map<String, ArrayList> mapMinutes = new HashMap<String, ArrayList>();
	// 	mapMinutes.put("minutes", minutesArray);

	// 	return mapMinutes;
	// }
    
    public static Map<String, ArrayList> getMinutesOneDayOne() {
    	Calendar now = Calendar.getInstance();
    	now.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		int k=1;
		int currentHour = now.get(Calendar.HOUR_OF_DAY);
		int currentMinute = now.get(Calendar.MINUTE);
		ArrayList minutesArray = new ArrayList();
		
		k=currentHour+1;
		while(k <24) {			
			minutesArray.add(k+":00");
			minutesArray.add(k+":15");
			minutesArray.add(k+":30");
			minutesArray.add(k+":45");
			k++;
		}
		
		k=0;
		while(k<= currentHour) {
			if(k==0 && currentMinute >=45 ) {
				if(currentMinute< 15) {
					minutesArray.add(k+":00");
					//minutesArray.add(k+":15");
				}  else if(currentMinute< 30) {
					minutesArray.add(k+":00");
					minutesArray.add(k+":15");
					//minutesArray.add(k+":30");
				} else if(currentMinute< 45) {
					minutesArray.add(k+":00");
					minutesArray.add(k+":15");
					//minutesArray.add(k+":30");
				}

			} else {
				minutesArray.add(k+":00");
				minutesArray.add(k+":15");
				minutesArray.add(k+":30");
				minutesArray.add(k+":45");
			}

			k++;
		}
		

		Map<String, ArrayList> mapMinutes = new HashMap<String, ArrayList>();
		mapMinutes.put("minutes", minutesArray);

		return mapMinutes;
	}

	public static void holdingActor() {
    	System.out.println("holding Actor");
	}

	public static int getMinuteRange(int Minute) {
		int minuteRange = 0;
		if(Minute>=0 && Minute <15) {
			minuteRange  = 0;
		} if(Minute>=15 && Minute <30) {
			minuteRange  = 15;
		} if(Minute>=30 && Minute <45) {
			minuteRange  = 30;
		} if(Minute>=45 && Minute <60) {
			minuteRange  = 45;
		}
		return minuteRange;
	}

	public static String replaceString(String stringData) {
		if(stringData != null && !stringData.isEmpty()) {
			stringData = stringData.replace("{", "");
			stringData = stringData.replace("}", "");
			stringData = stringData.replace("[", "");
			stringData = stringData.replaceAll("]", "");


		}

		return stringData;
	}

	public static String replaceDoubleQuotes(String stringData) {
		if(stringData != null && !stringData.isEmpty()) {
			//stringData = stringData.replace('"', ' ');
			stringData = stringData.replaceAll("\"", "");
			//stringData = stringData.replace("\"", ' ');
		}

		return stringData;
	}
	
	public static String unixTimeToDate(long unixSeconds) {
		if(unixSeconds>0) {   
			Date date = new java.util.Date(unixSeconds*1000L); 
	 
			SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		   
		   //dateFormat.setTimeZone(java.util.TimeZone.getTimeZone("GMT+8")); 
		   
			return dateFormat.format(date);
		} else {
			return "";
		}
	}
}
