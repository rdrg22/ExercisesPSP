package PetFeeder;

public class PetFeeder extends Thread {

	private String petName;
	private int eatingTime;
	
	
	public	PetFeeder(String petName, int eatingTime) {
		this.petName = petName;
		this.eatingTime = eatingTime;
	}
	
	public void run() {
		System.out.println(petName + " started eating");
		try {
			Thread.sleep(eatingTime * 1000L);
		} catch (InterruptedException e) {
			System.out.println(petName + " was interrupted while eating.");
		}
		System.out.println(petName + " finished eating");
	}
}