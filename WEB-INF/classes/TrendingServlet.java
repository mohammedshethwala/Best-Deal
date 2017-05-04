import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class TrendingServlet extends HttpServlet
{
	
	public void init()
	{
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("doPost Start: ");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		
		String fname = null;
		
		if(session != null)
		{
			fname=(String)session.getAttribute("firstName");
		}
		
		out.println("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("<title>Best Deal</title><link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
		
		out.println("<script type=\"text/javascript\" src=\"javascript.js\"></script></head>");
		out.println("<body onload='init()'><div id=\"container\"><header><h1><a href=\"/\">Best<span>Deal</span></a></h1><h2>Best Price Guaranteed</h2>");
		out.println("<form  name='autofillform1' action=''>");
		out.println("<div name='autofillform'>");
		out.println("<strong>Search Products: </strong>");
		out.println("<input type='text' name='searchId' size='40' id='searchId' onkeyup='doCompletion()' placeholder='Search Here...'>");
		out.println("<div id='auto-row'>");
		out.println("<table border='0' id='complete-table' class='popupBox'></table>");
		out.println("</div>");
		out.println("</div>");
		out.println("</form>");
		
		out.println("</header>");
		
		if(fname != null && !fname.isEmpty() && session != null)
		{
			System.out.println("Inside welcome string");
			out.println("<h5>Welcome ");
			out.println(fname);
			out.println("</h5>");
			out.println("<nav><ul><li class=\"start selected\"><a href=\"LoggedInHomeServlet\">Home</a></li>");
		}
		else
		{
			out.println("<nav><ul><li class=\"start selected\"><a href=\"HomeServlet\">Home</a></li>");
		}
		
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=Smartphones\">SmartPhones</a></li>");
		out.println("<li><a href=\"ContentServlet?productType=Tablets\">Tablets</a></li>");
		out.println("<li><a href=\"ContentServlet?productType=Laptops\">Laptops</a></li>");
		out.println("<li class=\"\"><a href=\"ContentServlet?productType=Televisions\">Televisions</a></li>");
		out.println("<li><a href=\"ContentServlet?productType=Accessories\">Accessories</a></li>");
		
		if(fname != null && !fname.isEmpty() && session != null)
		{
			out.println("<li><a href=\"ViewCartServlet\">Cart</a></li>");
			out.println("<li><a href=\"ViewOrders\">Your Orders</a></li>");
			out.println("<li><a href=\"LogoutServlet\">Logout</a></li>");
		}
		else
		{
			out.println("<li><a href=\"LoginServlet\">Login</a></li>");
			out.println("<li><a href=\"SignUp.html\">SignUp</a></li>");
			out.println("<li><a href=\"ViewCartServlet\">Cart</a></li>");
		}
		
		out.println("</ul></nav><img class=\"header-image\" src=\"images/home.jpg\" alt=\"Advertisment Image Here\" />");
		
//------------------------------------------------------------------------------------------------------------------------------------------
		
	//Body starts
	
		out.println("<div id=\"body\"><section id=\"content\">");
		
		String trendType = request.getParameter("trendType");
		
		//Top 5 most like products code here
		if(trendType.equals("topFiveLiked"))
		{
			LinkedHashMap<String, Double> top5LikedProducts;
			top5LikedProducts = MongoDBDataStoreUtilities.getTop5LikedProducts();
			
			out.println("<h3 align='center'>Top 5 Most Liked Products</h3>");
			out.println("<table>");
			out.println("<tr><td><b>Product Name</b></td><td><b>Average Rating</b></td></tr>");
			
			for(Map.Entry<String, Double> m :top5LikedProducts.entrySet())
			{
				String key = m.getKey();
				Double value = m.getValue();
				
				out.println("<tr><td>"+key+"</td>");
				out.println("<td>"+value+"</td></tr>");
			}
			
			out.println("</table>");
		}
		
		//Top 5 zip codes code
		if(trendType.equals("topFiveZipCodes"))
		{
			LinkedHashMap<String, Integer> top5ZipCodes;
			top5ZipCodes = MongoDBDataStoreUtilities.getTop5ZipCodes();
			
			out.println("<h3 align='center'>Top 5 Zip Codes Where Maximum Number Of Products Sold</h3>");
			out.println("<table>");
			out.println("<tr><td><b>Zip Code</b></td><td><b>Products Sold In This Zip Code</b></td></tr>");
			
			for(Map.Entry<String, Integer> m :top5ZipCodes.entrySet())
			{
				String key = m.getKey();
				Integer value = m.getValue();
				
				out.println("<tr><td>"+key+"</td>");
				out.println("<td>"+value+"</td></tr>");
			}
			
			out.println("</table>");
		}
		
		//Top 5 sold Products code
		if(trendType.equals("topFiveSold"))
		{
			LinkedHashMap<String, ArrayList<Object>> top5SoldProducts;
			top5SoldProducts = MySqlDataStoreUtilities.getTop5SoldProducts();
			
			String itemName;
			float itemPrice;
			int itemQty;
			
			out.println("<h3 align='center'>Top 5 Sold Products</h3>");
			out.println("<table>");
			out.println("<tr><td><b>Product Name</b></td><td><b>Product Price</b></td><td><b>Quantity Sold</b></td></tr>");
			
			for(Map.Entry<String, ArrayList<Object>> m :top5SoldProducts.entrySet())
			{
				ArrayList<Object> values = m.getValue();
				
				itemName = (String)values.get(0);
				itemPrice = (Float)values.get(1);
				itemQty = (Integer)values.get(2);
				
				out.println("<tr><td>"+itemName+"</td>");
				out.println("<td>"+itemPrice+"</td>");
				out.println("<td>"+itemQty+"</td></tr>");
			}
			
			out.println("</table>");
			
		}
		
		out.println("</section>");
		
	//Sidebar starts	
		out.println("<aside class=\"sidebar\">");
		
		out.println("<ul><li><h4>Trending</h4><ul>");
		out.println("<li><a href=\"TrendingServlet?trendType=topFiveLiked\">Top 5 Liked Products</a></li>");
		out.println("<li><a href=\"TrendingServlet?trendType=topFiveZipCodes\">Top 5 Zip Codes</a></li>");
		out.println("<li><a href=\"TrendingServlet?trendType=topFiveSold\">Top 5 Sold Products</a></li></ul></li>");
		
		out.println("<ul><li><h4>SmartPhones</h4><ul>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Smartphones&companyType=Apple\">Apple</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Smartphones&companyType=Samsung\">Samsung</a></li>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Smartphones&companyType=HTC\">HTC</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Smartphones&companyType=Motorola\">Motorola</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Smartphones&companyType=LG\">LG</a></li></ul></li>");
		
		out.println("<ul><li><h4>Tablets</h4><ul>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Tablets&companyType=Apple\">Apple</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Tablets&companyType=Samsung\">Samsung</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Tablets&companyType=Acer\">Acer</a></li>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Tablets&companyType=Amazon\">Amazon</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Tablets&companyType=LG\">LG</a></li></ul></li>");
		
		out.println("<ul><li><h4>Laptops</h4><ul>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Laptops&companyType=Apple\">Apple</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Laptops&companyType=Dell\">Dell</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Laptops&companyType=HP\">HP</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Laptops&companyType=Lenovo\">Lenovo</a></li>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Laptops&companyType=Microsoft\">Microsoft</a></li></ul></li>");
		
		out.println("<ul><li><h4>Tv's</h4><ul><li><a href=\"CompanyWiseProductsServlet?productType=Televisions&companyType=LG\">LG</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Televisions&companyType=Samsung\">Samsung</a></li>");
		out.println("<li><a href=\"CompanyWiseProductsServlet?productType=Televisions&companyType=Vizio\">Vizio</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Televisions&companyType=Sony\">Sony</a></li><li><a href=\"CompanyWiseProductsServlet?productType=Televisions&companyType=Sharp\">Sharp</a></li>");
		
		out.println("</ul></li></aside><div class=\"clear\"></div></div>");
		
	//Footer starts	
		out.println("<footer><div class=\"footer-content\"><ul><li><h4>About Us</h4></li></ul><ul>");
		out.println("<li><h4>Contact Us</h4></li></ul><ul class=\"endfooter\"><li><h4>Customer Service</h4></li>");
		out.println("</ul><div class=\"clear\"></div></div><div class=\"footer-bottom\">");
		out.println("<p>&copy; BestDeal 2016. by Mohammed Shethwala</p></div></footer></div>");
		
		
		out.println("</body></html>");
		
		out.close();
		}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}