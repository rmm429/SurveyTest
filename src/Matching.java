import java.util.ArrayList;

public class Matching extends Question
{

    protected static Matching instance = new Matching();

    protected ArrayList<String> MatchingTerms = new ArrayList<String>();
    protected ArrayList<String> MatchingDefs = new ArrayList<String>();

    public Matching() { }

    public void SetTermsDefinitions()
    {

        int terms_definitions = 0;

        boolean isCorrectChoice = false;

        while (isCorrectChoice == false)
        {
            try
            {
                ConsoleManager.getInstance().Display("Enter the number of terms and definitions for your matching question:");
                terms_definitions = Integer.parseInt(ConsoleManager.getInstance().Read());

                if (terms_definitions > 0)
                {
                    GetTerms(terms_definitions);
                    GetDefinitions(terms_definitions);
                    isCorrectChoice = true;
                }
                else
                {
                    ConsoleManager.getInstance().Display("Invalid choice");
                    ConsoleManager.getInstance().Display("");
                }
            }
            catch (NumberFormatException nfe)
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }
        }


        if (GetSurveyTestType().equals("Test"))
        {

            GetCorrectChoices(terms_definitions);


        }
    }

    public void GetTerms(int terms)
    {

        ArrayList<String> ChosenTerms = new ArrayList<String>();

        boolean isNewTerm = false;

        String CurTerm = null;

        for (int i = 0; i < terms; i++)
        {

            isNewTerm = false;

            while (isNewTerm == false)
            {

                ConsoleManager.getInstance().Display("Enter term #" + (i + 1));
                CurTerm = ConsoleManager.getInstance().Read();

                if (ChosenTerms.contains(CurTerm))
                {
                    ConsoleManager.getInstance().Display("Invalid choice");
                    ConsoleManager.getInstance().Display("");
                }
                else
                {
                    ChosenTerms.add(CurTerm);
                    MatchingTerms.add(CurTerm);
                    isNewTerm = true;
                }

            }

        }

    }

    public void GetDefinitions(int defs)
    {

        ArrayList<String> ChosenDefs = new ArrayList<String>();

        boolean isNewDef = false;

        String CurDef = null;

        for (int i = 0; i < defs; i++)
        {

            isNewDef = false;

            while (isNewDef == false)
            {

                char letter = (char) (i + 65);
                ConsoleManager.getInstance().Display("Enter definition " + letter);
                CurDef = ConsoleManager.getInstance().Read();

                if (ChosenDefs.contains(CurDef))
                {
                    ConsoleManager.getInstance().Display("Invalid choice");
                    ConsoleManager.getInstance().Display("");
                }
                else
                {
                    ChosenDefs.add(CurDef);
                    MatchingDefs.add(CurDef);
                    isNewDef = true;
                }

            }

        }

    }

    public void GetCorrectChoices(int terms_defs)
    {

        ArrayList<Character> ChosenLetters = new ArrayList<Character>();

        for (int i = 0; i < terms_defs; i++)
        {

            char FirstLetter = 'A';
            char LastLetter = (char) ( (terms_defs - 1) + 65);

            boolean isCorrectChoices = false;

            while (isCorrectChoices == false)
            {
                try
                {
                    ConsoleManager.getInstance().Display("Enter correct definition letter for term #" + (i + 1) + ": \"" + MatchingTerms.get(i) + "\" (" + FirstLetter + "-" + LastLetter + "):");
                    String CurCorrectChoiceCharStr = ConsoleManager.getInstance().Read();

                    char CurCorrectChoiceChar = CurCorrectChoiceCharStr.charAt(0);

                    if (ChosenLetters.contains(CurCorrectChoiceChar))
                    {
                        ConsoleManager.getInstance().Display("Invalid choice");
                        ConsoleManager.getInstance().Display("");
                    }
                    else
                    {
                        ChosenLetters.add(CurCorrectChoiceChar);
                        int CurCorrectChoiceNum = CurCorrectChoiceChar - 65;
                        String CurCorrectChoiceStr = MatchingDefs.get(CurCorrectChoiceNum);
                        CorrectChoices.add(CurCorrectChoiceStr);
                        isCorrectChoices = true;
                    }


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

        }

    }

    public void Display()
    {

        ConsoleManager.getInstance().Display(GetPrompt().GetPrompt());

        int TermDefNum = MatchingTerms.size();

        for (int i = 0; i < TermDefNum; i++)
        {
            String left = (i + 1) + ".\t" + MatchingTerms.get(i);
            String right = ((char)(i + 65)) + ".\t" + MatchingDefs.get(i);
            ConsoleManager.getInstance().Display(left, right);
        }

        ConsoleManager.getInstance().Display("");

        if (GetSurveyTestType().equals("Test"))
        {

            DisplayCorrectChoices();

        }
    }

    //COMMENT OUT
    public void DisplayCorrectChoices()
    {
        ConsoleManager.getInstance().Display("Correct Choice(s)");
        ConsoleManager.getInstance().Display(CorrectChoices);
        ConsoleManager.getInstance().Display("");
    }

    public void Edit() { }

    public void GetQuestionType() { }

    public void Serialize() { }

    public static Matching getInstance()
    {
        return instance;
    }

}
