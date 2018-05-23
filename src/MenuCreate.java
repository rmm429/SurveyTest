public class MenuCreate extends Menu
{

    private String name;

    public MenuCreate()
    {

    }

    public void SetChoices()
    {
        MenuChoices.add("Add a new T/F question");
        MenuChoices.add("Add a new multiple choice");
        MenuChoices.add("Add a new short answer question");
        MenuChoices.add("Add a new essay question");
        MenuChoices.add("Add a new ranking question");
        MenuChoices.add("Add a new matching question");
        MenuChoices.add("Exit");
    }

    public void GetChoice(int choice, Survey survey)
    {

        switch (choice)
        {
            case 1:
                Question TF = new TrueFalse();
                TF.SetSurveyTestType(survey.getClass().getName());
                TF.SetPrompt();
                ((TrueFalse) TF).SetChoices();
                survey.AddQuestion(TF);
                survey.SetMenu(new MenuCreate());
                break;
            case 2:
                Question MC = new MultipleChoice();
                MC.SetSurveyTestType(survey.getClass().getName());
                MC.SetPrompt();
                ((MultipleChoice) MC).SetChoices();
                survey.AddQuestion(MC);
                survey.SetMenu(new MenuCreate());
                break;
            case 3:
                Question SA = new ShortAnswer();
                SA.SetSurveyTestType(survey.getClass().getName());
                SA.SetPrompt();
                ((ShortAnswer) SA).SetChoices();
                survey.AddQuestion(SA);
                survey.SetMenu(new MenuCreate());
                break;
            case 4:
                Question Essay = new Essay();
                Essay.SetSurveyTestType(survey.getClass().getName());
                Essay.SetPrompt();
                ((Essay) Essay).GetChoices();
                survey.AddQuestion(Essay);
                survey.SetMenu(new MenuCreate());
                break;
            case 5:
                Question ranking = new Ranking();
                ranking.SetSurveyTestType(survey.getClass().getName());
                ranking.SetPrompt();
                ((Ranking) ranking).SetChoices();
                survey.AddQuestion(ranking);
                survey.SetMenu(new MenuCreate());
                break;
            case 6:
                Question matching = new Matching();
                matching.SetSurveyTestType(survey.getClass().getName());
                matching.SetPrompt();
                ((Matching) matching).SetTermsDefinitions();
                survey.AddQuestion(matching);
                break;
            case 7:
                survey.SetMenu(new MenuSurveyTestMain());
                break;
            default:
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
                survey.SetMenu(new MenuCreate());
                break;
        }

    }

    public void SetName(String n)
    {
        name = n;
    }

    public String GetName()
    {
        return name;
    }

    public void SurveyTestName()
    {
        if (GetType().equals("Survey"))
        {
            ConsoleManager.getInstance().Display("Enter the name of your survey:");
        }
        else if (GetType().equals("Test"))
        {
            ConsoleManager.getInstance().Display("Enter the name of your test:");
        }

        SetName(ConsoleManager.getInstance().Read());
    }

    public void AddPrompt() { }

    public void AddQuestions() { }

    public void Save() { }

    public void Quit() { }


}