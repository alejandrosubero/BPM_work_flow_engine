package com.bpm.engine.dto;

import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

public class ResponseAssignedDTO {

	  @SerializedName("error")
	    private String error;

	    @SerializedName("mensaje")
	    private String mensaje;

	    @SerializedName("entidades")
	    private List<AssignedDTO> entidades;

	    
		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		public String getMensaje() {
			return mensaje;
		}

		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}

		public List<AssignedDTO> getEntidades() {
			return entidades;
		}

		public void setEntidades(List<AssignedDTO> entidades) {
			this.entidades = entidades;
		}

		@Override
		public int hashCode() {
			return Objects.hash(entidades, error, mensaje);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ResponseAssignedDTO other = (ResponseAssignedDTO) obj;
			return Objects.equals(entidades, other.entidades) && Objects.equals(error, other.error)
					&& Objects.equals(mensaje, other.mensaje);
		}
	    
	    
	
}
