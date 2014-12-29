package com.aquawindow;

/*
 * #%L
 * igniter
 * %%
 * Copyright (C) 2013 - 2014 Adam Bien
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.airhacks.afterburner.injection.Injector;
import com.aquawindow.views.mainwindow.MainWindowView;

public class AquaWindow extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	MainWindowView appView = new MainWindowView();
        Scene scene = new Scene(appView.getView());
        stage.setTitle("AquaSIX");
 //       final String uri = getClass().getResource("app.css").toExternalForm();
//       scene.getStylesheets().add(uri);
        stage.setScene(scene);
//        FlatterFX.style();
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        Injector.forgetAll();
        if(DataBase.getConnection() != null) {
        	System.out.println("Connection exists!!");
        	DataBase.getConnection().close();
        	System.out.println("Connection closed");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
