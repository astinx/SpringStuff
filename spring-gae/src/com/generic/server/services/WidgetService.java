package com.generic.server.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.generic.server.model.Widget;

@Service
public class WidgetService {

	public List<Widget> getAll() {
		return Arrays.asList(new Widget("CompraOnline", "Realiza compras online"),
				new Widget("PermutaTuPerro!", "Cambia de perro YA!"));
	}
	
}
