import java.io.Console;
import java.util.ArrayList;

public class Ranking extends MultipleChoice
{

    //Setting a Serializaiton ID to ensure objects can be serialzied/deserialized from any execution instance of the program
    private static final long serialVersionUID = 6529685098267757690L;

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
        String OldChoice = null;
        String ChoiceStr;
        char ChoiceChar = 0;

        while (OldChoice == null)
        {
            try
            {
                ConsoleManager.getInstance().Display("Which choice do you want to modify:");
                DisplayChoices();
                ChoiceStr = ConsoleManager.getInstance().Read();
                ChoiceChar = ChoiceStr.charAt(0);
                OldChoice = AllChoices.get(ChoiceChar - 65);
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

        String NewChoice = null;

        while (NewChoice == null)
        {
            try
            {
                ConsoleManager.getInstance().Display("What is the new value for choice " + ChoiceChar + ":");
                NewChoice = ConsoleManager.getInstance().Read();

                AllChoices.set(ChoiceChar - 65, NewChoice);

                if (!CorrectChoices.isEmpty())
                {
                    if (CorrectChoices.contains(OldChoice))
                    {
                        CorrectChoices.set(CorrectChoices.indexOf(OldChoice), NewChoice);
                    }
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
    }

    public void DisplayCorrectChoices()
    {
        ConsoleManager.getInstance().Display("Correct Choice(s)");
        ConsoleManager.getInstance().Display(CorrectChoices);
        ConsoleManager.getInstance().Display("");
    }

    public ArrayList<String> GetResponse()
    {

        int UserResponseNum = 0;
        ArrayList<String> UserResponse = new ArrayList<String>();

        boolean contains = true;

        for (int i = 0; i < AllChoices.size(); i++)
        {

            try
            {

                contains = true;

                while (contains == true)
                {

                    char CurChoiceLetter = (char) (i + 65);
                    ConsoleManager.getInstance().Display("Rank choice " + CurChoiceLetter + " (1-" + AllChoices.size() + "):");
                    UserResponseNum = Integer.parseInt(ConsoleManager.getInstance().Read());

                    if (!UserResponse.contains(AllChoices.get(UserResponseNum - 1)))
                    {
                        UserResponse.add(AllChoices.get(UserResponseNum - 1));
                        contains = false;
                    }
                    else
                    {
                        ConsoleManager.getInstance().Display("Invalid choice");
                        ConsoleManager.getInstance().Display("");
                    }

                }

            }
            catch (NumberFormatException nfe)
            {

                i--;

                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }
            catch (IndexOutOfBoundsException ioobe)
            {
                i--;

                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }
        }


        return UserResponse;

    }

    public void Edit() { }

    public void GetQuestionType() { }

    public void Serialize() { }

}
