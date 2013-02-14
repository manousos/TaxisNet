package gr.manousos.DAO;

import gr.manousos.model.User;

public interface UserDAO {
	public User getUserByUserName(String username);

	public Boolean Login(String username, String password);
}
