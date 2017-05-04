import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.Set;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class DataAnalyticsServlet extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
     
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		String productType = "";
		//productType = request.getParameter("productType");
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Best Deal</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">Best<span>Deal</span></a></h1><h2>Best Price Guaranteed</h2><h3>Store Manager Portal</h3></header>");
		out.println("<nav><ul>");
		out.println("<li class=\"\"><a href=\"StoreManagerPortal\">Product List</a></li>");
		out.println("<li><a href=\"AddProduct?type=addProduct\">Add Product</a></li>");
		out.println("<li><a href=\"DataAnalyticsServlet\">Data Analytics</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		
	
		out.println("<div align='center' id=\"body\"><article><h3 align=\"center\">Select any one query from the folowing and click Execute Query</h3>");
		out.println("<fieldset>");

		out.println("<form action='DataAnalyticsServlet' method='post'>");
		out.println("<p><select name='queryType'>");
		out.println("<option name='queryType' value='1' selected>Print the list of all products and their ratings</option>");
		out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
		out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
		out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
		out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
		
		out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
		out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
		out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
		out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
		out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
		
		out.println("<option name='queryType' value='11'>Get the total number of products reviewed and got Rating 5 in Every City</option>");
		out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
		out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
		out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
		out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
		
		out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
		out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
		out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
		out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
		out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
		
		out.println("</select></p>");
		out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
		out.println("</form>");
		
		
		out.println("</fieldset></article</div></div></body></html>");
		
		out.close();
	 
	}
			
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	  
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		String queryType = "";
		queryType = request.getParameter("queryType");
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Best Deal</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" /></head>");
		out.println("<body><div id=\"container\">");
		out.println("<header><h1><a href=\"/\">Best<span>Deal</span></a></h1><h2>Best Price Guaranteed</h2><h3>Store Manager Portal</h3></header>");
		out.println("<nav><ul>");
		out.println("<li class=\"\"><a href=\"StoreManagerPortal\">Product List</a></li>");
		out.println("<li><a href=\"AddProduct?type=addProduct\">Add Product</a></li>");
		out.println("<li><a href=\"DataAnalyticsServlet\">Data Analytics</a></li>");
		out.println("<li><a href=\"LogoutServlet\">Logout</a></li></ul></nav>");
		
	
		out.println("<div align='center' id=\"body\"><article><h3 align=\"center\">Select any one query from the folowing and click Execute Query</h3>");
		out.println("<fieldset>");

		if(queryType.equals("1"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1' selected>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			out.println("<option name='queryType' value='11'>Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			
			LinkedHashMap<String, Double> productListAndTheirRatings;
			productListAndTheirRatings = MongoDBDataStoreUtilities.getProductListAndTheirRatings();
			
			int number=1;
			
			out.println("<h3 align='center'>List of All Products And Their Ratings</h3>");
			out.println("<table>");
			out.println("<tr><td>No.</td><td><b>Product Name</b></td><td><b>Average Rating</b></td></tr>");
			
			for(Map.Entry<String, Double> m :productListAndTheirRatings.entrySet())
			{
				String key = m.getKey();
				Double value = m.getValue();
				
				out.println("<tr><td>"+number+"</td>");
				out.println("<td>"+key+"</td>");
				out.println("<td>"+value+"</td></tr>");
				
				number++;
			}
			
			out.println("</table>");
		}
		
		if(queryType.equals("2"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1'>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2' selected>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			out.println("<option name='queryType' value='11'>Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			
			HashMap<String,Review> reviews;
			reviews = MongoDBDataStoreUtilities.getReviewListWhereRatingGreaterThan3();
			
			String name;
			String emailId;
			int reviewRating;
			Date date;
			String reviewText;
			int number=1;
			
			out.println("<h3 align='center'>List of Reviews Where Rating Greater Than 3</h3>");
			out.println("<table>");
			out.println("<tr><td>No.</td><td>Product Name</td><td>Email Id</td><td>Review Rating</td><td>Review Date</td><td>Review text</td></tr>");
				
				for(Map.Entry<String,Review> m :reviews.entrySet()){
					
					Review c = m.getValue();
					
					name = c.getProductName();
					emailId = c.getEmailId();
					reviewRating = c.getReviewRating();
					
					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
					date = c.getReviewDate();
					String reviewDate = dateFormat.format(date);
					
					reviewText = c.getReviewText();
					
					out.println("<tr><td>"+number+"</td>");
					out.println("<td>"+name+"</td>");
					out.println("<td>"+emailId+"</td>");
					out.println("<td>"+reviewRating+"</td>");
					out.println("<td>"+reviewDate+"</td>");
					out.println("<td>"+reviewText+"</td></tr>");
					
					number++;
				}
			
			out.println("</table>");	
		}
		
		if(queryType.equals("3"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1'>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3' selected>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			out.println("<option name='queryType' value='11'>Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			
			HashMap<String,Review> reviews;
			reviews = MongoDBDataStoreUtilities.getReviewListWhereRating5AndPriceMoreThan1000();
			
			String name;
			String emailId;
			int reviewRating;
			Date date;
			String reviewText;
			int number=1;
			
			out.println("<h3 align='center'>List of Reviews Where Rating Equals 5 And Price Greater Than $1000</h3>");
			out.println("<table>");
			out.println("<tr><td>No.</td><td>Product Name</td><td>Email Id</td><td>Review Rating</td><td>Review Date</td><td>Review text</td></tr>");
				
				for(Map.Entry<String,Review> m :reviews.entrySet()){
					
					Review c = m.getValue();
					
					name = c.getProductName();
					emailId = c.getEmailId();
					reviewRating = c.getReviewRating();
					
					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
					date = c.getReviewDate();
					String reviewDate = dateFormat.format(date);
					
					reviewText = c.getReviewText();
					
					out.println("<tr><td>"+number+"</td>");
					out.println("<td>"+name+"</td>");
					out.println("<td>"+emailId+"</td>");
					out.println("<td>"+reviewRating+"</td>");
					out.println("<td>"+reviewDate+"</td>");
					out.println("<td>"+reviewText+"</td></tr>");
					
					number++;
				}
			
			out.println("</table>");	
		}
		
		if(queryType.equals("4"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1'>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4' selected>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			out.println("<option name='queryType' value='11'>Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			
			LinkedHashMap<String, Integer> howManyReviewsForEveryProduct;
			howManyReviewsForEveryProduct = MongoDBDataStoreUtilities.getHowManyReviewsForEveryProduct();
			int number=1;
			
			out.println("<h3 align='center'>Total Number Of Reviews For Every Product</h3>");
			out.println("<table>");
			out.println("<tr><td>No.</td><td><b>Product Name</b></td><td><b>Number of Reviews for this product</b></td></tr>");
			
			for(Map.Entry<String, Integer> m :howManyReviewsForEveryProduct.entrySet())
			{
				String key = m.getKey();
				Integer value = m.getValue();
				
				out.println("<tr><td>"+number+"</td>");
				out.println("<td>"+key+"</td>");
				out.println("<td>"+value+"</td></tr>");
				
				number++;
			}
			
			out.println("</table>");
			
		}
		
		if(queryType.equals("5"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1'>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5' selected>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			out.println("<option name='queryType' value='11'>Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			
			HashMap<String,Review> reviews;
			reviews = MongoDBDataStoreUtilities.getListOfReviewsForShoppersInChicago();
			
			String name;
			String emailId;
			int reviewRating;
			Date date;
			String reviewText;
			int number=1;
			
			out.println("<h3 align='center'>List of Reviews for shoppers in Chicago</h3>");
			out.println("<table>");
			out.println("<tr><td>No.</td><td>Product Name</td><td>Email Id</td><td>Review Rating</td><td>Review Date</td><td>Review text</td></tr>");
				
				for(Map.Entry<String,Review> m :reviews.entrySet()){
					
					Review c = m.getValue();
					
					name = c.getProductName();
					emailId = c.getEmailId();
					reviewRating = c.getReviewRating();
					
					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
					date = c.getReviewDate();
					String reviewDate = dateFormat.format(date);
					
					reviewText = c.getReviewText();
					
					out.println("<tr><td>"+number+"</td>");
					out.println("<td>"+name+"</td>");
					out.println("<td>"+emailId+"</td>");
					out.println("<td>"+reviewRating+"</td>");
					out.println("<td>"+reviewDate+"</td>");
					out.println("<td>"+reviewText+"</td></tr>");
					
					number++;
				}
			
			out.println("</table>");	
		}
		
		if(queryType.equals("6"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1'>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6' selected>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			out.println("<option name='queryType' value='11'>Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			
			LinkedHashMap<String, ArrayList<Object>> highestPricedProductReviewedInEveryCity;
			highestPricedProductReviewedInEveryCity = MongoDBDataStoreUtilities.getHighestPricedProductReviewedInEveryCity();
			
			int number=1;
			
			out.println("<h3 align='center'>Highest Price Product Reviewed In Every City</h3>");
			out.println("<table>");
			out.println("<tr><td>No.</td><td><b>City</b></td><td><b>Product Name</b></td><td><b>Product Price</b></td></tr>");
			
			for(Map.Entry<String, ArrayList<Object>> m :highestPricedProductReviewedInEveryCity.entrySet())
			{
				String key = m.getKey();
				ArrayList<Object> values = m.getValue();
				
				out.println("<tr><td>"+number+"</td>");
				out.println("<td>"+values.get(0)+"</td>");
				out.println("<td>"+values.get(1)+"</td>");
				out.println("<td>"+values.get(2)+"</td></tr>");
				
				number++;
			}
			
			out.println("</table>");
			
		}
		
		if(queryType.equals("7"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1'>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7' selected>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			out.println("<option name='queryType' value='11'>Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			
			LinkedHashMap<String, ArrayList<Object>> highestPricedProductReviewedInEveryZipCode;
			highestPricedProductReviewedInEveryZipCode = MongoDBDataStoreUtilities.getHighestPricedProductReviewedInEveryZipCode();
			
			int number=1;
			
			out.println("<h3 align='center'>Highest Price Product Reviewed In Every Zip Code</h3>");
			out.println("<table>");
			out.println("<tr><td>No.</td><td><b>Zip Code</b></td><td><b>Product Name</b></td><td><b>Product Price</b></td></tr>");
			
			for(Map.Entry<String, ArrayList<Object>> m :highestPricedProductReviewedInEveryZipCode.entrySet())
			{
				String key = m.getKey();
				ArrayList<Object> values = m.getValue();
				
				out.println("<tr><td>"+number+"</td>");
				out.println("<td>"+values.get(0)+"</td>");
				out.println("<td>"+values.get(1)+"</td>");
				out.println("<td>"+values.get(2)+"</td></tr>");
				
				number++;
			}
			
			out.println("</table>");
			
		}
		
		if(queryType.equals("8"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1'>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6' >Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8' selected>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			out.println("<option name='queryType' value='11'>Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			
			LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> Top5ListOfLikedProductsForEveryCity = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>>();
			Top5ListOfLikedProductsForEveryCity = MongoDBDataStoreUtilities.getTop5ListOfLikedProductsForEveryCity();
			
			out.println("<h3 align='center'>Top 5 List Of Liked Products For Every City</h3>");
			
			for(Map.Entry<String, LinkedHashMap<String, ArrayList<Object>>> p :Top5ListOfLikedProductsForEveryCity.entrySet())
			{
				String key1 = p.getKey();
				LinkedHashMap<String, ArrayList<Object>> productsListHashMap = p.getValue();
				
				out.println("<h3 align=\"center\">"+key1+"</h3>");
				//out.println("<fieldset>");
				
				int number=1;
			
				out.println("<table>");
				out.println("<tr><td>No.</td><td><b>Product Name</b></td><td><b>Rating</b></td></tr>");
				
					for(Map.Entry<String, ArrayList<Object>> m :productsListHashMap.entrySet())
					{
						String key = m.getKey();
						ArrayList<Object> values = m.getValue();
						
						out.println("<tr><td>"+number+"</td>");
						out.println("<td>"+values.get(1)+"</td>");
						out.println("<td>"+values.get(2)+"</td></tr>");
						
						number++;
					}
				
				out.println("</table>");
				
				//out.println("</fieldset>");
				
			}
			
		}
		
		if(queryType.equals("9"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1'>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6' >Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9' selected>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			out.println("<option name='queryType' value='11'>Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			
			LinkedHashMap<String, LinkedHashMap<String, Review>> ListOfReviewsGroupedByCity = new LinkedHashMap<String, LinkedHashMap<String, Review>>();
			ListOfReviewsGroupedByCity = MongoDBDataStoreUtilities.getListOfReviewsGroupedByCity();
			
			out.println("<h3 align='center'>List Of Reviews Grouped by City</h3>");
			
			for(Map.Entry<String, LinkedHashMap<String, Review>> p :ListOfReviewsGroupedByCity.entrySet())
			{
				String key1 = p.getKey();
				LinkedHashMap<String, Review> productsListHashMap = p.getValue();
				
				out.println("<h3 align=\"center\">"+key1+"</h3>");
				//out.println("<fieldset>");
				
				String name;
				String emailId;
				int reviewRating;
				Date date;
				String reviewText;
				int number=1;
			
				out.println("<table>");
				out.println("<tr><td>No.</td><td>Product Name</td><td>Email Id</td><td>Review Rating</td><td>Review Date</td><td>Review text</td></tr>");
				
					for(Map.Entry<String, Review> m :productsListHashMap.entrySet())
					{
						String key = m.getKey();
						Review c = m.getValue();
						
						name = c.getProductName();
						emailId = c.getEmailId();
						reviewRating = c.getReviewRating();
						
						DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
						date = c.getReviewDate();
						String reviewDate = dateFormat.format(date);
						
						reviewText = c.getReviewText();
						
						out.println("<tr><td>"+number+"</td>");
						out.println("<td>"+name+"</td>");
						out.println("<td>"+emailId+"</td>");
						out.println("<td>"+reviewRating+"</td>");
						out.println("<td>"+reviewDate+"</td>");
						out.println("<td>"+reviewText+"</td></tr>");
						
						number++;
					}
				
				out.println("</table>");
				
				//out.println("</fieldset>");
			}
			
		}
		
		if(queryType.equals("10"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1'>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10' selected>Print a list of reviews grouped by RetailerName</option>");
			out.println("<option name='queryType' value='11'>Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			
			LinkedHashMap<String, LinkedHashMap<String, Review>> ListOfReviewsGroupedByRetailerName = new LinkedHashMap<String, LinkedHashMap<String, Review>>();
			ListOfReviewsGroupedByRetailerName = MongoDBDataStoreUtilities.getListOfReviewsGroupedByRetailerName();
			
			out.println("<h3 align='center'>List Of Reviews Grouped by Retailer Name</h3>");
			
			for(Map.Entry<String, LinkedHashMap<String, Review>> p :ListOfReviewsGroupedByRetailerName.entrySet())
			{
				String key1 = p.getKey();
				LinkedHashMap<String, Review> productsListHashMap = p.getValue();
				
				out.println("<h3 align=\"center\">"+key1+"</h3>");
				//out.println("<fieldset>");
				
				String name;
				String emailId;
				int reviewRating;
				Date date;
				String reviewText;
				int number=1;
			
				out.println("<table>");
				out.println("<tr><td>No.</td><td>Product Name</td><td>Email Id</td><td>Review Rating</td><td>Review Date</td><td>Review text</td></tr>");
				
					for(Map.Entry<String, Review> m :productsListHashMap.entrySet())
					{
						String key = m.getKey();
						Review c = m.getValue();
						
						name = c.getProductName();
						emailId = c.getEmailId();
						reviewRating = c.getReviewRating();
						
						DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
						date = c.getReviewDate();
						String reviewDate = dateFormat.format(date);
						
						reviewText = c.getReviewText();
						
						out.println("<tr><td>"+number+"</td>");
						out.println("<td>"+name+"</td>");
						out.println("<td>"+emailId+"</td>");
						out.println("<td>"+reviewRating+"</td>");
						out.println("<td>"+reviewDate+"</td>");
						out.println("<td>"+reviewText+"</td></tr>");
						
						number++;
					}
				
				out.println("</table>");
				
				//out.println("</fieldset>");
			}
		}
		
		if(queryType.equals("11"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1'>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			
			out.println("<option name='queryType' value='11' selected>Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			
			LinkedHashMap<String, LinkedHashMap<String, Double>> ProductsReviewedAndGotRating5InEveryCity = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
			ProductsReviewedAndGotRating5InEveryCity = MongoDBDataStoreUtilities.getProductsReviewedAndGotRating5InEveryCity();
			
			out.println("<h3 align='center'>Total Number Of Products Reviewed And Got Rating 5 In Every City</h3>");
			
			for(Map.Entry<String, LinkedHashMap<String, Double>> p :ProductsReviewedAndGotRating5InEveryCity.entrySet())
			{
				String key1 = p.getKey();
				LinkedHashMap<String, Double> productsListHashMap = p.getValue();
				
				out.println("<h3 align=\"center\">"+key1+"</h3>");
				
				int number=1;
			
				out.println("<table>");
				out.println("<tr><td>No.</td><td>Product Name</td><td>Rating</td></tr>");
				
					for(Map.Entry<String, Double> m :productsListHashMap.entrySet())
					{
						String key = m.getKey();
						Double value = m.getValue();
						
						out.println("<tr><td>"+number+"</td>");
						out.println("<td>"+key+"</td>");
						out.println("<td>"+value+"</td></tr>");
						
						number++;
					}
				
				out.println("</table>");
				
			}
		}
		
		if(queryType.equals("12"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1'>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			
			out.println("<option name='queryType' value='11' >Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12' selected>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			
			LinkedHashMap<String, LinkedHashMap<String, Double>> MostLikedProductInEveryCity = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
			MostLikedProductInEveryCity = MongoDBDataStoreUtilities.getMostLikedProductInEveryCity();
			
			out.println("<h3 align='center'>Total Number Of Products Reviewed And Got Rating 5 In Every City</h3>");
			
			for(Map.Entry<String, LinkedHashMap<String, Double>> p :MostLikedProductInEveryCity.entrySet())
			{
				String key1 = p.getKey();
				LinkedHashMap<String, Double> productsListHashMap = p.getValue();
				
				out.println("<h3 align=\"center\">"+key1+"</h3>");
				
				int number=1;
			
				out.println("<table>");
				out.println("<tr><td>No.</td><td>Product Name</td><td>Rating</td></tr>");
				
					for(Map.Entry<String, Double> m :productsListHashMap.entrySet())
					{
						String key = m.getKey();
						Double value = m.getValue();
						
						out.println("<tr><td>"+number+"</td>");
						out.println("<td>"+key+"</td>");
						out.println("<td>"+value+"</td></tr>");
						
						number++;
					}
				
				out.println("</table>");
				
			}
			
		}
		
		//Remaining
		if(queryType.equals("13"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1'>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			
			out.println("<option name='queryType' value='11' >Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13' selected>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			
			
			
		}
		
		//Remaining
		if(queryType.equals("14"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1'>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			
			out.println("<option name='queryType' value='11' >Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14' selected>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			
			
			
		}
		
		if(queryType.equals("15"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1'>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			
			out.println("<option name='queryType' value='11' >Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15' selected>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			
			LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> MostDislikedProductForEveryCitySortedByRetailerName = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>>();
			MostDislikedProductForEveryCitySortedByRetailerName = MongoDBDataStoreUtilities.getMostDislikedProductForEveryCitySortedByRetailerName();
			
			out.println("<h3 align='center'>Top 5 List Of Most Disliked Products In Every City Sorted By Retailer</h3>");
			
			for(Map.Entry<String, LinkedHashMap<String, ArrayList<Object>>> p :MostDislikedProductForEveryCitySortedByRetailerName.entrySet())
			{
				String key1 = p.getKey();
				LinkedHashMap<String, ArrayList<Object>> productsListHashMap = p.getValue();
				
				out.println("<h3 align=\"center\">"+key1+"</h3>");
				
				int number=1;
			
				out.println("<table>");
				out.println("<tr><td>No.</td><td><b>Retailer Name</b></td><td><b>Product Name</b></td><td><b>Rating</b></td></tr>");
				
					for(Map.Entry<String, ArrayList<Object>> m :productsListHashMap.entrySet())
					{
						String key = m.getKey();
						ArrayList<Object> values = m.getValue();
						
						out.println("<tr><td>"+number+"</td>");
						out.println("<td>"+values.get(0)+"</td>");
						out.println("<td>"+values.get(1)+"</td>");
						out.println("<td>"+values.get(2)+"</td></tr>");
						
						number++;
					}
				
				out.println("</table>");
				
			}
			
		}
		
		if(queryType.equals("16"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1'>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			
			out.println("<option name='queryType' value='11' >Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			
			out.println("<option name='queryType' value='16' selected>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			
			LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> MostDislikedProductForEveryZipSortedByRetailerName = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>>();
			MostDislikedProductForEveryZipSortedByRetailerName = MongoDBDataStoreUtilities.getMostDislikedProductForEveryZipCodeSortedByRetailerName();
			
			out.println("<h3 align='center'>Top 5 List Of Most Disliked Products In Every City Sorted By Retailer</h3>");
			
			for(Map.Entry<String, LinkedHashMap<String, ArrayList<Object>>> p :MostDislikedProductForEveryZipSortedByRetailerName.entrySet())
			{
				String key1 = p.getKey();
				LinkedHashMap<String, ArrayList<Object>> productsListHashMap = p.getValue();
				
				out.println("<h3 align=\"center\">Zip Code: "+key1+"</h3>");
				
				int number=1;
			
				out.println("<table>");
				out.println("<tr><td>No.</td><td><b>Retailer Name</b></td><td><b>Product Name</b></td><td><b>Rating</b></td></tr>");
				
					for(Map.Entry<String, ArrayList<Object>> m :productsListHashMap.entrySet())
					{
						String key = m.getKey();
						ArrayList<Object> values = m.getValue();
						
						out.println("<tr><td>"+number+"</td>");
						out.println("<td>"+values.get(0)+"</td>");
						out.println("<td>"+values.get(1)+"</td>");
						out.println("<td>"+values.get(2)+"</td></tr>");
						
						number++;
					}
				
				out.println("</table>");
				
			}
			
		}
		
		//Remaining
		if(queryType.equals("17"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1'>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			
			out.println("<option name='queryType' value='11' >Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17' selected>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			
			
			
		}
		
		if(queryType.equals("18"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1'>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			
			out.println("<option name='queryType' value='11' >Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18' selected>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			
			LinkedHashMap<String, LinkedHashMap<String, Review>> ReviewsWhereAgeGreaterThan50InEveryCity = new LinkedHashMap<String, LinkedHashMap<String, Review>>();
			ReviewsWhereAgeGreaterThan50InEveryCity = MongoDBDataStoreUtilities.getReviewsWhereAgeGreaterThan50InEveryCity();
			
			out.println("<h3 align='center'>List Of Reviews Where Age > 50 And Sorted By Age in Every City</h3>");
			
			for(Map.Entry<String, LinkedHashMap<String, Review>> p :ReviewsWhereAgeGreaterThan50InEveryCity.entrySet())
			{
				String key1 = p.getKey();
				LinkedHashMap<String, Review> productsListHashMap = p.getValue();
				
				out.println("<h3 align=\"center\">"+key1+"</h3>");
				
				String name;
				String emailId;
				int reviewRating;
				Date date;
				String reviewText;
				int userAge;
				int number=1;
			
				out.println("<table>");
				out.println("<tr><td>No.</td><td>User Age</td><td>Product Name</td><td>Email Id</td><td>Review Rating</td><td>Review Date</td><td>Review text</td></tr>");
				
					for(Map.Entry<String, Review> m :productsListHashMap.entrySet())
					{
						String key = m.getKey();
						Review c = m.getValue();
						
						name = c.getProductName();
						emailId = c.getEmailId();
						reviewRating = c.getReviewRating();
						userAge = c.getUserAge();
						
						DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
						date = c.getReviewDate();
						String reviewDate = dateFormat.format(date);
						
						reviewText = c.getReviewText();
						
						out.println("<tr><td>"+number+"</td>");
						out.println("<td>"+userAge+"</td>");
						out.println("<td>"+name+"</td>");
						out.println("<td>"+emailId+"</td>");
						out.println("<td>"+reviewRating+"</td>");
						out.println("<td>"+reviewDate+"</td>");
						out.println("<td>"+reviewText+"</td></tr>");
						
						number++;
					}
				
				out.println("</table>");
				
			}
			
		}
		
		if(queryType.equals("19"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1'>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			
			out.println("<option name='queryType' value='11' >Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19' selected>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20'>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
			
			
			
		}
		
		if(queryType.equals("20"))
		{
			out.println("<form action='DataAnalyticsServlet' method='post'>");
			out.println("<p><select name='queryType'>");
			out.println("<option name='queryType' value='1'>Print the list of all products and their ratings</option>");
			out.println("<option name='queryType' value='2'>Print a list of reviews where rating greater than 3</option>");
			out.println("<option name='queryType' value='3'>Get a list of products that got review rating 5 and price more than thousand</option>");
			out.println("<option name='queryType' value='4'>Print a list of how many reviews for every product </option>");
			out.println("<option name='queryType' value='5'>Get the list of reviews for shoppers in Chicago</option>");
			out.println("<option name='queryType' value='6'>Find highest price product reviewed/sold in every city</option>");
			out.println("<option name='queryType' value='7'>Find highest price product reviewed/sold in every zip-code</option>");
			out.println("<option name='queryType' value='8'>Get the top 5 list of liked products for every city</option>");
			out.println("<option name='queryType' value='9'>Print a list of reviews grouped by City</option>");
			out.println("<option name='queryType' value='10'>Print a list of reviews grouped by RetailerName</option>");
			
			out.println("<option name='queryType' value='11' >Get the total number of products reviewed and got Rating 5 in Every City</option>");
			out.println("<option name='queryType' value='12'>Most liked product in every city</option>");
			out.println("<option name='queryType' value='13'>Print the median product prices per city</option>");
			out.println("<option name='queryType' value='14'>Get top 5 list of most liked and expensive products sorted by retailer name for every city</option>");
			out.println("<option name='queryType' value='15'>Get the top 5 list of most Disliked products sorted by retailer name for every city</option>");
			
			out.println("<option name='queryType' value='16'>Get the top 5 list of most Disliked products sorted by retailer name for every zip-code</option>");
			out.println("<option name='queryType' value='17'>Get the top 2 list of zip-codes where highest number of products got review rating 5</option>");
			out.println("<option name='queryType' value='18'>Get a list of reviews where reviewer age greater than 50 and the list is sorted by age in every city</option>");
			out.println("<option name='queryType' value='19'>Get the top 5 list of most liked products sorted by manufacturer name for every city</option>");
			out.println("<option name='queryType' value='20' selected>Search reviews text for keywords (pattern-matching) and print the list of reviews that have the matched keywords</option>");
			out.println("</select></p>");
			out.println("<p><input name=\"send\"   class=\"formbutton\" value=\"Execute Query\" type=\"submit\" /></p>");
			out.println("</form>");
		}
		
		out.println("</fieldset></article</div></div></body></html>");
		
		out.close();
	}
			
}


