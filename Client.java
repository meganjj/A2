public interface Client {
	public String getId();

	public void accept(Visitor visitor);
}
