package ma.aui.sse.paradigms.integration.xs.calculator.provider;

public class Screenshot{

    private byte[] bytes;
    private int size;
    private boolean status; //status is set to be equal to true if bytes is equal to null

    public Screenshot(){
        bytes = null;
        status = false;
    }

    public Screenshot(byte[] bytes, int size){
        this.bytes = bytes;
        this.size = size;
        if(this.bytes == null)
            status = false;
        else status = true;    
    }

    public byte[] getBytes(){
        return this.bytes;
    }

    public int getSize(){
        return this.size;
    }

    public boolean getStatus(){
        return this.status;
    }

    public void setBytes(byte[] bytes){
        this.bytes = bytes;
        if(this.bytes == null)
            status = false;
        else status = true;  
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public void setSize(int size){
        this.size = size;
    }
}