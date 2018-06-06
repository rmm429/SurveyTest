import java.io.Console;
import java.util.ArrayList;

public class MultipleChoice extends Question
{

    //Setting a Serializaiton ID to ensure objects can be serialzied/deserialized from any execution instance of the program
    private static final long serialVersionUID = 6529685098267757690L;

    private int responses;

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
        else if (GetSurveyTestType().equals("Survey"))
        {
            GetResponses();
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

        String NewChoice = null;

        while (NewChoice == null)
        {
            try
            {
                ConsoleManager.getInstance().Display("What is the new value for choice " + choice + ":");
                NewChoice = ConsoleManager.getInstance().Read();

                AllChoices.set(choice - 1, NewChoice);

                if (!CorrectChoice.isEmpty())
                {
                    if (CorrectChoice.equals(OldChoice))
                    {
                        CorrectChoice = NewChoice;
                    }
                }

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
                    responses = CorrectChoicesNum;
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

    public void GetResponses()
    {

        int ResponsesNum = 0;

        boolean isNum = false;

        while (isNum == false)
        {

            isNum = false;

            try
            {

                ConsoleManager.getInstance().Display("Enter the number of responses for your question:");
                ResponsesNum = Integer.parseInt(ConsoleManager.getInstance().Read());

                if (ResponsesNum <= 0 || ResponsesNum >= AllChoices.size())
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

        responses = ResponsesNum;

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

    public void EditCorrectChoices()
    {
        String OldChoice = null;
        int choice = 0;

        while (OldChoice == null)
        {
            try
            {
                if (CorrectChoices.isEmpty())
                {
                    OldChoice = CorrectChoice;
                }
                else
                {
                    ConsoleManager.getInstance().Display("Which choice do you want to modify:");
                    DisplayCorrectChoices();
                    choice = Integer.parseInt(ConsoleManager.getInstance().Read());
                    OldChoice = CorrectChoices.get(choice - 1);
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

        String NewChoice = null;
        int NewChoiceNum = 0;

        while (NewChoice == null)
        {
            try
            {
                if (CorrectChoices.isEmpty())
                {
                    ConsoleManager.getInstance().Display("What is the new value for the correct choice:");
                    NewChoiceNum = Integer.parseInt(ConsoleManager.getInstance().Read());
                    NewChoice = AllChoices.get(NewChoiceNum - 1);
                    CorrectChoice = NewChoice;

                }
                else
                {
                    ConsoleManager.getInstance().Display("What is the new value for correct choice #" + choice + " (1-" + AllChoices.size() + ")" + ":");
                    NewChoiceNum = Integer.parseInt(ConsoleManager.getInstance().Read());
                    NewChoice = AllChoices.get(NewChoiceNum - 1);

                    if (CorrectChoices.contains(NewChoice))
                    {
                        NewChoice = null;

                        ConsoleManager.getInstance().Display("Invalid choice");
                        ConsoleManager.getInstance().Display("");
                    }
                    else
                    {
                        CorrectChoices.set(choice - 1, NewChoice);
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

    public ArrayList<String> Take()
    {
        prompt.Display();
        ConsoleManager.getInstance().Display("");

        DisplayChoices();

        ArrayList<String> UserResponse = GetResponse();

        return UserResponse;
    }

    public ArrayList<String> GetResponse()
    {

        int UserResponseNum = 0;
        ArrayList<String> UserResponse = new ArrayList<String>();

        boolean contains = true;

        for (int i = 0; i < responses; i++)
        {

            try
            {

                contains = true;

                while (contains == true)
                {

                    ConsoleManager.getInstance().Display("Select an answer for response #" + (i + 1) + " (1-" + AllChoices.size() + "):");
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
