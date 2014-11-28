package org.dms.sys.webscripts.helloworld.directresponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Class FreeMarker.
 */
@Controller(value="helloWorld")
public class HelloWorld {

	/**
	 * Hello world.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping("/2helloWorld2")
    public String helloWorld(Model model) {
        model.addAttribute("userId", "Hello World!"); 
        return "helloWorld";
    }
}
