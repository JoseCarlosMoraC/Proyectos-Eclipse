package Repaso.Controllers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProbandoLog {
	private static final Logger logger =                LogManager.getLogger(ProbandoLog.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logger.debug("Me quiero morir");
		logger.debug("Me queda poco");
		logger.debug("Morí");
	}

}
