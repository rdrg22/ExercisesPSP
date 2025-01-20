package RobotTask99;

public class RobotTask implements Runnable {

	private String robotName;
	private int taskTime;
	
	public RobotTask(String robotName, int taskTime) {
		this.robotName = robotName;
        this.taskTime = taskTime;
    }

 	//Run method, defines the action that will be executed in the thread
	public void run() {
        System.out.println(robotName + " started task");
        try {
            Thread.sleep(taskTime * 1000L);
        } catch (InterruptedException e) {
            System.out.println(robotName + " was interrupted while performing the task.");
        }
        System.out.println(robotName + " go to sleep");
    }
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 99; i++) {
			//Creation of RobotTask instances with different names and times
			RobotTask robot1 = new RobotTask("C3PO", 2);

			//Creation of the threads
			Thread thread1 = new Thread(robot1);
	        
			//Start the execution of the threads
			thread1.start();
			
		}
		System.out.println("All robots are working in all tasks...");     
		System.out.println("--------------------------------------");      
    }	
}
