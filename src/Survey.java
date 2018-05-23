import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Survey implements Serializable
{

    //Setting a Serializaiton ID to ensure objects can be serialzied/deserialized from any execution instance of the program
    private static final long serialVersionUID = 6529685098267757690L;

    protected String name;
    protected Menu menu = new MenuSurveyTestMain();
    protected ArrayList<Question> questions = new ArrayList<Question>();

    public Survey()
    {

    }

    public void SetName(String n)
    {
        name = n;
    }

    public String GetName()
    {

        return name;

    }

    public void SetQuestions(ArrayList<Question> q)
    {
        questions = q;
    }

    public ArrayList<Question> GetQuestions()
    {
        return questions;
    }

    public void Create()
    {
        if (GetMenu().GetType().equals("Survey"))
        {
            ConsoleManager.getInstance().Display("Enter the name for your Survey:");
        }
        else if (GetMenu().GetType().equals("Test"))
        {
            ConsoleManager.getInstance().Display("Enter the name for your Test:");
        }

        SetName(ConsoleManager.getInstance().Read());
        SetMenu(new MenuCreate());
    }

    public void Display()
    {
        SetMenu(new MenuDisplay());
    }

    public void DisplayQuestionType(String ClassName)
    {
        switch(ClassName)
        {
            case "Essay":
                ConsoleManager.getInstance().Display("Essay");
                break;
            case "Matching":
                ConsoleManager.getInstance().Display("Matching");
                break;
            case "MultipleChoice":
                ConsoleManager.getInstance().Display("Multiple Choice");
                break;
            case "Ranking":
                ConsoleManager.getInstance().Display("Ranking");
                break;
            case "ShortAnswer":
                ConsoleManager.getInstance().Display("Short Answer");
                break;
            case "TrueFalse":
                ConsoleManager.getInstance().Display("True/False");
                break;

        }
    }

    public void DisplaySurvey()
    {
        ConsoleManager.getInstance().Display("Survey name:");
        ConsoleManager.getInstance().Display(GetName());
        ConsoleManager.getInstance().Display("");

        int QuestionsNum = 0;

        for (Question q : GetQuestions())
        {
            QuestionsNum++;
            ConsoleManager.getInstance().Display("Question #" + QuestionsNum);
            DisplayQuestionType(q.getClass().getName());
            ConsoleManager.getInstance().Display("");
            q.Display();
        }
    }

    public static void SaveSurvey(Survey survey, String SurveyTestType)
    {

        String FileName = survey.GetName();

        if (SurveyTestType.equals("Survey"))
        {
            FileName += ".survey";
        }
        else if (SurveyTestType.equals("Test"))
        {
            FileName += ".test";
        }

        FileManager.getInstance().OutputToFile(FileName, survey, SurveyTestType);

    }

    public static void LoadSurvey(String ChosenFile, HashMap<String, Survey> all, String SurveyTestType)
    {

        Survey LoadedSurvey = FileManager.getInstance().GetFileContent(ChosenFile);

        Survey copy = (Survey) Survey.Copy(LoadedSurvey);

        all.put(copy.GetName(), copy);

    }


    public void Save()
    {

        SetMenu(new MenuSave());

    }

    public void Load()
    {

        SetMenu(new MenuLoad());

    }

    public void AddQuestion(Question q)
    {
        questions.add(q);
    }


    public Menu GetMenu()
    {
        return menu;
    }

    public void SetMenu(Menu m)
    {
        menu = m;
    }

    public void Clear()
    {

        name = null;
        questions.clear();
    }

    public static Object Copy (Object orig)
    {
        Object obj = null;

        try
        {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(orig);
            out.flush();
            out.close();

            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            obj = in.readObject();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }

        return obj;
    }

}
