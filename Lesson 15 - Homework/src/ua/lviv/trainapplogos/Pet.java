package ua.lviv.trainapplogos;

public class Pet {
	private Enum pettype; 
	private String name;
	
	public Pet(Enum pettype, String name) {
		super();
		this.pettype = pettype;
		this.name = name;
	}

	public Enum getPettype() {
		return pettype;
	}

	public void setPettype(Enum pettype) {
		this.pettype = pettype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pettype == null) ? 0 : pettype.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pettype == null) {
			if (other.pettype != null)
				return false;
		} else if (!pettype.equals(other.pettype))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Animal [pettype=" + pettype + ", name=" + name + "]";
	}
	
	
	
}
