package by.epam.web.command;

public class CommandResponse {
    private String target;
    private boolean redirect;
    private boolean invalidateSession;

    private CommandResponse(){}

    private CommandResponse(String target){
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public boolean isInvalidate() {
        return invalidateSession;
    }

    public class Builder {
        private Builder(){}

        public Builder setTarget(String target){
            CommandResponse.this.target = target;
            return this;
        }

        public Builder setRedirect(boolean redirect){
            CommandResponse.this.redirect = redirect;
            return this;
        }

        public Builder setInvalidate(boolean invalidate){
            CommandResponse.this.invalidateSession = invalidate;
            return this;
        }

        public CommandResponse build(){
            return CommandResponse.this;
        }
    }

    public static Builder newBuilder(){
        return new CommandResponse().new Builder();
    }

    public static CommandResponse forwardUnknown(){
        return new CommandResponse("unknown");
    }

    public static CommandResponse forwardIndex(){
        return new CommandResponse("index");
    }
}
