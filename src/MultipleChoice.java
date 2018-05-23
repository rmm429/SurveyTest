import java.io.Console;
import java.util.ArrayList;

public class MultipleChoice extends Question
{

    public MultipleChoice()
    {

    }

    public void SetChoices()
    {

        boolean isChoicesNum = false;
        int ChoicesNum = 0;

        while (isChoicesNum == false)
        {
            try
            {
                ConsoleManager.getInstance().Display("Enter the number of choices for your question:");
                ChoicesNum = Integer.parseInt(ConsoleManager.getInstance().Read());

                if (ChoicesNum > 0)
                {
                    isChoicesNum = true;
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

        GetChoices(ChoicesNum);

    }

    public void GetChoices(int choices)
    {
        for (int i = 0; i < choices; i++)
        {
            ConsoleManager.getInstance().Display("Enter choice #" + (i + 1));
            AllChoices.add(ConsoleManager.getInstance().Read());
        }

        if (GetSurveyTestType().equals("Test"))
        {

            GetCorrectChoices();

        }

    }

    public void EditChoice()
    {

        String OldChoice = null;
        int choice = 0;

        while (OldChoice == null)
        {
            try
            {
                ConsoleManager.getInstance().Display("Which choice do you want to modify:");
                DisplayChoices();
                //65 = A
                choice = Integer.parseInt(ConsoleManager.getInstance().Read());
                OldChoice = AllChoices.get(choice - 1);
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


        ConsoleManager.getInstance().Display("What is the new value for choice " + choice + ":");
        String NewChoice = ConsoleManager.getInstance().Read();

        AllChoices.set(choice - 1, NewChoice);
    }

    public void GetCorrectChoices()
    {

        int CorrectChoicesNum = 0;

        boolean isNum = false;

        while (isNum == false)
        {

            isNum = false;

            try
            {

                ConsoleManager.getInstance().Display("Enter number of correct choices:");
                CorrectChoicesNum = Integer.parseInt(ConsoleManager.getInstance().Read());

                if (CorrectChoicesNum <= 0 || CorrectChoicesNum >= AllChoices.size())
                {
                    ConsoleManager.getInstance().Display("Invalid choice");
                    ConsoleManager.getInstance().Display("");
                }
                else
                {
                    isNum = true;
                }

            }
            catch (NumberFormatException nfe)
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }
        }

        ArrayList<String> ChosenChoices = new ArrayList<String>();

        boolean isCorrectChoices = false;

        int i = 1;

        while (isCorrectChoices == false)
        {

            isCorrectChoices = false;

                try
                {

                    if (CorrectChoicesNum == 1)
                    {
                        ConsoleManager.getInstance().Display("Enter correct choice:");
                        CorrectChoice = AllChoices.get(Integer.parseInt(ConsoleManager.getInstance().Read()) - 1);
                        isCorrectChoices = true;
                    }
                    else
                    {
                        while (i <= CorrectChoicesNum)
                        {

                            isCorrectChoices = false;

                            ConsoleManager.getInstance().Display("Enter correct choice #" + i + ":");
                            CorrectChoice = AllChoices.get(Integer.parseInt(ConsoleManager.getInstance().Read()) - 1);

                            if (ChosenChoices.contains(CorrectChoice))
                            {
                                ConsoleManager.getInstance().Display("Invalid choice");
                                ConsoleManager.getInstance().Display("");
                            }
                            else
                            {
                                ChosenChoices.add(CorrectChoice);
                                CorrectChoices.add(CorrectChoice);
                                isCorrectChoices = true;
                                i++;
                            }

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

    public void DisplayChoices()
    {
        int MCChoiceNum = 0;

        for (String MCChoice : AllChoices)
        {
            MCChoiceNum++;
            ConsoleManager.getInstance().Display(MCChoiceNum + ".\t" + MCChoice);
        }

        ConsoleManager.getInstance().Display("");

        if (GetSurveyTestType().equals("Test"))
        {

            DisplayCorrectChoices();

        }
    }

    public void DisplayCorrectChoices()
    {
        if (CorrectChoices.isEmpty())
        {
            ConsoleManager.getInstance().Display("Correct Choice(s)");
            ConsoleManager.getInstance().Display(CorrectChoice);
            ConsoleManager.getInstance().Display("");
        }
        else
        {
            ConsoleManager.getInstance().Display("Correct Choice(s)");
            ConsoleManager.getInstance().Display(CorrectChoices);
            ConsoleManager.getInstance().Display("");
        }

    }

    public void Edit() { }

    public void GetQuestionType() { }

    public void Serialize() { }

}
