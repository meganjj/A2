import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PositiveTweetsVisitor implements Visitor {

	private double positiveCount = 0;
    	private double totalMessages = 0;
    	private double positivePercentage = 0;
    	private List<String> positiveWords= new ArrayList<>
        (Arrays.asList("good", "awesome", "nice", "happy"));

	@Override
	public void visitUser(User user) {
		
	}

	@Override
	public void visitGroup(UserGroup group) {
	}

	public double getPositivePercentage() {
		if (totalMessages == 0) {
		    return positivePercentage;
		}
		setPositivePercentage((positiveCount/totalMessages)*100.0);
		return (positivePercentage);
	    }
	
	public void setPositivePercentage(double positivePercentage) {
		this.positivePercentage = positivePercentage;
	}
	
}
