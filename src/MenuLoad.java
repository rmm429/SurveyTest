import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class MenuLoad extends Menu
{

    public MenuLoad() { }

    public void SetChoices()
    {

        if (GetType().equals("Survey"))
        {
            MenuChoices.add("Which Survey would you like to load:");
        }
        else if (GetType().equals("Test"))
        {
            MenuChoices.add("Which Test would you like to load:");
        }

    }

    public String DisplaySurveysFromFile(String SurveyTestType)
    {

        ArrayList<String> FilesInDir = new ArrayList<String>();

        File WorkingDirectory = new File(".");

        File[] files = null;

        String ChosenSurveyTestName = "";

        while (ChosenSurveyTestName.equals(""))
        {

            try
            {
                if (SurveyTestType.equals("Survey"))
                {
                    files = WorkingDirectory.listFiles(new FilenameFilter() {
                        public boolean accept(File dir, String name) {
                            return name.toLowerCase().endsWith(".survey");
                        }
                    });
                }
                else if (SurveyTestType.equals("Test"))
                {
                    files = WorkingDirectory.listFiles(new FilenameFilter() {
                        public boolean accept(File dir, String name) {
                            return name.toLowerCase().endsWith(".test");
                        }
                    });
                }

                int FileNum = 1;

                for (File file : files)
                {
                    String CurFileName = file.getName();

                    FilesInDir.add(CurFileName);

                    ConsoleManager.getInstance().Display(FileNum + ".\t" + CurFileName);

                    FileNum++;
                }

                int ChosenSurveyTestNum = Integer.parseInt(ConsoleManager.getInstance().Read());

                ChosenSurveyTestName = FilesInDir.get(ChosenSurveyTestNum - 1);
            }
            catch (IndexOutOfBoundsException ioobe)
            {
                ConsoleManager.getInstance().Display("Invalid choice");
                ConsoleManager.getInstance().Display("");
            }

        }


        return ChosenSurveyTestName;


    }

    public void GetChoice(String ChosenFile, HashMap<String, Survey> all, Survey survey, String SurveyTestType)
    {
        Survey.LoadSurvey(ChosenFile, all, SurveyTestType);
        survey.SetMenu(new MenuSurveyTestMain());
    }

}
