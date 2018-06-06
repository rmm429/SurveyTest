import java.io.*;
import java.util.*;

public class Survey implements Serializable
{

    //Setting a Serializaiton ID to ensure objects can be serialzied/deserialized from any execution instance of the program
    private static final long serialVersionUID = 6529685098267757690L;

    protected String name;
    protected Menu menu = new MenuSurveyTestMain();
    protected ArrayList<Question> questions = new ArrayList<Question>();
    protected ArrayList<Response> responses = new ArrayList<Response>();
    protected HashMap<Integer, ArrayList<Response>> AllResponses = new HashMap<Integer, ArrayList<Response>>();
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

    public void ModifySurvey(String SurveyTestType)
    {

        SetMenu(new MenuModify());

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

        EditQuestion(SelectedQuestion, SurveyTestType);


    }

    public void DisplayQuestions()
    {

        int i = 1;

        for (Question q : GetQuestions())
        {
            SetQuestionType(q.getClass().getName());
            ConsoleManager.getInstance().Display("Question #" + i + " (" + GetQuestionType() + ")");
            i++;
        }

        ConsoleManager.getInstance().Display("");

    }

    public void EditQuestion(Question q, String SurveyTestType)
    {
        SetQuestionType(q.getClass().getName());

        EditPrompt(q);

        if (!GetQuestionType().equals("True/False") && !GetQuestionType().equals("Essay") && !GetQuestionType().equals("Short Answer"))
        {
            EditChoices(q);
        }

        if (SurveyTestType.equals("Test"))
        {
            EditCorrectChoices(q);
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
        }

    }

    public void EditCorrectChoices(Question q)
    {
        q.DisplayCorrectChoices();
        ConsoleManager.getInstance().Display("");

        boolean isYN = false;
        char YN = 0;

        while (isYN == false)
        {
            ConsoleManager.getInstance().Display("Do you wish to modify correct choice(s) (Y/N):");
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
            q.EditCorrectChoices();
        }
    }

    public void Take()
    {
        SetMenu(new MenuTake());
    }

    public void TakeSurvey(String SurveyTestType)
    {

        responses.clear();

        SetMenu(new MenuTake());

        ArrayList<String> CurUserResponseLst = new ArrayList<String>();
        Response CurUserResponse = null;

        int QuestionNum = 1;

        for (Question q : GetQuestions())
        {
            SetQuestionType(q.getClass().getName());

            ConsoleManager.getInstance().Display("Question #" + QuestionNum + " (" + GetQuestionType() + ")");
            ConsoleManager.getInstance().Display("");

            CurUserResponseLst = q.Take();
            CurUserResponse = new Response(CurUserResponseLst);
            AddResponse(CurUserResponse);

            QuestionNum++;
        }

        ArrayList<Response> AllCurResponses = null;

        int ResponseNum = 1;

        for (Response r : responses)
        {

            if (AllResponses.get(ResponseNum) == null)
            {
                AllCurResponses = new ArrayList<Response>();
            }
            else
            {
                AllCurResponses = AllResponses.get(ResponseNum);
            }

            AllCurResponses.add(r);
            AllResponses.put(ResponseNum, AllCurResponses);

            ResponseNum++;

        }

        DisplayResponses(SurveyTestType);
    }

    public void AddResponse(Response r)
    {
        responses.add(r);
    }

    public void DisplayResponses(String SurveyTestType)
    {
        for (int i = 0; i < GetQuestions().size(); i++)
        {
            Question CurQuestion = questions.get(i);
            SetQuestionType(CurQuestion.getClass().getName());
            String CurQuestionType = GetQuestionType();

            Response CurResponse = responses.get(i);

            ConsoleManager.getInstance().Display("Response for Question #" + (i+1) + " (" + CurQuestionType + "):");

            DisplayQuestionResponse(CurResponse, CurQuestionType);

            ConsoleManager.getInstance().Display("");

            if (SurveyTestType.equals("Test"))
            {
                CurQuestion.DisplayCorrectChoices();
            }
        }
    }

    public void DisplayQuestionResponse(Response CurResponse, String CurQuestionType)
    {
        switch (CurQuestionType)
        {
            case "True/False":
            case "Essay":
                ConsoleManager.getInstance().Display(CurResponse.GetResponse().get(0));
                break;
            case "Multiple Choice":
            case "Short Answer":
            case "Ranking":
            case "Matching":
                ConsoleManager.getInstance().Display(CurResponse.GetResponse());
                break;
        }
    }

    public void Tabulate()
    {
        SetMenu(new MenuTabulate());
    }

    public void TabulateSurvey(String SurveyTestType)
    {
        Collection<ArrayList<Response>> AllResponsesValues = AllResponses.values();
        Object[] AllResponseValuesArray = AllResponsesValues.toArray();

        if (AllResponsesValues.size() == 0 && SurveyTestType.equals("Survey"))
        {
            ConsoleManager.getInstance().Display("The survey has not been taken yet.");
            ConsoleManager.getInstance().Display("");
        }
        else if (AllResponsesValues.size() == 0 && SurveyTestType.equals("Test"))
        {
            ConsoleManager.getInstance().Display("The test has not been taken yet.");
            ConsoleManager.getInstance().Display("");
        }
        else
        {

            for (int i = 0; i < questions.size(); i++)
            {

                Question CurQuestion = questions.get(i);
                SetQuestionType(CurQuestion.getClass().getName());
                String QuestionType = GetQuestionType();

                ConsoleManager.getInstance().Display("Question #" + (i + 1) + " (" + QuestionType + "):");
                ConsoleManager.getInstance().Display("");

                ArrayList<Response> CurResponses = (ArrayList<Response>)AllResponseValuesArray[i];

                for (Response r : CurResponses)
                {
                    switch (QuestionType)
                    {
                        case "True/False":
                        case "Essay":
                            ConsoleManager.getInstance().Display(r.GetResponse().get(0));
                            break;
                        case "Multiple Choice":
                        case "Short Answer":
                        case "Ranking":
                        case "Matching":
                            ConsoleManager.getInstance().Display(r.GetResponse());
                            ConsoleManager.getInstance().Display("");
                            break;
                    }

                }

                ConsoleManager.getInstance().Display("");

                TabulateResponse(CurResponses, QuestionType);

                ConsoleManager.getInstance().Display("");

            }

        }
    }

    public void TabulateResponse(ArrayList<Response> CurResponses, String QuestionType)
    {

        HashMap<Response, Integer> ResponseCount = new HashMap<Response, Integer>();

        ArrayList<ArrayList<String>> CheckedResponses = new ArrayList<ArrayList<String>>();

        int CurResponseCount = 1;

        for (int i = 0; i < CurResponses.size(); i++)
        {

            Response base = CurResponses.get(i);
            ArrayList<String> BaseResponses = base.GetResponse();

            for (int j = i+1; j < CurResponses.size(); j++)
            {

                Response next = CurResponses.get(j);
                ArrayList<String> NextResponses = next.GetResponse();

                if (CheckArrayList(BaseResponses, NextResponses) && !CheckedResponses.contains(base))
                {
                    CurResponseCount++;
                }

            }

            if (!CheckedResponses.contains(BaseResponses))
            {
                CheckedResponses.add(BaseResponses);
                ResponseCount.put(base, CurResponseCount);
                CurResponseCount = 1;

            }

        }

        Set<Response> Responses = ResponseCount.keySet();
        Object[] ResponsesArray = Responses.toArray();

        Collection<Integer> Counts = ResponseCount.values();
        Object[] CountsArray = Counts.toArray();

        switch (QuestionType)
        {
            case "True/False":
            case "Essay":

                for (int i = 0; i < ResponsesArray.length; i++)
                {
                    Response r = (Response)ResponsesArray[i];

                    ConsoleManager.getInstance().Display(r.GetResponse().get(0) + ": " + CountsArray[i]);
                }

                break;

            case "Multiple Choice":
            case "Short Answer":
            case "Ranking":
            case "Matching":

                for (int i = 0; i < ResponsesArray.length; i++)
                {
                    Response r = (Response)ResponsesArray[i];

                    ConsoleManager.getInstance().Display(CountsArray[i] + ")");
                    ConsoleManager.getInstance().Display(r.GetResponse());
                    ConsoleManager.getInstance().Display("");
                }

                break;
        }

    }

    public boolean CheckArrayList(ArrayList<String> one, ArrayList<String> two)
    {
        boolean isEqual = false;

        if (one.size() == two.size())
        {

            for (int i = 0; i < one.size(); i++)
            {
                String OneCur = one.get(i);
                String TwoCur = two.get(i);

                if (!OneCur.equals(TwoCur))
                {
                    isEqual = false;
                    break;
                }

                isEqual = true;

            }

        }

        return isEqual;
    }



    public void Grade()
    {
        SetMenu(new MenuGrade());
    }

    public void GradeSurvey()
    {

        //Responses array
        Object[] AllResponsesArray = AllResponses.values().toArray();

        HashMap<ArrayList<Response>, ArrayList<Boolean>> isCorrectResponseAll = new HashMap<ArrayList<Response>, ArrayList<Boolean>>();

        int TotalGradableQuestions = 0;

        for (int i = 0; i < questions.size(); i++)
        {
            Question CurQuestion = questions.get(i);
            SetQuestionType(CurQuestion.getClass().getName());
            String QuestionType = GetQuestionType();

            boolean isCorrect = false;

            ArrayList<Response> CurQuestionResponses = (ArrayList<Response>) AllResponsesArray[i];

            if (!QuestionType.equals("Essay"))
            {

                TotalGradableQuestions++;

                ArrayList<Boolean> isCorrectAll = new ArrayList<Boolean>();

                for (Response CurResponse : CurQuestionResponses)
                {

                    ArrayList<String> CurResponseArrayList = CurResponse.GetResponse();

                    if (!CurQuestion.CorrectChoices.isEmpty())
                    {
                        ArrayList<String> CurQuestionCorrectChoices = CurQuestion.CorrectChoices;

                        if (CheckArrayList(CurResponseArrayList, CurQuestionCorrectChoices))
                        {
                            isCorrect = true;
                        }

                    }
                    else
                    {
                        String CurQuestionCorrectChoice = CurQuestion.CorrectChoice;
                        String CurQuestionResponse = CurResponseArrayList.get(0);

                        if (CurQuestionCorrectChoice.equals(CurQuestionResponse))
                        {
                            isCorrect = true;
                        }
                    }

                    isCorrectAll.add(isCorrect);

                    isCorrect = false;

                }

                isCorrectResponseAll.put(CurQuestionResponses, isCorrectAll);

            }

        }

        ArrayList<Integer> TotalPoints = new ArrayList<Integer>();

        //Array of ArrayList<Boolean>, length is # of questions
        Object[] ValueSetCorrect = isCorrectResponseAll.values().toArray();

        //Goes through responses and booleans for each question
        for (int i = 0; i < ValueSetCorrect.length; i++)
        {

            //Booleans for current question
            ArrayList<Boolean> CurBoolean = (ArrayList<Boolean>)ValueSetCorrect[i];

            //Goes through each response and boolean for current question
            for (int j = 0; j < CurBoolean.size(); j++)
            {

                if (TotalPoints.isEmpty())
                {

                    for (int k = 0; k < CurBoolean.size(); k++)
                    {
                        TotalPoints.add(0);
                    }

                }

                boolean b = CurBoolean.get(j);

                if (b == true)
                {
                    int CurTotalPoints = TotalPoints.get(j);
                    CurTotalPoints++;
                    TotalPoints.set(j, CurTotalPoints);
                }
            }

        }

        for (int i = 0; i < TotalPoints.size(); i++)
        {
            try
            {
                ConsoleManager.getInstance().Display("Grade for response set #" + (i + 1) + ":");

                int CurTotalPoints = TotalPoints.get(i);

                ConsoleManager.getInstance().Display(CurTotalPoints + "/" + TotalGradableQuestions);
                ConsoleManager.getInstance().Display("");
            }
            catch (ArithmeticException ae)
            {
                ConsoleManager.getInstance().Display("0/" + TotalGradableQuestions);
                ConsoleManager.getInstance().Display("");
            }


        }

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
        responses.clear();
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
