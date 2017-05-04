import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class MongoDBDataStoreUtilities
{
	static DBCollection myReviews;
	
	public static void getConnection()
	{
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
	}
	
	public static void insertReview(String name, String type, double price, String retailer, String retailerZip, String retailerCity,
										String retailerState, String productOnSale, String manufacturer, String manufacturerRebate,
										String emailId, int userAge, String userGender, String userOccupation, int reviewRating,
										Date reviewDate, String reviewText)
	{
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		BasicDBObject document = new BasicDBObject();
		
		document.put("productName", name);
		document.put("productType", type);
		document.put("productPrice", price);
		document.put("retailer", retailer);
		document.put("retailerZip", retailerZip);
		document.put("retailerCity", retailerCity);
		document.put("retailerState", retailerState);
		document.put("productOnSale", productOnSale);
		document.put("manufacturer", manufacturer);
		document.put("manufacturerRebate", manufacturerRebate);
		document.put("emailId", emailId);
		document.put("userAge", userAge);
		document.put("userGender", userGender);
		document.put("userOccupation", userOccupation);
		document.put("reviewRating", reviewRating);
		document.put("reviewDate", reviewDate);
		document.put("reviewText", reviewText);
		

		myReviews.insert(document);
	}
	
	public static HashMap<String, Review> getReviews()
	{
		HashMap<String, Review> reviews = new HashMap<String, Review>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		DBCursor cursor = myReviews.find();
		
		while (cursor.hasNext())
		{
			BasicDBObject obj= (BasicDBObject) cursor.next();
			
			Review review = new Review(obj.getString("productName"), obj.getString("productType"), obj.getDouble("productPrice")
										,obj.getString("retailer"), obj.getString("retailerZip"),obj.getString("retailerCity")
										,obj.getString("retailerState"), obj.getString("productOnSale"), obj.getString("manufacturer")
										, obj.getString("manufacturerRebate"), obj.getString("emailId"), obj.getInt("userAge")
										, obj.getString("userGender"), obj.getString("userOccupation"), obj.getInt("reviewRating")
										,obj.getDate("reviewDate"), obj.getString("reviewText"));
			
			reviews.put(obj.getString("productName")+obj.getString("_id"), review);
		}
		System.out.println("Reviews hashmap in MongoDbDataStoreUtilities: "+reviews);
		
		return reviews;
		
	}
	
	public static LinkedHashMap<String, Integer> getTop5ZipCodes()
	{
		LinkedHashMap<String, Integer> top5ZipCodes = new LinkedHashMap<String, Integer>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		AggregationOutput output =
            myReviews.aggregate(
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$retailerZip")
                                    .append("count", new BasicDBObject("$sum", 1)))
                    ,
                    new BasicDBObject("$sort", new BasicDBObject("count", -1)),
					new BasicDBObject("$limit", 5)
            );

		String zip="";
		int count = 0;
		
		for (DBObject doc : output.results())
		{
			zip = (String) doc.get("_id");
			count = (Integer) doc.get("count");
			
			top5ZipCodes.put(zip, count);
			
			//System.out.println("Zip: " + zip);
			//System.out.println("Count: " + count);
		}

		//System.out.println("top5ZipCodes: " + top5ZipCodes);
		return top5ZipCodes;
	}
	
	public static LinkedHashMap<String, Double> getTop5LikedProducts()
	{
		LinkedHashMap<String, Double> top5LikedProducts = new LinkedHashMap<String, Double>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		//db.myReviews.aggregate([{$group:{_id:'$productName', avgRating: {$avg:"$reviewRating"}}}, {$sort: {avgRating: -1}}, {$limit: 5}])
		
		AggregationOutput output =
            myReviews.aggregate(
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$productName")
                                    .append("avgRating", new BasicDBObject("$avg", "$reviewRating")))
                    ,
                    new BasicDBObject("$sort", new BasicDBObject("avgRating", -1)),
					new BasicDBObject("$limit", 5)
            );

		String productName="";
		double avg = 0;
		
		for (DBObject doc : output.results())
		{
			productName = (String) doc.get("_id");
			avg = (Double) doc.get("avgRating");
			
			if(avg>4)
			{
				top5LikedProducts.put(productName, avg);
			}
				
			
			//System.out.println("productName: " + productName);
			//System.out.println("avg: " + avg);
		}

		//System.out.println("top5ZipCodes: " + top5LikedProducts);
		return top5LikedProducts;
	}
	
	public static LinkedHashMap<String, Double> getProductListAndTheirRatings()
	{
		LinkedHashMap<String, Double> productListAndTheirRatings = new LinkedHashMap<String, Double>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		//db.myReviews.aggregate([{$group:{_id:'$productName', avgRating: {$avg:"$reviewRating"}}}, {$sort: {avgRating: -1}}, {$limit: 5}])
		
		AggregationOutput output =
            myReviews.aggregate(
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$productName")
                                    .append("avgRating", new BasicDBObject("$avg", "$reviewRating")))
                    ,
                    new BasicDBObject("$sort", new BasicDBObject("avgRating", -1))
            );

		String productName="";
		double avg = 0;
		
		for (DBObject doc : output.results())
		{
			productName = (String) doc.get("_id");
			avg = (Double) doc.get("avgRating");
			
			productListAndTheirRatings.put(productName, avg);
			
			//System.out.println("productName: " + productName);
			//System.out.println("avg: " + avg);
		}

		//System.out.println("top5ZipCodes: " + top5LikedProducts);
		return productListAndTheirRatings;
	}
	
	public static HashMap<String, Review> getReviewListWhereRatingGreaterThan3()
	{
		HashMap<String, Review> reviews = new HashMap<String, Review>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		BasicDBObject query = new BasicDBObject("reviewRating", new BasicDBObject("$gt", 3));
		
		DBCursor cursor = myReviews.find(query);
		
		while (cursor.hasNext())
		{
			BasicDBObject obj= (BasicDBObject) cursor.next();
			
			Review review = new Review(obj.getString("productName"), obj.getString("productType"), obj.getDouble("productPrice")
										,obj.getString("retailer"), obj.getString("retailerZip"),obj.getString("retailerCity")
										,obj.getString("retailerState"), obj.getString("productOnSale"), obj.getString("manufacturer")
										, obj.getString("manufacturerRebate"), obj.getString("emailId"), obj.getInt("userAge")
										, obj.getString("userGender"), obj.getString("userOccupation"), obj.getInt("reviewRating")
										,obj.getDate("reviewDate"), obj.getString("reviewText"));
			
			reviews.put(obj.getString("productName")+obj.getString("_id"), review);
		}
		//System.out.println("Reviews hashmap in MongoDbDataStoreUtilities: "+reviews);
		
		return reviews;
		
	}
	
	public static HashMap<String, Review> getReviewListWhereRating5AndPriceMoreThan1000()
	{
		HashMap<String, Review> reviews = new HashMap<String, Review>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		//BasicDBObject query = new BasicDBObject("reviewRating", new BasicDBObject("$gt", 3));
		
		DBObject clause1 = new BasicDBObject("reviewRating", new BasicDBObject("$eq", 5));  
		DBObject clause2 = new BasicDBObject("productPrice", new BasicDBObject("$gt", 1000));    
		BasicDBList and = new BasicDBList();
		and.add(clause1);
		and.add(clause2);
		DBObject query = new BasicDBObject("$and", and);
		
		DBCursor cursor = myReviews.find(query);
		
		while (cursor.hasNext())
		{
			BasicDBObject obj= (BasicDBObject) cursor.next();
			
			Review review = new Review(obj.getString("productName"), obj.getString("productType"), obj.getDouble("productPrice")
										,obj.getString("retailer"), obj.getString("retailerZip"),obj.getString("retailerCity")
										,obj.getString("retailerState"), obj.getString("productOnSale"), obj.getString("manufacturer")
										, obj.getString("manufacturerRebate"), obj.getString("emailId"), obj.getInt("userAge")
										, obj.getString("userGender"), obj.getString("userOccupation"), obj.getInt("reviewRating")
										,obj.getDate("reviewDate"), obj.getString("reviewText"));
			
			reviews.put(obj.getString("productName")+obj.getString("_id"), review);
		}
		//System.out.println("Reviews hashmap in MongoDbDataStoreUtilities: "+reviews);
		
		return reviews;
		
	}
	
	public static LinkedHashMap<String, Integer> getHowManyReviewsForEveryProduct()
	{
		LinkedHashMap<String, Integer> howManyReviewsForEveryProduct = new LinkedHashMap<String, Integer>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		AggregationOutput output =
            myReviews.aggregate(
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$productName")
                                    .append("count", new BasicDBObject("$sum", 1)))
                    ,
                    new BasicDBObject("$sort", new BasicDBObject("count", -1))
            );

		String productName="";
		int count = 0;
		
		for (DBObject doc : output.results())
		{
			productName = (String) doc.get("_id");
			count = (Integer) doc.get("count");
			
			howManyReviewsForEveryProduct.put(productName, count);
		}

		return howManyReviewsForEveryProduct;
	}
	
	public static HashMap<String, Review> getListOfReviewsForShoppersInChicago()
	{
		HashMap<String, Review> reviews = new HashMap<String, Review>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		BasicDBObject query = new BasicDBObject("retailerCity", new BasicDBObject("$eq", "Chicago"));
		
		DBCursor cursor = myReviews.find(query);
		
		while (cursor.hasNext())
		{
			BasicDBObject obj= (BasicDBObject) cursor.next();
			
			Review review = new Review(obj.getString("productName"), obj.getString("productType"), obj.getDouble("productPrice")
										,obj.getString("retailer"), obj.getString("retailerZip"),obj.getString("retailerCity")
										,obj.getString("retailerState"), obj.getString("productOnSale"), obj.getString("manufacturer")
										, obj.getString("manufacturerRebate"), obj.getString("emailId"), obj.getInt("userAge")
										, obj.getString("userGender"), obj.getString("userOccupation"), obj.getInt("reviewRating")
										,obj.getDate("reviewDate"), obj.getString("reviewText"));
			
			reviews.put(obj.getString("productName")+obj.getString("_id"), review);
		}
		//System.out.println("Reviews hashmap in MongoDbDataStoreUtilities: "+reviews);
		
		return reviews;
		
	}
	
	public static LinkedHashMap<String, ArrayList<Object>> getHighestPricedProductReviewedInEveryCity()
	{
		LinkedHashMap<String, ArrayList<Object>> highestPricedProductReviewedInEveryCity = new LinkedHashMap<String, ArrayList<Object>>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		AggregationOutput output =
            myReviews.aggregate(
					new BasicDBObject("$sort", new BasicDBObject("productPrice", -1)),
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$retailerCity")
                                    .append("maxPrice", new BasicDBObject("$max", "$productPrice"))
									.append("productName", new BasicDBObject("$first", "$productName")))
                    ,
                    new BasicDBObject("$sort", new BasicDBObject("retailerCity", 1))
            );

		String retailerCity="";
		double maxPrice = 0;
		String productName="";
		
		for (DBObject doc : output.results())
		{
			retailerCity = (String) doc.get("_id");
			maxPrice = (Double) doc.get("maxPrice");
			productName = (String) doc.get("productName");
			
			ArrayList<Object> values = new ArrayList<Object>();
			
			values.add(retailerCity);
			values.add(productName);
			values.add(maxPrice);
			
			highestPricedProductReviewedInEveryCity.put(retailerCity, values);
			
			//System.out.println("retailerCity: " + retailerCity);
			//System.out.println("productName: " + productName);
			//System.out.println("maxPrice: " + maxPrice);
		}

		//System.out.println("highestPricedProductReviewedInEveryCity: " + highestPricedProductReviewedInEveryCity);
		return highestPricedProductReviewedInEveryCity;
	}
	
	public static LinkedHashMap<String, ArrayList<Object>> getHighestPricedProductReviewedInEveryZipCode()
	{
		LinkedHashMap<String, ArrayList<Object>> highestPricedProductReviewedInEveryZipCode = new LinkedHashMap<String, ArrayList<Object>>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		AggregationOutput output =
            myReviews.aggregate(
					new BasicDBObject("$sort", new BasicDBObject("productPrice", -1)),
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$retailerZip")
                                    .append("maxPrice", new BasicDBObject("$max", "$productPrice"))
									.append("productName", new BasicDBObject("$first", "$productName")))
                    ,
                    new BasicDBObject("$sort", new BasicDBObject("retailerZip", 1))
            );

		String retailerZip="";
		double maxPrice = 0;
		String productName="";
		
		for (DBObject doc : output.results())
		{
			retailerZip = (String) doc.get("_id");
			maxPrice = (Double) doc.get("maxPrice");
			productName = (String) doc.get("productName");
			
			ArrayList<Object> values = new ArrayList<Object>();
			
			values.add(retailerZip);
			values.add(productName);
			values.add(maxPrice);
			
			highestPricedProductReviewedInEveryZipCode.put(retailerZip, values);
		
		}
		
		return highestPricedProductReviewedInEveryZipCode;
	}
	
	public static LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> getTop5ListOfLikedProductsForEveryCity()
	{

		LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> Top5ListOfLikedProductsForEveryCity = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>>();
		//LinkedHashMap<String, ArrayList<Object>> productsListHashMap = new LinkedHashMap<String, ArrayList<Object>>();
		//ArrayList<Object> values = new ArrayList<Object>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		
		AggregationOutput output1 =
            myReviews.aggregate(
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$retailerCity"))
            );

		String retailerCity="";
		
		for (DBObject doc : output1.results())
		{
			retailerCity = (String) doc.get("_id");
		
			AggregationOutput output =
            myReviews.aggregate(
					new BasicDBObject("$match", 
				    new BasicDBObject("retailerCity",
				    new BasicDBObject("$eq", retailerCity) ) ),
					
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$productName")
                                    .append("avgRating", new BasicDBObject("$avg", "$reviewRating")))
                    ,
                    new BasicDBObject("$sort", new BasicDBObject("avgRating", -1)),
					
					new BasicDBObject("$limit", 5)
            );
			
			String city = retailerCity;
			String productName="";
			double avg = 0;
			
			LinkedHashMap<String, ArrayList<Object>> productsListHashMap = new LinkedHashMap<String, ArrayList<Object>>();
			
			for (DBObject doc2 : output.results())
			{
				productName = (String) doc2.get("_id");
				avg = (Double) doc2.get("avgRating");
				
				ArrayList<Object> values = new ArrayList<Object>();
				values.add(retailerCity);
				values.add(productName);
				values.add(avg);
				
				
				productsListHashMap.put(productName, values);
				
				System.out.println("city: " + city);
				System.out.println("productName: " + productName);
				System.out.println("avg: " + avg);
				
			}
			
			Top5ListOfLikedProductsForEveryCity.put(retailerCity, productsListHashMap);
			System.out.println("productsListHashMap: " + productsListHashMap);
		}
		
		
		System.out.println("Top5ListOfLikedProductsForEveryCity: " + Top5ListOfLikedProductsForEveryCity);
		return Top5ListOfLikedProductsForEveryCity;
	}
	
	public static LinkedHashMap<String, LinkedHashMap<String, Review>> getListOfReviewsGroupedByCity()
	{
		LinkedHashMap<String, LinkedHashMap<String, Review>> ListOfReviewsGroupedByCity = new LinkedHashMap<String, LinkedHashMap<String, Review>>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		AggregationOutput output1 =
            myReviews.aggregate(
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$retailerCity"))
            );

		String retailerCity="";
		
		for (DBObject doc : output1.results())
		{
			retailerCity = (String) doc.get("_id");
			LinkedHashMap<String, Review> productsListHashMap = new LinkedHashMap<String, Review>();
			
			BasicDBObject query = new BasicDBObject("retailerCity", new BasicDBObject("$eq", retailerCity));
		
			DBCursor cursor = myReviews.find(query);
			
			while (cursor.hasNext())
			{
				BasicDBObject obj= (BasicDBObject) cursor.next();
				
				Review review = new Review(obj.getString("productName"), obj.getString("productType"), obj.getDouble("productPrice")
											,obj.getString("retailer"), obj.getString("retailerZip"),obj.getString("retailerCity")
											,obj.getString("retailerState"), obj.getString("productOnSale"), obj.getString("manufacturer")
											, obj.getString("manufacturerRebate"), obj.getString("emailId"), obj.getInt("userAge")
											, obj.getString("userGender"), obj.getString("userOccupation"), obj.getInt("reviewRating")
											,obj.getDate("reviewDate"), obj.getString("reviewText"));
											
				System.out.println("review of Product: " + obj.getString("productName"));
				
				productsListHashMap.put(obj.getString("productName"), review);
			}
			
			ListOfReviewsGroupedByCity.put(retailerCity, productsListHashMap);
			System.out.println("productsListHashMap: " + productsListHashMap);
		}
		
		System.out.println("ListOfReviewsGroupedByCity: " + ListOfReviewsGroupedByCity);
		return ListOfReviewsGroupedByCity;
	}
	
	public static LinkedHashMap<String, LinkedHashMap<String, Review>> getListOfReviewsGroupedByRetailerName()
	{
		LinkedHashMap<String, LinkedHashMap<String, Review>> ListOfReviewsGroupedByRetailerName = new LinkedHashMap<String, LinkedHashMap<String, Review>>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		AggregationOutput output1 =
            myReviews.aggregate(
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$retailer"))
            );

		String retailer="";
		
		for (DBObject doc : output1.results())
		{
			retailer = (String) doc.get("_id");
			LinkedHashMap<String, Review> productsListHashMap = new LinkedHashMap<String, Review>();
			
			BasicDBObject query = new BasicDBObject("retailer", new BasicDBObject("$eq", retailer));
		
			DBCursor cursor = myReviews.find(query);
			
			while (cursor.hasNext())
			{
				BasicDBObject obj= (BasicDBObject) cursor.next();
				
				Review review = new Review(obj.getString("productName"), obj.getString("productType"), obj.getDouble("productPrice")
											,obj.getString("retailer"), obj.getString("retailerZip"),obj.getString("retailerCity")
											,obj.getString("retailerState"), obj.getString("productOnSale"), obj.getString("manufacturer")
											, obj.getString("manufacturerRebate"), obj.getString("emailId"), obj.getInt("userAge")
											, obj.getString("userGender"), obj.getString("userOccupation"), obj.getInt("reviewRating")
											,obj.getDate("reviewDate"), obj.getString("reviewText"));
											
				System.out.println("review of Product: " + obj.getString("productName"));
				
				productsListHashMap.put(obj.getString("productName")+obj.getString("_id"), review);
			}
			
			ListOfReviewsGroupedByRetailerName.put(retailer, productsListHashMap);
			System.out.println("productsListHashMap: " + productsListHashMap);
		}
		
		System.out.println("ListOfReviewsGroupedByRetailerName: " + ListOfReviewsGroupedByRetailerName);
		return ListOfReviewsGroupedByRetailerName;
	}
	
	public static LinkedHashMap<String, LinkedHashMap<String, Double>> getProductsReviewedAndGotRating5InEveryCity()
	{
		LinkedHashMap<String, LinkedHashMap<String, Double>> ProductsReviewedAndGotRating5InEveryCity = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		AggregationOutput output1 =
            myReviews.aggregate(
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$retailerCity"))
            );

		String retailerCity="";
		
		for (DBObject doc : output1.results())
		{
			retailerCity = (String) doc.get("_id");
			LinkedHashMap<String, Double> productsListHashMap = new LinkedHashMap<String, Double>();
	
			AggregationOutput output =
            myReviews.aggregate(
					new BasicDBObject("$match", 
				    new BasicDBObject("retailerCity",
				    new BasicDBObject("$eq", retailerCity) ) ),
					
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$productName")
                                    .append("avgRating", new BasicDBObject("$avg", "$reviewRating"))),
									
					new BasicDBObject("$match", 
				    new BasicDBObject("avgRating",
				    new BasicDBObject("$eq", 5) ) ),
                    
                    new BasicDBObject("$sort", new BasicDBObject("avgRating", -1))
            );

			String productName="";
			double avg = 0;
			
			for (DBObject doc2 : output.results())
			{
				productName = (String) doc2.get("_id");
				avg = (Double) doc2.get("avgRating");
				
				productsListHashMap.put(productName, avg);
				
				//System.out.println("productName: " + productName);
				//System.out.println("avg: " + avg);
			}
			
			ProductsReviewedAndGotRating5InEveryCity.put(retailerCity, productsListHashMap);
			//System.out.println("productsListHashMap: " + productsListHashMap);
		}
		
		//System.out.println("ProductsReviewedAndGotRating5InEveryCity: " + ProductsReviewedAndGotRating5InEveryCity);
		return ProductsReviewedAndGotRating5InEveryCity;
	}
	
	public static LinkedHashMap<String, LinkedHashMap<String, Double>> getMostLikedProductInEveryCity()
	{
		LinkedHashMap<String, LinkedHashMap<String, Double>> MostLikedProductInEveryCity = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		AggregationOutput output1 =
            myReviews.aggregate(
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$retailerCity"))
            );

		String retailerCity="";
		
		for (DBObject doc : output1.results())
		{
			retailerCity = (String) doc.get("_id");
			LinkedHashMap<String, Double> productsListHashMap = new LinkedHashMap<String, Double>();
	
			AggregationOutput output =
            myReviews.aggregate(
					new BasicDBObject("$match", 
				    new BasicDBObject("retailerCity",
				    new BasicDBObject("$eq", retailerCity) ) ),
					
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$productName")
                                    .append("avgRating", new BasicDBObject("$avg", "$reviewRating"))),
									
					new BasicDBObject("$match", 
				    new BasicDBObject("avgRating",
				    new BasicDBObject("$eq", 5) ) ),
                    
                    new BasicDBObject("$sort", new BasicDBObject("avgRating", -1)),
					new BasicDBObject("$limit", 1)
            );

			String productName="";
			double avg = 0;
			
			for (DBObject doc2 : output.results())
			{
				productName = (String) doc2.get("_id");
				avg = (Double) doc2.get("avgRating");
				
				productsListHashMap.put(productName, avg);
				
				//System.out.println("productName: " + productName);
				//System.out.println("avg: " + avg);
			}
			
			MostLikedProductInEveryCity.put(retailerCity, productsListHashMap);
			//System.out.println("productsListHashMap: " + productsListHashMap);
		}
		
		//System.out.println("MostLikedProductInEveryCity: " + MostLikedProductInEveryCity);
		return MostLikedProductInEveryCity;
	}
	
	//Remaining - queryType: 13
	public static LinkedHashMap<String, LinkedHashMap<String, Double>> getMedianProductPricesPerCity()
	{
		
		LinkedHashMap<String, LinkedHashMap<String, Double>> MedianProductPricesPerCity = new LinkedHashMap<String, LinkedHashMap<String, Double>>();
		/*
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		AggregationOutput output1 =
            myReviews.aggregate(
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$retailerCity"))
            );

		String retailerCity="";
		
		for (DBObject doc : output1.results())
		{
			retailerCity = (String) doc.get("_id");
			LinkedHashMap<String, Double> productsListHashMap = new LinkedHashMap<String, Double>();
	
			AggregationOutput output =
            myReviews.aggregate(
					new BasicDBObject("$match", 
				    new BasicDBObject("retailerCity",
				    new BasicDBObject("$eq", retailerCity) ) ),
					
                    new BasicDBObject("$sort", new BasicDBObject("productPrice", 1)),
										
					new BasicDBObject("$limit", 1)
            );

			String productName="";
			double medianPrice = 0;
			
			for (DBObject doc2 : output.results())
			{
				productName = (String) doc2.get("_id");
				medianPrice = (Double) doc2.get("productPrice");
				
				productsListHashMap.put(productName, medianPrice);
				
				//System.out.println("productName: " + productName);
				//System.out.println("avg: " + avg);
			}
			
			MedianProductPricesPerCity.put(retailerCity, productsListHashMap);
			//System.out.println("productsListHashMap: " + productsListHashMap);
		}
		
		//System.out.println("MedianProductPricesPerCity: " + MedianProductPricesPerCity);
		*/
		return MedianProductPricesPerCity;
	}
	
	//Remaining - queryType: 14
	public static LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> getTop5MostLikedAndExpensiveProductsSortedByRetailerNameForEveryCity()
	{

		LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> Top5ListOfLikedProductsForEveryCity = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>>();
		//LinkedHashMap<String, ArrayList<Object>> productsListHashMap = new LinkedHashMap<String, ArrayList<Object>>();
		//ArrayList<Object> values = new ArrayList<Object>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		
		AggregationOutput output1 =
            myReviews.aggregate(
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$retailerCity"))
            );

		String retailerCity="";
		
		for (DBObject doc : output1.results())
		{
			retailerCity = (String) doc.get("_id");
		
			AggregationOutput output =
            myReviews.aggregate(
					new BasicDBObject("$match", 
				    new BasicDBObject("retailerCity",
				    new BasicDBObject("$eq", retailerCity) ) ),
					
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$productName")
                                    .append("avgRating", new BasicDBObject("$avg", "$reviewRating")))
                    ,
                    new BasicDBObject("$sort", new BasicDBObject("avgRating", -1)),
					
					//new BasicDBObject("$sort", new BasicDBObject("productPrice", -1)),
					
					new BasicDBObject("$limit", 5)
            );
			
			String productPrice="";
			String city = retailerCity;
			String productName="";
			double avg = 0;
			
			LinkedHashMap<String, ArrayList<Object>> productsListHashMap = new LinkedHashMap<String, ArrayList<Object>>();
			
			for (DBObject doc2 : output.results())
			{
				productName = (String) doc2.get("_id");
				avg = (Double) doc2.get("avgRating");
				productPrice = (String) doc2.get("productPrice");
				
				ArrayList<Object> values = new ArrayList<Object>();
				values.add(retailerCity);
				values.add(productName);
				values.add(avg);
				
				
				productsListHashMap.put(productName, values);
				
				System.out.println("productPrice: " + productPrice);
				System.out.println("city: " + city);
				System.out.println("productName: " + productName);
				System.out.println("avg: " + avg);
				
			}
			
			Top5ListOfLikedProductsForEveryCity.put(retailerCity, productsListHashMap);
			System.out.println("productsListHashMap: " + productsListHashMap);
		}
		
		
		System.out.println("Top5ListOfLikedProductsForEveryCity: " + Top5ListOfLikedProductsForEveryCity);
		return Top5ListOfLikedProductsForEveryCity;
	}
	
	//queryType: 15
	public static LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> getMostDislikedProductForEveryCitySortedByRetailerName()
	{
		LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> MostDislikedProductForEveryCitySortedByRetailerName = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		AggregationOutput output1 =
            myReviews.aggregate(
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$retailerCity"))
            );

		String retailerCity="";
		
		for (DBObject doc : output1.results())
		{
			retailerCity = (String) doc.get("_id");
			LinkedHashMap<String, ArrayList<Object>> productsListHashMap = new LinkedHashMap<String, ArrayList<Object>>();
	
			AggregationOutput output =
            myReviews.aggregate(
					new BasicDBObject("$match", 
				    new BasicDBObject("retailerCity",
				    new BasicDBObject("$eq", retailerCity) ) ),
					
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$productName")
									.append("retailer", new BasicDBObject("$first", "$retailer"))
                                    .append("avgRating", new BasicDBObject("$avg", "$reviewRating"))),
                    
                    new BasicDBObject("$sort", new BasicDBObject("avgRating", 1)),
					
					new BasicDBObject("$limit", 5)
            );

			String retailer="";
			String productName="";
			double avg = 0;
			
			for (DBObject doc2 : output.results())
			{
				productName = (String) doc2.get("_id");
				avg = (Double) doc2.get("avgRating");
				retailer = (String) doc2.get("retailer");
				
				ArrayList<Object> values = new ArrayList<Object>();
				
				values.add(retailer);
				values.add(productName);
				values.add(avg);
				
				productsListHashMap.put(productName, values);
				
				System.out.println("retailer: " + retailer);
				System.out.println("productName: " + productName);
				System.out.println("avg: " + avg);
			}
			
			MostDislikedProductForEveryCitySortedByRetailerName.put(retailerCity, productsListHashMap);
			System.out.println("productsListHashMap: " + productsListHashMap);
		}
		
		System.out.println("MostDislikedProductForEveryCitySortedByRetailerName: " + MostDislikedProductForEveryCitySortedByRetailerName);
		return MostDislikedProductForEveryCitySortedByRetailerName;
	}
	
	//queryType: 16
	public static LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> getMostDislikedProductForEveryZipCodeSortedByRetailerName()
	{
		LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> MostDislikedProductForEveryZipSortedByRetailerName = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		AggregationOutput output1 =
            myReviews.aggregate(
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$retailerZip"))
            );

		String retailerZip="";
		
		for (DBObject doc : output1.results())
		{
			retailerZip = (String) doc.get("_id");
			LinkedHashMap<String, ArrayList<Object>> productsListHashMap = new LinkedHashMap<String, ArrayList<Object>>();
	
			AggregationOutput output =
            myReviews.aggregate(
					new BasicDBObject("$match", 
				    new BasicDBObject("retailerZip",
				    new BasicDBObject("$eq", retailerZip) ) ),
					
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$productName")
									.append("retailer", new BasicDBObject("$first", "$retailer"))
                                    .append("avgRating", new BasicDBObject("$avg", "$reviewRating"))),
                    
                    new BasicDBObject("$sort", new BasicDBObject("avgRating", 1)),
					
					new BasicDBObject("$limit", 5)
            );

			String retailer="";
			String productName="";
			double avg = 0;
			
			for (DBObject doc2 : output.results())
			{
				productName = (String) doc2.get("_id");
				avg = (Double) doc2.get("avgRating");
				retailer = (String) doc2.get("retailer");
				
				ArrayList<Object> values = new ArrayList<Object>();
				
				values.add(retailer);
				values.add(productName);
				values.add(avg);
				
				productsListHashMap.put(productName, values);
				
				//System.out.println("retailer: " + retailer);
				//System.out.println("productName: " + productName);
				//System.out.println("avg: " + avg);
			}
			
			MostDislikedProductForEveryZipSortedByRetailerName.put(retailerZip, productsListHashMap);
			//System.out.println("productsListHashMap: " + productsListHashMap);
		}
		
		System.out.println("MostDislikedProductForEveryZipSortedByRetailerName: " + MostDislikedProductForEveryZipSortedByRetailerName);
		return MostDislikedProductForEveryZipSortedByRetailerName;
	}
	
	//queryType: 18
	public static LinkedHashMap<String, LinkedHashMap<String, Review>> getReviewsWhereAgeGreaterThan50InEveryCity()
	{
		LinkedHashMap<String, LinkedHashMap<String, Review>> ReviewsWhereAgeGreaterThan50InEveryCity = new LinkedHashMap<String, LinkedHashMap<String, Review>>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		AggregationOutput output1 =
            myReviews.aggregate(
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$retailerCity"))
            );

		String retailerCity="";
		
		for (DBObject doc : output1.results())
		{
			retailerCity = (String) doc.get("_id");
			LinkedHashMap<String, Review> productsListHashMap = new LinkedHashMap<String, Review>();
			
			DBObject clause1 = new BasicDBObject("retailerCity", new BasicDBObject("$eq", retailerCity));
			DBObject clause2 = new BasicDBObject("userAge", new BasicDBObject("$gt", 50)); 
			BasicDBList and = new BasicDBList();
			and.add(clause1);
			and.add(clause2);
			DBObject query = new BasicDBObject("$and", and);
		
			DBCursor cursor = myReviews.find(query);
			cursor.sort(new BasicDBObject("userAge ", 1));
			
			while (cursor.hasNext())
			{
				BasicDBObject obj= (BasicDBObject) cursor.next();
				
				Review review = new Review(obj.getString("productName"), obj.getString("productType"), obj.getDouble("productPrice")
											,obj.getString("retailer"), obj.getString("retailerZip"),obj.getString("retailerCity")
											,obj.getString("retailerState"), obj.getString("productOnSale"), obj.getString("manufacturer")
											, obj.getString("manufacturerRebate"), obj.getString("emailId"), obj.getInt("userAge")
											, obj.getString("userGender"), obj.getString("userOccupation"), obj.getInt("reviewRating")
											,obj.getDate("reviewDate"), obj.getString("reviewText"));
											
				System.out.println("review of Product: " + obj.getString("productName"));
				
				productsListHashMap.put(obj.getString("productName")+obj.getString("_id"), review);
			}
			
			ReviewsWhereAgeGreaterThan50InEveryCity.put(retailerCity, productsListHashMap);
			System.out.println("productsListHashMap: " + productsListHashMap);
		}
		
		System.out.println("ReviewsWhereAgeGreaterThan50InEveryCity: " + ReviewsWhereAgeGreaterThan50InEveryCity);
		return ReviewsWhereAgeGreaterThan50InEveryCity;
	}
	
	//queryType: 19
	public static LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> getTop5ListOfLikedProductsForEveryCitySortedByManuName()
	{

		LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>> Top5ListOfLikedProductsForEveryCitySortedByManuName = new LinkedHashMap<String, LinkedHashMap<String, ArrayList<Object>>>();
		//LinkedHashMap<String, ArrayList<Object>> productsListHashMap = new LinkedHashMap<String, ArrayList<Object>>();
		//ArrayList<Object> values = new ArrayList<Object>();
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		DB db= mongo.getDB("CustomerReviews");
		myReviews= db.getCollection("myReviews");
		
		
		AggregationOutput output1 =
            myReviews.aggregate(
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$retailerCity"))
            );

		String retailerCity="";
		
		for (DBObject doc : output1.results())
		{
			retailerCity = (String) doc.get("_id");
		
			/*
			AggregationOutput output =
            myReviews.aggregate(
					new BasicDBObject("$match", 
				    new BasicDBObject("retailerCity",
				    new BasicDBObject("$eq", retailerCity) ) ),
					
                    new BasicDBObject("$group",
                            new BasicDBObject("_id", "$productName")
                                    .append("avgRating", new BasicDBObject("$avg", "$reviewRating")))
                    ,
                    new BasicDBObject("$sort", new BasicDBObject("avgRating", -1)),
					
					new BasicDBObject("$limit", 5)
            );
			*/
			
			//final BasicDBObject groupFields = new BasicDBObject("_id", "$chainId");
			Map<String, Object> dbObjIdMap = new HashMap<String, Object>();
			dbObjIdMap.put("productName", "$productName");
			dbObjIdMap.put("manufacturer", "$manufacturer");
			
			DBObject groupFields = new BasicDBObject( "_id", new BasicDBObject(dbObjIdMap));
			groupFields.put("avgRating", new BasicDBObject("$avg", "$reviewRating"));

			final DBObject group = new BasicDBObject("$group", groupFields);

			final DBObject sortFields = new BasicDBObject("avgRating", -1);
			final DBObject sort = new BasicDBObject("$sort", sortFields);
			
			final DBObject projectFields = new BasicDBObject("_id", 0);
			projectFields.put("_id", "$productName");
			projectFields.put("manufacturer", "$manufacturer");
			projectFields.put("avgRating", "$avgRating");
			final DBObject project = new BasicDBObject("$project", projectFields);

			final AggregationOutput output = myReviews.aggregate(
																new BasicDBObject("$match", 
																new BasicDBObject("retailerCity",
																new BasicDBObject("$eq", retailerCity) ) ),
																group, 
																sort, 
																project, 
																new BasicDBObject("$limit", 5)
																);
			
			String manufacturer = "";
			String city = retailerCity;
			String productName="";
			double avg = 0;
			
			LinkedHashMap<String, ArrayList<Object>> productsListHashMap = new LinkedHashMap<String, ArrayList<Object>>();
			
			for (DBObject doc2 : output.results())
			{
				productName = (String) doc2.get("_id");
				avg = (Double) doc2.get("avgRating");
				manufacturer = (String) doc2.get("manufacturer");
				
				ArrayList<Object> values = new ArrayList<Object>();
				values.add(retailerCity);
				values.add(productName);
				values.add(avg);
				values.add(manufacturer);
				
				
				productsListHashMap.put(productName, values);
				
				System.out.println("city: " + city);
				System.out.println("productName: " + productName);
				System.out.println("avg: " + avg);
				System.out.println("manufacturer: " + manufacturer);
				
			}
			
			Top5ListOfLikedProductsForEveryCitySortedByManuName.put(retailerCity, productsListHashMap);
			System.out.println("productsListHashMap: " + productsListHashMap);
		}
		
		
		System.out.println("Top5ListOfLikedProductsForEveryCitySortedByManuName: " + Top5ListOfLikedProductsForEveryCitySortedByManuName);
		return Top5ListOfLikedProductsForEveryCitySortedByManuName;
	}
	
	public static void main(String args[]){
		
		//MongoDBDataStoreUtilities.getTop5ZipCodes();
		//MongoDBDataStoreUtilities.getTop5LikedProducts();
		//MongoDBDataStoreUtilities.getReviewListWhereRatingGreaterThan3();
		//MongoDBDataStoreUtilities.getHighestPricedProductReviewedInEveryCity();
		//MongoDBDataStoreUtilities.getTop5ListOfLikedProductsForEveryCity();
		//MongoDBDataStoreUtilities.getListOfReviewsGroupedByCity();
		//MongoDBDataStoreUtilities.getListOfReviewsGroupedByRetailerName();
		//MongoDBDataStoreUtilities.getProductsReviewedAndGotRating5InEveryCity();
		//MongoDBDataStoreUtilities.getMostDislikedProductForEveryZipCodeSortedByRetailerName();
		MongoDBDataStoreUtilities.getTop5ListOfLikedProductsForEveryCitySortedByManuName();
	}

}