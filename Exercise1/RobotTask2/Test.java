package RobotTask;

public class Test {

	public static void main(String[] args) {
        RobotTask robot1 = new RobotTask("R2D2", 10);
        RobotTask robot2 = new RobotTask("C3PO4", 10);
        RobotTask robot3 = new RobotTask("RoboCop", 5);

        Thread thread1 = new Thread(robot1);
        Thread thread2 = new Thread(robot2);
        Thread thread3 = new Thread(robot3);
        
        thread1.start();
        thread2.start();
        thread3.start();
        
        System.out.println("All robots are working in all tasks...");     
        System.out.println("--------------------------------------");
        
    }
}
