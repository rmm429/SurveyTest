import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Response implements Serializable
{

    //Setting a Serializaiton ID to ensure objects can be serialzied/deserialized from any execution instance of the program
    private static final long serialVersionUID = 6529685098267757690L;

    private ArrayList<String> response = new ArrayList<String>();

    public Response() { }

    public Response(ArrayList<String> r)
    {
        response = r;
    }

    public ArrayList<String> GetResponse()
    {
        return response;
    }

    public void SetResponse(ArrayList<String> r)
    {
        response = r;
    }

    public void Display()
    {

        ConsoleManager.getInstance().Display(GetResponse());

    }

}
