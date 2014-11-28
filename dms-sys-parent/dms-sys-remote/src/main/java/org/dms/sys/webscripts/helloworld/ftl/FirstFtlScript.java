package org.dms.sys.webscripts.helloworld.ftl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Class FirstFtlScript.
 */
@Controller
public class FirstFtlScript {
	
	/** Static list of users to simulate Database. */
    private static List<User> userList = new ArrayList<User>();
 
    //Initialize the list with some data for index screen
    static {
        userList.add(new User("Bill", "Gates"));
        userList.add(new User("Steve", "Jobs"));
        userList.add(new User("Larry", "Page"));
        userList.add(new User("Sergey", "Brin"));
        userList.add(new User("Larry", "Ellison"));
    }
	
	/**
	 * Saves the static list of users in model and renders it
	 * via freemarker template.
	 *
	 * @param model the model
	 * @return The index view (FTL)
	 */
    @RequestMapping(value = "/service/hello-ftl", method = RequestMethod.GET)
    public String index(@ModelAttribute("model") ModelMap model) {
 
        model.addAttribute("userList", userList);
        return "hello-world/hello.get";
    }
}
