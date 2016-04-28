package squire.Networking;

/**
 * The request handler dealing with all things Users
 */
class UserRequestHandler implements RequestHandler {
    @Override
    public String[] routes() {
        return new String[]{"test"};
    }

    @Override
    public void handle(String route, Request req, Response res) {
        switch (route) {
            case "test":
                res.set("test", "test123");
                break;
        }
    }
}

