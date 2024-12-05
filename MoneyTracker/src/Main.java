import view.AbstractView;
import viewfactories.AbstractViewFactory;
import viewfactories.JSwingViewFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Main {


    public static void main(String[] args){

        Properties properties = loadProperties();
        if(Objects.equals(properties.getProperty("view.type"),"JSwing")){
            AbstractView view = createJSwingView();
            view.render();
        }else if(Objects.equals(properties.getProperty("view.type"),"Terminal")) {
            AbstractView view = createTerminalView();
            view.render();
        }else {
            System.out.println("Error: View type could not be loaded.");
        }



    }


    private static Properties loadProperties() {
        try {
            String rootPath = "MoneyTracker/src/util/";
            String appConfigPath = rootPath + "config.properties";

            Properties properties = new Properties();
            properties.load(new FileInputStream(appConfigPath));
            return properties;
        }catch(Exception e){
            System.out.println("Error: Can't load properties correctly");
            e.printStackTrace();
        }
        return null;
    }

    private static AbstractView createJSwingView(){
        AbstractViewFactory factory = new JSwingViewFactory();
        return factory.createView();

    }

    private static AbstractView createTerminalView(){

        return null;
    }
}
