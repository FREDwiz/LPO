package accounts;

public interface Client {

	String getFirstName();

	String getSecondName();

	long getSocialSecurity();

	Person getSpouse();

	boolean isSingle();

}