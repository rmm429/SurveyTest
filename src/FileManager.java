import java.io.*;

public class FileManager implements Serializable {

    private static FileManager instance = new FileManager();

    public FileManager() {
    }

    public Survey GetFileContent(String ChosenFile)
    {

        Survey LoadedSurvey = null;

        try
        {
            FileInputStream fileIn = new FileInputStream(ChosenFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            LoadedSurvey = (Survey) in.readObject();
            in.close();
            fileIn.close();
        }
        catch (IOException i)
        {
            System.err.println("IOException");
            i.printStackTrace();
        }
        catch (ClassNotFoundException c)
        {
            System.err.println("Survey class not found");
            c.printStackTrace();
        }

        return LoadedSurvey;

    }

    public void OutputToFile(String FileName, Survey survey, String SurveyTestType)
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(FileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(survey);
            out.close();
            fileOut.close();
            ConsoleManager.getInstance().Display(survey.GetName() + " has been saved in the file " + FileName);
            ConsoleManager.getInstance().Display("");
        }
        catch (IOException i)
        {
            System.err.println("IOException");
            i.printStackTrace();
        }
    }

    public static FileManager getInstance()
    {
        return instance;
    }

}
