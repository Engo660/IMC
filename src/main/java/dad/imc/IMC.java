package dad.imc;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IMC extends Application {
	private TextField operando1Text;
	private TextField operando2Text;
	private Text resultadoText;
	private Text op1Antes;
	private Text op1Despues;
	private Text op2Antes;
	private Text op2Despues;
	private Text clasificacionText;

	private DoubleProperty op1 = new SimpleDoubleProperty();
	private DoubleProperty op2 = new SimpleDoubleProperty();
	private DoubleProperty resultado = new SimpleDoubleProperty();

	@Override
	public void start(Stage primaryStage) throws Exception {
		operando1Text = new TextField();
		operando2Text = new TextField();
		resultadoText = new Text();
		op1Antes = new Text();
		op1Despues = new Text();
		op2Antes = new Text();
		op2Despues = new Text();
		clasificacionText = new Text();

		HBox h1 = new HBox(5, op1Antes, operando1Text, op1Despues);
		HBox h2 = new HBox(5, op2Antes, operando2Text, op2Despues);
		HBox h3 = new HBox(5, resultadoText);
		HBox h4 = new HBox(5, clasificacionText);

		VBox vroot = new VBox(5, h1, h2, h3, h4);
		vroot.setFillWidth(false);
		vroot.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vroot, 320, 200);

		primaryStage.setTitle("IMC");
		primaryStage.setScene(scene);
		primaryStage.show();

		op1Antes.setText("Peso: ");
		op1Despues.setText("kg");

		op2Antes.setText("Altura: ");
		op2Despues.setText("cm");

		resultado.asString("%.2f").addListener((o, ov, nv) -> resultadoText.setText("IMC: " + nv));
		operando1Text.textProperty().bindBidirectional(op1, new NumberStringConverter());
		operando2Text.textProperty().bindBidirectional(op2, new NumberStringConverter());

		resultado.bind(op1.divide((op2.divide(100)).multiply(op2.divide(100))));
		resultado.addListener((o, ov, nv) -> {
			double i = nv.doubleValue();
			if (i < 18.5) {
				clasificacionText.setText("Bajo Peso");
			} else if (i >= 18.5 && i < 25) {
				clasificacionText.setText("Normal");
			} else if (i >= 25 && i < 30) {
				clasificacionText.setText("Sobrepeso");
			} else {
				clasificacionText.setText("Obeso");
			}
		});

	}

	public static void main(String[] args) {
		launch(args);

	}

}
