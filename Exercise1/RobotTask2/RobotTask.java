package RobotTask;

	public class RobotTask implements Runnable {

		private String robotName;
		private int taskTime;
			
		 public RobotTask(String robotName, int taskTime) {
		        this.robotName = robotName;
		        this.taskTime = taskTime;
		    }

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
}