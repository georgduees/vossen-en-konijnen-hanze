
public class main {
	public static void main(String[] args) 
	{
		// Get default locale
		Locale locale = Locale.getDefault();

		// Set the default locale to pre-defined locale
		Locale.setDefault(Locale.ENGLISH);

		// Set the default locale to custom locale
		locale = new Locale("en");
		Locale.setDefault(locale);
		averageButton();
	}
}
