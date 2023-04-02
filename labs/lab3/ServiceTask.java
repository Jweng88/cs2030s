/**
 * This class temporarily represents a task 
 * (will be made abstract / interface in the future)
 *
 * @author Fikri (Lab 12A)
 * @version CS2030S AY21/22 Semester 2
 */ 
class ServiceTask {
  private final String serviceIdentifier;

  public ServiceTask(int taskId) {
    switch (taskId) {
      case 0:
        this.serviceIdentifier = "Deposit";
        break;
      case 1:
        this.serviceIdentifier = "Withdrawal";
        break;
      case 2:
        this.serviceIdentifier = "OpenAccount";
        break;
      default:
        this.serviceIdentifier = "Unknown";
    }
  }

  @Override
  public String toString() {
    return this.serviceIdentifier;
  }
}
