package io.swagger.api.model.util;

public enum FilasEnum {

	FILA_USUARIO("usuarios"),
	FILA_RESERVAS("reservas");
	
	private String valor;
	
	private FilasEnum(String valor) {
		this.valor = valor;
	}
	
	public String getValor(){
		return valor;
	}
}
