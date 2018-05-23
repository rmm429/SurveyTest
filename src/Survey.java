import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Survey implements Serializable
{

    //Setting a Serializaiton ID to ensure objects can be serialzied/deserialized from any execution instance of the program
    private static final long serialVersionUID = 6529685098267757690L;

    protected String name;
    protected Menu menu = new MenuSurveyTestMain();
    protected ArrayList<Question> questions = new ArrayList<Question>();
    protected String QuestionType;

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

        String CurName = "";

        while (CurName.equals(""))
        {
            if (GetMenu().GetType().equals("Survey"))
            {
                ConsoleManager.getInstance().Display("Enter the name for your Survey:");
            }
            else if (GetMenu().GetType().equals("Test"))
            {
                ConsoleManager.getInstance().Display("Enter the name for your Test:");
            }

            CurName = ConsoleManager.getInstance().Read();

            if (CurName.equals(""))
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }
        }

        SetName(CurName);
        SetMenu(new MenuCreate());
    }

    public void Display()
    {
        SetMenu(new MenuDisplay());
    }

    public void SetQuestionType(String ClassName)
    {

        String qt = "";

        switch(ClassName)
        {
            case "Essay":
                qt = "Essay";
                break;
            case "Matching":
                qt = "Matching";
                break;
            case "MultipleChoice":
                qt = "Multiple Choice";
                break;
            case "Ranking":
                qt = "Ranking";
                break;
            case "ShortAnswer":
                qt = "Short Answer";
                break;
            case "TrueFalse":
                qt = "True/False";
                break;
        }

        QuestionType = qt;

    }

    public String GetQuestionType()
    {
        return QuestionType;
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
            SetQuestionType(q.getClass().getName());
            ConsoleManager.getInstance().Display(GetQuestionType());
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

    public void Modify()
    {
        SetMenu(new MenuModify());
    }

    public void ModifySurvey()
    {

        Question SelectedQuestion = null;
        int QuestionNum = 0;

        while (SelectedQuestion == null)
        {
            try
            {
                if (GetQuestions().size() == 1)
                {
                    QuestionNum = 0;
                }
                else
                {
                    ConsoleManager.getInstance().Display("What question do you wish to modify (1-" + GetQuestions().size() + "):");
                    ConsoleManager.getInstance().Display("");
                    DisplayQuestions();
                    QuestionNum = Integer.parseInt(ConsoleManager.getInstance().Read()) - 1;
                }

                SelectedQuestion = GetQuestions().get(QuestionNum);
            }
            catch (NumberFormatException nfe)
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }
            catch (IndexOutOfBoundsException ioobe)
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }
        }

        EditQuestion(SelectedQuestion);


    }

    public void DisplayQuestions() {

        int i = 1;

        for (Question q : GetQuestions())
        {
            SetQuestionType(q.getClass().getName());
            ConsoleManager.getInstance().Display("Question #" + i + " (" + GetQuestionType() + ")");
            i++;
        }

        ConsoleManager.getInstance().Display("");

    }

    public void EditQuestion(Question q)
    {

        EditPrompt(q);

        if (!GetQuestionType().equals("True/False"))
        {
            EditChoices(q);
        }

    }

    public void EditPrompt(Question q)
    {
        ConsoleManager.getInstance().Display("Prompt: " + q.GetPrompt().GetPrompt());
        ConsoleManager.getInstance().Display("");

        boolean isYN = false;
        char YN = 0;

        while (isYN == false)
        {
            ConsoleManager.getInstance().Display("Do you wish to modify the prompt (Y/N):");
            String YNString = ConsoleManager.getInstance().Read();
            YNString = YNString.toUpperCase();
            YN = YNString.charAt(0);

            if (YN != 'Y' && YN != 'N')
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }
            else
            {
                isYN = true;
            }
        }

        if (YN == 'Y')
        {

            String NewPromptString = "";

            while (NewPromptString.equals(""))
            {
                ConsoleManager.getInstance().Display("Enter a new prompt:");
                NewPromptString = ConsoleManager.getInstance().Read();

                if (NewPromptString.equals(""))
                {
                    ConsoleManager.getInstance().Display("Invalid choice");
                    ConsoleManager.getInstance().Display("");
                }

            }

            Prompt NewPrompt = new Prompt(NewPromptString);

            q.SetPrompt(NewPrompt);

        }
    }

    public void EditChoices(Question q)
    {
        ConsoleManager.getInstance().Display("Choices:");
        q.DisplayChoices();
        ConsoleManager.getInstance().Display("");

        boolean isYN = false;
        char YN = 0;

        while (isYN == false)
        {
            ConsoleManager.getInstance().Display("Do you wish to modify choices (Y/N):");
            String YNString = ConsoleManager.getInstance().Read();
            YNString = YNString.toUpperCase();
            YN = YNString.charAt(0);

            if (YN != 'Y' && YN != 'N')
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }
            else
            {
                isYN = true;
            }
        }

        if (YN == 'Y')
        {
            q.EditChoice();
            //********************DO EDIT CHOICES FOR OTHER QUESTIONS***************************
        }

    }

    public void Take()
    {
        SetMenu(new MenuTake());
    }

    public void Tabulate()
    {
        SetMenu(new MenuTabulate());
    }

    public void Grade()
    {
        SetMenu(new MenuGrade());
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
