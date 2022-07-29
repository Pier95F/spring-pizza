package jana60.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pizza")
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@NotEmpty(message = "Il campo non può essere vuoto")
	@Column(unique = true, nullable = false)
	private String name;
	@NotEmpty(message = "Il campo non può essere vuoto")
	private String description;
	@NotNull(message = "Il campo non può essere vuoto")
	@Min (value = 2)
	private Double price;
	
	@ManyToMany
	@JoinTable(name = "pizza_ingredients",
			   joinColumns = @JoinColumn(name = "pizza_id"),
			   inverseJoinColumns = @JoinColumn (name = "ingredients_id"))
	private List<Ingredienti> ingredients;
	
	
	public List<Ingredienti> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredienti> ingredients) {
		this.ingredients = ingredients;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
}
