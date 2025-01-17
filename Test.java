package RobotTask;

public class Test {

	public static void main(String[] args) {
        RobotTask robot1 = new RobotTask("R2D2", 10);
        RobotTask robot2 = new RobotTask("C3PO4", 10);
        RobotTask robot3 = new RobotTask("RoboCop", 5);

        robot1.start();
        robot2.start();
        robot3.start();
        
        System.out.println("All robots are working in all tasks...");     
        System.out.println("");
        
        
    }
	
}
