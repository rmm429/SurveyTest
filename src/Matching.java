import java.util.ArrayList;

public class Matching extends Question
{

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
                GetTerms(terms_definitions);
                GetDefinitions(terms_definitions);
                isCorrectChoice = true;
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
        for (int i = 0; i < terms; i++)
        {
            ConsoleManager.getInstance().Display("Enter term #" + (i + 1));
            MatchingTerms.add(ConsoleManager.getInstance().Read());
        }

    }

    public void GetDefinitions(int defs)
    {
        for (int i = 0; i < defs; i++)
        {
            char letter = (char) (i + 65);
            ConsoleManager.getInstance().Display("Enter definition " + letter);
            MatchingDefs.add(ConsoleManager.getInstance().Read());
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

}
