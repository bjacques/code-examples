package crap;

public class Crap {
	public static void main(String[] args) {
		Resource resource = new ClassPathResource("/application/context/references/user/user.xml");
		File file = resource.getFile();
	}
}
