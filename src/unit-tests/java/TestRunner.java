import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by Domn Werner on 4/23/2016.
 *
 * This is the main class that runs our suite of tests and reports the results.
 */
public class TestRunner
{
    public static void main(String[] args)
    {
        Result result = JUnitCore.runClasses(TestSuite.class);
        for (Failure failure : result.getFailures())
        {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
