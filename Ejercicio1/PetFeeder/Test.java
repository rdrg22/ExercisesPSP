package PetFeeder;

public class Test {

	public class MultiThreadPetFeeding{
		public static void main(String []args) {
			PetFeeder pet1 = new PetFeeder("Buddy the dog", 3);
			PetFeeder pet2 = new PetFeeder("Whiskers the cat", 2);
			PetFeeder pet3 = new PetFeeder("Nibbles the Rabbit", 1);
			
			pet1.start();
			pet2.start();
			pet3.start();
			
			System.out.println("All pets are eating...");
		}
	}
	
}
