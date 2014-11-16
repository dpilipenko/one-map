package com.rosetta.onemap


class User extends org.springframework.security.core.userdetails.User {

	@Override
	public String toString() {
		return "User [username=" + username + ", emailAddress=" + emailAddress
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", office=" + office + ", phone=" + phone + ", level="
				+ level + ", craft=" + craft + ", enabled=" + enabled
				+ ", accountExpired=" + accountExpired + ", accountLocked="
				+ accountLocked + ", passwordExpired=" + passwordExpired
				+ ", dateCreated=" + dateCreated + ", lastUpdated="
				+ lastUpdated + "]";
	}

	String username
	String emailAddress
	String firstName
	String lastName
	Office office
	String phone
	String level // TODO change 'level' to 'title' because level is a SQL reserved keyword
	String craft
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	Date dateCreated
	Date lastUpdated

	static constraints = {
		username blank:false
		emailAddress blank:false, email:true
		firstName blank:false
		lastName blank:false
	}

	static mapping = {
	}
	
	public User() { 
		super( "guest", "", new HashSet());
		this.username = "guest";
	}
	
	public User(String username) {
		super(username, "", new HashSet());
		this.username = username;
	}
	
	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (accountExpired ? 1231 : 1237);
		result = prime * result + (accountLocked ? 1231 : 1237);
		result = prime * result + ((craft == null) ? 0 : craft.hashCode());
		result = prime * result
				+ ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result
				+ ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((office == null) ? 0 : office.hashCode());
		result = prime * result + (passwordExpired ? 1231 : 1237);
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (accountExpired != other.accountExpired) {
			return false;
		}
		if (accountLocked != other.accountLocked) {
			return false;
		}
		if (craft == null) {
			if (other.craft != null) {
				return false;
			}
		} else if (!craft.equals(other.craft)) {
			return false;
		}
		if (dateCreated == null) {
			if (other.dateCreated != null) {
				return false;
			}
		} else if (!dateCreated.equals(other.dateCreated)) {
			return false;
		}
		if (emailAddress == null) {
			if (other.emailAddress != null) {
				return false;
			}
		} else if (!emailAddress.equals(other.emailAddress)) {
			return false;
		}
		if (enabled != other.enabled) {
			return false;
		}
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		if (lastUpdated == null) {
			if (other.lastUpdated != null) {
				return false;
			}
		} else if (!lastUpdated.equals(other.lastUpdated)) {
			return false;
		}
		if (level == null) {
			if (other.level != null) {
				return false;
			}
		} else if (!level.equals(other.level)) {
			return false;
		}
		if (office == null) {
			if (other.office != null) {
				return false;
			}
		} else if (!office.equals(other.office)) {
			return false;
		}
		if (passwordExpired != other.passwordExpired) {
			return false;
		}
		if (phone == null) {
			if (other.phone != null) {
				return false;
			}
		} else if (!phone.equals(other.phone)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}
}
