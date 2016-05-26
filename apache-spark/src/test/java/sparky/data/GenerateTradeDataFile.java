package sparky.data;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GenerateTradeDataFile {

	public static void main(String[] args) throws Exception
	{
		Path path = Paths.get("G:/git/code-examples/spark/src/test/resources/trades-generated.txt");
		
		Files.write(path, generateCsvTradeData());
		
		System.out.println("Done");
	}

	
	private static List<String> generateCsvTradeData()
	{
		List<String> lines = new ArrayList<>();
		lines.add("abc");
		return lines;
	}
}
