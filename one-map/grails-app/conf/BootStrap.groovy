import com.rosetta.onemap.User

class BootStrap {

    def init = { servletContext ->
		User dan = new User();
		dan.firstName = "Dan";
		dan.save(flush: true);
    }
    def destroy = {
    }
}
