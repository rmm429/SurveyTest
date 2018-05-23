import java.io.Serializable;

public class Prompt implements Serializable
{

    private String prompt;

    public Prompt() { }

    public Prompt(String p)
    {

        prompt = p;

    }

    public String GetPrompt()
    {
        return prompt;
    }

    public void SetPrompt(String p)
    {
        prompt = p;
    }

    public void Display()
    {

        ConsoleManager.getInstance().Display(GetPrompt());

    }

}
