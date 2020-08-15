package model;

import java.util.Date;

public class Casos {
		private String ccaa_iso;
		private Date fecha;
		private int num_casos;
		private int num_casos_prueba_pcr;
		private int num_casos_prueba_test_ac;
		private int num_casos_prueba_otras;
		private int num_casos_prueba_desconocida;
		
		public Casos(String ccaa_iso, Date fecha, int num_casos, int num_casos_prueba_pcr, int num_casos_prueba_test_ac,
				int num_casos_prueba_otras, int num_casos_prueba_desconocida) {
			super();
			this.ccaa_iso = ccaa_iso;
			this.fecha = fecha;
			this.num_casos = num_casos;
			this.num_casos_prueba_pcr = num_casos_prueba_pcr;
			this.num_casos_prueba_test_ac = num_casos_prueba_test_ac;
			this.num_casos_prueba_otras = num_casos_prueba_otras;
			this.num_casos_prueba_desconocida = num_casos_prueba_desconocida;
		}

		public String getCcaa_iso() {
			return ccaa_iso;
		}

		public void setCcaa_iso(String ccaa_iso) {
			this.ccaa_iso = ccaa_iso;
		}

		public Date getFecha() {
			return fecha;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}

		public int getNum_casos() {
			return num_casos;
		}

		public void setNum_casos(int num_casos) {
			this.num_casos = num_casos;
		}

		public int getNum_casos_prueba_pcr() {
			return num_casos_prueba_pcr;
		}

		public void setNum_casos_prueba_pcr(int num_casos_prueba_pcr) {
			this.num_casos_prueba_pcr = num_casos_prueba_pcr;
		}

		public int getNum_casos_prueba_test_ac() {
			return num_casos_prueba_test_ac;
		}

		public void setNum_casos_prueba_test_ac(int num_casos_prueba_test_ac) {
			this.num_casos_prueba_test_ac = num_casos_prueba_test_ac;
		}

		public int getNum_casos_prueba_otras() {
			return num_casos_prueba_otras;
		}

		public void setNum_casos_prueba_otras(int num_casos_prueba_otras) {
			this.num_casos_prueba_otras = num_casos_prueba_otras;
		}

		public int getNum_casos_prueba_desconocida() {
			return num_casos_prueba_desconocida;
		}

		public void setNum_casos_prueba_desconocida(int num_casos_prueba_desconocida) {
			this.num_casos_prueba_desconocida = num_casos_prueba_desconocida;
		}



		
		
}
