/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.actions;

import com.dao.UserDAOImpl;
import com.entity.User;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Dell
 */
@Namespace("/")
public class UserAction {
    private String login;
    private String password;
	private List<User> userList = new ArrayList<User>();
	private UserDAOImpl userDAO = new UserDAOImpl();
	/**
	 * To save or update user.
	 * @return String
	 */
        @Action(value = "/saveOrUpdateUser",
                results = {
                    @Result(name = "success", type = "redirectAction", location = "listUserBis")
                }
        )
	public String saveOrUpdate()
	{	User user= new User(login, password);
		userDAO.saveOrUpdateUser(user);
		return SUCCESS;	}	
	/**
	 * To list all users.
	 * @return String
	 */
        
        @Action(value = "/listUserBis",
                results = {
                    @Result(name = "success", location = "accueil.jsp")
                }
        )
	public String list()
	{	userList = userDAO.listUser();
		return SUCCESS;}
	/**
	 * To delete a user.
	 * @return String
	 */
	
        @Action(value = "/deleteUser",
                results = {
                    @Result(name = "success", type = "redirectAction", location = "listUserBis")
                }
        )
        public String delete(){
HttpServletRequest request ;
  request= (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		userDAO.deleteUser(request.getParameter("login"));
		return SUCCESS;	}	
	/**
	 * To list a single user by Id.
	 * @return String
	 */
        
        @Action(value = "/editUser",
                results = {
                    @Result(name = "success", location = "update.jsp")
                }
        )
	public String edit()
	{
HttpServletRequest request;
request= (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	User	user = userDAO.listUserById(request.getParameter("login"));
	if(user!=null)
            return SUCCESS;
        return ERROR ;
	}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDAOImpl getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }
	
public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
    
}
