package customException;

public class CustomException {
	public static void main(String a[]) {
		try {
			ValidateAge(9);
		} catch (Exception e) {
			System.out.println("Exception fires...\n" + e);
		}
	}

	static void ValidateAge(int Age) throws InvalidAgeException { // throws-Defines the exception.
		if (Age < 18) {
			throw new InvalidAgeException("You are not valid to vote.");
		} else {
			System.out.println("Go and vote now.");
		}
	}
}