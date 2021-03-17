package ma.aui.sse.paradigms.integration.xs.calculator.provider;

public class OperatingSystem{

    private String name;
    private String version;
    private boolean status; //status is set to be equal to true if both the name and version are different from null, i.e both the name and version could be fetched and extracted

    public OperatingSystem(){}

    public OperatingSystem(String name, String version){
        this.name = name;
        this.version = version;
        if(!name.equals(null) && !version.equals(null)){
            status = true;
        }
    }

    public String getName(){
        return this.name;
    }

    public String getVersion(){
        return this.version;
    }

    public boolean getStatus(){
        return this.status;
    }

    public void setName(String name){
        this.name = name;
        if(!this.name.equals(null) && !this.version.equals(null)){
            status = true;
        }
        else status = false;
    }

    public void setVersion(String version){
        this.version = version;
        if(!this.name.equals(null) && !this.version.equals(null)){
            status = true;
        }
        else status = false;
    }

    public void setStatus(boolean status){
        this.status = status;
    }
}