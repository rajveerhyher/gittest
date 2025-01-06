package basictestApi;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		

	JsonPath js = new JsonPath(payload.ComplexResponse());
	
	//Q1: Print no of courses returned by API
	int CourseCount = js.getInt("courses.size()");
	System.out.println(CourseCount);
	
	//Q2:Print Purchase Amount
	
	int TotalAmount= js.getInt("dashboard.purchaseAmount");
	System.out.println(TotalAmount);
	
	//Q3:Print Title of the first course
	
	String TitlefstCourse = js.getString("courses[0].title");
	System.out.println(TitlefstCourse);
	
	//Q4:Print All course titles and their respective Prices
	
	for(int i=0;i< CourseCount;i++)
	{
		System.out.println(js.get("courses["+i+"].title").toString());
		System.out.println(js.get("courses["+i+"].price").toString());
	}
	
	//Q5:Print no of copies sold by RPA Course
	for(int i=0;i< CourseCount;i++)
	{
		String CourseTitle= js.get("courses["+i+"].title");
		if(CourseTitle.equalsIgnoreCase("RPA"))
		{
			System.out.println(js.get("courses["+i+"].copies").toString());
		}
	}
	
	
	}
	
	
	
	}


