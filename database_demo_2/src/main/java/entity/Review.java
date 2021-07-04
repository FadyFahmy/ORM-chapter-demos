package entity;

public class Review {
	
	private int rating;
	private String description;
	
	public Review(int rating, String description) {
		this.rating = rating;
		this.description = description;
	}
	
	
	@Override
	public String toString() {
		return "Review [rating=" + rating + ", description=" + description + "]";
	}
	
	
}
