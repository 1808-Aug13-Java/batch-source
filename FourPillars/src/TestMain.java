import com.revature.pillars.animals.Animal;
import com.revature.pillars.animals.Dog;
import com.revature.resources.MathHelper;

public class TestMain {
	
	public static void main(String[] args) {
		System.out.println("Rads: " + MathHelper.degreesToRadians(180d));
		
		//Creates an approximate 3, 4, 5 triangle
		Animal dog = new Dog(5, 0, 0, 53.13);
		
		dog.move();
		
		System.out.println("X:" + dog.getX() + "  Y:" + dog.getY());
	}
	
} // end of class TestMain
