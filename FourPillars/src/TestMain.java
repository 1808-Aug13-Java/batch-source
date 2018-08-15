import com.revature.pillars.animals.Animal;
import com.revature.pillars.animals.Dog;
import com.revature.resources.MathHelper;

public class TestMain {
	
	public static void main(String[] args) {
		System.out.println("Rads: " + MathHelper.degreesToRadians(180d));
		
		Animal dog = new Dog(5, 0, 0, 30);
		
		dog.move();
		
		System.out.println("X:" + dog.getX() + "  Y:" + dog.getY());
	}
	
} // end of class TestMain
