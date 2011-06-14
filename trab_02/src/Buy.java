public class Buy extends Transaction {

	public Buy(User user, Item item) {
		super(user, item);
	}

	public Buy(User user, Item item, int qtt) {
		super(user, item, qtt);
	}
	
	@Override
	public void execute(User user, Item item) {
		int oldQtt = item.getQtt();
		item.setQtt(oldQtt+this.qtt);
	}

}
