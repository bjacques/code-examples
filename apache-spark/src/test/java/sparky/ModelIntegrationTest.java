package sparky;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sparky.config.SpringConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class ModelIntegrationTest
{
	@Autowired
	NaughtyTradeModel model;
	
	@Test
	public void loadAppCtx()
	{
		LocalDate runDate = LocalDate.of(2015, Month.MAY, 29);
		
		model.execute(runDate);
		
//		assertThatAlertCreated();
	}

}
