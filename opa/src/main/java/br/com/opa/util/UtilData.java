package br.com.opa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import br.com.opa.enums.TipoIntervaloTempoEnum;
import de.jollyday.Holiday;
import de.jollyday.HolidayManager;
import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayHandlerType;
import net.objectlab.kit.datecalc.jdk8.LocalDateKitCalculatorsFactory;

public class UtilData {

	public enum FormatoData {
		DATA_HORA_MIN_SEG("dd/MM/yyyy HH:mm:ss"),
		DATA("dd/MM/yyyy"),
		DATA_HORA_MIN("dd/MM/yyyy HH:mm"),
		HORA_MINUTO_SEGUNDO("HH:mm:ss"),
		HORA_MINUTO("HH:mm"),
		ANO("yyyy"),
		MES("MM"),
		DIA("dd"),
		DATA_PROTOCOLO("yyyyMMdd"),
		DATA_HORA_MIN_SEG_INTER("yyyy-MM-dd HH:mm:ss");

		private String formato;

		private FormatoData(String formato) {
			this.formato = formato;
		}
	
		public String getFormato() {
			return formato;
		}

		public static FormatoData getFormato(String formato) {
			return Arrays.asList(FormatoData.values()).stream().filter(f -> f.getFormato().equals(formato)).findFirst().get();
		}

	}

	public static Date converterStringParaData(String dataString, FormatoData formato) {
		try {
			if (!new UtilString().vazio(dataString)) {
				SimpleDateFormat sdf = new SimpleDateFormat(formato.getFormato());
				return sdf.parse(dataString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String converterDataParaString(Date data, FormatoData formato) {
		try {
			if (data != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(formato.getFormato());
				return sdf.format(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

    public static String converterStringDataParaFormatoXML(String data) {
    	String dd = "";
    	String mm = "";
    	String yyyy = "";
    	String retorno = "";
    	if(!"".equals(data)){
	    	if(data.contains("/")) {
	    		dd = data.substring(0, 2);
	    		mm = data.substring(3,5);
	    		yyyy = data.substring(6,10);    		
	    	} else {
	    		dd = data.substring(0, 2);
	    		mm = data.substring(2,4);
	    		yyyy = data.substring(4,8);
	    	}
	    	retorno = yyyy + "-" + mm + "-" + dd;
    	}
    	return retorno;
    }
    
    public static String converterStringHoraMinutoSegundoParaFormatoXML(String data) {
    	data = data.replace(":", "");
    	String hh = "00";
    	String min = "00";
    	String ss = "00";
    	hh = data.substring(0, 2);
    	if(data.length() == 4){
	    	min = data.substring(2, 4);
    	} else if (data.length() == 6) {
    		min = data.substring(2, 4);
    		ss = data.substring(4, 6);
    	}
    	String retorno = hh + ":" + min + ":" + ss;  
    	return retorno;
    	
    }
	
	public XMLGregorianCalendar converteStringEmXMLGregorianCalendar(String data){
        if (data != null){
        	//String mydatetime= "2011-09-29T08:55:00";
        	XMLGregorianCalendar xmlGrogerianCalendar = null;
			try {
				xmlGrogerianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(data);
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
            return xmlGrogerianCalendar;
        }
        return null;
    }

	public static XMLGregorianCalendar converteDateEmXMLGregorianCalendar(Date data){
        if (data != null){
        	XMLGregorianCalendar xmlGrogerianCalendar = null;
			try {
				GregorianCalendar gregorianCalendar = new GregorianCalendar();
				gregorianCalendar.setTime(data);
				xmlGrogerianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
            return xmlGrogerianCalendar;
        }
        return null;
    }
   
    public static String formataAno(Date data){
        if (data != null){
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern(FormatoData.ANO.getFormato());
            return sdf.format(data);
        }
        return "";
    }
   
    public static String formataHoraMinSeg(Date data){
        if (data != null){
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern(FormatoData.HORA_MINUTO_SEGUNDO.getFormato());
            return sdf.format(data);
        }
        return "";
    }

    public static Date subtrair(Date data, int minuto, int hora, int dia, int mes, int ano) {
    	if (data != null) {
    		Date dataRetorno = new Date();
    		dataRetorno.setTime(data.getTime());
    		dataRetorno = subtrairMinuto(dataRetorno, minuto);
    		dataRetorno = subtrairHora(dataRetorno, hora);
    		dataRetorno = subtrairDia(dataRetorno, dia);
    		dataRetorno = subtrairMes(dataRetorno, mes);
    		dataRetorno = subtrairAno(dataRetorno, ano);
	    	return dataRetorno;
    	}
   		return null;
    }

    public static Date subtrairAno(Date data, int ano) {
    	if (data != null) {
	    	LocalDateTime dateTime = LocalDateTime.ofInstant(data.toInstant(), ZoneId.systemDefault()).minusYears(ano);
	    	return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    	}
    	return null;
	}

	public static Date subtrairMes(Date data, int mes) {
		if (data != null) {
	    	LocalDateTime dateTime = LocalDateTime.ofInstant(data.toInstant(), ZoneId.systemDefault()).minusMonths(mes);
	    	return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    	}
    	return null;
	}

	public static Date subtrairHora(Date data, int hora) {
    	if (data != null) {
	    	LocalDateTime dateTime = LocalDateTime.ofInstant(data.toInstant(), ZoneId.systemDefault()).minusHours(hora);
	    	return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    	}
    	return null;
	}

	public static Date subtrairMinuto(Date data, int minuto) {
    	if (data != null) {
	    	LocalDateTime dateTime = LocalDateTime.ofInstant(data.toInstant(), ZoneId.systemDefault()).minusMinutes(minuto);
	    	return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    	}
    	return null;
	}

	public static Date subtrairDia(Date data, int dia) {
    	if (data != null) {
	    	LocalDateTime dateTime = LocalDateTime.ofInstant(data.toInstant(), ZoneId.systemDefault()).minusDays(dia);
	    	return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    	}
   		return null;
    }

    public static Date adicionarMinutos(Date data, int minutos) {
    	if (data != null) {
	    	LocalDateTime dateTime = LocalDateTime.ofInstant(data.toInstant(), ZoneId.systemDefault()).plusMinutes(minutos);
	    	return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    	}
   		return null;
    }
    
	public Date subtrairMilisegundos(Date data, int milisegundos) {
		if(data != null){
			LocalDateTime dateTime = LocalDateTime.ofInstant(data.toInstant(), ZoneId.systemDefault()).minusSeconds(milisegundos);
			return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
		}
		return null;
	}
    

    public static Date adicionarDias(Date data, int dias) {
    	if (data != null) {
	    	LocalDateTime dateTime = LocalDateTime.ofInstant(data.toInstant(), ZoneId.systemDefault()).plusDays(dias);
	    	return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    	}
   		return null;
    }

    public Date converterStringDataXMLParaData(String data) {
    	String yyyy = data.substring(0, 4);
    	String mm = data.substring(5, 7);
    	String dd = data.substring(8, 10);
    	Date d = new UtilData().convertStringDataToData(dd+"/"+mm+"/"+yyyy);
    	if (d != null) {
    		return new UtilData().convertStringDataToData(dd+"/"+mm+"/"+yyyy);
    	} else {
    		return new Date();
    	}
    }
    
    public LocalDateTime converterStringDataXMLParaLocalDateTime(String data) {
    	if (!UtilNullEmpty.isNullOrEmpty(data)) {
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FormatoData.DATA.getFormato());
    		return LocalDateTime.parse(data, formatter);
    	}
		return null;
    }
    
    public LocalDate converterStringDataXMLParaLocalDate(String data) {
    	if (!UtilNullEmpty.isNullOrEmpty(data)) {
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FormatoData.DATA.getFormato());
    		return LocalDate.parse(data, formatter);
    	}
		return null;
    }

    public java.util.Date convertStringDataToData(String data) {
    	SimpleDateFormat sdf = new SimpleDateFormat(FormatoData.DATA.getFormato());
    	Date d = null;
    	try {
			d = sdf.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d; 
    }
    
    public static long calcularDiferencaEmDias(final Date dataInicial, final Date dataFinal) {
    	return TimeUnit.MILLISECONDS.toDays(dataFinal.getTime()) - TimeUnit.MILLISECONDS.toDays(dataInicial.getTime()); 
    }
    
    public static String formataDataProtocolo(Date data){
        if (data != null){
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern(FormatoData.DATA_PROTOCOLO.getFormato());
            return sdf.format(data);
        }
        return "";
    }

//	public static boolean isIntervaloDeDataMaiorQue(String dataInicial, String dataFinal, Integer quantidadeDeDias) {
//		//RETIRA AS "/" DA DATA INICIAL E FINAL ENVIADA PELA JSP
//		dataInicial = dataInicial.substring(0, 10);
//		dataFinal = dataFinal.substring(0, 10);
//		
//		String [] dataInicialFormatada = dataInicial.split("/"); 
//		String [] dataFinalFormatada = dataFinal.split("/");
//				
//		GregorianCalendar gInicial = new GregorianCalendar();
//		GregorianCalendar gFinal = new GregorianCalendar();
//		
//		//SEPARA OS DIA, MES E ANO DA DATA INICIAL PARA PODER SER COMPARADO 
//		gInicial.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dataInicialFormatada[0]));
//		gInicial.set(Calendar.MONTH, Integer.parseInt(dataInicialFormatada[1]));
//		gInicial.set(Calendar.YEAR, Integer.parseInt(dataInicialFormatada[2]));
//		//SEPARA OS DIA, MES E ANO DA DATA INICIAL PARA PODER SER COMPARADO 
//		gFinal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dataFinalFormatada[0]));
//		gFinal.set(Calendar.MONTH, Integer.parseInt(dataFinalFormatada[1]));
//		gFinal.set(Calendar.YEAR, Integer.parseInt(dataFinalFormatada[2]));
//		int diferencaDeDias = (int) ((gFinal.getTimeInMillis() - gInicial.getTimeInMillis() + MS_POR_HORA)/MS_POR_DIA); 
//		return (diferencaDeDias) > quantidadeDeDias;
//	}

//	public static boolean isIntervaloDeDataMenorQue(String dataInicial, String dataFinal, Integer quantidadeDeDias) {
//		//RETIRA AS "/" DA DATA INICIAL E FINAL ENVIADA PELA JSP
//		dataInicial = dataInicial.substring(0, 10);
//		dataFinal = dataFinal.substring(0, 10);
//		
//		String [] dataInicialFormatada = dataInicial.split("/"); 
//		String [] dataFinalFormatada = dataFinal.split("/");
//				
//		GregorianCalendar gInicial = new GregorianCalendar();
//		GregorianCalendar gFinal = new GregorianCalendar();
//		
//		//SEPARA OS DIA, MES E ANO DA DATA INICIAL PARA PODER SER COMPARADO 
//		gInicial.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dataInicialFormatada[0]));
//		gInicial.set(Calendar.MONTH, Integer.parseInt(dataInicialFormatada[1]));
//		gInicial.set(Calendar.YEAR, Integer.parseInt(dataInicialFormatada[2]));
//		//SEPARA OS DIA, MES E ANO DA DATA INICIAL PARA PODER SER COMPARADO 
//		gFinal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dataFinalFormatada[0]));
//		gFinal.set(Calendar.MONTH, Integer.parseInt(dataFinalFormatada[1]));
//		gFinal.set(Calendar.YEAR, Integer.parseInt(dataFinalFormatada[2]));
//		int diferencaDeDias = (int) ((gFinal.getTimeInMillis() - gInicial.getTimeInMillis() + MS_POR_HORA)/MS_POR_DIA); 
//		return (diferencaDeDias) <= quantidadeDeDias;
//	}
   
    public static String converterStringDataParaFormatoXMLComTempoddMMyyyy(String data) {
    	String dd = data.substring(0, 2);
    	String mm = data.substring(3, 5);
    	String yyyy = data.substring(6, 10);
    	String hh = data.substring(11, 13);
    	String min = data.substring(14, 16);
    	String ss = "00";
    	String retorno = yyyy + "-" + mm + "-" + dd + "T" + hh + ":" + min + ":" + ss;
    	return retorno;
    }

    public Date converterStringDataXMLParaDateUtil(String data) {
    	String yyyy = data.substring(0, 4);
    	String mm = data.substring(5, 7);
    	String dd = data.substring(8, 10);
    	Date d = new UtilData().converterStringDataToDateUtil(dd+"/"+mm+"/"+yyyy);
    	if (d != null) {
    		return new UtilData().converterStringDataToDateUtil(dd+"/"+mm+"/"+yyyy);
    	} else {
    		return new Date();
    	}
    }
    public java.util.Date converterStringDataToDateUtil(String data) {
    	SimpleDateFormat sdf = new SimpleDateFormat(FormatoData.DATA.getFormato());
    	Date d = null;
    	try {
			d = sdf.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d; 
    }
    
    public static int diferencaEmDias(Calendar c1, Calendar c2) {
       long m1 = c1.getTimeInMillis();
       long m2 = c2.getTimeInMillis();
       return (int) ((m2 - m1) / (24*60*60*1000));
    }

	public static Calendar converterStringParaCalendar(String data) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new UtilData().converterStringParaDataHora(data));
        return calendar;
	}

	private Date converterStringParaDataHora(String data) {
		try {
			if (data != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(FormatoData.DATA_HORA_MIN.getFormato());
				return sdf.parse(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String converterDataXmlComTempoParaDataString(String data) {
    	String yyyy = data.substring(0, 4);
    	String mm = data.substring(5, 7);
    	String dd = data.substring(8, 10);
    	String hhmm = data.substring(11, 16);
    	String retorno = dd + "/" + mm + "/" + yyyy + " " + hhmm;
    	return retorno;
	}

	public static String converterDataXmlComSegundosParaDataString(String data) {
    	String yyyy = data.substring(0, 4);
    	String mm = data.substring(5, 7);
    	String dd = data.substring(8, 10);
    	String hhmmss = data.substring(11, 19);
    	String retorno = dd + "/" + mm + "/" + yyyy + " " + hhmmss;
    	return retorno;
	}

	public static long converterStringDataParaTime(String data, FormatoData formato) {
		if(data != null && !data.equals("")) {
			return new UtilData().converterStringDataToDateUtil(data, formato).getTime();
		}
   		return 0;
	}
    
    public java.util.Date converterStringDataToDateUtil(String data, FormatoData formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato.getFormato());
        Date d = null;
        try {
			d = sdf.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
    }

	public static String converterStringDataXMLParaStringData(String data) {
    	String yyyy = data.substring(0, 4);
    	String mm = data.substring(5, 7);
    	String dd = data.substring(8, 10);
    	
    	return dd+"/"+mm+"/"+yyyy;
	}

	public static String converterLocalDateTimeParaString(LocalDateTime localDateTime, FormatoData formato) {
		if (localDateTime != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato.getFormato());
			return localDateTime.format(formatter);
		}
		return null;
	}

	public static LocalDateTime converterStringParaLocalDateTime(String str, FormatoData formato) {
		if (!UtilNullEmpty.isNullOrEmpty(str)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato.getFormato());
			return LocalDateTime.parse(str, formatter);
		}
		return null;
	}

	public static String converterLocalDateParaString(LocalDate localDate) {
		if (localDate != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FormatoData.DATA.getFormato());
			return localDate.format(formatter);
		}
		return null;
	}

	public static LocalDate converterStringParaLocalDate(String str) {
		if (!UtilNullEmpty.isNullOrEmpty(str)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FormatoData.DATA.getFormato());
			return LocalDate.parse(str, formatter);
		}
		return null;
	}

	public static Date converterLocalDateParaDate(LocalDate localDate) {
		if (localDate != null) {
			return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		return null;
	}

	public static String converterLocalTimeParaString(LocalTime localTime, FormatoData formato) {
		if (localTime != null) {
			return localTime.format(DateTimeFormatter.ofPattern(formato.getFormato()));
		}
		return null;
	}

	public static LocalTime converterStringParaLocalTime(String hora, FormatoData formato) {
		if (hora != null) {
			return LocalTime.parse(hora, DateTimeFormatter.ofPattern(formato.getFormato()));
		}
		return null;
	}

	public static LocalDate converterXmlGregorianCalendarParaLocalDate(XMLGregorianCalendar xmlGregorianCalendar) {
		if (xmlGregorianCalendar != null) {
			return xmlGregorianCalendar.toGregorianCalendar().toZonedDateTime().toLocalDate();
		}
		return null;
	}

	public static boolean isIntervaloDeDataMaiorQue(LocalDate dataInicial, LocalDate dataFinal, Integer intervalo, TipoIntervaloTempoEnum tipoIntervalo) {
		if (tipoIntervalo.equals(TipoIntervaloTempoEnum.DIA)) {
			return calcularDiferencaEmDias(dataInicial, dataFinal) > intervalo;
		} else if (tipoIntervalo.equals(TipoIntervaloTempoEnum.MES)) {
			return calcularDiferencaEmMeses(dataInicial, dataFinal) > intervalo;
		} else if (tipoIntervalo.equals(TipoIntervaloTempoEnum.ANO)) {
			return calcularDiferencaEmAnos(dataInicial, dataFinal) > intervalo;
		}
		return false;
    }

	public static boolean isIntervaloDeDataMenorQue(LocalDate dataInicial, LocalDate dataFinal, Integer intervalo, TipoIntervaloTempoEnum tipoIntervalo) {
		if (tipoIntervalo.equals(TipoIntervaloTempoEnum.DIA)) {
			return calcularDiferencaEmDias(dataInicial, dataFinal) <= intervalo;
		} else if (tipoIntervalo.equals(TipoIntervaloTempoEnum.MES)) {
			return calcularDiferencaEmMeses(dataInicial, dataFinal) <= intervalo;
		} else if (tipoIntervalo.equals(TipoIntervaloTempoEnum.ANO)) {
			return calcularDiferencaEmAnos(dataInicial, dataFinal) <= intervalo;
		}
		return false;
    }

	public static long calcularDiferencaEmDias(LocalDate dataInicial, LocalDate dataFinal) {
		return ChronoUnit.DAYS.between(dataInicial, dataFinal);
	}

	public static long calcularDiferencaEmMeses(LocalDate dataInicial, LocalDate dataFinal) {
		return ChronoUnit.MONTHS.between(dataInicial, dataFinal);
	}

	public static long calcularDiferencaEmAnos(LocalDate dataInicial, LocalDate dataFinal) {
		return ChronoUnit.YEARS.between(dataInicial, dataFinal);
	}

	public static boolean isIntervaloDeDataMenorQue(LocalDateTime dataInicial, LocalDateTime dataFinal, Integer quantidadeHoras) {
    	return calcularDiferencaEmHoras(dataInicial, dataFinal) <= quantidadeHoras;
    }

	public static boolean isIntervaloDeDataMaiorQue(LocalDateTime dataInicial, LocalDateTime dataFinal, Integer quantidadeHoras) {
    	return calcularDiferencaEmHoras(dataInicial, dataFinal) > quantidadeHoras;
    }

	public static long calcularDiferencaEmHoras(LocalDateTime dataInicial, LocalDateTime dataFinal) {
		return ChronoUnit.HOURS.between(dataInicial, dataFinal);
	}

	public static Date converterLocalDateTimeParaDate(LocalDateTime localDate) {
		if (localDate != null) {
			return Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
		}
		return null;
	}

	public static Date converterDataParaTimezoneServidor(Date data, ZoneId timeZoneUsuario) {
		if (data == null || timeZoneUsuario == null) {
			return null;
		}
		if (!timeZoneUsuario.equals(ZoneId.systemDefault().getId())) {
			ZonedDateTime dataHoraUsuario = ZonedDateTime.ofInstant(data.toInstant(), timeZoneUsuario);
			ZonedDateTime dataHoraServidor = dataHoraUsuario.withZoneSameInstant(ZoneId.systemDefault());
			return Date.from(dataHoraServidor.toInstant());
		} else {
			return data;
		}
	}

	public static LocalDateTime converterLocalDateTimeParaTimezoneUsuario(LocalDateTime data, ZoneId timeZoneUsuario) {
		if (data == null || timeZoneUsuario == null) {
			return null;
		}
		if (!timeZoneUsuario.equals(ZoneId.systemDefault().getId())) {
			ZonedDateTime dataHoraServidor = ZonedDateTime.of(data, ZoneId.systemDefault());
			ZonedDateTime dataHoraUsuario = dataHoraServidor.withZoneSameInstant(timeZoneUsuario);
			return dataHoraUsuario.toLocalDateTime();
		} else {
			return data;
		}
	}

	public static LocalDateTime converterLocalDateTimeParaTimezoneServidor(LocalDateTime data, ZoneId timeZoneUsuario) {
		if (data == null || timeZoneUsuario == null) {
			return null;
		}
		if (!timeZoneUsuario.equals(ZoneId.systemDefault().getId())) {
			ZonedDateTime dataHoraUsuario = ZonedDateTime.of(data, timeZoneUsuario);
			ZonedDateTime dataHoraServidor = dataHoraUsuario.withZoneSameInstant(ZoneId.systemDefault());
			return dataHoraServidor.toLocalDateTime();
		} else {
			return data;
		}
	}

	public static LocalDate adicionarDiasUteis(LocalDate data, Integer dias){
		LocalDate dataFinal = LocalDate.from(data);
		while (dias > 0) {
			dataFinal = dataFinal.plusDays(1);
			if (isDataDiaUtil(dataFinal)) {
				--dias;
			}
		}
		return dataFinal;
	}

	public static boolean isDataDiaUtil(LocalDate dataInformada) {
		HolidayManager gerenciadorDeFeriados = HolidayManager.getInstance(de.jollyday.HolidayCalendar.BRAZIL);
		Set<Holiday> feriados = gerenciadorDeFeriados.getHolidays(dataInformada.getYear());
		Set<LocalDate> dataDosFeriados = new HashSet<LocalDate>();
		for (Holiday feriado : feriados) {
			dataDosFeriados.add(feriado.getDate());
		}
		HolidayCalendar<LocalDate> calendarioDeFeriados = new DefaultHolidayCalendar<LocalDate>(dataDosFeriados);
		LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays("BR", calendarioDeFeriados);
		DateCalculator<LocalDate> calendario =  LocalDateKitCalculatorsFactory.getDefaultInstance().getDateCalculator("BR", HolidayHandlerType.FORWARD);
		return !calendario.isNonWorkingDay(dataInformada);
	}
	
	public String formatDate(java.util.Date date, String pattern) {
		String retorno;
		if (date == null) {
			retorno = "";
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern,
					new Locale("pt", "BR"));
			//			formatter.setTimeZone(TimeZone.getTimeZone("GMT-3"));
			retorno = formatter.format(date);
		}
		return retorno;
	}

}