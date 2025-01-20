package RobotTaskExtends;

import RobotTask.RobotTask;

//Class that implements the Runnable interface 
public class RobotTaskExtends extends Thread {

	private String robotName;
	private int taskTime;
		
	 public RobotTaskExtends(String robotName, int taskTime) {
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
	        System.out.println("------------------------");
	        System.out.println(robotName + " go to sleep");
	    }
		
		public static void main(String[] args) {
			//Creation of RobotTask instances with different names and times
			RobotTask robot1 = new RobotTask("R2D2", 10);
			RobotTask robot2 = new RobotTask("C3PO4", 20);
			RobotTask robot3 = new RobotTask("RoboCop", 5);

			//Creation of the threads
			Thread thread1 = new Thread(robot1);
			Thread thread2 = new Thread(robot2);
			Thread thread3 = new Thread(robot3);
	        
			//Start the execution of the threads
			thread1.start();
			thread2.start();
			thread3.start();
	        
			System.out.println("All robots are working in all tasks...");     
			System.out.println("--------------------------------------");      
	    }
}
