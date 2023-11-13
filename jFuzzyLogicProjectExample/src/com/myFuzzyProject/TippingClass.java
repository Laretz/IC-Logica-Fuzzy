package com.myFuzzyProject;

import java.util.Scanner;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class TippingClass {
	public static void main(String[] args) throws Exception {
		Scanner ler = new Scanner(System.in);
		float umidade, chuva;
		
		String filename = "tipper.fcl"; //arquivo FCL
		FIS fis = FIS.load(filename, true); //carregamento

		if (fis == null) { //erro durante o carregamento do arquivo
			System.err.println("Can't load file: '" + filename + "'");
			System.exit(1);
		}

		// Get default function block
		FunctionBlock fb = fis.getFunctionBlock(null);

	
		
		// Defini��es de vari�veis de entrada FIS
		System.out.println("Umidade atual do solo (1-seco, 5-moderado 10-umido)? ");
		umidade = ler.nextFloat();
		System.out.println("Previsão do tempo (1- baixa chance de chuva, 5- moderada chance de chuva -, 10- alta chance de chuva)? ");
		chuva = ler.nextFloat();
		fb.setVariable("umidade", umidade);  //8,5
		fb.setVariable("chuva", chuva);  //7,5  tip=19.99999

		// Execu��o do sistema
		fb.evaluate();

		// Show output variable's chart
		fb.getVariable("nivelDeIrrigacao").defuzzify();

		// Print ruleSet
		System.out.println(fb);
		System.out.println("nivelDeIrrigacao: " + fb.getVariable("nivelDeIrrigacao").getValue());

	}

}
