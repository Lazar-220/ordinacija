package rs.ac.bg.fon.ai.zajednicki.json;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import rs.ac.bg.fon.ai.zajednicki.domen.Fizijatar;

public class Json {

	public static void main(String[] args) {
		
		Fizijatar f1=new Fizijatar(1, "Pera", "Peric", "pera", "p1");
		Fizijatar f2=new Fizijatar(2, "Zika", "Zikic", "zika", "z1");
		Fizijatar f3=new Fizijatar(3, "Mika", "Mikic", "mika", "m1");
		
		List<Fizijatar>lista=new ArrayList<Fizijatar>();
		lista.add(f1);
		lista.add(f2);
		lista.add(f3);
		
		Gson gson=new GsonBuilder()
				.setPrettyPrinting()
				.excludeFieldsWithoutExposeAnnotation().create();
		
		//upisivanje u fajl
		
		try (FileWriter out=new FileWriter("fizijatri.json")){
			gson.toJson(lista, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//citanje iz fajla
		
		try (FileReader in=new FileReader("fizijatri.json")){
			lista=Arrays.asList(gson.fromJson(in, Fizijatar[].class));
			for(Fizijatar f:lista) {
				System.out.println(f+", username: "+f.getKorisnickoIme());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
