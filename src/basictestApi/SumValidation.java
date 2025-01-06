package basictestApi;

import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
@Test

public void sumofcourses()
{
	//Q6: Verify if Sum of all Course prices matches with Purchase Amount
	JsonPath js = new JsonPath(payload.ComplexResponse());  
	int CourseCount = js.getInt("courses.size()");
	int Sum=0;
		for(int i=0;i< CourseCount;i++)
		{
			int Price= js.get("courses["+i+"].price");
			int qty= js.get("courses["+i+"].copies");
			int TotalAmt= Price* qty;
			System.out.println(TotalAmt);
			Sum=Sum+TotalAmt;
			
		}
		System.out.println(Sum);
		
}
}
