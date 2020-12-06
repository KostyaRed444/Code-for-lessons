import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

class InputText{
    public String Text;
}

public class Main {
    public static String getText(String filename){
        String res=new String();
        try{
            File file= new File(filename);
            Scanner sc= new Scanner(file);
            while(sc.hasNextLine()){
                String line=sc.nextLine();
                res+=line;
            }
            sc.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) throws IOException{
        String settings="?api-version=3.0from=ru&to=de&to=it&to=pl";
        String URL_service="https://api.cognitive.microsofttranslator.com/translate";

        String API_URL=URL_service+settings;

        String POSTData=getText("Mytext.json");

        URL url=new URL(API_URL);

        HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Ocp-Apim-Subscription-Key", "82e622ccc27b4ad0af0918182329a742");
        urlConnection.setRequestProperty("Ocp-Apim-Subscription-Region", "westeurope");
        urlConnection.setRequestProperty("Content-type", "application/json");

        urlConnection.setDoOutput(true);
        OutputStream out= urlConnection.getOutputStream();
        out.write(POSTData.getBytes());

        Scanner in=new Scanner(urlConnection.getInputStream());

        try(FileOutputStream file=new FileOutputStream("result.json")){
            if (in.hasNext()){
                byte[] buffer=in.nextLine().getBytes();
                file.write(buffer, 0, buffer.length);
            }else System.out.println("No output returned");
        }catch(FileNotFoundException e){
            e.printStackTrace();
            System.out.println("File not found");
        }catch (IOException e){
            e.printStackTrace();
        }

        urlConnection.disconnect();
        System.out.println("Done");
    }
}
