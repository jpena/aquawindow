package com.aquawindow.views.mainwindow;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;

import com.aquawindow.dao.MainWindowDAO;
import com.aquawindow.models.ListMenuItem;

public class MainWindowPresenter implements Initializable {
	
	@FXML private TreeView<ListMenuItem> listaMenus;
	@FXML private TabPane tp;
	@FXML private TextField buscarTree;
	@FXML private Button testConn;
	
	private static List<TreeItem<ListMenuItem>> _listaItemsOriginal;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tp.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);
		TreeItem<ListMenuItem> root = new TreeItem<ListMenuItem>();
		root.setExpanded(true);
		
		
		try {
			root.getChildren().setAll(MainWindowDAO.getDAO().getMainTreeMenu());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*ListMenuItem menu1 = new ListMenuItem("Crear Menu","com.aquawindow.views.menu.MenuView");		
		root.getChildren().add(new TreeItem<ListMenuItem>(menu1));		
		root.getChildren().addAll(new TreeItem<ListMenuItem>("Inicio"),
				new TreeItem<String>("Correo Electrónico"),
				new TreeItem<String>("Calendario"));*/
		listaMenus.setRoot(root);
		listaMenus.setShowRoot(false);
		

		_listaItemsOriginal = FXCollections.observableArrayList(listaMenus.getRoot().getChildren());
		
		/*buscarTree.setOnKeyReleased(keyEvent ->  _filtrarArbol());	

		listaMenus.setOnMouseClicked(mouseEvent -> _crearTab(mouseEvent));*/

	}

	@FXML
	private void _crearTab(MouseEvent mouseEvent) {
		if (mouseEvent.getClickCount() == 2
				&& listaMenus.getSelectionModel().getSelectedItem().getChildren().isEmpty()) {
			
			String tabTitle = listaMenus.getSelectionModel().getSelectedItem().getValue().getTitulo();
			Tab tabActual = null;
			
			for(Tab tb: tp.getTabs()){
				if(tb.getText().equals(tabTitle)){
					tabActual=tb;
					break;
				}
				
			}
			
			if(null == tabActual){
				tabActual = new Tab(tabTitle);

				try {
					Object instance = Class.forName(listaMenus.getSelectionModel().getSelectedItem().getValue().getClase()).newInstance();
					Method method = instance.getClass().getDeclaredMethod("getView");					
					tabActual.setContent((Node)method.invoke(instance, null));					
				}catch (Exception e) {
					e.printStackTrace();
				}

				tp.getTabs().add(tabActual);					
			}
			tp.getSelectionModel().select(tabActual);			

		}
	}

	@FXML
	private void _filtrarArbol() {
		listaMenus.getRoot().getChildren().setAll(_listaItemsOriginal);	
		listaMenus.getRoot().getChildren()
			.removeIf(item -> !item.getValue().getTitulo().toLowerCase().startsWith(buscarTree.getText().toLowerCase()));
	}

}
