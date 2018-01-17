package sidekicks;

import com.example.enums.ClientType;
import com.example.enums.CouponType;


public class EnumsConverter {

	public EnumsConverter() {
	}

	
	//!!!!----Client Type----!!!!
	//!!!!-------------------!!!!
	//!!!!-------------------!!!!
	//!!!!-------------------!!!!
	/***
	 * Method that take String and returns client type
	 * @param type
	 * @return
	 */
	public static ClientType stringToClient(String type)
	{
		switch(type)
		{
		case "ADMIN":
		case "Admin":
		case "admin":
			return ClientType.ADMIN;
		case "COMPANY":
		case "Company":
		case "company":
			return ClientType.COMPANY;
		case "CUSTOMER":
		case "Customer":
		case "customer":
			return ClientType.CUSTOMER;
			default:
				System.out.println("Client not known to the system");
				return null;
		}
	}
	
	/***
	 * Method that takes Client and return his name as a string
	 * @param type
	 * @return
	 */
	public static String clientToString(ClientType type)
	{
		switch(type)
		{
		case ADMIN:
			return ClientType.ADMIN.name();
		case COMPANY:
			return ClientType.COMPANY.name();
		case CUSTOMER:
			return ClientType.CUSTOMER.name();
			default:
				System.out.println("Client not known to the system");
				return null;
		}
	}
	
	//!!!!----Coupon Type----!!!!
	//!!!!-------------------!!!!
	//!!!!-------------------!!!!
	//!!!!-------------------!!!!
	
	/***
	 * Method that takes String and return Coupon type
	 * @param type
	 * @return
	 */
	public static CouponType stringToCoupon(String type)
	{
		switch(type)
		{
		case "CAMPING":
		case "Camping":
		case "camping":
			return CouponType.CAMPING;
		case "ELECTRICITY":
		case "Electricty":
		case "electricity":
			return CouponType.ELECTRICITY;
		case "FOOD":
		case "Food":
		case "food":
			return CouponType.FOOD;
		case "HEALTH":
		case "Health":
		case "health":
			return CouponType.HEALTH;
		case "RESTURANS":
		case "Resturans":
		case "resturans":
			return CouponType.RESTURANTS;
		case "SPORTS":
		case "Sports":
		case "sports":
			return CouponType.SPORTS;
		case "TRAVELLING":
		case "Travelling":
		case "travelling":
			return CouponType.TRAVELLING;
			default:
				System.out.println("Coupon Type not known to the system");
				return null;
		}
	}
	
	/***
	 * Method that takes Coupon Type and return his name as a String
	 * @param type
	 * @return
	 */
	public static String couponToString(CouponType type)
	{
		switch(type)
		{
		case CAMPING:
			return CouponType.CAMPING.name();
		case ELECTRICITY:
			return CouponType.ELECTRICITY.name();
		case FOOD:
			return CouponType.FOOD.name();
		case HEALTH:
			return CouponType.HEALTH.name();
		case RESTURANTS:
			return CouponType.RESTURANTS.name();
		case SPORTS:
			return CouponType.SPORTS.name();
		case TRAVELLING:
			return CouponType.TRAVELLING.name();
			default:
				System.out.println("Coupon Type not known to the system");
				return null;
		}
	}
	
	
}
