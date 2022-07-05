package co.edu.ufps.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.edu.ufps.commands.GenericServiceImpl;
import co.edu.ufps.model.Proyect;
import co.edu.ufps.repository.ProyectRepository;
import co.edu.ufps.services.ProyectService;

@Service
public class ProyectServiceImpl extends GenericServiceImpl<Proyect, Integer> implements ProyectService{

	@Autowired
	private ProyectRepository proyectRepository;
	
	@Override
	public CrudRepository<Proyect, Integer> getDao() {
		return proyectRepository;
	}

	@Override
	public Proyect select(String titulo) {
		return proyectRepository.findByTitulo(titulo);
	}
	
	public ByteArrayInputStream exportAllData(int id) throws Exception {
		String [] columns = {"Id", "Titulo", "Objetivo", "Notas", "Fecha Inicio", "Fecha Fin", "Estado", "Fecha Creacion"};
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Sheet sheet = workbook.createSheet("Proyectos");
		Row row = sheet.createRow(0);
		for(int i=0; i<columns.length;i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columns[i]);
		}
		
	    Proyect proyect = this.get(id);
		int initRow = 1;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String fechaini = sdf.format(proyect.getFechainicio());
			String fechafin = sdf.format(proyect.getFechafin());
			String fechacrea = sdf.format(proyect.getFechacreacion());
			row = sheet.createRow(initRow);
			row.createCell(0).setCellValue(proyect.getId());
			row.createCell(1).setCellValue(proyect.getTitulo());
			row.createCell(2).setCellValue(proyect.getObjetivo());
			row.createCell(3).setCellValue(proyect.getNotas());
			row.createCell(4).setCellValue(fechaini);
			row.createCell(5).setCellValue(fechafin);
			row.createCell(6).setCellValue(proyect.getEstado());
			row.createCell(7).setCellValue(fechacrea);
			initRow++;
		
	
		workbook.write(stream);
		workbook.close();
		return new ByteArrayInputStream(stream.toByteArray());
	}

}
