import java.io.Console;
import java.util.ArrayList;

public class Ranking extends MultipleChoice
{

    public Ranking() { }

    public void GetChoices(int choices)
    {
        for (int i = 0; i < choices; i++)
        {
            char Letter = (char) (i + 65);

            ConsoleManager.getInstance().Display("Enter choice " + Letter);
            AllChoices.add(ConsoleManager.getInstance().Read());
        }

        if (GetSurveyTestType().equals("Test"))
        {

        //COMMENT OUT
        GetCorrectChoice();


        }

    }

    public void EditChoice()
    {

    }

    public void GetCorrectChoice()
    {

        ArrayList<Integer> ChosenNums = new ArrayList<Integer>();

        //Adding blank elements to the ArrayList to fill it so that elements can be set out of order
        for (int i = 0; i < AllChoices.size(); i++)
        {
            CorrectChoices.add("");
        }

        for (int i = 0; i < AllChoices.size(); i++)
        {

            char Letter = (char) (i + 65);

            boolean isCorrectChoices = false;

            while (isCorrectChoices == false)
            {
                try
                {
                    ConsoleManager.getInstance().Display("Enter correct ranking for choice " + Letter + ": \"" + AllChoices.get(i) + "\" (1-" + AllChoices.size() + "):");
                    int CurCorrectChoiceIndex = Integer.parseInt(ConsoleManager.getInstance().Read()) - 1;


                    if (ChosenNums.contains(CurCorrectChoiceIndex))
                    {
                        ConsoleManager.getInstance().Display("Invalid choice");
                        ConsoleManager.getInstance().Display("");
                    }
                    else
                    {
                        ChosenNums.add(CurCorrectChoiceIndex);
                        String CurCorrectChoiceStr = AllChoices.get(i);
                        CorrectChoices.set(CurCorrectChoiceIndex, CurCorrectChoiceStr);
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

    public void DisplayChoices()
    {
        char MCChoiceChar = 'A';

        for (String MCChoice : AllChoices)
        {
            ConsoleManager.getInstance().Display(MCChoiceChar + ".\t" + MCChoice);
            MCChoiceChar++;
        }

        ConsoleManager.getInstance().Display("");

        if (GetSurveyTestType().equals("Test"))
        {


            DisplayCorrectChoice();

        }
    }

    public void DisplayCorrectChoice()
    {
        ConsoleManager.getInstance().Display("Correct Choice(s)");
        ConsoleManager.getInstance().Display(CorrectChoices);
        ConsoleManager.getInstance().Display("");
    }

    public void Edit() { }

    public void GetQuestionType() { }

    public void Serialize() { }

}
